package br.com.everis.applicant.model.service;

import br.com.everis.applicant.model.entity.User;

public interface CustomUserDetailsService {
    User loadCurrentUser();
}
