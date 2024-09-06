package dao.reserv;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import dto.Hospital;
import dto.Hospital_time;

public interface ReservationDao {
	List<Hospital_time> findHospitalTimeByHospitalId(Map<String, Object> params);
	Hospital findHospitalLunchTimeAndIntervalByHospitalId(Integer hospitalId);
	List<LocalTime> findReservedTimesByDate(Map<String, Object> params);


}

