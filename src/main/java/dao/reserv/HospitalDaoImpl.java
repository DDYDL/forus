package dao.reserv;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.Hospital;
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
	public List<Hospital> findHospitalByAddress(String address) {
		return sqlSession.selectList("mapper.hospital.findHospitalByAddress", address);
	}
}
