package jblog.repository;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jblog.vo.BlogVo;

@Repository
public class BlogRepository {
	@Autowired
	private DataSource dataSource;
	
	private SqlSession sqlSession;
	
	public BlogRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public void insert(BlogVo vo) {
		sqlSession.insert("blog.insert", vo);
		
	}

	public BlogVo getBlogByBlogId(String blogId) {
		return sqlSession.selectOne("blog.getBlogByBlogId", blogId);
	}

	public void updateBlogTitle(String blogId, String title) {
		sqlSession.update("blog.updateBlogTitle", Map.of("blogId", blogId, "title", title));
	}

	public void updateBlogTitleAndLogoFile(String blogId, String title, String url) {
		sqlSession.update("blog.updateBlogTitleAndLogoFile", Map.of("blogId", blogId, "title", title, "url", url));
	}


}
