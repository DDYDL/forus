package dao.recruit;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.Recruit_post;
import util.MybatisSqlSessionFactory;

public class SearchDaoImpl implements SearchDao {
	private SqlSession sqlSession;
	public SearchDaoImpl() {
		sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
	}

	@Override
	public List<Recruit_post> selectRecruit_postList(Map<String, Object> map) throws Exception {
		return sqlSession.selectList("mapper.search.selectRecruit_postList", map);
	}

	@Override
	public Integer selectListCount(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne("mapper.search.selectListCount", map);
	}

}
