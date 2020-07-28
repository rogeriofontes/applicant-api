package br.com.everis.applicant.web.resources;

import br.com.everis.applicant.model.entity.Profile;
import br.com.everis.applicant.model.service.ProfileService;
import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.web.dto.request.ProfileRequest;
import br.com.everis.applicant.web.dto.response.ProfileResponse;
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
@RequestMapping("/v1/profiles")
public class ProfileResources {

	private ProfileService profileService;
	private ConversionService conversionService;

	public ProfileResources(ProfileService profileService,
                            ConversionService conversionService) {
		this.profileService = profileService;
		this.conversionService = conversionService;
	}

	@GetMapping()
	@ResponseBody
	public ResponseEntity<List<Profile>> getAll() {
		log.info("teste");
		List<Profile> result = profileService.listAll();

		if (result != null) {
			log.info(Constants.TOTAL + result.size());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping(path = "/{id}")
	@ResponseBody
	public ResponseEntity<ProfileResponse> get(@PathVariable("id") Long id) {
		return profileService.findById(id).map(record -> {
			log.info(Constants.TOTAL + record.toString());
			ProfileResponse response = conversionService.convert(record, ProfileResponse.class);
			return ResponseEntity.ok().body(response);
		}).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@CacheEvict(value = Constants.PROFILE_IN_CACHE, allEntries = true)
	public ResponseEntity<Profile> add(@Valid @RequestBody ProfileRequest profileRequest) {
		Profile profile = conversionService.convert(profileRequest, Profile.class);
		Profile result = profileService.save(profile);

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
	@CacheEvict(value = Constants.PROFILE_IN_CACHE, allEntries = true)
	public ResponseEntity<Profile> change(@PathVariable("id") Long id, @RequestBody ProfileRequest profileRequest) {
		return profileService.findById(id).map(record -> {
			Profile profile = conversionService.convert(profileRequest, Profile.class);
			record.update(id, profile);
			Profile result = profileService.edit(id, profile);
			return ResponseEntity.ok().body(result);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = "/{id}")
	@CacheEvict(value = Constants.PROFILE_IN_CACHE, allEntries = true)
	public ResponseEntity<?> remove(@PathVariable("id") Long id) {
		return profileService.findById(id).map(record -> {
			profileService.remove(id);
			return ResponseEntity.ok(Constants.DADOS_DELETADOS);
		}).orElse(ResponseEntity.notFound().build());
	}
}
