package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(value= {"msg","msg1"})
public class SesAttrbController {
	
	@GetMapping(value="/sessiontest")
	public String sessiontest(Model model) {
		
		model.addAttribute("msg","我是session里的值！！");
		model.addAttribute("msg1","我是session里的值1！！");
		return "sessionTest";
	}
	
	
	@GetMapping(value="/queryUserId/{id}")
	public String queryUserId(@PathVariable("id") String id,Model model) {
		
		System.out.println(id);
		
		return "querySuccess";
	}

}
