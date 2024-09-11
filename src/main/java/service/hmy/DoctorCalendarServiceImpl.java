package service.hmy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import dao.hmy.DoctorCalendarDao;
import dao.hmy.DoctorCalendarDaoImpl;
import dao.reserv.HospitalQnaDao;
import dao.reserv.HospitalQnaDaoImpl;
import dto.Reservation;

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
}
