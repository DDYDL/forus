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
	private static final int DEFAULT_LIMIT = 5;

	public HospitalList() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HospitalListService hospitalService = new HospitalServiceImpl();
			String latStr = request.getParameter("latitude");
			String lonStr = request.getParameter("longitude");
			String keyword = request.getParameter("keyword");
			String pageStr = request.getParameter("page");

			int page = pageStr != null ? Integer.parseInt(pageStr) : 1;
			int offset = (page - 1) * DEFAULT_LIMIT;

			double latitude = DEFAULT_LATITUDE;
			double longitude = DEFAULT_LONGITUDE;
			double radius = DEFAULT_RADIUS;


			System.out.println("요청 페이지: " + page);
			System.out.println("계산된 OFFSET: " + offset);
			System.out.println("요청 위도: " + latStr);
			System.out.println("요청 경도: " + lonStr);


			List<Hospital> hospitals = new ArrayList<>();

			if (StringNullCheck.isNotEmpty(keyword)) {
				System.out.println("키워드로 검색 - OFFSET: " + offset + ", LIMIT: " + DEFAULT_LIMIT);
				hospitals = hospitalService.getHospitalsByKeyword(keyword, offset, DEFAULT_LIMIT);

			} else if (StringNullCheck.isNotEmpty(latStr) && StringNullCheck.isNotEmpty(lonStr)) {
				latitude = Double.parseDouble(latStr);
				longitude = Double.parseDouble(lonStr);

				System.out.println("쿼리 실행 전 - 위도: " + latitude + ", 경도: " + longitude + ", 반경: " + radius + ", OFFSET: " + offset + ", LIMIT: " + DEFAULT_LIMIT);

				hospitals = hospitalService.getHospitalsByLocation(latitude, longitude, radius, offset, DEFAULT_LIMIT);

				System.out.println("쿼리 결과 개수: " + hospitals.size());

			}

			String isAjax = request.getParameter("ajax");
			if ("true".equals(isAjax)) {
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				Gson gson = new Gson();
				String json = gson.toJson(hospitals);
				response.getWriter().write(json);
			} else {
				request.setAttribute("hospitals", hospitals);
				request.getRequestDispatcher("/reserv/hospitallist.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace(); // 서버 로그에 예외를 기록합니다.
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("서버 내부 오류가 발생했습니다: " + e.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
