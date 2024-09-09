package dao.reserv;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import dto.Reservation;
import dto.Hospital;
import dto.Hospital_time;

public interface ReservationDao {
	List<Hospital_time> findHospitalTimeByHospitalId(Map<String, Object> params);
	Hospital findHospitalLunchTimeAndIntervalByHospitalId(Integer hospitalId);
	List<LocalTime> findReservedTimesByDate(Map<String, Object> params);

	void insertReserv(Reservation reserv) throws Exception;
	void updateReserv(Reservation reservation) throws Exception;
	void deleteReserv(Reservation reservation) throws Exception;
	Reservation selectReserv(Integer id) throws Exception;
	
	// 오늘 이후, 지난 예약 조회
	List<Map<String, Object>> selectMyAfterReservList(Integer id) throws Exception;
	List<Map<String, Object>> selectMyBeforeReservList(Integer userId, String pet, String startDate, String endDate, boolean isConsult) throws Exception;
	
	// 지난 예약 검색 옵션(펫별, 진료완료, 기간별)
	List<Map<String, Object>> selectPetReservList(Integer id) throws Exception;
	List<Map<String, Object>> selectCompleteReservList(Integer id) throws Exception;
	List<Map<String, Object>> selectPeriodReservList(Integer id) throws Exception;
}