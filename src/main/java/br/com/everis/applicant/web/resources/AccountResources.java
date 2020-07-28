package br.com.everis.applicant.web.resources;

import br.com.everis.applicant.model.entity.User;
import br.com.everis.applicant.model.service.AccountService;
import br.com.everis.applicant.web.dto.request.AccountRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/register")
public class AccountResources {

    private AccountService accountService;
    private ConversionService accountRequestConverter;

    public AccountResources(AccountService accountService, ConversionService accountRequestConverter) {
        this.accountService = accountService;
        this.accountRequestConverter = accountRequestConverter;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Boolean> register(@RequestBody AccountRequest accountRequest) {
        log.info("Account Service: " + accountRequest);
        User user = accountRequestConverter.convert(accountRequest, User.class);
        boolean registered = accountService.register(user);
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }
}
