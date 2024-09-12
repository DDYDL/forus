package dao.recruit;

import java.util.List;
import java.util.Map;

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

	@Override
	public List<Recruit_apply> selectRecruit_applyList(Integer post_id) throws Exception {
		return sqlSession.selectList("mapper.recruit_apply.selectRecruit_applyList", post_id);
	}

	@Override
	public Recruit_apply selectRecruit_applyId(Integer apply_id) throws Exception {
		return  sqlSession.selectOne("mapper.recruit_apply.selectRecruit_applyId", apply_id);
	}

	@Override
	public void updateRecruit_apply_status(Recruit_apply apply) throws Exception {
		sqlSession.update("mapper.recruit_apply.updateRecruit_apply_status", apply);
		sqlSession.commit();
	}

	@Override
	public List<Map<String, Object>> selectApplyListByUserId(Integer user_id) throws Exception {
		return sqlSession.selectList("mapper.recruit_apply.selectApplyListByUserId", user_id);
	}
}
