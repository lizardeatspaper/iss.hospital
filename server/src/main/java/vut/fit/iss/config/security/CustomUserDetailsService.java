package vut.fit.iss.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import vut.fit.iss.domain.user.User;
import vut.fit.iss.domain.user.UserRole;
import vut.fit.iss.repository.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByAccountUserName(username);
        if (user.isPresent()) {
            return new CustomUserDetails(user.get().getAccount().getUserName(), user.get().getAccount().getPassword(), true, true, true, true,
                    loadUserAuthorities(user.get().getRole()), user.get().getId());
        } else {
            throw new UsernameNotFoundException("could not find the user '"
                    + username + "'");
        }
    }

    protected List<GrantedAuthority> loadUserAuthorities(UserRole role) {
        List<GrantedAuthority> authorities;

        switch (role) {
            case ADMIN:
                authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_DOCTOR", "ROLE_STAFF", "ROLE_USER");
                break;
            case DOCTOR:
                authorities = AuthorityUtils.createAuthorityList("ROLE_DOCTOR", "ROLE_STAFF", "ROLE_USER");
                break;
            case NURSE:
                authorities = AuthorityUtils.createAuthorityList("ROLE_STAFF", "ROLE_USER");
                break;
            case PATIENT:
                authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
                break;
            default:
                throw new IllegalStateException();
        }
        return authorities;
    }
}