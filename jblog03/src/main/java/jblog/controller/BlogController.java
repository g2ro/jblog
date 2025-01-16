package jblog.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jblog.security.Auth;
import jblog.service.BlogService;
import jblog.vo.PostVo;

@Controller
@RequestMapping("/{id}")
public class BlogController {
	private BlogService blogService;
	
	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}
	
	@GetMapping("")
	public String blogMain(@PathVariable("id") String blogId, Model model) {
		model.addAttribute("blogId", blogId);
		Map<String, Object> data = blogService.getMain(blogId);
		model.addAttribute("data", data);
		return "blog/main";
	}
	
	@Auth
	@GetMapping("/admin")
	public String adminDefault(@PathVariable("id") String blogId, Model model) {
		model.addAttribute("blogId", blogId);
		Map<String, Object> data = blogService.getMain(blogId);
		model.addAttribute("data", data);
		return "blog/main";
	}
	
	@Auth
	@GetMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String blogId, Model model) {
		model.addAttribute("blogVo", blogService.getBlogVo(blogId));

		return "blog/admin-basic";
	}
	
	@Auth
	@PostMapping("/admin/update")
	public String adminUpdate(@PathVariable("id") String blogId,
			@RequestParam("title") String title,
			@RequestParam(value= "logo-file", required = false) MultipartFile logoFile,
			Model model) {
		model.addAttribute("blogId", blogId);
		blogService.updateBlog(blogId, title, logoFile);
		
		return "redirect:/" + blogId;
	}
	
	@Auth
	@GetMapping("/admin/category")
	public String adminCategory(@PathVariable("id") String blogId, Model model) {
		model.addAttribute("CategoryVoList",blogService.getCategory(blogId));
		return "blog/admin-category";
	}
	
	@Auth
	@PostMapping("/admin/category/create")
	public String adminCategoryUpdate(
			@PathVariable("id") String blogId,
			@RequestParam("name") String name,
			@RequestParam("desc") String desc,
			Model model) {
		blogService.createBlogCategory(blogId, name, desc);
		return "redirect:/" + blogId + "/admin/category";
	}
	
	@Auth
	@GetMapping("/admin/write")
	public String adminWrite(@PathVariable("id") String blogId, Model model) {
		model.addAttribute("CategoryVoList",blogService.getCategory(blogId));
		return "blog/admin-write";
	}
	
	@Auth
	@PostMapping("/admin/write")
	public String doWrite(
			@PathVariable("id") String blogId, 
			@RequestParam("title") String title,
			@RequestParam("categoryId") Long CategoryId,
			@RequestParam("content") String content) {
		blogService.createWrite(title, CategoryId, content);
		return "redirect:/" + blogId + "/admin"; // 나중에 자기가 쓴 글로 이동하도
	}
}