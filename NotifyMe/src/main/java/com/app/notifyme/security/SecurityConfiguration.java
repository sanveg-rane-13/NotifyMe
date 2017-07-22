package com.app.notifyme.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/signup/**").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/productPrevStats").permitAll()
				.antMatchers("/productStats").permitAll()
				.antMatchers("/mostSearched").permitAll()
				.antMatchers("/getErrors").permitAll()
				.antMatchers("/resolveError").permitAll()
				.antMatchers("/addProduct").permitAll()
				.antMatchers("/searchProduct").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN") 
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.httpBasic();
		
		http.csrf().disable();
	}

}