package org.bglogin.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Class for Annotation Type Configuration
 * 
 * @author Giuseppe Vincenzi
 *
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DriverManagerDataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.passwordEncoder(passwordEncoder())
				.usersByUsernameQuery("select username, password, enabled from public.\"USER\" where username=?")
				.authoritiesByUsernameQuery(
						"select username as username,b.role as role from public.\"USER\" as a "
						+ "inner join public.\"ROLE\" as b on b.role_id=a.role_id "
						+ "where username=?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("/", "/welcome/**").access("hasRole('ADMIN') or hasRole('USER')")
		.antMatchers("/admin/**").access("hasRole('ADMIN')")
		.and().formLogin().loginPage("/index").defaultSuccessUrl("/welcome")
		.failureUrl("/index?error")
		.usernameParameter("username").passwordParameter("password")
		.and()
		.exceptionHandling().accessDeniedPage("/403")
		.and()
		.logout().logoutSuccessUrl("/index?logout").invalidateHttpSession(true);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}
