package controller.reserv;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;



import dto.Hospital;
import service.reserv.HospitalListService;
import service.reserv.HospitalServiceImpl;

@WebServlet("/hospitalList")
public class HospitalList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HospitalList() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		// HospitalServiceImpl 객체 초기화
		HospitalListService hospitalService = new HospitalServiceImpl();


		String latStr = request.getParameter("latitude");
		String lonStr = request.getParameter("longitude");

		// 위도와 경도를 파싱할 때, 값이 null인 경우 기본값을 설정하거나 예외 처리
		double latitude = 33.450701; // 기본값
		double longitude = 126.570667; // 기본값
		double radius = 5.0;

		if (latStr != null && lonStr != null && !latStr.isEmpty() && !lonStr.isEmpty()) {
			try {
				latitude = Double.parseDouble(latStr);
				longitude = Double.parseDouble(lonStr);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}


		// 병원 데이터 조회
		List<Hospital> hospitals = hospitalService.getHospitalsByLocation(latitude, longitude, radius);
		String isAjax = request.getParameter("ajax");

		if ("true".equals(isAjax)) {
			// JSON 응답 설정
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			Gson gson = new Gson();
			String json = gson.toJson(hospitals);
			response.getWriter().write(json);
		} else {
			// 일반적인 페이지 로드로 JSP로 포워딩
			request.setAttribute("hospitals", hospitals);
			request.getRequestDispatcher("/reserv/hospitallist.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
