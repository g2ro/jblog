package jblog.controller;

import java.util.ArrayList;
import java.util.List;
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
import jblog.vo.BlogVo;
import jblog.vo.PostVo;

@Controller
@RequestMapping("/{id}")
public class BlogController {
	private BlogService blogService;
	
	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}
	
	@GetMapping({"", "/{categoryId:^[0-9]*$}", "/{categoryId:^[0-9]*$}/{postId:^[0-9]*$}"})
	public String blogMain(
			@PathVariable("id") String blogId,
			@PathVariable(value="categoryId", required = false) Integer categoryId,
			@PathVariable(value="postId", required = false) Integer postId,
			Model model) {
		model.addAttribute("blogId", blogId);
		model.addAttribute("CategoryVoList",blogService.getCategory(blogId));
		
		Map<String, Object> postData = blogService.getMain(blogId, categoryId, postId);
		BlogVo blogVo = (BlogVo) postData.get("blogVo");
		if(blogVo == null) {
			return "redirect:/";
		}
		
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("PostVoList", postData.get("postVoList"));
		model.addAttribute("post",postData.get("postVo"));
		
		return "blog/main";	
		}
	
	@Auth
	@GetMapping({"/admin", "/admin/{categoryId:^[0-9]*$}", "/admin/{categoryId:^[0-9]*$}/{postId:^[0-9]*$}"})
	public String adminDefault(
			@PathVariable("id") String blogId,
			@PathVariable(value="categoryId", required = false) Integer categoryId,
			@PathVariable(value="postId", required = false) Integer postId,
			Model model) {
		model.addAttribute("blogId", blogId);
		model.addAttribute("CategoryVoList",blogService.getCategory(blogId));
		
		Map<String, Object> postData = blogService.getMain(blogId, categoryId, postId);
		BlogVo blogVo = (BlogVo) postData.get("blogVo");
		if(blogVo == null) {
			return "redirect:/";
		}
		
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("PostVoList", postData.get("postVoList"));
		model.addAttribute("post",postData.get("postVo"));
		
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
	
	@Auth
	@GetMapping("/admin/delete/{categoryId}")
	public String delete(
			@PathVariable("id") String blogId,
			@PathVariable("categoryId") String categoryId
			) {
		blogService.deleteCategory(blogId, categoryId);
		return "redirect:/" + blogId + "/admin/category";
	}
}