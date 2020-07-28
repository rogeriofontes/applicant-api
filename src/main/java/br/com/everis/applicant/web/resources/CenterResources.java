package br.com.everis.applicant.web.resources;

import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.model.entity.Center;
import br.com.everis.applicant.model.service.CenterService;
import br.com.everis.applicant.web.dto.request.CenterRequest;
import br.com.everis.applicant.web.dto.response.CenterResponse;
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
@RequestMapping(value = "/v1/centers")
public class CenterResources {

	private CenterService centerService;
	private ConversionService conversionService;

	public CenterResources(CenterService centerService,
                           ConversionService conversionService) {
		this.centerService = centerService;
		this.conversionService = conversionService;
	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Center>> getAll() {
		log.info("teste");
		List<Center> result = centerService.listAll();

		if (result != null) {
			log.info(Constants.TOTAL + result.size());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping(path = "/{id}")
	@ResponseBody
	public ResponseEntity<CenterResponse> get(@PathVariable("id") Long id) {
		return centerService.findById(id).map(record -> {
			log.info(Constants.TOTAL + record.toString());
			CenterResponse response = conversionService.convert(record, CenterResponse.class);
			return ResponseEntity.ok().body(response);
		}).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@CacheEvict(value = Constants.CENTERS_IN_CACHE, allEntries = true)
	public ResponseEntity<Center> add(@Valid @RequestBody CenterRequest centerRequest) {
		Center center = conversionService.convert(centerRequest, Center.class);
		Center result = centerService.save(center);

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
	@CacheEvict(value = Constants.CENTERS_IN_CACHE, allEntries = true)
	public ResponseEntity<Center> change(@PathVariable("id") Long id, @RequestBody CenterRequest centerRequest) {
		return centerService.findById(id).map(record -> {
			Center center = conversionService.convert(centerRequest, Center.class);
			record.update(id, center);
			Center result = centerService.edit(id, center);
			return ResponseEntity.ok().body(result);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = "/{id}")
	@CacheEvict(value = Constants.CENTERS_IN_CACHE, allEntries = true)
	public ResponseEntity<?> remove(@PathVariable("id") Long id) {
		return centerService.findById(id).map(record -> {
			centerService.remove(id);
			return ResponseEntity.ok(Constants.DADOS_DELETADOS);
		}).orElse(ResponseEntity.notFound().build());
	}
}