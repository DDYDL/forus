package service.reserv;

import java.util.List;
import java.util.Map;

import dao.reserv.HospitalDao;
import dao.reserv.HospitalDaoImpl;
import dto.Hospital;
import dto.Hospital_time;

public class HospitalServiceImpl implements HospitalListService, HospitalDetailService {
	private HospitalDao hospitalDao;

	public HospitalServiceImpl() {
		hospitalDao = new HospitalDaoImpl();
	}

	@Override
	public List<Hospital> getHospitalsByLocation(double latitude, double longitude, double radius) {
		Map<String, Double> map = new java.util.HashMap<>();
		map.put("latitude", latitude);
		map.put("longitude", longitude);
		map.put("radius", radius);

		return hospitalDao.findHospitalByLocation(map);
	}

	@Override
	public Hospital getHospitalDetailByHospitalId(int hospitalId) {
		return hospitalDao.findHospitalDetailByHospitalId(hospitalId);
	}

	@Override
	public List<Hospital_time> getHospitalTimeByHospitalId(int hospitalId) {
		return hospitalDao.findHospitalTimeByHospitalId(hospitalId);
	}

}
