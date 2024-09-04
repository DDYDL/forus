package dao;

import org.apache.ibatis.session.SqlSession;

import dto.User;
import util.MybatisSqlSessionFactory;

public class UserDaoImpl implements UserDao {

	private SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
	
	@Override
	public void insertUser(User user) throws Exception {
		User suser = selectUser(user.getEmail());
		if(suser!=null) throw new Exception("아이디 중복 오류");
		sqlSession.insert("mapper.user.insertUser", user);
		sqlSession.commit();
	}

	@Override
	public User selectUser(String email) throws Exception {
		return sqlSession.selectOne("mapper.user.selectUser",email);
	}

	@Override
	public void updateUser(User user) throws Exception {
		sqlSession.update("mapper.user.updateUser",user);
		sqlSession.commit();
	}

}
