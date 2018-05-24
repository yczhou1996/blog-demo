package com.zyc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.zyc.domain.Article;
import com.zyc.domain.User;
import com.zyc.service.ArticleService;
import com.zyc.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ArticleService articleService;
	
	@GetMapping("/")
	public String root(){
		return "redirect:/articles/index";
	}
	
	@GetMapping("/index")
	public String index(Model model, HttpSession session){
		Object obj = session.getAttribute("user");
		com.zyc.domain.User user = (com.zyc.domain.User)obj;
		if(user!=null){
			model.addAttribute("loginon","on");
		}else{
			model.addAttribute("loginon","off");
		}
		List<Article> articles = articleService.findAll();
		model.addAttribute("articles", articles);
		return "index";
	}
	
	@GetMapping("/login")
	public String login(){
		return "login";
	}
	
	@PostMapping("/login")
	public String login(User user, Model model, HttpSession session){
		User u = userService.findByUsername(user.getUsername());
		if(u==null){
			model.addAttribute("loginError", true);
			model.addAttribute("errorMsg", "登陆失败，账号或者密码错误！");
			return "login";
		} else{
			session.setAttribute("user", u);
			return "redirect:/articles/index";
		}
	}
	
	@GetMapping("/register")
	public String register(){
		return "register";
	}
	
	@PostMapping("/register")
	public String registerUser(User user, Model model) {
		User u = userService.findByUsername(user.getUsername());
		if(u==null){
			userService.saveUser(user);
			model.addAttribute("errorMsg", "注册成功");
			return "redirect:/login";
		}else{
			model.addAttribute("errorMsg", "该用户名已被注册");
			return "register";
		}
	}
	
	@GetMapping("/logoff")
	public String logoff(HttpSession session){
		session.invalidate();
		return "/login";
	}
	
	@PostMapping("/login1")
	public ResponseEntity<?> loginn(User user, HttpSession session){
		String result = "ok";
		User u = userService.findByUsername(user.getUsername(),user.getPassword());
		if(u==null){
			result="error";
		} else{
			session.setAttribute("user", u);
		}
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/register1")
	public ResponseEntity<?> register1(User user) {
		String result = "ok";
		User u = userService.findByUsername(user.getUsername());
		if(u==null){
			userService.saveUser(user);	
		}else{
			result="error";
		}
		return ResponseEntity.ok(result);
	}
}
