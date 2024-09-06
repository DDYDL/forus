package service.reserv;

import java.util.List;

import dto.Hospital;
import dto.Hospital_time;

public interface HospitalDetailService {
	Hospital getHospitalDetailByHospitalId(int hospitalId);
	List<Hospital_time> getHospitalTimeByHospitalId(int hospitalId);



}
