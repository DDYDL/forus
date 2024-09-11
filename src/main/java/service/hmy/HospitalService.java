package service.hmy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Hospital;
import dto.Reservation;

public interface HospitalService {
	//작성한 병원 정보 db에 저장 
	void insertHospital(HttpServletRequest request) throws Exception;
	
	Hospital hmyHospital(Integer h_id) throws Exception;
	Integer hospitalModify(HttpServletRequest request)throws Exception;
	List<Reservation> getHospitalByUserid(Integer user_id) throws Exception;
	
}
