package br.com.everis.applicant.web.resources;

import br.com.everis.applicant.model.entity.JobVacancy;
import br.com.everis.applicant.model.service.JobVacancyService;
import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.web.dto.request.JobVacancyRequest;
import br.com.everis.applicant.web.dto.response.JobVacancyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Rogério Fontes
 *
 */
@Slf4j
@RestController
@RequestMapping(value = "/v1/job-vacancies")
public class JobVacancyResources {
	
	private JobVacancyService jobVacancyService;
	private ConversionService conversionService;

	public JobVacancyResources(JobVacancyService jobVacancyService,
							   ConversionService conversionService) {
		this.jobVacancyService = jobVacancyService;
		this.conversionService = conversionService;
	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<JobVacancy>> getAll() {
		List<JobVacancy> result = jobVacancyService.listAll();

		if (result != null) {
			log.info(Constants.TOTAL + result.size());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping(path = "/{id}")
	@ResponseBody
	public ResponseEntity<JobVacancyResponse> get(@PathVariable("id") Long id) {
		return jobVacancyService.findById(id).map(record -> {
			log.info(Constants.TOTAL + record.toString());
			JobVacancyResponse response = conversionService.convert(record, JobVacancyResponse.class);
			return ResponseEntity.ok().body(response);
		}).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@CacheEvict(value = Constants.MANAGE_HOME_OFFICE_IN_CACHE, allEntries = true)
	public ResponseEntity<JobVacancy> add(@Valid @RequestBody JobVacancyRequest jobVacancyRequest) {
		JobVacancy jobVacancy = conversionService.convert(jobVacancyRequest, JobVacancy.class);
		JobVacancy result = jobVacancyService.save(jobVacancy);

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
	@CacheEvict(value = Constants.MANAGE_HOME_OFFICE_IN_CACHE, allEntries = true)
	public ResponseEntity<JobVacancy> change(@PathVariable("id") Long id, @RequestBody JobVacancyRequest jobVacancyRequest) {
		return jobVacancyService.findById(id).map(record -> {
			JobVacancy interview = conversionService.convert(jobVacancyRequest, JobVacancy.class);
			record.update(id, interview);
			JobVacancy result = jobVacancyService.edit(id, interview);
			return ResponseEntity.ok().body(result);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = "/{id}")
	@CacheEvict(value = Constants.MANAGE_HOME_OFFICE_IN_CACHE, allEntries = true)
	public ResponseEntity<?> remove(@PathVariable("id") Long id) {
		return jobVacancyService.findById(id).map(record -> {
			jobVacancyService.remove(id);
			return ResponseEntity.ok(Constants.DADOS_DELETADOS);
		}).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping(value = "/teams")
	@ResponseBody
	public ResponseEntity<List<JobVacancyResponse>> getByTeam(
			@RequestParam(required = true, defaultValue = "0") Long teamId) {
		log.info("TeamId: " + teamId);
		List<JobVacancyResponse> collect = jobVacancyService.findByTeamId(teamId).stream().map(n -> conversionService.convert(n, JobVacancyResponse.class)).collect(Collectors.toList());
		return ResponseEntity.ok().body(collect);
	}
}
