package jblog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jblog.repository.PostRepository;
import jblog.vo.PostVo;

@Service
public class PostService {
	private PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public void createWrite(String title, Long categoryId, String content) {
		PostVo vo = new PostVo();
		vo.setTitle(title);
		vo.setCategoryId(categoryId);
		vo.setContents(content);
		
		postRepository.createWrite(vo);
		
	}

	public List<PostVo> getPostVo(String blogId, Integer categoryId) {
		return postRepository.getPostVoList(blogId, categoryId);
	}

	public List<PostVo> getDefaultCategoryPostVo(String blogId) {
		return postRepository.getDefaultCategoryPostVo(blogId);
	}

	public PostVo getPostVoById(String blogId, Integer categoryId, Integer postId) {
		PostVo vo = null;
		vo = postRepository.getPostVoById(blogId, categoryId ,postId);
		if(vo == null) {
			vo = new PostVo();
			vo.setTitle("잘 못된 URL입니다.");
			vo.setContents("");
			return vo; 
		}
		return vo;
	}
	
	
}
