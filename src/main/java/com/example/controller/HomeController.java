package com.example.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bean.User;
import com.fasterxml.jackson.databind.Module;

@Controller
//@RestController
public class HomeController {
	
	@Autowired
	private User user;

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(User user) {
		
		System.out.println(user);
		return "success";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String delete(@RequestParam(required=true) String Id) {
		
		System.out.println("您要删除的用户Id是："+Id);
		return "delete";
	}
	
	@RequestMapping(value="/exmap",method=RequestMethod.GET)
	public String exmap(@RequestParam Map<String,String> map) {
		for (Map.Entry<String, String> entry : map.entrySet()) {  
		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
		}  
		return "success";
	}
	
	@RequestMapping("/redirectTest")
	public String redirectTest(Model model) {
		model.addAttribute("redirectTest", "123");
		return "success";
	}
	
	@RequestMapping("/saveuser")
	public String saveuser(Model model) {
		user.setAge(18);
		user.setName("xiaoming");
		user.setPhoneNumber("12345678901");
		model.addAttribute("User", user);
		return "success";
	}
	
	@RequestMapping(value="/queryuser")
	public String queryuser(Model model,final HttpServletRequest req,final HttpServletResponse res) {
		String str= (String) req.getParameter("id");
		System.out.println(str);
		user.setAge(12);
		user.setName("laowang");
		user.setPhoneNumber("12345678901");
		
		return "succ";
	}
	
	
	@RequestMapping("/saveuserjson")
	@ResponseBody
	public User saveuserjson(User user) {
		user.setAge(18);
		user.setName("xiaoming");
		user.setPhoneNumber("12345678901");
		return user;
	}
	
	@RequestMapping("/queryuserjson")
	public String queryuserjson(@RequestBody User user) {
		System.out.println(user);
		return "querySuccess";
	}
	
	
	@RequestMapping("/user")
	public String user() {
		return "user";
	}
	
	@RequestMapping(value="/saveuserfrom",method=RequestMethod.POST)
	public String saveuserfrom() {
//		model.addAttribute("msg", "保存成功");
//		System.out.println(user);
		//转发练习 
		//return "forward:/forwarddemo";
		//重定向练习
		return "redirect:/redirectdemo";
	}
	
	
	@RequestMapping(value="/redirectdemo",method=RequestMethod.GET)
	public String redirectdemo(User user,Model model) {
		model.addAttribute("msg", "保存成功，我是重定向！！");
		System.out.println(user);
		return "msg";
	}
	
	@RequestMapping(value="/forwarddemo",method=RequestMethod.POST)
	public String forwarddemo(User user,Model model) {
		model.addAttribute("msg", "保存成功，我是转发！！");
		System.out.println(user);
		return "msg";
	}
}
