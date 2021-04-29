package com.cognizant.authorization.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity.IgnoredRequestConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cognizant.authorization.service.CustomerDetailsService;
import com.cognizant.authorization.service.JwtRequestFilter;

import lombok.extern.slf4j.Slf4j;

/**
 * This class is used for the security configuration. It extends the class
 * WebSecurityConfigurerAdapter It will take care of the authentication and
 * authorization based on the user credentials.
 */
@Slf4j
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomerDetailsService userDetailsService	;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("START");
		SessionManagementConfigurer<HttpSecurity> sessionCreationPolicy = 
				http.csrf().disable().authorizeRequests()
				.antMatchers("/login").permitAll().anyRequest()
				.authenticated().and().exceptionHandling().and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		HttpSecurity addFilterBefore = http.addFilterBefore(jwtRequestFilter,
				UsernamePasswordAuthenticationFilter.class);
		log.info("END");
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * Password encoder is an interface which is used through the authorization
	 * process. The encode function shall be used to encode the password. We are using NoOpEncoder.
	 */
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	

	/**
	 * These URLs will be ignored. The URL we are giving to the
	 * method antMatchers(), these URL's should not be put behind the authentication
	 * wall.
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		log.info("START");
		IgnoredRequestConfigurer antMatchers = 
				web.ignoring()
				.antMatchers("/h2-console/**");
		log.info("END");
	}

}