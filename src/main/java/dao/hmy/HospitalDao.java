package dao.hmy;

import java.util.List;

import dto.Hospital;
import dto.Reservation;

public interface HospitalDao {
	void insertHospital(Hospital hospital) throws Exception;

	void updateHospital(Hospital hospital) throws Exception;

	Hospital selectHospital(Integer h_id) throws Exception;
	
	List<Reservation> selectReservationList(Integer user_id) throws Exception;
}
