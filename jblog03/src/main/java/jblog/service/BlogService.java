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
//	public Map<String, Object> getMain(String blogId) {
//		BlogVo blogVo = blogRepository.getBlogByBlogId(blogId);
//		Map<String, Object> data = new HashMap<>();
//		data.put("blogVo", blogVo);
//		return data;
//		
//	}
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
	
	public List<PostVo> getPostVo(String blogId, Integer categoryId) {
		return postService.getPostVo(blogId, categoryId);
		
	}
	public List<PostVo> getDefaultCategoryPostVo(String blogId) {
		return postService.getDefaultCategoryPostVo(blogId);
	}
	public PostVo getPostVoById(String blogId, Integer categoryId, Integer postId) {
		return postService.getPostVoById(blogId, categoryId, postId);
		
	}
	public void deleteCategory(String blogId, String categoryId) {
		categoryService.deleteCategory(blogId, categoryId);
		
	}
	public Map<String, Object> getMain(String blogId, Integer categoryId, Integer postId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<PostVo> vo = null;
		PostVo postVo = null;
		BlogVo blogVo = blogRepository.getBlogByBlogId(blogId);
		map.put("blogVo", blogVo);
		if(categoryId == null) {
			// default 카테고리와 기본 최신 디폴트 카테고리의 내용을 보여주면 될듯
			vo = postService.getDefaultCategoryPostVo(blogId);
			map.put("postVoList", vo);
			
			if(vo.size() == 0) {
				postVo = new PostVo();
				postVo.setTitle("글이 존재하지 않습니다.");
				postVo.setContents("현재 카테고리엔 작성될 게시글이 존재하지 않습니다.");
				map.put("postVo", postVo);
				return map;
			}
			postVo = vo.get(0);
			map.put("postVo", postVo);
			return map;
		}
		
		if(postId == null) {
			// 특정 카테고리와 특정 카테고리의 최신글을 보여주면 될듯
			vo = postService.getPostVo(blogId, categoryId);
			map.put("postVoList", vo);
			if(vo.size() == 0) {
				postVo = new PostVo();
				postVo.setTitle("글이 존재하지 않습니다.");
				postVo.setContents("현재 카테고리엔 작성될 게시글이 존재하지 않습니다.");
				map.put("postVo", postVo);
				return map;
			}
			postVo = vo.get(0);
			map.put("postVo", postVo);
			return map;
		}
		
		// postId에 해당되는 카테고리와 post를 보여주면 됨.
		vo = postService.getPostVo(blogId, categoryId);
		postVo = postService.getPostVoById(blogId,categoryId,postId);
		map.put("postVoList", vo);
		map.put("postVo", postVo);
		return map;
	}
	
	
	
}
