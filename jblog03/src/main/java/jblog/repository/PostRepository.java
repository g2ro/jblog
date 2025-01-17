package jblog.repository;

import java.util.List;
import java.util.Map;

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

	public List<PostVo> getPostVoList(String blogId, Integer path1) {
		return sqlSession.selectList("post.getPostVoList", Map.of("blogId", blogId, "path1", path1));

	}

	public List<PostVo> getDefaultCategoryPostVo(String blogId) {
		return sqlSession.selectList("post.getDefaultCategoryPostVo", blogId);
	}

	public PostVo getPostVoById(String blogId, Integer path1, Integer path2) {
		return sqlSession.selectOne("post.getPostVoById", Map.of("blogId", blogId, "path1", path1,"path2", path2));
	}
	
	
}
