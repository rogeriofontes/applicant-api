package br.com.everis.applicant.web.resources;

import br.com.everis.applicant.model.entity.Project;
import br.com.everis.applicant.model.service.ProjectService;
import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.web.dto.request.ProjectRequest;
import br.com.everis.applicant.web.dto.response.ProjectResponse;
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
 *
 */
@Slf4j
@RestController
@RequestMapping(value = "/v1/projects")
public class ProjectResources extends HandleResources {

	private ProjectService projectService;
	private ConversionService conversionService;

	public ProjectResources(ProjectService projectService,
							ConversionService conversionService) {
		this.projectService = projectService;
		this.conversionService = conversionService;
	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Project>> getAll() {
		log.info("teste");
		List<Project> result = projectService.listAll();

		if (result != null) {
			log.info(Constants.TOTAL + result.size());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping(path = "/{id}")
	@ResponseBody
	public ResponseEntity<ProjectResponse> get(@PathVariable("id") Long id) {
		return projectService.findById(id).map(record -> {
			log.info(Constants.TOTAL + record.toString());
			ProjectResponse response = conversionService.convert(record, ProjectResponse.class);
			return ResponseEntity.ok().body(response);
		}).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@CacheEvict(value = Constants.PROJECT_IN_CACHE, allEntries = true)
	public ResponseEntity<Project> add(@Valid @RequestBody ProjectRequest projectRequest) {
		Project project = conversionService.convert(projectRequest, Project.class);
		Project result = projectService.save(project);

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
	@CacheEvict(value = Constants.PROJECT_IN_CACHE, allEntries = true)
	public ResponseEntity<Project> change(@PathVariable("id") Long id, @RequestBody ProjectRequest projectRequest) {
		return projectService.findById(id).map(record -> {
			Project project = conversionService.convert(projectRequest, Project.class);
			record.update(id, project);
			Project result = projectService.edit(id, project);
			return ResponseEntity.ok().body(result);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = "/{id}")
	@CacheEvict(value = Constants.PROJECT_IN_CACHE, allEntries = true)
	public ResponseEntity<?> remove(@PathVariable("id") Long id) {
		return projectService.findById(id).map(record -> {
			projectService.remove(id);
			return ResponseEntity.ok(Constants.DADOS_DELETADOS);
		}).orElse(ResponseEntity.notFound().build());
	}
}
