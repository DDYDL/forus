package dao.reserv;


import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;


import dto.Pet;
import dto.Reservation;
import dto.Hospital;
import dto.Hospital_time;
import dto.User;
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

	@Override
	public User findUserInfoByUserId(Integer userId) {
		return sqlSession.selectOne("mapper.reservation.findUserInfoByUserId", userId);
	}

	@Override
	public List<Pet> findPetsByUserId(Integer userId) {
		return sqlSession.selectList("mapper.reservation.findPetInfoByUserId", userId);
	}

	@Override
	public void insertReservation(Reservation reservation) {
		try {
			sqlSession.insert("mapper.reservation.insertReservation", reservation);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();

		}

	}


	@Override
	public void insertReserv(Reservation reserv) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateReserv(Reservation reservation) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteReserv(Reservation reservation) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Reservation selectReserv(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> selectMyAfterReservList(Integer id) throws Exception {
		List<Map<String, Object>> afterReservList = sqlSession.selectList("mapper.reservation.selectMyAfterReserv",id);
		return afterReservList;
	}

	@Override
	public List<Map<String, Object>> selectMyBeforeReservList(Integer id, Integer pet_id, String startDate, String endDate, boolean isConsult) throws Exception {
		Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("pet_id", pet_id);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        params.put("isConsult", isConsult);

        return sqlSession.selectList("mapper.reservation.selectMyBeforeReserv", params);
	}

	@Override
	public List<Map<String, Object>> selectPetReservList(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> selectCompleteReservList(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> selectPeriodReservList(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteReserv(int reserv_id) throws Exception {
		int deleteReserv = sqlSession.delete("mapper.pet.deleteReservation", reserv_id);
		sqlSession.commit();
		return deleteReserv;
	}

}
