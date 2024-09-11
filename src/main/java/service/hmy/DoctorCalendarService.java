package service.hmy;

import java.util.List;

import dto.Reservation;

public interface DoctorCalendarService {
	List<Reservation> getReservationList(String startDate,String endDate, int userId);
}
