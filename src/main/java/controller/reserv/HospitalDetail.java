package controller.reserv;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Hospital;
import dto.Hospital_qna;
import dto.Hospital_time;
import service.hmy.HospitalService;
import service.reserv.HospitalDetailService;
import service.reserv.HospitalQnaService;
import service.reserv.HospitalQnaServiceImpl;
import service.reserv.HospitalServiceImpl;

@WebServlet("/hospitalDetail")
public class HospitalDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HospitalDetail() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String hospitalId = request.getParameter("hospitalId");

			HospitalDetailService hospitalService = new HospitalServiceImpl();
			Hospital hospital = hospitalService.getHospitalDetailByHospitalId(Integer.parseInt(hospitalId));

			List<Hospital_time> hospitalTimes = hospitalService.getHospitalTimeByHospitalId(
				Integer.parseInt(hospitalId));

			HospitalQnaService hospitalQnaService = new HospitalQnaServiceImpl();
			Integer managerId = hospitalQnaService.getManagerIdByHospitalId(Integer.parseInt(hospitalId));

			request.setAttribute("managerId", managerId);
			request.setAttribute("hospital", hospital);
			request.setAttribute("hospitalTimes", hospitalTimes);
			request.getRequestDispatcher("/reserv/hospitaldetail.jsp").forward(request, response);

		} catch (NumberFormatException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("잘못된 형식의 병원 ID입니다.");
			e.printStackTrace();

		} catch (IllegalArgumentException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write(e.getMessage());
			e.printStackTrace();

		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("병원 상세 정보를 가져오는 중 오류가 발생했습니다.");
			e.printStackTrace();
		}
	}


}
