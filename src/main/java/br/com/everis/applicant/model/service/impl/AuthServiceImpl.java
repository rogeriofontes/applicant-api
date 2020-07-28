package br.com.everis.applicant.model.service.impl;

import br.com.everis.applicant.model.entity.User;
import br.com.everis.applicant.model.service.AuthService;
import br.com.everis.applicant.model.service.PasswordCryptoService;
import br.com.everis.applicant.model.service.UserService;
import br.com.everis.applicant.util.JWTUtil;
import br.com.everis.applicant.web.dto.request.LoginRequest;
import br.com.everis.applicant.web.dto.response.TokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import java.util.Optional;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private UserDetailsService customUserDetailsService;
    private UserService userService;
    private PasswordCryptoService cryptoService;

    public AuthServiceImpl(UserDetailsService customUserDetailsService, UserService userService, PasswordCryptoService cryptoService) {
        this.customUserDetailsService = customUserDetailsService;
        this.userService = userService;
        this.cryptoService = cryptoService;
    }

    @Override
    public TokenResponse validate(LoginRequest loginRequest) throws ServletException {
        validateLoginRequestIsNull(loginRequest);
        return resultLoginInfo(loginRequest);
    }

    private TokenResponse resultLoginInfo(LoginRequest loginRequest) {
        UserDetails userResult = loadUserByUsername(loginRequest);

        if (userResult != null && !userResult.getUsername().isEmpty()) {
            Optional<User> user = userService.findByEmail(userResult.getUsername());
            if (user.isPresent()) {
                TokenResponse tokenResult = new TokenResponse();
                tokenResult.setToken(JWTUtil.createToken(user.get().getEmail()));
                tokenResult.setUserId(user.get().getId());
                tokenResult.setRoles(user.get().getProfiles());
                return tokenResult;
            }
        }

        return new TokenResponse();
    }

    private UserDetails loadUserByUsername(LoginRequest loginRequest) {
        return customUserDetailsService.loadUserByUsername(loginRequest.getEmail());
    }

    private void validateLoginRequestIsNull(LoginRequest loginRequest) throws ServletException {
        if (loginRequest.getEmail() == null || loginRequest.getPassword() == null) {
            throw new ServletException("Please fill in username and password");
        }
    }

    private boolean validated(LoginRequest loginRequest, String password) {
        return cryptoService.matches(loginRequest.getPassword(), password);
    }
}
