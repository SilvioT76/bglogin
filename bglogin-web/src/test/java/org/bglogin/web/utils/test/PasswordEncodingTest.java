package org.bglogin.web.utils.test;

import java.util.Collection;

import org.bglogin.web.config.test.TestWebConfig;
import org.bglogin.web.utils.PasswordEncodingUtils;
import org.junit.runner.RunWith;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit Test of Service for login
 * 
 * @author Giuseppe Vincenzi
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestWebConfig.class)
@ActiveProfiles("test")
public class PasswordEncodingTest {
	@Test
	public void testEncodingPassword(){
		String password="consulente";
		String encodedPassword = PasswordEncodingUtils.encodingPassword(password);
		System.out.println("encodedPassword: "+encodedPassword);
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		Assert.assertTrue(encoder.matches(password, encodedPassword));
	}
	
	private boolean hasRole(String role) {
		  Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
		  SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		  boolean hasRole = false;
		  for (GrantedAuthority authority : authorities) {
		     hasRole = authority.getAuthority().equals(role);
		     if (hasRole) {
			  break;
		     }
		  }
		  return hasRole;
		}
}
