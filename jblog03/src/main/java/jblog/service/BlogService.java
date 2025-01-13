package jblog.service;

import org.springframework.stereotype.Service;

import jblog.repository.BlogRepository;
import jblog.vo.BlogVo;
import jblog.vo.UserVo;

@Service
public class BlogService {
	private BlogRepository blogRepository;
	
	public BlogService(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}
	public void createBlog(UserVo userVo) {
		
		BlogVo vo = new BlogVo();
		vo.setBlog_id(userVo.getId());
		vo.setTitle(userVo.getName() + "의 블로그");
		vo.setProfile("/assets/upload_images/default.png");
		
		blogRepository.insert(vo);
	}
	
}
