package com.example.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.bean.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value="UserSaveSession",tags="user增删改查")
@Controller
public class UserController {
	@Autowired
	private User user;
	//List<User> userlist = new ArrayList<>();
	//展示用户
	@ApiOperation("跳转到viewuser页面")
	@GetMapping(value="/viewuser")
	public String user(Model model) {
		User user =new User();
		user.setId("1");
		user.setName("小明");
		user.setAge(13);
		user.setPhoneNumber("11111111111");
		
		User user1 =new User();
		user1.setId("2");
		user1.setName("小红");
		user1.setAge(13);
		user1.setPhoneNumber("22222222222");
		
		List<User> userlist = new ArrayList<>();
		userlist.add(user);
		userlist.add(user1);
		
		model.addAttribute("userlist", userlist);
		return "viewuser";
	}
	
	
	//新增用户
	@ApiOperation("跳转到adduser页面")
	@GetMapping(value="/adduser")
	public String adduser(Model model) {
		User user =new User();
		model.addAttribute("dto", user);
		return "adduser";
	}
	
//	新增用户提交
	@ApiOperation("新增用户跳转")
	@PostMapping("/saveadduser")
	  public String addForm(User user,HttpServletRequest request) {
	   System.out.println("user1:"+user.toString());
	   request.getSession().setAttribute(user.getId(), user);
	   User user2 = (User) request.getSession().getAttribute(user.getId());
	   System.out.println("user2:"+user2.toString());
	   return "redirect:/saveadduserview";
	  }
	@ApiOperation("新增用户")
	@GetMapping(value="/saveadduserview")
	public String saveadduser(User user,Model model,HttpServletRequest req) {
		System.out.println(user);
		List<User> userlist = new ArrayList<>();
		Enumeration<String> e=req.getSession().getAttributeNames();     
		   System.out.println(e);
		   if(e!=null) 
		   while( e.hasMoreElements())   {   
		       String sessionName=(String)e.nextElement();  
		       User user2 = (User) req.getSession().getAttribute(sessionName); 
		       userlist.add(user2);
		   }
		   model.addAttribute("userlist", userlist);
		return "viewuser";
	}
	
	
	//编辑用户
	@ApiOperation("跳转到updateuser页面")
	@GetMapping(value="/updateuser")
	public String updateuser(String id,Model model,HttpServletRequest req) {
		System.out.println(user);
		User user=(User) req.getSession().getAttribute(id);
		model.addAttribute("dto", user);
		return "updateuser";
	}
	
	
//	编辑用户提交
	@ApiOperation("根据Id编辑用户")
	@PostMapping(value="/viewsessionuser")
	 public String viewsessionuser(Model model,HttpServletRequest request,User user) {
		  if(user.getId()!=null) {
		  List<User> userlist = new ArrayList<User>();
		  Enumeration<String> e=request.getSession().getAttributeNames();     
		  while( e.hasMoreElements())   {   
		      String sessionName=(String)e.nextElement();   
		      User user2 = (User) request.getSession().getAttribute(sessionName); 
		      if(user2.getId()!=user.getId()) {
		      userlist.add(user2);
		      }
		  }  
		  model.addAttribute("userlist",userlist);
		  }
		return "viewuser";
	}
	
	//delete user  add by xuconghui  20180530
	@ApiOperation("根据Id删除用户")
	@GetMapping(value="/deleteuser")
	public String deleteuser(HttpSession session,String id,Model model,HttpServletRequest req) {
		System.out.println(id);
		session.removeAttribute(id);

		List<User> userlist = new ArrayList<User>();
		  Enumeration<String> e=req.getSession().getAttributeNames();     
		  while( e.hasMoreElements())   {   
		      String sessionName=(String)e.nextElement();   
		      User user2 = (User) req.getSession().getAttribute(sessionName); 
		      if(!user2.getId().equals(id)) {
		    	  System.out.println(user2.getId());
		    	  userlist.add(user2);
		      }
		  }  
		  model.addAttribute("userlist",userlist);
		
		return "viewuser";
	}
	
}
