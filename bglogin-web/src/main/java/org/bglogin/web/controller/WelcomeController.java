package org.bglogin.web.controller;


import java.security.Principal;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.bglogin.web.utils.*;
import org.bglogin.model.entity.Activity;
import org.bglogin.services.ds.IActivityDS;
import org.bglogin.services.ds.IUserDS;
import org.bglogin.commons.enums.BGLoginErrorEnum;
import org.bglogin.web.enums.LoginMessageKeyEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 * WelcomeController
 * 
 * @author Giuseppe Vincenzi
 *
 */
@Controller
//@RequestMapping(value = "/")
public class WelcomeController {
	
	@Autowired
	IActivityDS activityDS;
	@Autowired
	IUserDS userDS;

	@RequestMapping(value = { "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage(HttpServletRequest request, HttpSession session) {

		ModelAndView model = new ModelAndView();
		
		String role = "";
		if (request.isUserInRole("ADMIN")) {
			role = "ADMIN";
			session.setAttribute("Role", role);
			model.addObject("welcomeMessage", LoginMessageKeyEnum.LOGIN_ADMIN_OK.getKey());
		}
		
		else if (request.isUserInRole("USER")) {
			role = "USER";
			session.setAttribute("Role", role);
			model.addObject("welcomeMessage", LoginMessageKeyEnum.LOGIN_USER_OK.getKey());		
		}
		
		List<Activity> att = activityDS.getList();
		return new ModelAndView("Visualizza", "activities", att);
	}
	

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error",BGLoginErrorEnum.LOGIN_ERROR.getKey()); 
		}

		if (logout != null) {
			model.addObject("msg", LoginMessageKeyEnum.LOGIN_LOGOUT_OK.getKey());
		}
		model.setViewName("index");

		return model;
	}
	
	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
	public ModelAndView newUser(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		return new ModelAndView("NuovoUtente");
	}
	
	@RequestMapping(value = "/recuperaPassword", method = RequestMethod.GET)
	public ModelAndView recover() {

		return new ModelAndView("RecuperaPassword");
	}
	
	@RequestMapping(value = "/recPwd", method = RequestMethod.POST)
	public ModelAndView recover(@RequestParam(value = "username") String name,
			@RequestParam(value = "email") String email, HttpServletRequest request, HttpSession session) throws MessagingException {
		
		ModelAndView model = new ModelAndView();
		session.setAttribute(name, "name1");
		session.setAttribute(email, "email1");
		String[] dest = {email};
		String pwd = userDS.recPwd(name, email);
		String passwd = pwd.substring(pwd.length()-8);
		String msg = "La tua password temporanea é " + passwd + ". Per modificarla clicca <a href=" + request.getContextPath() + "/modPwd>QUI</a>";

		MailUtils.inviaMail(dest, "Recupero password", msg, "recuperopassword@assembly.it");
		model.addObject("msg",LoginMessageKeyEnum.INVIO_PASSWORD.getKey());
		model.setViewName("index");
		return model;
	}
	
	@RequestMapping(value = "/modPwd", method = RequestMethod.GET)
	public ModelAndView modifyPwd() {

		return new ModelAndView("ModificaPassword");
	}
	
	@RequestMapping(value = "/modPwd", method = RequestMethod.POST)
	public ModelAndView modificaPwd(@RequestParam(value = "password") String password,
			HttpSession session) throws MessagingException {
		
		String name = (String) session.getAttribute("name1");
		String email = (String) session.getAttribute("email1");
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String pwd = encoder.encode(password);
		int rows = userDS.updatePwd(name, pwd, email);
		if (rows>0) {
			
			String role = "USER";
			session.setAttribute("Role", role);
			
		}
		List<Activity> att = activityDS.getList();
		return new ModelAndView("Visualizza", "activities", att);
	}
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public ModelAndView createUser(@RequestParam(value = "username") String name,
			@RequestParam(value = "password") String password, @RequestParam(value = "email") String email) {

		ModelAndView model = new ModelAndView();
		int check;
		check = userDS.check(name, "username");
		if (check>0) {
			model.addObject("msg", LoginMessageKeyEnum.LOGIN_USER_PRESENT.getKey());
			model.setViewName("index");
			return model;
		}
		check = userDS.check(email, "email");
		if (check>0) {
			model.addObject("msg", LoginMessageKeyEnum.LOGIN_EMAIL_PRESENT.getKey());
			model.setViewName("index");
			return model;
		}
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String pwd = encoder.encode(password);
		userDS.create(name,pwd,email);
		
		model.addObject("msg", LoginMessageKeyEnum.LOGIN_NEW_USER.getKey());
		model.setViewName("index");
		return model;
			
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied(Principal user) {
		ModelAndView model = new ModelAndView();
		model.addObject("errorMessage", BGLoginErrorEnum.LOGIN_PERMISSION_ERROR.getKey());
		model.setViewName("errors/403");
		return model;

	}
	
}
