package service.hmy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import dao.hmy.DoctorCalendarDao;
import dao.hmy.DoctorCalendarDaoImpl;
import dao.reserv.HospitalQnaDao;
import dao.reserv.HospitalQnaDaoImpl;
import dto.Reservation;
import dto.UserPetInfo;

public class DoctorCalendarServiceImpl implements DoctorCalendarService{

	private DoctorCalendarDao doctorCalendarDao;

	public DoctorCalendarServiceImpl() {
		doctorCalendarDao = new DoctorCalendarDaoImpl();
	}
	@Override
	public List<Reservation> getReservationList(String startDate, String endDate, int userId) {

		Integer hospitalId = doctorCalendarDao.findHospitalIdByUserId(userId);

		Map<String, Object> params = new HashMap<>();
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("hospitalId", hospitalId);

		return doctorCalendarDao.findReservationsByDate(params);
	}

	@Override
	public UserPetInfo getUserPetInfo(int reservationId) {
		return doctorCalendarDao.findUserPetInfoByReservationId(reservationId);
	}

	@Override
	public boolean updateReservationMemo(int reservationId, String memo) {
		try {
			doctorCalendarDao.updateReservationMemo(reservationId, memo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateReservationStatus(Integer reservationId, String reservationStatus) {
		try {
			doctorCalendarDao.updateReservationStatus(reservationId, reservationStatus);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
