package br.com.everis.applicant.model.service.impl;

import br.com.everis.applicant.model.entity.User;
import br.com.everis.applicant.model.repository.UserRepository;
import br.com.everis.applicant.model.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private UserRepository userRepository;

    public AccountServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean register(User user) {
        User result = userRepository.save(user);
        return result != null;
    }
}
