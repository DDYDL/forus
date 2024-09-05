package service.hmy;

import javax.servlet.http.HttpServletRequest;

import dto.Hospital;

public interface HospitalService {
	void hospitalNew (HttpServletRequest request) throws Exception;
	Integer hospitalModify(HttpServletRequest request)throws Exception;
}
