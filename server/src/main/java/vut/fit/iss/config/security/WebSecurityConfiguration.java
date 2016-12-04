package vut.fit.iss.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import vut.fit.iss.domain.user.UserRole;
import vut.fit.iss.service.user.UserService;

import java.util.List;
import java.util.Optional;

@Configuration
public class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    //    private final AccountService service;
    private final UserService service;

    @Autowired
    public WebSecurityConfiguration(UserService service) {
        this.service = service;
    }

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
        auth
                .userDetailsService(userDetailsService())
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Optional<vut.fit.iss.domain.user.User> user = service.getByUserName(username);
                if (user.isPresent()) {
                    return new User(user.get().getAccount().getUserName(), user.get().getAccount().getPassword(), true, true, true, true,
                            loadUserAuthorities(user.get().getRole()));
                } else {
                    throw new UsernameNotFoundException("could not find the user '"
                            + username + "'");
                }
            }

            protected List<GrantedAuthority> loadUserAuthorities(UserRole role) {
                List<GrantedAuthority> authorities = null;
                switch (role) {
                    case ADMIN:
                        authorities = AuthorityUtils.createAuthorityList("ADMIN_ROLE");
                }
                return authorities;
            }
        };
    }
}
