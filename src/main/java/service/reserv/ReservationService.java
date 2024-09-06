package service.reserv;

import java.util.List;
import java.util.Map;

import dto.Pet;
import dto.Reservation;
import dto.User;

public interface ReservationService {

	Map<String,Object> getAvailableTimeSlots(int hospitalId, String date);

	User getUserInfo(Integer userId);
	Pet getPetInfo(Integer petId);


	List<Reservation> myAfterReserv() throws Exception;
	// List<Reservation> myBeforeReserv(PageInfo pageInfo) throws Exception;
	Reservation reservDetail(Integer reserv_id) throws Exception;
}
