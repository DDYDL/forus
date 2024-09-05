package dao.hmy;

import dto.Hospital;

public interface HospitalDao {
	void insertHospital(Hospital hospital) throws Exception;

	void updateHospital(Hospital hospital) throws Exception;

}
