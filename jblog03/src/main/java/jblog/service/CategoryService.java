package jblog.service;

import java.util.List;

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
		
		categoryRepository.createBlogCategory(categoryVo);
	}
	public List<CategoryVo> getCategoryByBlogId(String blogId) {
		return categoryRepository.getCategoryByBlogId(blogId);
	}
	public void createBlogCategory(String blogId, String name, String desc) {
		CategoryVo vo = new CategoryVo();
		vo.setBlog_id(blogId);
		vo.setName(name);
		vo.setDescription(desc);
		categoryRepository.createBlogCategory(vo);
		
	}
	public void deleteCategory(String blogId, String categoryId) {
		categoryRepository.deleteCategory(blogId, categoryId);
	}
	
	
}
