package service.reserv;

import java.util.Map;

public interface ReservationService {

	Map<String,Object> getAvailableTimeSlots(int hospitalId, String date);
}
