package dao.hmy;

import java.util.List;
import java.util.Map;

import dto.Reservation;
import dto.UserPetInfo;

public interface DoctorCalendarDao {

	Integer findHospitalIdByUserId(int userId);
	List<Reservation> findReservationsByDate(Map<String, Object> params);

	UserPetInfo findUserPetInfoByReservationId(int reservationId);

	void updateReservationMemo(int reservationId, String memo);

}