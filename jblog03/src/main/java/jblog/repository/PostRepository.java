package jblog.repository;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.PostVo;

@Repository
public class PostRepository {
	private DataSource dataSource;
	private SqlSession sqlSession;
	
	public PostRepository(DataSource dataSource, SqlSession sqlSession) {
		this.dataSource = dataSource;
		this.sqlSession =sqlSession;
	}

	public void createWrite(PostVo vo) {
		sqlSession.insert("post.createWrite", vo);
	}
	
	
}
