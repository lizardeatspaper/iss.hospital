package vut.fit.iss.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;
    @Autowired
    private RestAuthenticationAccessDeniedHandler restAuthenticationAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/**").authenticated().anyRequest().permitAll()
                .and()
                .formLogin()
                .defaultSuccessUrl("/api/user", true)
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403").and()
                .httpBasic().disable()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(ajaxLogoutSuccessHandler)
                .deleteCookies("JSESSIONID").invalidateHttpSession(false).and()
                .exceptionHandling().accessDeniedHandler(restAuthenticationAccessDeniedHandler)
                .and().csrf().disable();
    }

}
