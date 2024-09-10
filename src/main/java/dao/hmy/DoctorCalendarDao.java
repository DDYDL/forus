package dao.hmy;

import java.util.List;
import java.util.Map;

import dto.Reservation;

public interface DoctorCalendarDao {

	Integer findHospitalIdByUserId(int userId);
	List<Reservation> findReservationsByDate(Map<String, Object> params);


}