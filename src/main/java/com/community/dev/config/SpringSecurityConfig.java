package com.community.dev.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// resources that will be ignored for authentication
		web.ignoring().antMatchers("/dev-community/**", "/fastselect/**", "/remarkable/**",
				"/startbootstrap-blog-post/**", "/twbs-pagination/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
        http
        .authorizeRequests()
        	.antMatchers("/**").permitAll()
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/admin/login")
            .permitAll()
            .and()
        .logout()
	        .logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
	        .invalidateHttpSession(true)
	        .logoutSuccessUrl("/");
		// @formatter:on
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

}
