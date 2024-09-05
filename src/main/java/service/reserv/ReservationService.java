package service.reserv;

import java.util.List;
import dto.Reservation;

public interface ReservationService {
	List<Reservation> myAfterReserv() throws Exception;
	// List<Reservation> myBeforeReserv(PageInfo pageInfo) throws Exception;
	Reservation reservDetail(Integer reserv_id) throws Exception;
}
