package service.reserv;

import java.util.List;
import java.util.Map;

import dto.Pet;
import dto.Reservation;
import dto.User;

public interface ReservationService {

	Map<String,Object> getAvailableTimeSlots(int hospitalId, String date);

	User getUserInfo(Integer userId);
	List<Pet> getPetsInfo(Integer petId);

	void insertReservation(Reservation reservation);

	public Reservation createReservation(String selectedDate, String selectedTime, String reservationContent,
		String customContent, String userId, String petId, String hId);

	List<Map<String, Object>> myAfterReservList(Integer id) throws Exception;
	List<Map<String, Object>> selectMyBeforeReservList(Integer id, Integer pet_id, String startDate, String endDate, boolean isConsult) throws Exception;
	Reservation reservDetail(Integer reserv_id) throws Exception;
	
	Integer deleteReservation(int reserv_id)throws Exception;
}
