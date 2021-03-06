package com.example.controller;


import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DevicePlatform;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.bean.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="UserBean",tags="User操作接口")
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
	
	@RequestMapping(value="/redirectTest",method=RequestMethod.GET)
	public String redirectTest(Model model) {
		model.addAttribute("redirectTest", "123");
		return "success";
	}
	
	@RequestMapping(value="/saveuser",method=RequestMethod.POST)
	public String saveuser(Model model) {
		user.setAge(18);
		user.setName("xiaoming");
		user.setPhoneNumber("12345678901");
		model.addAttribute("User", user);
		return "success";
	}
	
	@RequestMapping(value="/queryuser",method=RequestMethod.GET)
	public String queryuser(Model model,final HttpServletRequest req,final HttpServletResponse res) {
		String str= (String) req.getParameter("id");
		System.out.println(str);
		user.setAge(12);
		user.setName("laowang");
		user.setPhoneNumber("12345678901");
		
		return "succ";
	}
	
	
	@RequestMapping(value="/saveuserjson",method=RequestMethod.POST)
	@ResponseBody
	public User saveuserjson(User user) {
		user.setAge(18);
		user.setName("xiaoming");
		user.setPhoneNumber("12345678901");
		return user;
	}
	
	@RequestMapping(value="/queryuserjson",method=RequestMethod.GET)
	public String queryuserjson(@RequestBody User user) {
		System.out.println(user);
		return "querySuccess";
	}
	
	
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public String user(User user) {
		return "user";
	}
	
	@RequestMapping(value="/saveuserfrom",method=RequestMethod.POST)
	public String saveuserfrom(@Valid User user,BindingResult result,Model model,RedirectAttributes redirectAttributes) {
		model.addAttribute("msg", "保存成功");
		redirectAttributes.addAttribute("msg","保存成功1");
		if(result.hasErrors()) {
			System.out.println(user);
			if(user.getAge()>150 || user.getAge()<1){
				FieldError fe=new FieldError("user", "age",user.getAge(), false, null, null, "年龄必须在1~150之间！");
				result.addError(fe);
			}
			return "user";
			}
			System.out.println(user);
			model.addAttribute("user",user);
			return "msg";
		
//		System.out.println(user);
		//转发练习 
		//return "forward:/forwarddemo";
		//重定向练习
		//return "redirect:/redirectdemo";
	}
	
	
	@RequestMapping(value="/redirectdemo",method=RequestMethod.GET)
	public String redirectdemo(User user,Model model) {
		//model.addAttribute("msg", "保存成功，我是重定向！！");
		
		System.out.println(user);
		return "msg";
	}
	
	@RequestMapping(value="/forwarddemo",method=RequestMethod.POST)
	public String forwarddemo(@Valid User user,BindingResult result,Model model) {
		//model.addAttribute("msg", "保存成功，我是转发！！");
		if(result.hasErrors()) {
		System.out.println(user);
		
		return "user";
		}
		System.out.println(user);
		model.addAttribute("user",user);
		return "msg";
	}
	
	@ApiOperation("新增用户")
	@PostMapping("/postadd")
	@ResponseBody
	public User postadd(@RequestBody @Valid User user,BindingResult result) {
		if(result.hasErrors()) {
			System.out.println(user);
			return user;
			}
		return user;
	}
	
	@ApiOperation("修改用户")
	@PutMapping("/putupdate")
	@ResponseBody
	public User putupdate(@RequestBody User user) {
		System.out.println(user);
		return user;
	}
	
	@ApiOperation("查询用户")
	@GetMapping("/getquery")
	public String getquery(String id) {
		System.out.println(id);
		return "succ";
	}
	
	@ApiOperation("删除用户")
	@DeleteMapping("/deleteuser")
	public String deleteuser(String id) {
		System.out.println(id);
		return "succ";
	}
	
	@ApiOperation("跳转index")
	@GetMapping("/index")
	public String index(HttpServletRequest req,Device device,Model model) {
//		Enumeration<String> headerNames = req.getHeaderNames();
//		 while(headerNames.hasMoreElements()){
//		        String name = (String) headerNames.nextElement();
//		            System.out.println(name + ":" + req.getHeader(name));
//		      }
//		System.out.println(device.isMobile());
//		System.out.println(device.isNormal());
//		System.out.println(device.isTablet());
//		DevicePlatform devicePlatform = device.getDevicePlatform();
//		System.out.println(devicePlatform);
		return "index";
	}
	
	@ApiOperation("国际化切换")
	@GetMapping("/changelanguage")
	public String changelanguage(HttpSession sess,String lang) {
		//System.out.println(lang);
		if(lang.equals("zh-CN")) {
		sess.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale("zh","CN"));
		}else{
		sess.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale("en","US"));
		}
		return "forward:/index";
	}
}
