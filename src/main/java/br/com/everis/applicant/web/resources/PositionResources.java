package br.com.everis.applicant.web.resources;

import br.com.everis.applicant.model.entity.Position;
import br.com.everis.applicant.model.service.PositionService;
import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.web.dto.request.PositionRequest;
import br.com.everis.applicant.web.dto.response.PositionResponse;
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
@RequestMapping(value = "/v1/positions")
public class PositionResources {

	private PositionService positionService;
	private ConversionService conversionService;

	public PositionResources(PositionService positionService,
                             ConversionService conversionService) {
		this.positionService = positionService;
		this.conversionService = conversionService;
	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Position>> getAll() {
		log.info("teste");
		List<Position> result = positionService.listAll();

		if (result != null) {
			log.info(Constants.TOTAL + result.size());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping(path = "/{id}")
	@ResponseBody
	public ResponseEntity<PositionResponse> get(@PathVariable("id") Long id) {
		return positionService.findById(id).map(record -> {
			log.info(Constants.TOTAL + record.toString());
			PositionResponse response = conversionService.convert(record, PositionResponse.class);
			return ResponseEntity.ok().body(response);
		}).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@CacheEvict(value = Constants.POSITION_IN_CACHE, allEntries = true)
	public ResponseEntity<Position> add(@Valid @RequestBody PositionRequest positionRequest) {
		Position position = conversionService.convert(positionRequest, Position.class);
		Position result = positionService.save(position);

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
	@CacheEvict(value = Constants.POSITION_IN_CACHE, allEntries = true)
	public ResponseEntity<Position> change(@PathVariable("id") Long id, @RequestBody PositionRequest positionRequest) {
		return positionService.findById(id).map(record -> {
			Position position = conversionService.convert(positionRequest, Position.class);
			record.update(id, position);
			Position result = positionService.edit(id, position);
			return ResponseEntity.ok().body(result);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = "/{id}")
	@CacheEvict(value = Constants.POSITION_IN_CACHE, allEntries = true)
	public ResponseEntity<?> remove(@PathVariable("id") Long id) {
		return positionService.findById(id).map(record -> {
			positionService.remove(id);
			return ResponseEntity.ok(Constants.DADOS_DELETADOS);
		}).orElse(ResponseEntity.notFound().build());
	}
}
