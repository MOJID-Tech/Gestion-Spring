package com.example.demo.security;


import com.example.demo.entity.Role;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserService userService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       /* http.csrf().disable();
        http.authorizeRequests().anyRequest().permitAll()*/
       System.out.println("hello autorities");

     http
               .cors()
                .and()
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //public
                .antMatchers(HttpMethod.POST, "/users/authenticate**").permitAll()
                 .antMatchers(HttpMethod.POST, "/clients**").permitAll()
                .antMatchers(HttpMethod.POST, "/users/reset-password**").permitAll()
                .antMatchers(HttpMethod.POST, "/prestation/create**").permitAll()
                .antMatchers(HttpMethod.GET, "/prestation**").permitAll()
             .antMatchers(HttpMethod.PUT, "/prestation**").permitAll()
             .antMatchers(HttpMethod.DELETE, "/demonstration**").permitAll()
             .antMatchers(HttpMethod.GET, "/demonstration/search**").permitAll()
             .antMatchers(HttpMethod.GET, "/demonstration/Get**").permitAll()

             //private
                .antMatchers(HttpMethod.GET,"/demande/Manager**").hasAuthority("Manager")


                .anyRequest().permitAll()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider, userService));
    }
}

