package dao.recruit;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.Recruit_apply;
import util.MybatisSqlSessionFactory;

public class RecruitApplyDaoImpl implements RecruitApplyDao {
	private SqlSession sqlSession;
	public RecruitApplyDaoImpl() {
		sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
	}
	
	@Override
	public void insertRecruit_apply(Recruit_apply apply) throws Exception {
		sqlSession.insert("mapper.recruit_apply.insertRecruit_apply", apply);
		sqlSession.commit();
	}

	@Override
	public List<Recruit_apply> selectRecruit_apply(Integer user_id) throws Exception {
		return sqlSession.selectList("mapper.recruit_apply.selectRecruit_apply", user_id);
	}

}
