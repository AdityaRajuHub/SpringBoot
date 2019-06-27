package com.example.rest.jpa.demo.config;

import javax.servlet.Filter;

import org.springframework.boot.autoconfigure.security.servlet.WebSecurityEnablerConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.rest.jpa.demo.filter.CustomFilter;

@EnableGlobalMethodSecurity(prePostEnabled=true)		//added for Filter based authorization
@EnableWebSecurity	//needed for adding security
@Configuration		
public class SpringAuthConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("{noop}user").roles("USER")
		.and().withUser("admin").password("{noop}admin").roles("ADMIN");
	}
	
	@Override
	public void configure(HttpSecurity httpSecurity) {
		try {
			httpSecurity.csrf().disable(); //Cross site scripting forgery
			
			//basic level no auth
			/*httpSecurity.authorizeRequests().anyRequest().permitAll()
			.and().httpBasic();*/
			
			//Basic InMemory authentication with authorization
			/*httpSecurity.authorizeRequests()
			.antMatchers("/topics/**").hasRole("ADMIN")
			.anyRequest().fullyAuthenticated().and().httpBasic();*/
			
			/*
			 * addFilterBefore(filter, class) – adds a filter before the position of the specified filter class
			 * addFilterAfter(filter, class) – adds a filter after the position of the specified filter class
			 * addFilterAt(filter, class) – adds a filter at the location of the specified filter class
			 * addFilter(filter) – adds a filter that must be an instance of or extend one of the filters provided by Spring Security
			 */
			httpSecurity.authorizeRequests()
			.anyRequest().permitAll();
			//.fullyAuthenticated()
			//.and()
			//.addFilterAfter(getCustomFilter(), BasicAuthenticationFilter.class);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Bean
	public CustomFilter getCustomFilter() {
		return new CustomFilter();
	}
	
	//public void 
}
