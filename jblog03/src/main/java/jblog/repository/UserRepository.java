package jblog.repository;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.UserVo;

@Repository
public class UserRepository {
	
	private DataSource dataSource;
	private SqlSession sqlSession;
	
	public UserRepository(SqlSession sqlSession, DataSource dataSource) {
		this.dataSource = dataSource;
		this.sqlSession = sqlSession;
	}
	
	public int insert(UserVo vo) {
		return sqlSession.insert("user.insert", vo);
	}
	
	public UserVo findByIdAndPassword(String id, String password) {
		return sqlSession.selectOne("user.findByIdAndPassword", Map.of("id", id, "password", password));
	}

	public UserVo getUserById(String blogId) {
		return sqlSession.selectOne("user.getUserById", blogId);
	}
}
