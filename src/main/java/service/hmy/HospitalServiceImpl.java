package service.hmy;

import java.io.File;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.hmy.HospitalDao;
import dao.hmy.HospitalDaoImpl;
import dao.my.UserDao;
import dao.my.UserDaoImpl;
import dto.Hospital;
import dto.Hospital_time;
import dto.User;

public class HospitalServiceImpl implements HospitalService {

	private HospitalDao hospitalDao;
	private UserDao userDao;

	public HospitalServiceImpl() {
		this.hospitalDao = new HospitalDaoImpl();
		this.userDao = new UserDaoImpl();
	}

	@Override
	public void insertHospital(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");

		String path = request.getServletContext().getRealPath("upload" + File.separator + "hospital");
		int size = 10 * 1024 * 1024;

		MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());

		Hospital hospital = new Hospital();

		hospital.setH_id(hospital.getH_id());

		request.setCharacterEncoding("utf-8");
		// request session의 인자 user
		User user = (User) request.getSession().getAttribute("user");
		// RecruitWriting에서 파라미터로 넘어온 value들을 지정
		hospital.setUser_id(user.getId());

		hospital.setH_exponent_name(multi.getParameter("h_exponent_name"));
		hospital.setH_num(Integer.parseInt(multi.getParameter("h_num")));
		hospital.setH_name(multi.getParameter("h_name"));
		hospital.setH_manager_name(multi.getParameter("h_manager_name"));
		hospital.setH_phone(multi.getParameter("h_phone"));
		hospital.setH_address(multi.getParameter("h_address"));

		hospital.setH_longitude(multi.getParameter("longitude"));
		hospital.setH_latitude(multi.getParameter("latitude"));

		String[] h_animal_types = multi.getParameterValues("h_animal_type");
		String h_animal_type = String.join(",", h_animal_types);
		hospital.setH_animal_type(h_animal_type);

		if (multi.getFile("file") != null) {
			System.out.println(multi.getFilesystemName("file"));
			hospital.setH_picture(multi.getFilesystemName("file"));
		}
		
		if (multi.getFile("h_license") != null) {
			hospital.setH_picture(multi.getFilesystemName("h_license"));
		}

		hospital.setH_memo(multi.getParameter("h_memo"));
		hospital.setH_memo_road(multi.getParameter("h_memo_road"));

		String[] h_pays = multi.getParameterValues("h_pay");
		String h_pay = String.join(",", h_pays);
		hospital.setH_pay(h_pay);

		hospital.setH_sns(multi.getParameter("h_sns"));
		hospital.setH_parking(Boolean.parseBoolean(multi.getParameter("h_parking")));
		hospital.setH_lunch_time_start(LocalTime.parse(multi.getParameter("h_lunch_time_start")));
		hospital.setH_lunch_time_end(LocalTime.parse(multi.getParameter("h_lunch_time_end")));

		System.out.println("hos1" + hospital);

		hospitalDao.insertHospital(hospital);
		userDao.ishospitalstatus(user);

		hospital = hospitalDao.selectHospitaluser(user.getId());
		System.out.println(hospital.getH_id());

		Integer hid = hospital.getH_id();
		System.out.println(hid);

		Hospital_time hospital_time = new Hospital_time();
		hospital_time.setH_id(hospital.getH_id());

		System.out.println("hos2" + hospital);

		hospitalDao.insertnewTime(hospital_time);
		System.out.println(hospital_time);
	}

	@Override
	public Hospital hospitalModify(HttpServletRequest request) throws Exception {
		String path = request.getServletContext().getRealPath("upload" + File.separator + "hospital");
		int size = 10 * 1024 * 1024;

		MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());

		Hospital hospital = new Hospital();
		hospital.setH_id(Integer.parseInt(multi.getParameter("h_id")));

		hospital.setH_exponent_name(multi.getParameter("h_exponent_name"));
		hospital.setH_num(Integer.parseInt(multi.getParameter("h_num")));
		hospital.setH_name(multi.getParameter("h_name"));
		hospital.setH_manager_name(multi.getParameter("h_manager_name"));
		hospital.setH_phone(multi.getParameter("h_phone"));
		hospital.setH_address(multi.getParameter("h_address"));

		/*
		 * hospital.setH_longitude(multi.getParameter("longitude"));
		 * hospital.setH_latitude(multi.getParameter("latitude"));
		 */

		String longitude = multi.getParameter("longitude");
		String latitude = multi.getParameter("latitude");

		if (longitude != null && !longitude.isEmpty()) {
			hospital.setH_longitude(longitude);
		}
		if (latitude != null && !latitude.isEmpty()) {
			hospital.setH_latitude(latitude);
		}
		
		System.out.println(longitude);
		System.out.println(latitude);

		String[] h_animal_types = multi.getParameterValues("h_animal_type");
		String h_animal_type = String.join(",", h_animal_types);
		hospital.setH_animal_type(h_animal_type);

		// hospital.setH_picture(multi.getParameter("h_picture"));
		hospital.setH_memo(multi.getParameter("h_memo"));
		hospital.setH_memo_road(multi.getParameter("h_memo_road"));

		String[] h_pays = multi.getParameterValues("h_pay");
		String h_pay = String.join(",", h_pays);

		hospital.setH_pay(h_pay);

		hospital.setH_sns(multi.getParameter("h_sns"));

		hospital.setH_parking(Boolean.parseBoolean(multi.getParameter("h_parking")));
		System.out.println("h_parking:" + Boolean.parseBoolean(multi.getParameter("h_parking")));
		hospital.setH_lunch_time_start(LocalTime.parse(multi.getParameter("h_lunch_time_start")));
		hospital.setH_lunch_time_end(LocalTime.parse(multi.getParameter("h_lunch_time_end")));
		System.out.println("set hospital:" + hospital);

		if (multi.getFile("file") != null) {
			hospital.setH_license(multi.getFilesystemName("file"));
		}

		if (multi.getFile("h_license") != null) {
			hospital.setH_picture(multi.getFilesystemName("h_license"));
		}

		hospitalDao.updateHospital(hospital);
		return hospital;
	}

	@Override
	public Hospital hmyHospital(Integer h_id) throws Exception {
		Hospital hospital = hospitalDao.selectHospital(h_id);
		return hospital;

	}

	@Override
	public Hospital selecthospitaluser(Integer user_id) throws Exception {
		Hospital hospital = hospitalDao.selectHospitaluser(user_id);
		// hId=;
		return hospital;
	}

	@Override
	public Hospital HospitalDetail(Integer h_id) throws Exception {
		Hospital hospital = hospitalDao.selectHospital(h_id);
		if (hospital == null)
			throw new Exception("병원 정보 오류");
		return hospital;
	}

	@Override
	public List<Map> selectReservationList(Integer h_id) throws Exception {

		return hospitalDao.selectReservationList(h_id);

	}

	@Override
	public Hospital getHospitalByUserId(Integer user_id) throws Exception {
		return hospitalDao.selectHospitalByUserId(user_id);
	}

	@Override
	public Hospital_time htimetable(Integer h_id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hospital_time> selectHospitalTimeList(Integer h_id) throws Exception {
		System.out.println(h_id);

		return hospitalDao.selectHospitalTime(h_id);
	}

	@Override
	public void modifyHospitalTime(Integer h_id, Integer h_interval_time, List<Hospital_time> htList) throws Exception {
		hospitalDao.updateHospitalIntevalTime(h_id, h_interval_time);

		for (Hospital_time ht : htList) {
			Hospital_time isHt = hospitalDao.selectHospitalTimeByWeek(h_id, ht.getWeek_no());
			if (isHt == null) {
				hospitalDao.insertHospitalTime(ht);
			} else {
				System.out.println(ht);
				hospitalDao.updateHospitalTime(ht);
			}
		}
	}

	@Override
	public void insertHospitalTime(Integer h_id, Integer h_interval_time, List<Hospital_time> htList) throws Exception {

	}

	@Override
	public Integer updatestatus(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertnewTime(Integer Hospita_time) throws Exception {
		// TODO Auto-generated method stub

	}

}
