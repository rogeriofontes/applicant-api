package br.com.everis.applicant.web.converter.request;

import br.com.everis.applicant.model.entity.Profile;
import br.com.everis.applicant.model.entity.User;
import br.com.everis.applicant.model.service.PasswordCryptoService;
import br.com.everis.applicant.model.repository.UserProfileRepository;
import br.com.everis.applicant.web.dto.request.AccountRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class AccountRequestConverter implements Converter<AccountRequest, User> {

     private PasswordCryptoService passwordCryptoService;
     private UserProfileRepository userProfileRepository;

    public AccountRequestConverter(PasswordCryptoService passwordCryptoService, UserProfileRepository userProfileRepository) {
        this.passwordCryptoService = passwordCryptoService;
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public User convert(AccountRequest accountRequest) {
        return User.builder()
                .name(accountRequest.getName())
                .email(accountRequest.getEmail())
                .password(getCryptoPassword(accountRequest))
                .profiles(getProfiles(accountRequest.getUserType()))
                .build();
    }

    private String getCryptoPassword(AccountRequest accountRequest) {
        return passwordCryptoService.encrypt(accountRequest.getPassword());
    }

    private Set<Profile> getProfiles(String userTypeRole) {
        List<Profile> profiles = userProfileRepository.findByRole(userTypeRole);
        return new HashSet<>(profiles);
    }
}
