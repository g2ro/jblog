package jblog.service;

import org.springframework.stereotype.Service;

import jblog.repository.CategoryRepository;
import jblog.vo.CategoryVo;

@Service
public class CategoryService {
	private CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	public void createDefaultCategory(String id) {
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setBlog_id(id);
		categoryVo.setName("default");
		categoryVo.setDescription("디폴트 카테고리입니다.");
		
		categoryRepository.createDefaultCategory(categoryVo);
	}
	
	
}
