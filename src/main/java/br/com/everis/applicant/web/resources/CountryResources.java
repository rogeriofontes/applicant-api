package br.com.everis.applicant.web.resources;

import br.com.everis.applicant.model.entity.Country;
import br.com.everis.applicant.model.service.CountryService;
import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.web.dto.request.CountryRequest;
import br.com.everis.applicant.web.dto.response.CountryResponse;
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
@RequestMapping("/v1/countrys")
public class CountryResources {

	private CountryService countryService;
	private ConversionService conversionService;

	public CountryResources(CountryService countryService,
                            ConversionService conversionService) {
		this.countryService = countryService;
		this.conversionService = conversionService;
	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Country>> getAll() {
		log.info("teste");
		List<Country> result = countryService.listAll();

		if (result != null) {
			log.info(Constants.TOTAL + result.size());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping(path = "/{id}")
	@ResponseBody
	public ResponseEntity<CountryResponse> get(@PathVariable("id") Long id) {
		return countryService.findById(id).map(record -> {
			log.info(Constants.TOTAL + record.toString());
			CountryResponse response = conversionService.convert(record, CountryResponse.class);
			return ResponseEntity.ok().body(response);
		}).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@CacheEvict(value = Constants.COUNTRYS_IN_CACHE, allEntries = true)
	public ResponseEntity<Country> add(@Valid @RequestBody CountryRequest countryRequest) {
		Country country = conversionService.convert(countryRequest, Country.class);
		Country result = countryService.save(country);

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
	@CacheEvict(value = Constants.COUNTRYS_IN_CACHE, allEntries = true)
	public ResponseEntity<Country> change(@PathVariable("id") Long id, @RequestBody CountryRequest countryRequest) {
		return countryService.findById(id).map(record -> {
			Country country = conversionService.convert(countryRequest, Country.class);
			record.update(id, country);
			Country result = countryService.edit(id, country);
			return ResponseEntity.ok().body(result);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = "/{id}")
	@CacheEvict(value = Constants.COUNTRYS_IN_CACHE, allEntries = true)
	public ResponseEntity<?> remove(@PathVariable("id") Long id) {
		return countryService.findById(id).map(record -> {
			countryService.remove(id);
			return ResponseEntity.ok(Constants.DADOS_DELETADOS);
		}).orElse(ResponseEntity.notFound().build());
	}
}
