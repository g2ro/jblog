package jblog.repository;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	
	private DataSource dataSource;
	private SqlSession sqlSession;
	
	public CategoryRepository(SqlSession sqlSession, DataSource dataSource) {
		this.dataSource = dataSource;
		this.sqlSession = sqlSession;
	}
	
	public int createBlogCategory(CategoryVo vo) {
		return sqlSession.insert("category.createBlogCategory", vo);
	}

	public List<CategoryVo> getCategoryByBlogId(String blogId) {
		return sqlSession.selectList("category.getCategoryByBlogId", blogId);
	}
	
}
