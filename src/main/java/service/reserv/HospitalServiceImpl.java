package service.reserv;

import java.util.HashMap;
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
	public List<Hospital> getHospitalsByLocation(double latitude, double longitude, double radius, int offset, int limit) {
		Map<String, Object> map = new java.util.HashMap<>();
		map.put("latitude", latitude);
		map.put("longitude", longitude);
		map.put("radius", radius);

		map.put("offset", offset);
		map.put("limit", limit);

		return hospitalDao.findHospitalByLocation(map);
	}

	public List<Hospital> getHospitalsByKeyword(String keyword, int offset, int limit) {
		Map<String, Object> params = new HashMap<>();
		params.put("keyword", keyword);
		params.put("offset", offset);
		params.put("limit", limit);
		return hospitalDao.findHospitalByKeyword(params);
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
