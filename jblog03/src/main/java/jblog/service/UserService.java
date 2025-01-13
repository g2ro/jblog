package jblog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jblog.repository.UserRepository;
import jblog.vo.UserVo;

@Service
public class UserService {
	private UserRepository userRepository;
	private BlogService blogService;
	
	public UserService(UserRepository userRepository, BlogService blogService) {
		this.userRepository = userRepository;
		this.blogService = blogService;
	}
	
	@Transactional
	public void join(UserVo userVo) {
		userRepository.insert(userVo);
		blogService.createBlog(userVo);
	}
}
