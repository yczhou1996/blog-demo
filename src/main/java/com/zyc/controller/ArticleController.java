package com.zyc.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zyc.domain.Article;
import com.zyc.domain.Comment;
import com.zyc.domain.Likes;
import com.zyc.domain.User;
import com.zyc.service.ArticleService;
import com.zyc.service.CommentService;
import com.zyc.service.LikesService;
import com.zyc.service.UserService;

import net.minidev.json.JSONObject;
import net.sf.json.JSONArray;

@Controller
@RequestMapping("/articles")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private LikesService likesService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping("/index11")
	public String index(Model model){
		List<Article> articles = articleService.findAll();
		model.addAttribute("articles", articles);
		return "articles/index";
	}
	
	@RequestMapping("/index")
	public String index(Model model,@RequestParam(value = "start", defaultValue = "0") 
			int start,@RequestParam(value = "size", defaultValue = "5") int size,HttpSession session) {
		start = start<0?0:start;
		Pageable pageable = new PageRequest(start, size);
		Page<Article> page = articleService.findAllArticle(pageable);
		List<Article> articles = new ArrayList<>();
		for(Article article : page){
			articles.add(article);
		}
		Object obj = session.getAttribute("user");
		if(obj==null){
			model.addAttribute("loginon", "off");
		}else{
			model.addAttribute("loginon", "on");
		}
		model.addAttribute("page", page);
		model.addAttribute("articles", articles);
		return "articles/index";
	}
	
	@GetMapping("/write")
	public String write(Model model,HttpSession session){
		Object obj = session.getAttribute("user");
		com.zyc.domain.User user = (com.zyc.domain.User)obj;
		if(user==null){
			return "redirect:/login";
		}
		model.addAttribute("article", new Article());
		return "articles/edit";
	}
	
	@GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model){
        Article article = articleService.getArticleById(id);
        model.addAttribute("article", article);
        return "articles/edit";
    }
	
	@PostMapping("/save")
	public String save(Article article,Model model,HttpSession session){
		try{
			Object obj = session.getAttribute("user");
			com.zyc.domain.User user = (com.zyc.domain.User)obj;
			article.setUser_id(user.getId());
			articleService.saveArticle(article);
		}catch (Exception e) {
			model.addAttribute("loginError", true);
			model.addAttribute("errorMsg", "保存失败");
			return "articles/edit";
		}
		return "redirect:/articles/index";
	}
	
	@GetMapping("/view/{id}")
	public String view(@PathVariable("id") Long id, Model model){
		articleService.readingIncrease(id); 
		Article article = articleService.getArticleById(id);
		User user = userService.getUserById(article.getUser_id());
		
		List<Comment> comments = commentService.findComments(id);
		for(Comment comment:comments){
			String name = userService.getUserById(comment.getUser_id()).getUsername();
			comment.setUsername(name);
		}
		comments = comments.subList(0, comments.size()<5?comments.size():5);
		model.addAttribute("author",user.getUsername());
        model.addAttribute("articles", article);
        model.addAttribute("comments", comments);
        return "articles/view";
	}
	
	@PostMapping(value="likess")
	public ResponseEntity<?> likess(@RequestParam(value = "id") Long id,HttpSession session){
		String result = "ok";
		Object obj = session.getAttribute("user");
		com.zyc.domain.User user = (com.zyc.domain.User)obj;
		if(user==null||user.getId()==0){
			return ResponseEntity.ok("error");
		}
		if(likesService.getLikeById(user.getId(),id)==null){
			//可以点赞
			Likes like = new Likes();
			like.setArticle_id(id);
			like.setUser_id(user.getId());
			likesService.saveLike(like);
			Article article = articleService.getArticleById(id);
			article.setLikeSize(article.getLikeSize()+1);
			articleService.saveArticle(article);
		}else{
			result = "all";
		}
		
		return ResponseEntity.ok(result);
	}
	
	@PostMapping(value="publish")
	public ResponseEntity<?> publish(@RequestParam(value = "id") Long article_id
			,@RequestParam(value = "comment") String content, HttpSession session){
		Object obj = session.getAttribute("user");
		com.zyc.domain.User user = (com.zyc.domain.User)obj;
		if(user==null||user.getId()==0){
			return ResponseEntity.ok("error");
		}
		articleService.commentIncrease(article_id);
		Comment comment = new Comment();
		comment.setUser_id(user.getId());
		comment.setArticle_id(article_id);
		comment.setContent(content);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");  
		String str=sdf.format(new Date());  
		comment.setTime(str);
		commentService.saveComment(comment);
		String name = userService.getUserById(comment.getUser_id()).getUsername();
		comment.setUsername(name);
		String json = JSONArray.fromObject(comment).toString();
		return ResponseEntity.ok(json);
	}
	
	@PostMapping(value="more")
	public ResponseEntity<?> more(@RequestParam(value = "id") Long article_id
			,@RequestParam(value = "count") int count, HttpSession session){
		count+=5;
		List<Comment> comments = commentService.findComments(article_id);
		comments = comments.subList(0, count>comments.size()?comments.size():count);
		for(Comment comment:comments){
			String name = userService.getUserById(comment.getUser_id()).getUsername();
			comment.setUsername(name);
		}
		String json = JSONArray.fromObject(comments).toString();
		 
		return ResponseEntity.ok(json);
	}
}
