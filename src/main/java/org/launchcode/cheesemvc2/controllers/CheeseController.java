package org.launchcode.cheesemvc2.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="cheese")
public class CheeseController {

	static HashMap<String, String> cheeses = new HashMap<>();
	
	@RequestMapping(value="")
	public String index(Model model) {
		model.addAttribute("cheeses", cheeses);		
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
		cheeses.put(cheeseName, description);
		model.addAttribute("title", "Add Cheese");
		// redirect is relative to top level request mapping
		return "redirect:";
	}
	
	@RequestMapping(value="remove", method=RequestMethod.GET)
	public String removeForm(Model model) {
		model.addAttribute("cheeses", cheeses);
		model.addAttribute("title", "Remove a Cheese");
		return "cheese/remove";
	}
	
	@RequestMapping(value="remove", method=RequestMethod.POST)
	public String processRemoveForm(Model model, @RequestParam ArrayList<String> cheese) {
		for (int i = 0; i < cheese.size(); i++) {
			if (cheeses.containsKey(cheese.get(i))) {
				cheeses.remove(cheese.get(i));
			}
		}
		return "redirect:";
	}
}
