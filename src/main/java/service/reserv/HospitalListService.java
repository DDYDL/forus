package service.reserv;

import java.util.List;

import dto.Hospital;

public interface HospitalListService {
	List<Hospital> getHospitalsByLocation(double lat, double lon, double radius);



}
