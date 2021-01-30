package ru.jelly.app.config;
/*
 * Date: 1/28/21
 * Time: 8:05 AM
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    Environment env;

    @Profile("!dev")
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // todo: delete later
    @Profile("dev")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        if (env.acceptsProfiles(Profiles.of("dev"))) {
            http.authorizeRequests().anyRequest().permitAll();
        } else {
            http.authorizeRequests()
                    .mvcMatchers("/login/*", "registration/*", "/resources/**", "/static/**").permitAll()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/", true).permitAll()
                    .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/login");

            http.authorizeRequests().anyRequest().authenticated();
        }
    }
}
