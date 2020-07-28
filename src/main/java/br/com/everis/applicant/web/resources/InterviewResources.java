package br.com.everis.applicant.web.resources;

import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.model.entity.Interview;
import br.com.everis.applicant.model.service.InterviewService;
import br.com.everis.applicant.web.dto.request.InterviewRequest;
import br.com.everis.applicant.web.dto.response.InterviewResponse;
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
@RequestMapping(value = "/v1/interviews")
public class InterviewResources {

	private InterviewService interviewService;
	private ConversionService conversionService;

	public InterviewResources(InterviewService interviewService,
                              ConversionService conversionService) {
		this.interviewService = interviewService;
		this.conversionService = conversionService;
	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Interview>> getAll() {
		List<Interview> result = interviewService.listAll();

		if (result != null) {
			log.info(Constants.TOTAL + result.size());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping(path = "/{id}")
	@ResponseBody
	public ResponseEntity<InterviewResponse> get(@PathVariable("id") Long id) {
		return interviewService.findById(id).map(record -> {
			log.info(Constants.TOTAL + record.toString());
			InterviewResponse response = conversionService.convert(record, InterviewResponse.class);
			return ResponseEntity.ok().body(response);
		}).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@CacheEvict(value = Constants.MANAGE_HOME_OFFICE_IN_CACHE, allEntries = true)
	public ResponseEntity<Interview> add(@Valid @RequestBody InterviewRequest interviewRequest) {
		Interview interview = conversionService.convert(interviewRequest, Interview.class);
		Interview result = interviewService.save(interview);

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
	public ResponseEntity<Interview> change(@PathVariable("id") Long id, @RequestBody InterviewRequest interviewRequest) {
		return interviewService.findById(id).map(record -> {
			Interview interview = conversionService.convert(interviewRequest, Interview.class);
			record.update(id, interview);
			Interview result = interviewService.edit(id, interview);
			return ResponseEntity.ok().body(result);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = "/{id}")
	@CacheEvict(value = Constants.MANAGE_HOME_OFFICE_IN_CACHE, allEntries = true)
	public ResponseEntity<?> remove(@PathVariable("id") Long id) {
		return interviewService.findById(id).map(record -> {
			interviewService.remove(id);
			return ResponseEntity.ok(Constants.DADOS_DELETADOS);
		}).orElse(ResponseEntity.notFound().build());
	}
}
