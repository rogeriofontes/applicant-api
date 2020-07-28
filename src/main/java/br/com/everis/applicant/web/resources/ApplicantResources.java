package br.com.everis.applicant.web.resources;

import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.model.entity.Applicant;
import br.com.everis.applicant.model.service.ApplicantService;
import br.com.everis.applicant.web.dto.request.ApplicantRequest;
import br.com.everis.applicant.web.dto.response.ApplicationResponse;
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
@RequestMapping(value = "/v1/applicants")
public class ApplicantResources {

	private ApplicantService applicantService;
	private ConversionService conversionService;

	public ApplicantResources(ApplicantService applicantService,
                              ConversionService conversionService) {
		this.applicantService = applicantService;
		this.conversionService = conversionService;
	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Applicant>> getAll() {
		log.info("teste");
		List<Applicant> result = applicantService.listAll();

		if (result != null) {
			log.info(Constants.TOTAL + result.size());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping(path = "/{id}")
	@ResponseBody
	public ResponseEntity<ApplicationResponse> get(@PathVariable("id") Long id) {
		return applicantService.findById(id).map(record -> {
			log.info(Constants.TOTAL + record.toString());
			ApplicationResponse response = conversionService.convert(record, ApplicationResponse.class);
			return ResponseEntity.ok().body(response);
		}).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@CacheEvict(value = Constants.APPLICANT_IN_CACHE, allEntries = true)
	public ResponseEntity<Applicant> add(@Valid @RequestBody ApplicantRequest applicantRequest) {
		Applicant applicant = conversionService.convert(applicantRequest, Applicant.class);
		Applicant result = applicantService.save(applicant);

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
	public ResponseEntity<Applicant> change(@PathVariable("id") Long id, @RequestBody ApplicantRequest applicantRequest) {
		return applicantService.findById(id).map(record -> {
			Applicant applicant = conversionService.convert(applicantRequest, Applicant.class);
			record.update(id, applicant);
			Applicant result = applicantService.edit(id, applicant);
			return ResponseEntity.ok().body(result);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = "/{id}")
	@CacheEvict(value = Constants.APPLICANT_IN_CACHE, allEntries = true)
	public ResponseEntity<?> remove(@PathVariable("id") Long id) {
		return applicantService.findById(id).map(record -> {
			applicantService.remove(id);
			return ResponseEntity.ok(Constants.DADOS_DELETADOS);
		}).orElse(ResponseEntity.notFound().build());
	}
}
