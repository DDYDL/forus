package service.hmy;

import javax.servlet.http.HttpServletRequest;

import dto.Hospital;

public interface HospitalService {
	void join(Hospital hospital) throws Exception;
	void hospitalNew (HttpServletRequest request) throws Exception;
	Hospital hmyHospital(Integer h_id) throws Exception;
	Integer hospitalModify(HttpServletRequest request)throws Exception;
	
}
