package jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jblog.component.FileUploader;
import jblog.repository.BlogRepository;
import jblog.repository.PostRepository;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.PostVo;
import jblog.vo.UserVo;

@Service
public class BlogService {
	private BlogRepository blogRepository;
	private CategoryService categoryService;
	private PostService postService;
	private FileUploader fileUploader;
	
	public BlogService(
			BlogRepository blogRepository,
			FileUploader fileUploader, 
			CategoryService categoryService, 
			PostService postService) {
		this.blogRepository = blogRepository;
		this.fileUploader = fileUploader;
		this.postService = postService;
		this.categoryService = categoryService;
	}
	public void createBlog(UserVo userVo) {
		
		BlogVo vo = new BlogVo();
		vo.setBlog_id(userVo.getId());
		vo.setTitle(userVo.getName() + "의 블로그");
		vo.setProfile("/assets/upload_images/default.png");
		
		blogRepository.insert(vo);
	}
	public Map<String, Object> getMain(String blogId) {
		BlogVo blogVo = blogRepository.getBlogByBlogId(blogId);
		Map<String, Object> data = new HashMap<>();
		data.put("blogVo", blogVo);
		return data;
		
	}
	public BlogVo getBlogVo(String blogId) {
		return blogRepository.getBlogByBlogId(blogId);
		
	}
	public void updateBlog(String blogId, String title, MultipartFile logoFile) {
		if(logoFile.getSize() == 0) {
			blogRepository.updateBlogTitle(blogId, title);
			return;
		}
		String url = fileUploader.restore(logoFile);
		blogRepository.updateBlogTitleAndLogoFile(blogId, title, url);
		
		
	}
	
	public List<CategoryVo> getCategory(String blogId) {
		return categoryService.getCategoryByBlogId(blogId);
	}
	
	public void createBlogCategory(String blogId, String name, String desc) {
		categoryService.createBlogCategory(blogId, name, desc);
	}
	
	public void createWrite(String title, Long categoryId, String content) {
		postService.createWrite(title, categoryId, content);
	}
	
	public List<PostVo> getPostVo(String blogId, Integer path1) {
		return postService.getPostVo(blogId, path1);
		
	}
	public List<PostVo> getDefaultCategoryPostVo(String blogId) {
		return postService.getDefaultCategoryPostVo(blogId);
	}
	public PostVo getPostVoById(String blogId, Integer path1, Integer path2) {
		return postService.getPostVoById(blogId, path1, path2);
		
	}
	public void deleteCategory(String blogId, String categoryId) {
		categoryService.deleteCategory(blogId, categoryId);
		
	}
	
	
	
}
