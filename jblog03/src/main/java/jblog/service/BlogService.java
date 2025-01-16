package jblog.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jblog.component.FileUploader;
import jblog.repository.BlogRepository;
import jblog.vo.BlogVo;
import jblog.vo.UserVo;

@Service
public class BlogService {
	private BlogRepository blogRepository;
	private FileUploader fileUploader;
	
	public BlogService(BlogRepository blogRepository,FileUploader fileUploader) {
		this.blogRepository = blogRepository;
		this.fileUploader = fileUploader;
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
		if(logoFile == null) {
			blogRepository.updateBlogTitle(blogId, title);
			return;
		}
		String url = fileUploader.restore(logoFile);
		blogRepository.updateBlogTitleAndLogoFile(blogId, title, url);
		
		
	}
	
	
	
}
