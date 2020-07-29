package br.com.everis.applicant.web.resources;

import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.model.entity.ApplicationEvaluation;
import br.com.everis.applicant.model.service.ApplicationEvaluationService;
import br.com.everis.applicant.web.dto.request.ApplicantEvaluationRequest;
import br.com.everis.applicant.web.dto.response.ApplicationEvaluationResponse;
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
@RequestMapping(value = "/v1/applicant-evaluations")
public class ApplicantEvaluationResources {

	private ApplicationEvaluationService applicantEvaluationService;
	private ConversionService conversionService;

	public ApplicantEvaluationResources(ApplicationEvaluationService applicantEvaluationService,
                                        ConversionService conversionService) {
		this.applicantEvaluationService = applicantEvaluationService;
		this.conversionService = conversionService;
	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<ApplicationEvaluation >> getAll() {
		log.info("teste");
		List<ApplicationEvaluation> result = applicantEvaluationService.listAll();

		if (result != null) {
			log.info(Constants.TOTAL + result.size());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping(path = "/{id}")
	@ResponseBody
	public ResponseEntity<ApplicationEvaluationResponse> get(@PathVariable("id") Long id) {
		return applicantEvaluationService.findById(id).map(record -> {
			log.info(Constants.TOTAL + record.toString());
			ApplicationEvaluationResponse response = conversionService.convert(record, ApplicationEvaluationResponse.class);
			return ResponseEntity.ok().body(response);
		}).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@CacheEvict(value = Constants.APPLICANT_IN_CACHE, allEntries = true)
	public ResponseEntity<ApplicationEvaluation> add(@Valid @RequestBody ApplicantEvaluationRequest applicantEvaluationRequest) {
		ApplicationEvaluation applicantEvaluation = conversionService.convert(applicantEvaluationRequest, ApplicationEvaluation.class);
		ApplicationEvaluation result = applicantEvaluationService.save(applicantEvaluation);

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
	@CacheEvict(value = Constants.APPLICANT_IN_CACHE, allEntries = true)
	public ResponseEntity<ApplicationEvaluationResponse> change(@PathVariable("id") Long id, @RequestBody ApplicantEvaluationRequest applicantEvaluationRequest) {
		return applicantEvaluationService.findById(id).map(record -> {
			ApplicationEvaluation applicantEvaluation = conversionService.convert(applicantEvaluationRequest, ApplicationEvaluation.class);
			ApplicationEvaluation applicantEvaluationSaved = applicantEvaluationService.edit(id, applicantEvaluation);
			ApplicationEvaluationResponse result = conversionService.convert(applicantEvaluationSaved, ApplicationEvaluationResponse.class);
			return ResponseEntity.ok().body(result);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = "/{id}")
	@CacheEvict(value = Constants.APPLICANT_IN_CACHE, allEntries = true)
	public ResponseEntity<?> remove(@PathVariable("id") Long id) {
		return applicantEvaluationService.findById(id).map(record -> {
			applicantEvaluationService.remove(id);
			return ResponseEntity.ok(Constants.DADOS_DELETADOS);
		}).orElse(ResponseEntity.notFound().build());
	}
}
