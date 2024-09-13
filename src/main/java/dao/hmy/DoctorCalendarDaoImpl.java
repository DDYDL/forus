package dao.hmy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.Reservation;
import dto.UserPetInfo;
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

	@Override
	public UserPetInfo findUserPetInfoByReservationId(int reservationId) {
		return sqlSession.selectOne("mapper.doctorCalendar.findUserPetInfoByReservationId", reservationId);
	}

	@Override
	public void updateReservationMemo(int reservationId, String memo) {
		try {
			Map<String,Object>params = new HashMap<>();
			params.put("reservationId", reservationId);
			params.put("memo", memo);

			int updated = sqlSession.update("mapper.doctorCalendar.updateReservationMemo", params);

			if (updated == 0) {
				throw new RuntimeException("예약 메모 수정에 실패했습니다.");
			}
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		finally {
			sqlSession.close();
		}

	}
}
