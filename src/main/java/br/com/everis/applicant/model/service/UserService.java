package br.com.everis.applicant.model.service;

import br.com.everis.applicant.model.entity.User;

import java.util.Optional;

public interface UserService extends BaseService<User, Long> {
    Optional<User> findByEmail(String email);
}

