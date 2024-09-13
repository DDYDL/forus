package service.hmy;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dto.Hospital;
import dto.Hospital_time;

public interface HospitalService {
	//작성한 병원 정보 db에 저장 
	void insertHospital(HttpServletRequest request) throws Exception;
	
	Hospital hmyHospital(Integer h_id) throws Exception;
	Integer hospitalModify(HttpServletRequest request)throws Exception;
	List<Map> selectReservationList(Integer h_id) throws Exception;

	Hospital HospitalDetail(Integer h_id) throws Exception;

	Hospital getHospitalByUserId(Integer user_id) throws Exception;
	
	Hospital_time htimetable(Integer h_id)throws Exception;

	
}
