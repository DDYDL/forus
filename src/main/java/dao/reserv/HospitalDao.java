package dao.reserv;

import java.util.List;
import java.util.Map;

import dto.Hospital;

public interface HospitalDao {

	List<Hospital> findHospitalByLocation(Map<String, Double> map);

	List<Hospital> findHospitalByAddress(String address);


}
