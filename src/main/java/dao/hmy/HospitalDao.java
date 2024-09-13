package dao.hmy;

import java.util.List;
import java.util.Map;

import dto.Hospital;

public interface HospitalDao {
	void insertHospital(Hospital hospital) throws Exception;

	void updateHospital(Hospital hospital) throws Exception;

	Hospital selectHospital(Integer h_id) throws Exception;
	
	List<Map> selectReservationList(Integer h_id) throws Exception;

	Hospital selectHospitalByUserId(Integer user_id)throws Exception;
}
