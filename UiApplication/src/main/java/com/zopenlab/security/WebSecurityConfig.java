package com.zopenlab.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	private Http401AuthenticationEntryPoint http401AuthenticationEntryPoint;
	
	private final ObjectMapper objectMapper;
	
	
	
	public WebSecurityConfig(ObjectMapper objectMapper) {
		super();
		this.objectMapper = objectMapper;
	}

	@Bean
    public RequestBodyReaderAuthenticationFilter authenticationFilter() throws Exception {
        RequestBodyReaderAuthenticationFilter authenticationFilter
            = new RequestBodyReaderAuthenticationFilter();
        authenticationFilter.setAuthenticationSuccessHandler(this::loginSuccessHandler);
        authenticationFilter.setAuthenticationFailureHandler(this::loginFailureHandler);
        authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
        authenticationFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationFilter;
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		/*auth.inMemoryAuthentication().withUser("admin").password(encoder.encode("1234")).authorities("ROLE_ADMIN","ROLE_USER");
		auth.inMemoryAuthentication().withUser("user1").password(encoder.encode("1234")).authorities("ROLE_USER");*/
		
	/*	auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("Select username as principal, password as credentials, active  from user where username = ?")
			.authoritiesByUsernameQuery("select user_username as principal, roles_role_name as role from user_roles where user_username = ?")
			.passwordEncoder(encoder)
			.rolePrefix("ROLE_");*/
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
 
		http.
			csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
			
			http.authorizeRequests().
			anyRequest().authenticated()
					
		.and()
		.addFilter(authenticationFilter())
		//.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
		.logout()
		.logoutUrl("/logout")
		.logoutSuccessHandler(this::logoutSuccessHandler)
		.invalidateHttpSession(true)
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(http401AuthenticationEntryPoint);
	}
	
	private void loginSuccessHandler( HttpServletRequest request,
	        HttpServletResponse response,
	        Authentication authentication) throws IOException {
		response.setStatus(HttpStatus.OK.value());
		objectMapper.writeValue(response.getWriter(), "Yayy you logged in!");
	 
	}
	private void loginFailureHandler( HttpServletRequest request,
	        HttpServletResponse response,
	        AuthenticationException ex) throws IOException {
		 	response.setStatus(HttpStatus.UNAUTHORIZED.value());
	        objectMapper.writeValue(response.getWriter(), "Nopity nop!");
	}
	
	private void logoutSuccessHandler( HttpServletRequest request,
	        HttpServletResponse response,
	        Authentication authentication) throws IOException {
		response.setStatus(HttpStatus.OK.value());
		objectMapper.writeValue(response.getWriter(), "Bye");
	 
	}
	
}
