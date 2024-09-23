package service.hmy;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dto.Hospital;
import dto.Hospital_time;

public interface HospitalService {
	// 작성한 병원 정보 db에 저장
	void insertHospital(HttpServletRequest request) throws Exception;

	Integer updatestatus(Integer id) throws Exception;

	Hospital hmyHospital(Integer h_id) throws Exception;

	Hospital hospitalModify(HttpServletRequest request) throws Exception;

	List<Map> selectReservationList(Integer h_id) throws Exception;

	Hospital HospitalDetail(Integer h_id) throws Exception;

	Hospital getHospitalByUserId(Integer user_id) throws Exception;

	Hospital_time htimetable(Integer h_id) throws Exception;

	List<Hospital_time> selectHospitalTimeList(Integer h_id) throws Exception;

	void modifyHospitalTime(Integer h_id, Integer h_interval_time, List<Hospital_time> htList) throws Exception;

	void insertHospitalTime(Integer h_id, Integer h_interval_time, List<Hospital_time> htList) throws Exception;
	
	void insertnewTime(Integer Hospita_time)throws Exception;
}
