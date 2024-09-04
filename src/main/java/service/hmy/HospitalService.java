package service.hmy;

import dto.Hospital;

public interface HospitalService {
	void join(Hospital hos) throws Exception;

	void login(String id, String password) throws Exception;

}
