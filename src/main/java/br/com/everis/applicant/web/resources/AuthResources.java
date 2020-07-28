package br.com.everis.applicant.web.resources;

import br.com.everis.applicant.model.service.AuthService;
import br.com.everis.applicant.web.dto.request.LoginRequest;
import br.com.everis.applicant.web.dto.response.TokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;

@Slf4j
@RestController
@RequestMapping("/v1/auth")
public class AuthResources {

    private br.com.everis.applicant.model.service.AuthService AuthService;

    public AuthResources(AuthService authService) {
        AuthService = authService;
    }

    @PostMapping
    @ResponseBody
    public TokenResponse login(@RequestBody LoginRequest loginRequest) throws ServletException {
        return AuthService.validate(loginRequest);
    }
}
