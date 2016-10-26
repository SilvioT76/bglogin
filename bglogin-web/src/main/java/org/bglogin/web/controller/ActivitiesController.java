package org.bglogin.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bglogin.model.entity.Activity;
import org.bglogin.services.ds.IActivityDS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Controller

public class ActivitiesController {
	
	@Autowired
	IActivityDS activityDS;
	
	@RequestMapping(value="activities", method=RequestMethod.POST)
	protected ModelAndView Insert(@RequestParam(value = "name") String name,
			@RequestParam(value = "date") String date, @RequestParam(value = "owner") String owner)  {
		
		activityDS.insert(name, date, owner);
		
		return new ModelAndView("Inserimento");
	}
	
	@RequestMapping(value="/activities/{id}", method=RequestMethod.DELETE)
	protected ModelAndView delete(@PathVariable("id") String id)  {
		
		int rows = activityDS.delete(id);
		List<Activity> att = new ArrayList<Activity>();
		if (rows>0) {
		att = activityDS.getList();
		}
		return new ModelAndView("Visualizza","activities", att);
		
	}
	
	@RequestMapping(value="/activities/{id}", method=RequestMethod.POST)
	protected ModelAndView aggiorna(@RequestParam(value = "name") String name,
			@RequestParam(value = "date") String date, @RequestParam(value = "owner") String owner, @PathVariable("id") String id)  {
		
		
		activityDS.update(name, date, owner, id);
		List<Activity> att = activityDS.getList();
		return new ModelAndView("activities","activities", att);
	}
	
	@RequestMapping(value="/activities/{id}/edit", method=RequestMethod.GET)
	protected ModelAndView verifica(@PathVariable("id") String id)  {
		
		Activity activity = activityDS.check(id);
		
		return new ModelAndView("Modifica", "activity", activity);
	}
	
	@RequestMapping("/Index")
	public ModelAndView helloWorld() {
 
		String message = "Hello World";
		return new ModelAndView("Index", "message", message);
	}
	
	@RequestMapping("/activities/new")
	public ModelAndView inserimento() {
		
		return new ModelAndView("Inserimento");
	}
	
	
	@RequestMapping("/activities")
	public ModelAndView index() {
		
		List<Activity> att = activityDS.getList();
		return new ModelAndView("Visualizza","activities", att);
	}

}


