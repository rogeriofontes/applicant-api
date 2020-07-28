package br.com.everis.applicant.web.resources;

import br.com.everis.applicant.model.entity.User;
import br.com.everis.applicant.model.service.UserService;
import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.web.dto.request.UserRequest;
import br.com.everis.applicant.web.dto.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/users")
public class UserResources extends HandleResources {

	private UserService userService;
	private ConversionService conversionService;

	public UserResources(UserService userService,
						 ConversionService conversionService) {
		this.userService = userService;
		this.conversionService = conversionService;
	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<User>> getAll() {
		log.info("teste");
		List<User> result = userService.listAll();

		if (result != null) {
			log.info(Constants.TOTAL + result.size());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<UserResponse> get(@PathVariable("id") Long id) {
		return userService.findById(id).map(record -> {
			log.info(Constants.TOTAL + record.toString());
			UserResponse response = conversionService.convert(record, UserResponse.class);
			return ResponseEntity.ok().body(response);
		}).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@CacheEvict(value = Constants.USER_IN_CACHE, allEntries = true)
	public ResponseEntity<User> add(@Valid @RequestBody UserRequest userRequest) {
		User user = conversionService.convert(userRequest, User.class);
		User result = userService.save(user);

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
	@CacheEvict(value = Constants.USER_IN_CACHE, allEntries = true)
	public ResponseEntity<User> change(@PathVariable("id") Long id, @RequestBody UserRequest userRequest) {
		return userService.findById(id).map(record -> {
			User user = conversionService.convert(userRequest, User.class);
			record.update(id, user);
			User result = userService.edit(id, user);
			return ResponseEntity.ok().body(result);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = "/{id}")
	@CacheEvict(value = Constants.USER_IN_CACHE, allEntries = true)
	public ResponseEntity<?> remove(@PathVariable("id") Long id) {
		return userService.findById(id).map(record -> {
			userService.remove(id);
			return ResponseEntity.ok(Constants.DADOS_DELETADOS);
		}).orElse(ResponseEntity.notFound().build());
	}
}
