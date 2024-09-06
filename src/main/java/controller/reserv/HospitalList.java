package controller.reserv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import controller.reserv.check.StringNullCheck;

import dto.Hospital;
import service.reserv.HospitalListService;
import service.reserv.HospitalServiceImpl;

@WebServlet("/hospitalList")
public class HospitalList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final double DEFAULT_LATITUDE = 33.450701;
	private static final double DEFAULT_LONGITUDE = 126.570667;
	private static final double DEFAULT_RADIUS = 5.0;

	public HospitalList() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HospitalListService hospitalService = new HospitalServiceImpl();
		String latStr = request.getParameter("latitude");
		String lonStr = request.getParameter("longitude");

		// 초기값 설정
		double latitude = DEFAULT_LATITUDE;
		double longitude = DEFAULT_LONGITUDE;
		double radius = DEFAULT_RADIUS;

		List<Hospital> hospitals = new ArrayList<>();


		if (StringNullCheck.isNotEmpty(latStr) && StringNullCheck.isNotEmpty(lonStr)) {
			try {
				latitude = Double.parseDouble(latStr);
				longitude = Double.parseDouble(lonStr);

				// 병원 데이터 조회
				hospitals = hospitalService.getHospitalsByLocation(latitude, longitude, radius);

			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

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
