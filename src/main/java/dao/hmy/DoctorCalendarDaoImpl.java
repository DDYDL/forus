package dao.hmy;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.Reservation;
import util.MybatisSqlSessionFactory;

public class DoctorCalendarDaoImpl implements DoctorCalendarDao{

	private SqlSession sqlSession;
	public DoctorCalendarDaoImpl() {
		sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
	}


	@Override
	public Integer findHospitalIdByUserId(int userId) {
		return sqlSession.selectOne("mapper.doctorCalendar.findHospitalIdByUserId", userId);
	}

	@Override
	public List<Reservation> findReservationsByDate(Map<String, Object> params) {
		return sqlSession.selectList("mapper.doctorCalendar.findReservationsByDate", params);
	}
}
