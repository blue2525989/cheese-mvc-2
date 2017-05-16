package org.launchcode.cheesemvc2.controllers;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="cheese")
public class CheeseController {

	static HashMap<String, String> cheese = new HashMap<>();
	
	@RequestMapping(value="")
	public String index(Model model) {
		model.addAttribute("cheeses", cheese);		
		String title = "My Cheeses";
		model.addAttribute("title", title);
		return "cheese/index";
	}
	
	@RequestMapping(value="add", method = RequestMethod.GET)
	public String displayAddCheeseForm(Model model) {
		model.addAttribute("title", "Add Cheese");
		return "cheese/add";
	}
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String processAddCheeseForm(Model model, @RequestParam String cheeseName,
			@RequestParam String description) {
		cheese.put(cheeseName, description);
		model.addAttribute("title", "Add Cheese");
		// redirect is relative to top level request mapping
		return "redirect:";
	}
}
