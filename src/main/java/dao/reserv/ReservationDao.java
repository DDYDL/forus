package dao.reserv;

import dto.Reservation;

public interface ReservationDao {
	void insertReserv(Reservation reserv) throws Exception;
	void updateReserv(Reservation reservation) throws Exception;
	void deleteReserv(Reservation reservation) throws Exception;
	Reservation selectReserv(Integer id) throws Exception;
	
	// 오늘 이후, 지난 예약 조회
	Reservation selectAllAfterReserv(Integer id) throws Exception;
	Reservation selectAllBeforeReserv(Integer id) throws Exception;
	
	// 지난 예약 검색 옵션(펫별, 진료완료, 기간별)
	Reservation selectPetReservList(Integer id) throws Exception;
	Reservation selectCompleteReservList(Integer id) throws Exception;
	Reservation selectPeriodReservList(Integer id) throws Exception;
}
