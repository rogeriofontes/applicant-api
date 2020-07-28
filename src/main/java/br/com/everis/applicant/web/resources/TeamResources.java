package br.com.everis.applicant.web.resources;

import br.com.everis.applicant.model.entity.Team;
import br.com.everis.applicant.model.service.TeamService;
import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.web.dto.request.TeamRequest;
import br.com.everis.applicant.web.dto.response.TeamResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * @author Rogério Fontes
 */
@Slf4j
@RestController
@RequestMapping(value = "/v1/teams")
public class TeamResources extends HandleResources {

    private TeamService teamService;
    private ConversionService conversionService;

    public TeamResources(TeamService teamService,
                         ConversionService conversionService) {
        this.teamService = teamService;
        this.conversionService = conversionService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Team>> getAll() {
        log.info("teste");
        List<Team> result = teamService.listAll();

        if (result != null) {
            log.info(Constants.TOTAL + result.size());
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<TeamResponse> get(@PathVariable("id") Long id) {
        return teamService.findById(id).map(record -> {
            log.info(Constants.TOTAL + record.toString());
            TeamResponse response = conversionService.convert(record, TeamResponse.class);
            return ResponseEntity.ok().body(response);
        }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @CacheEvict(value = Constants.TEAM_IN_CACHE, allEntries = true)
    public ResponseEntity<Team> add(@Valid @RequestBody TeamRequest teamRequest) {
        Team team = conversionService.convert(teamRequest, Team.class);
        Team result = teamService.save(team);

        if (result != null) {
            log.info(Constants.TOTAL + result.toString());
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId())
                    .toUri();
            return ResponseEntity.created(location).body(result);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping(path = "/{id}")
    @CacheEvict(value = Constants.TEAM_IN_CACHE, allEntries = true)
    public ResponseEntity<Team> change(@PathVariable("id") Long id, @RequestBody TeamRequest teamRequest) {
        return teamService.findById(id).map(record -> {
            Team team = conversionService.convert(teamRequest, Team.class);
            record.update(id, team);
            Team result = teamService.edit(id, team);
            return ResponseEntity.ok().body(result);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    @CacheEvict(value = Constants.TEAM_IN_CACHE, allEntries = true)
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        return teamService.findById(id).map(record -> {
            teamService.remove(id);
            return ResponseEntity.ok(Constants.DADOS_DELETADOS);
        }).orElse(ResponseEntity.notFound().build());
    }
}
