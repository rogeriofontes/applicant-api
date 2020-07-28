package br.com.everis.applicant.web.resources;

import br.com.everis.applicant.model.entity.Professional;
import br.com.everis.applicant.model.service.ProfessionalService;
import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.web.dto.request.ProfessionalRequest;
import br.com.everis.applicant.web.dto.response.ProfessionalResponse;
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
@RequestMapping(value = "/v1/professionals")
public class ProfessionalResources {

	private ProfessionalService professionalService;
	private ConversionService conversionService;

	public ProfessionalResources(ProfessionalService professionalService,
                                 ConversionService conversionService) {
		this.professionalService = professionalService;
		this.conversionService = conversionService;
	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Professional>> getAll() {
		log.info("teste");
		List<Professional> result = professionalService.listAll();

		if (result != null) {
			log.info(Constants.TOTAL + result.size());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping(path = "/{id}")
	@ResponseBody
	public ResponseEntity<ProfessionalResponse> get(@PathVariable("id") Long id) {
		return professionalService.findById(id).map(record -> {
			log.info(Constants.TOTAL + record.toString());
			ProfessionalResponse response = conversionService.convert(record, ProfessionalResponse.class);
			return ResponseEntity.ok().body(response);
		}).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@CacheEvict(value = Constants.PROFESSIONAL_IN_CACHE, allEntries = true)
	public ResponseEntity<Professional> add(@Valid @RequestBody ProfessionalRequest professionalRequest) {
		Professional professional = conversionService.convert(professionalRequest, Professional.class);
		Professional result = professionalService.save(professional);

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
	@CacheEvict(value = Constants.PROFESSIONAL_IN_CACHE, allEntries = true)
	public ResponseEntity<Professional> change(@PathVariable("id") Long id, @RequestBody ProfessionalRequest professionalRequest) {
		return professionalService.findById(id).map(record -> {
			Professional professional = conversionService.convert(professionalRequest, Professional.class);
			record.update(id, professional);
			Professional result = professionalService.edit(id, professional);
			return ResponseEntity.ok().body(result);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = "/{id}")
	@CacheEvict(value = Constants.PROFESSIONAL_IN_CACHE, allEntries = true)
	public ResponseEntity<?> remove(@PathVariable("id") Long id) {
		return professionalService.findById(id).map(record -> {
			professionalService.remove(id);
			return ResponseEntity.ok(Constants.DADOS_DELETADOS);
		}).orElse(ResponseEntity.notFound().build());
	}
}
