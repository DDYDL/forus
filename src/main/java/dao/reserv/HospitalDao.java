package dao.reserv;

import java.util.List;
import java.util.Map;

import dto.Hospital;
import dto.Hospital_time;

public interface HospitalDao {

	List<Hospital> findHospitalByLocation(Map<String, Double> map);

	List<Hospital> findHospitalByKeyword(String keyword);

	Hospital findHospitalDetailByHospitalId(int hospitalId);

	//시간 가져오는 메소드
	List<Hospital_time> findHospitalTimeByHospitalId(int hospitalId);

}
