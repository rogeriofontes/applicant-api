package br.com.everis.applicant.model.service.impl;

import br.com.everis.applicant.model.service.PasswordCryptoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PasswordCryptoServiceImpl implements PasswordCryptoService {

    private PasswordEncoder passwordEncoder;

    public PasswordCryptoServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encrypt(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
