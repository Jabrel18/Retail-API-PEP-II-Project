package com.cognixia.jump.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cognixia.jump.filter.JWTRequestFilter;

@Configuration
public class SecurityConfiguration {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JWTRequestFilter jwtRequestFilter;
	
	
	//Authentication 
	@Bean
	protected UserDetailsService userDetailsService() {
		
		return userDetailsService;
	}
	
	//Authorization
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
			http.csrf().disable()
			.authorizeHttpRequests()
			.antMatchers( HttpMethod.POST, "/api/user" ).permitAll()
			.antMatchers( HttpMethod.GET, "/api/user" ).hasRole("ADMIN")
			.antMatchers( HttpMethod.POST, "/api/product" ).hasRole("ADMIN")
			.antMatchers( HttpMethod.PUT, "/api/product" ).hasRole("ADMIN")
			.antMatchers( HttpMethod.DELETE, "/api/product" ).hasRole("ADMIN")
			.antMatchers( HttpMethod.GET, "/api/purchase/{id}" ).hasRole("ADMIN")
			
			.antMatchers("/authenticate").permitAll()			// anyone can create a JWT w/o needing to have a JWT first
			.anyRequest().authenticated()						// all APIs, you have to have a user account
			.and().httpBasic()

			.and()
			.sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS )  // tell spring security to NOT create sessions
			;
		
			
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); 
			
		return http.build();
	}
	
	@Bean
	protected PasswordEncoder encoder() {
		
		// plain text encoder -> encode/encrypt the password
		return NoOpPasswordEncoder.getInstance();
		
	}
	
	
	@Bean
	protected DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder( encoder() );

		return authProvider;	
	}
	
	// can autowire and access the authentication manager (manages authentication (login) of our project)
	@Bean
	protected AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		
		return authConfig.getAuthenticationManager();
	}
	
}
