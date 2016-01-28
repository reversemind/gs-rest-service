package com.test.hello.config;

import com.test.hello.Constants;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //    @Autowired
//    UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,    Constants.API + "/**").authenticated()
                .antMatchers(HttpMethod.POST,   Constants.API + "/**").authenticated()
                .antMatchers(HttpMethod.PUT,    Constants.API + "/**").authenticated()
                .antMatchers(HttpMethod.DELETE, Constants.API + "/**").authenticated()
                .anyRequest()
                .permitAll()
                .and()
                .httpBasic();
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
