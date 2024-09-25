package dao.hmy;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.Hospital;
import dto.Hospital_time;
import dto.User;
import util.MybatisSqlSessionFactory;

public class HospitalDaoImpl implements HospitalDao {

	private SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public void insertHospital(Hospital hospital) throws Exception {
		sqlSession.insert("mapper.hospital.insertHospital", hospital);
		sqlSession.commit();

	}

	@Override
	public Hospital selectHospital(Integer h_id) throws Exception {
		return sqlSession.selectOne("mapper.hospital.selectHospital", h_id);
	}

	@Override
	public Hospital selectHospitaluser(Integer user_id) throws Exception {
		return sqlSession.selectOne("mapper.hospital.selectHospitaluser", user_id);
	}

	@Override
	public void updateHospital(Hospital hospital) throws Exception {
		sqlSession.update("mapper.hospital.updateHospital", hospital);
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

	@Override
	public List<Hospital_time> selectHospitalTime(Integer h_id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mapper.hospital_time.selectHospitalTime", h_id);
	}

	@Override
	public void updateHospitalIntevalTime(Integer h_id, Integer h_interval_time) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("h_id", h_id);
		param.put("h_interval_time", h_interval_time);
		sqlSession.update("mapper.hospital.updateHospitalIntevalTime", param);
		sqlSession.commit();
	}

	@Override
	public Hospital_time selectHospitalTimeByWeek(Integer h_id, Integer week_no) throws Exception {
		Map<String, Integer> param = new HashMap<>();
		param.put("h_id", h_id);
		param.put("week_no", week_no);
		return sqlSession.selectOne("mapper.hospital_time.selectHospitalTimeByWeek", param);
	}

	@Override
	public void updateHospitalTime(Hospital_time hospital_time) throws Exception {
		sqlSession.update("mapper.hospital_time.updateHospitalTime", hospital_time);
		sqlSession.commit();
	}

	@Override
	public void insertHospitalTime(Hospital_time hospital_time) throws Exception {
		sqlSession.insert("mapper.hospital_time.insertHospitalTime", hospital_time);
		sqlSession.commit();
	}

	@Override
	public void insertnewTime(Hospital_time hospital_time) throws Exception {
		sqlSession.insert("mapper.hospital_time.insertnewTime", hospital_time);
		sqlSession.commit();
	}

}
