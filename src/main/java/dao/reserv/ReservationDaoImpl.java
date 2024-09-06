package dao.reserv;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.Hospital;
import dto.Hospital_time;
import util.MybatisSqlSessionFactory;

public class ReservationDaoImpl implements ReservationDao {
	private SqlSession sqlSession;

	public ReservationDaoImpl() {
		sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
	}


	@Override
	public List<Hospital_time> findHospitalTimeByHospitalId(Map<String, Object> params) {
		// 병원의 영업 시간을 가져옴
		return sqlSession.selectList("mapper.reservation.findHospitalTimeByHospitalId", params);
	}

	@Override
	public Hospital findHospitalLunchTimeAndIntervalByHospitalId(Integer hospitalId) {
		// 병원의 점심시간과 예약 간격을 가져옴
		return sqlSession.selectOne("mapper.reservation.findHospitalLunchTimeAndIntervalByHospitalId", hospitalId);
	}

	@Override
	public List<LocalTime> findReservedTimesByDate(Map<String, Object> params) {
		// 예약된 시간을 리스트로 가져옴
		return sqlSession.selectList("mapper.reservation.findReservedTimesByDate", params);
	}
}
