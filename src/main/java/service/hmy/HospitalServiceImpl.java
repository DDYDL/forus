package service.hmy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.hmy.HospitalDao;
import dao.hmy.HospitalDaoImpl;
import dto.Hospital;
import dto.Pet;
import dto.Reservation;
import dto.User;

public class HospitalServiceImpl implements HospitalService {

	private HospitalDao hospitalDao;

	public HospitalServiceImpl() {
		this.hospitalDao = new HospitalDaoImpl();
	}

	@Override
	public void insertHospital(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");

		String path = request.getServletContext().getRealPath("upload");
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
		hospital.setH_license(multi.getParameter("h_license"));
		hospital.setH_name(multi.getParameter("h_name"));
		hospital.setH_manager_name(multi.getParameter("h_manager_name"));
		hospital.setH_phone(multi.getParameter("h_phone"));
		hospital.setH_address(multi.getParameter("h_address"));
		// hospital.setH_longitude(multi.getParameter(""));
		// hospital.setH_latitude(multi.getParameter(""));
		hospital.setH_animal_type(multi.getParameter("h_animal_type"));
		hospital.setH_picture(multi.getParameter("h_picture"));
		hospital.setH_memo(multi.getParameter("h_memo"));
		hospital.setH_memo_road(multi.getParameter("h_memo_road"));
		hospital.setH_pay(multi.getParameter("h_pay"));
		hospital.setH_sns(multi.getParameter("h_sns"));
		// hospital.(Boolean)setH_parking(multi.getParameter(""));
		// hospital.setH_lunch_time_start(multi.getParameter(""));
		// hospital.setH_lunch_time_end(multi.getParameter(""));
		// hospital.setH_interval_time(multi.getParameter(""));
		System.out.println(hospital);;
		hospitalDao.insertHospital(hospital);

	}

	@Override
	public Integer hospitalModify(HttpServletRequest request) throws Exception {
		String path = request.getServletContext().getRealPath("upload");
		int size = 10 * 1024 * 1024;

		MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());

		Hospital hospital = new Hospital();
		hospital.setH_id(Integer.parseInt(multi.getParameter("h_id")));
		
		hospital.setH_num(Integer.parseInt(multi.getParameter("h_num")));

		// 파일 수정
		if (multi.getFile("file") != null) {
			hospital.setH_license(multi.getFilesystemName("file"));
		}

		hospitalDao.updateHospital(hospital);
		return hospital.getH_num();
	}

	@Override
	public Hospital hmyHospital(Integer h_id) throws Exception {
		Hospital hospital = hospitalDao.selectHospital(h_id);
		return hospital;

	}

	@Override
	public List<Reservation> getHospitalByUserid(Integer user_id) throws Exception {

		List<Reservation> reservList = hospitalDao.selectReservationList(user_id);
		if (reservList == null)
			throw new Exception("예약 없음");
		return reservList;
	}

}
