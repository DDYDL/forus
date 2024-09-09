package servicehmy;

import dto.hospital;

public interface HospitalService {
	void join(hospital hos) throws Exception;
	void login(String id, String password) throws Exception;

}
