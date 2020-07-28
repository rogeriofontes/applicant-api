package br.com.everis.applicant.model.service;

import br.com.everis.applicant.web.dto.request.LoginRequest;
import br.com.everis.applicant.web.dto.response.TokenResponse;

import javax.servlet.ServletException;

public interface AuthService {
    TokenResponse validate(LoginRequest loginRequest) throws ServletException;
}
