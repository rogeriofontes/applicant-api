package br.com.everis.applicant.model.service.impl;

import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.model.entity.Profile;
import br.com.everis.applicant.model.repository.ProfileRepository;
import br.com.everis.applicant.model.service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProfileServiceImpl implements ProfileService {
	
	private ProfileRepository profileRepository;

	public ProfileServiceImpl(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}

	@Override
	@Cacheable(Constants.PROFILE_IN_CACHE)
	public List<Profile> listAll() {
		List<Profile> retorno = new ArrayList<Profile>();
		try {
			retorno = this.profileRepository.findAll();
		} catch (Exception e) {
			//super.LOGGER.error("Ocorreu um erro na classe: ProfileService >> listarTodos(): " + e.getMessage());
			//throw new Exception(e.getMessage());
		}
		return retorno;
	}

	@Override
	@Cacheable(Constants.PROFILE_IN_CACHE)
	public Optional<Profile> findById(Long id) {
		Optional<Profile> result = this.profileRepository.findById(id);
		if (result.equals(null) || !result.isPresent()) {
			//return new Profile();
		}
		//return result.get();
		return null;
	}

	@Transactional
	@Override
	public Profile save(Profile profile) {
		try {
			return this.profileRepository.save(profile);
		} catch (Exception e) {
		  //super.LOGGER.error("Ocorreu um erro na classe: ProfileService >> salvar(ObjetoDominio objetoDominio): "
		  //		+ e.getMessage());
			//throw new Exception(e.getMessage());
		}
		return null;
	}

	@Transactional
	@Modifying
	@Override
	public Profile edit(Long id, Profile profile) {
		try {
			profile.update(id, profile);
			return this.profileRepository.save(profile);
		} catch (Exception e) {
			//super.LOGGER.error("Ocorreu um erro na classe: ProfileService >> editar(ObjetoDominio objetoDominio): "
			//		+ e.getMessage());
			// throw new Exception(e.getMessage());

		}
		return null;
	}

	@Transactional
	@Override
	public boolean remove(Long id) {
		try {
			this.profileRepository.deleteById(id);
		} catch (Exception e) {
			log.info("" + e.getMessage());
			//super.LOGGER.error("Ocorreu um erro na classe: ProfileService >> (Long id): " + e.getMessage());
			//throw new Exception(e.getMessage());
		}
		return true;
	}

	@Override
	@Cacheable(Constants.PROFILE_IN_CACHE)
	public Page<Profile> findAllPageable(Pageable pageable) {
		return null;
	}
}
