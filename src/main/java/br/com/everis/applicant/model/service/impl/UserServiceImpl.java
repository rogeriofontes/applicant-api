package br.com.everis.applicant.model.service.impl;

import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.model.entity.User;
import br.com.everis.applicant.model.repository.UserRepository;
import br.com.everis.applicant.model.service.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User edit(Long id, User user) {
		return userRepository.save(user);
	}

	@Override
	@Cacheable(Constants.USER_IN_CACHE)
	public List<User> listAll() {
		return userRepository.findAll();
	}

	@Override
	@Cacheable(Constants.USER_IN_CACHE)
	public Page<User> findAllPageable(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	@Cacheable(Constants.USER_IN_CACHE)
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public boolean remove(Long id) {
		try {
			userRepository.deleteById(id);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
