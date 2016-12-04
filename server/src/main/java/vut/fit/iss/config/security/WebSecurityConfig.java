package vut.fit.iss.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_PATH = "api/login";


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling()
                .and()
                .formLogin()
                .loginProcessingUrl(LOGIN_PATH)
                .usernameParameter("username")
                .passwordParameter("password")
                .successForwardUrl("/api/user")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher(LOGIN_PATH, "DELETE"))
                .and()
                .sessionManagement()
                .maximumSessions(1);

        http.authorizeRequests().antMatchers("/api/login").permitAll().anyRequest().authenticated();
    }

}
