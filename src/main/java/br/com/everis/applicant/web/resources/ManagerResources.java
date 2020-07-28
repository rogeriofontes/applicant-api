package br.com.everis.applicant.web.resources;

import br.com.everis.applicant.model.service.ManagerService;
import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.model.entity.Manager;
import br.com.everis.applicant.web.dto.request.ManagerRequest;
import br.com.everis.applicant.web.dto.response.ManagerResponse;
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
@RequestMapping(value = "/v1/managers")
public class ManagerResources {

	private ManagerService managerService;
	private ConversionService conversionService;

	public ManagerResources(ManagerService managerService,
                            ConversionService conversionService) {
		this.managerService = managerService;
		this.conversionService = conversionService;
	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Manager>> getAll() {
		log.info("teste");
		List<Manager> result = managerService.listAll();

		if (result != null) {
			log.info(Constants.TOTAL + result.size());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping(path = "/{id}")
	@ResponseBody
	public ResponseEntity<ManagerResponse> get(@PathVariable("id") Long id) {
		return managerService.findById(id).map(record -> {
			log.info(Constants.TOTAL + record.toString());
			ManagerResponse response = conversionService.convert(record, ManagerResponse.class);
			return ResponseEntity.ok().body(response);
		}).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@CacheEvict(value = Constants.PROFESSIONAL_IN_CACHE, allEntries = true)
	public ResponseEntity<Manager> add(@Valid @RequestBody ManagerRequest managerRequest) {
		Manager manager = conversionService.convert(managerRequest, Manager.class);
		Manager result = managerService.save(manager);

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
	public ResponseEntity<Manager> change(@PathVariable("id") Long id, @RequestBody ManagerRequest managerRequest) {
		return managerService.findById(id).map(record -> {
			Manager manager = conversionService.convert(managerRequest, Manager.class);
			record.update(id, manager);
			Manager result = managerService.edit(id, manager);
			return ResponseEntity.ok().body(result);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = "/{id}")
	@CacheEvict(value = Constants.PROFESSIONAL_IN_CACHE, allEntries = true)
	public ResponseEntity<?> remove(@PathVariable("id") Long id) {
		return managerService.findById(id).map(record -> {
			managerService.remove(id);
			return ResponseEntity.ok(Constants.DADOS_DELETADOS);
		}).orElse(ResponseEntity.notFound().build());
	}
}
