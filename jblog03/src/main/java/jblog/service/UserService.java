package jblog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jblog.repository.UserRepository;
import jblog.vo.UserVo;

@Service
public class UserService {
	private UserRepository userRepository;
	private BlogService blogService;
	private CategoryService categoryService;
	
	public UserService(UserRepository userRepository, BlogService blogService, CategoryService categoryService) {
		this.userRepository = userRepository;
		this.blogService = blogService;
		this.categoryService = categoryService;
	}
	
	@Transactional
	public void join(UserVo userVo) {
		userRepository.insert(userVo);
		blogService.createBlog(userVo);
		categoryService.createDefaultCategory(userVo.getId());
	}

	public UserVo getUser(String id, String password) {
		return userRepository.findByIdAndPassword(id, password);
		
	}

	public UserVo getUserById(String blogId) {
		return userRepository.getUserById(blogId);
	}

}
