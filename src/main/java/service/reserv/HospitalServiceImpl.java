package service.reserv;

import java.util.List;
import java.util.Map;

import dao.reserv.HospitalDao;
import dao.reserv.HospitalDaoImpl;
import dto.Hospital;

public class HospitalServiceImpl implements HospitalListService{
	private HospitalDao hospitalDao;
	public HospitalServiceImpl() {
		hospitalDao = new HospitalDaoImpl();
	}



	@Override
	public List<Hospital> getHospitalsByLocation(double latitude, double longitude, double radius) {
		//값 출력
		System.out.println("latitude : " + latitude);
		System.out.println("longitude : " + longitude);
		System.out.println("radius : " + radius);

		Map<String , Double> map = new java.util.HashMap<>();
		map.put("latitude",latitude);
		map.put("longitude",longitude);
		map.put("radius",radius);


		return hospitalDao.findHospitalByLocation(map);
	}

	@Override
	public List<Hospital> getHospitalsByAddress(String address) {
		return hospitalDao.findHospitalByAddress(address);
	}
}
