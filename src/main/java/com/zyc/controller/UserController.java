package com.zyc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zyc.domain.Article;
import com.zyc.domain.User;
import com.zyc.service.ArticleService;


@Controller
@RequestMapping("/user")  
public class UserController {
	 
	@Autowired
	private ArticleService articleService;
	
	@GetMapping("/edit")
	public String edit(Model model,@RequestParam(value = "start", defaultValue = "0") 
	int start,@RequestParam(value = "size", defaultValue = "5") int size,HttpSession session){
		start = start<0?0:start;
		Pageable pageable = new PageRequest(start, size);
		Object obj = session.getAttribute("user");
		if(obj==null){
			return "redirect:/login";
		}
		User user = (User)obj;
		Page<Article> page = articleService.findAllById(pageable, user.getId());
		
		List<Article> articles = new ArrayList<>();
		for(Article article : page){
			int length = article.getContent().length() >80 ? 80:article.getContent().length();
			article.setContent(article.getContent().substring(0, length)+"...");
			articles.add(article);
		}
		model.addAttribute("articles", articles);
		model.addAttribute("page", page);
		return "/user/edit";
	}
}
