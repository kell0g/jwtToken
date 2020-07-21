package io.kello.ajwt.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.kello.ajwt.security.JwtTokenFilter;
import io.kello.ajwt.security.JwtTokenFilterConfigurer;
import io.kello.ajwt.security.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// @Autowired
	// private JwtTokenProvider jwtTokenProvider;

//	@Autowired
//	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Entry points
		http.authorizeRequests()//
				.antMatchers("/api/v1/signin").permitAll()//
				.antMatchers("/api/v1/signup").permitAll()//
				.antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/security/**",
						"/swagger-ui.html", "/webjars/**", "/swagger/**")
				.permitAll().antMatchers("/h2-console/**").permitAll().and().csrf().disable().headers().frameOptions()
				.disable().and().authorizeRequests()
				// Disallow everything else..
				.anyRequest().authenticated();


		// sessionManagement not used. => use jwt
		http.sessionManagement().disable();
		
		// Apply JWT
		http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
		web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
				"/configuration/security/**", "/swagger-ui.html", "/webjars/**", "/swagger/**");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}