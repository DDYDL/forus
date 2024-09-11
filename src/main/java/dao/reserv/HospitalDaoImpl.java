package dao.reserv;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.Hospital;
import dto.Hospital_time;
import util.MybatisSqlSessionFactory;

public class HospitalDaoImpl implements HospitalDao {
	private SqlSession sqlSession;

	public HospitalDaoImpl() {
		sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
	}

	@Override
	public List<Hospital> findHospitalByLocation(Map<String, Double> map) {
		return sqlSession.selectList("mapper.hospital.findHospitalByLocation", map);

	}

	@Override
	public List<Hospital> findHospitalByKeyword(String keyword) {
		return sqlSession.selectList("mapper.hospital.findHospitalByKeyword", keyword);
	}

	@Override
	public Hospital findHospitalDetailByHospitalId(int hospitalId) {
		return sqlSession.selectOne("mapper.hospital.findHospitalDetailByHospitalId", hospitalId);
	}

	@Override
	public List<Hospital_time> findHospitalTimeByHospitalId(int hospitalId) {
		return sqlSession.selectList("mapper.hospital.findHospitalTimeByHospitalId", hospitalId);
	}

}
