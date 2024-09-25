package dao.hmy;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import dto.Hospital;
import dto.Hospital_time;
import dto.User;

public interface HospitalDao {
	void insertHospital(Hospital hospital) throws Exception;

	void updateHospital(Hospital hospital) throws Exception;

	Hospital selectHospital(Integer h_id) throws Exception;

	List<Map> selectReservationList(Integer h_id) throws Exception;

	Hospital selectHospitalByUserId(Integer user_id) throws Exception;

	List<Hospital_time> selectHospitalTime(Integer h_id) throws Exception;

	void updateHospitalIntevalTime(Integer h_id, Integer h_interval_time) throws Exception;

	Hospital_time selectHospitalTimeByWeek(Integer h_id, Integer week_no) throws Exception;

	void updateHospitalTime(Hospital_time hospital_time) throws Exception;

	void insertHospitalTime(Hospital_time hospital_time) throws Exception;

	void insertnewTime(Hospital_time hospital_time) throws Exception;
	
	Hospital selectHospitaluser(Integer user_id) throws Exception;
}
