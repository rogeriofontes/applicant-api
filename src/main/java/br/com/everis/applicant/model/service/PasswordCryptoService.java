package br.com.everis.applicant.model.service;

public interface PasswordCryptoService {
    String encrypt(String password);
    boolean matches(String rawPassword, String encodedPassword);
}
