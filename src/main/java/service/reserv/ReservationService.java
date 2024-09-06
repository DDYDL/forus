package service.reserv;

import java.util.List;
import java.util.Map;
import dto.Reservation;

public interface ReservationService {

	Map<String,Object> getAvailableTimeSlots(int hospitalId, String date);

	List<Map<String, Object>> myAfterReservList(Integer id) throws Exception;
	// List<Reservation> myBeforeReserv(PageInfo pageInfo) throws Exception;
	Reservation reservDetail(Integer reserv_id) throws Exception;
}
