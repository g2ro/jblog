package jblog.repository;

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


}
