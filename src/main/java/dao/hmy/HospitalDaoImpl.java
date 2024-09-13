package dao.hmy;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.Hospital;
import util.MybatisSqlSessionFactory;

public class HospitalDaoImpl implements HospitalDao {

	private SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public void insertHospital(Hospital hospital) throws Exception {
		sqlSession.insert("mapper.Hospital.insertHospital", hospital);
		sqlSession.commit();

	}

	@Override
	public Hospital selectHospital(Integer h_id) throws Exception {
		return sqlSession.selectOne("mapper.hospital.selectHospital", h_id);
	}

	@Override
	public void updateHospital(Hospital hospital) throws Exception {
		sqlSession.update("mapper.Hospital.updateHospital", hospital);
		sqlSession.commit();

	}

	@Override
	public List<Map> selectReservationList(Integer h_id) throws Exception {
		return sqlSession.selectList("mapper.reservation.reservList", h_id);
	}

	@Override
	public Hospital selectHospitalByUserId(Integer user_id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.hospital.selectHospitalByUserId", user_id);
	}

}
