package service.hmy;

import java.util.List;

import dto.Reservation;
import dto.UserPetInfo;

public interface DoctorCalendarService {
	List<Reservation> getReservationList(String startDate,String endDate, int userId);

	UserPetInfo getUserPetInfo(int reservationId);

	boolean updateReservationMemo(int reservationId, String memo);

}
