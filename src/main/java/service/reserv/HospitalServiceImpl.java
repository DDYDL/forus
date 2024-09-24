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
		// 매개변수로 받은 데이터를 맵에 저장
		Map<String, Object> map = new HashMap<>();
		map.put("latitude", latitude);
		map.put("longitude", longitude);
		map.put("radius", radius);
		map.put("offset", offset);
		map.put("limit", limit);

		// 병원 목록을 조회
		List<Hospital> hospitals = hospitalDao.findHospitalByLocation(map);

		processHospitalImages(hospitals);

		return hospitals;
	}

	public List<Hospital> getHospitalsByKeyword(String keyword, int offset, int limit) {
		// 매개변수로 받은 데이터를 맵에 저장
		Map<String, Object> params = new HashMap<>();
		params.put("keyword", keyword);
		params.put("offset", offset);
		params.put("limit", limit);

		// 병원 목록을 조회
		List<Hospital> hospitals = hospitalDao.findHospitalByKeyword(params);

		processHospitalImages(hospitals);

		return hospitals;
	}

	private void processHospitalImages(List<Hospital> hospitals) {
		for (Hospital hospital : hospitals) {
			String firstImage = extractFirstImage(hospital.getH_picture());  // 첫 번째 이미지 추출
			hospital.setH_picture(firstImage);
		}
	}
	private String extractFirstImage(String h_picture) {
		if (h_picture != null && !h_picture.isEmpty()) {
			String[] pictures = h_picture.split(",");
			return pictures[0];  // 첫 번째 이미지를 반환
		}
		return "hospitaldefault.png";
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
