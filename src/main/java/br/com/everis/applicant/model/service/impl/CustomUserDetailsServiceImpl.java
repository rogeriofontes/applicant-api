package br.com.everis.applicant.model.service.impl;

import br.com.everis.applicant.model.entity.Profile;
import br.com.everis.applicant.model.entity.User;
import br.com.everis.applicant.model.service.CustomUserDetailsService;
import br.com.everis.applicant.model.service.UserService;
import br.com.everis.applicant.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Slf4j
@Service
@Qualifier("customUserDetailsService")
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService, UserDetailsService {

    private UserService userService;

    public CustomUserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        return userService.findByEmail(email).map(user -> {
            var profiles = user.getProfiles();
            var authorities = buildUserAuthority(profiles);

            return buildUserForAuthentication(user, authorities);
        }).orElse(null);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Profile> profiles) {
        var auths = new HashSet<GrantedAuthority>();

        profiles.stream().forEach(profile -> {
            auths.add(new SimpleGrantedAuthority(profile.getRole()));
        });

        return new ArrayList<>(auths);
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true,
                true, true, true, authorities);
    }

    @Transactional
    @Override
    public User loadCurrentUser() {
        return userService.findByEmail(SecurityUtils.getCurrentLogin()).get();
    }
}
