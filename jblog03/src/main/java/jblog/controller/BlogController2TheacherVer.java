package jblog.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/{id:(?!assets).*}") // 이렇게 하면 assets을 뺄 수 있다.
public class BlogController2TheacherVer {
	
	@RequestMapping({"", "/{path1}", "/{path1}/{path2}"})
	public String index(
			@PathVariable("id") String id,
			@PathVariable("path1") Optional<Long> path1, // path가 들어오지 안들어 왔을때 null값이 들어오길 바라지만 그게 아닌 Optional을 통해 처리
			@PathVariable("path2") Optional<Long> path2) {
		
		Long categoryId = 0L;
		Long postId = 0L;
		
		if(path2.isPresent()) {

			categoryId = path1.get();
			postId = path2.get();
		}else if(path1.isPresent()) {
			// /jlob/userid/categoryid로 입력되어 있을 때
			categoryId = path1.get();
		}
		
		// 서비스에서 하기
		// categoryId == 0L ->기본 카테고리 번호(default categoryId)로 설정
		// postId == 0L ->기본 post 번호(default categoryId)로 설정
		
		System.out.println("BlogController.index(" + id + ", " + categoryId + ", " + postId);
		return "blog/main";
	}
	
	// @Auth
	@RequestMapping("/admin")
	public String adminDefault(
			@PathVariable("id") String id) {
		// auth 인터셉터를 통해 url의 id와 로그인 id를 비교후 처리 -> auth인터셉터에서 처리?
		return "blog/main";
	}
}

// assets이 걸린다는게 무슨 소리?