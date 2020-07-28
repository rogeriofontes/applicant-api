package br.com.everis.applicant.web.resources;

import br.com.everis.applicant.model.entity.ProgrammingLanguage;
import br.com.everis.applicant.model.service.ProgrammingLanguageService;
import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.web.dto.request.ProgrammingLanguageRequest;
import br.com.everis.applicant.web.dto.response.ProgrammingLanguageResponse;
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
@RequestMapping(value = "/v1/programming-languages")
public class ProgrammingLanguageResources extends HandleResources {

	private ProgrammingLanguageService programmingLanguageService;
	private ConversionService conversionService;

	public ProgrammingLanguageResources(ProgrammingLanguageService programmingLanguageService,
										ConversionService conversionService) {
		this.programmingLanguageService = programmingLanguageService;
		this.conversionService = conversionService;
	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<ProgrammingLanguage>> getAll() {
		log.info("teste");
		List<ProgrammingLanguage> result = programmingLanguageService.listAll();

		if (result != null) {
			log.info(Constants.TOTAL + result.size());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping(path = "/{id}")
	@ResponseBody
	public ResponseEntity<ProgrammingLanguageResponse> get(@PathVariable("id") Long id) {
		return programmingLanguageService.findById(id).map(record -> {
			log.info(Constants.TOTAL + record.toString());
			ProgrammingLanguageResponse response = conversionService.convert(record, ProgrammingLanguageResponse.class);
			return ResponseEntity.ok().body(response);
		}).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@CacheEvict(value = Constants.PROGRAMMING_LANGUAGE_IN_CACHE, allEntries = true)
	public ResponseEntity<ProgrammingLanguage> add(@Valid @RequestBody ProgrammingLanguageRequest programmingLanguageRequest) {
		ProgrammingLanguage programmingLanguage = conversionService.convert(programmingLanguageRequest, ProgrammingLanguage.class);
		ProgrammingLanguage result = programmingLanguageService.save(programmingLanguage);

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
	@CacheEvict(value = Constants.PROGRAMMING_LANGUAGE_IN_CACHE, allEntries = true)
	public ResponseEntity<ProgrammingLanguage> change(@PathVariable("id") Long id, @RequestBody ProgrammingLanguageRequest programmingLanguageRequest) {
		return programmingLanguageService.findById(id).map(record -> {
			ProgrammingLanguage programmingLanguage = conversionService.convert(programmingLanguageRequest, ProgrammingLanguage.class);
			record.update(id, programmingLanguage);
			ProgrammingLanguage result = programmingLanguageService.edit(id, programmingLanguage);
			return ResponseEntity.ok().body(result);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = "/{id}")
	@CacheEvict(value = Constants.PROGRAMMING_LANGUAGE_IN_CACHE, allEntries = true)
	public ResponseEntity<?> remove(@PathVariable("id") Long id) {
		return programmingLanguageService.findById(id).map(record -> {
			programmingLanguageService.remove(id);
			return ResponseEntity.ok(Constants.DADOS_DELETADOS);
		}).orElse(ResponseEntity.notFound().build());
	}
}
