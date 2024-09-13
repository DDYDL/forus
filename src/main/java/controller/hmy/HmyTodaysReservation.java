package controller.hmy;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Hospital;
import dto.User;
import service.hmy.HospitalService;
import service.hmy.HospitalServiceImpl;

/**
 * Servlet implementation class HmyTodaysReservation
 */
@WebServlet("/hmyTodaysReservation")
public class HmyTodaysReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HmyTodaysReservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		User user = (User) request.getSession().getAttribute("user"); // user 세션 가져오기

		int user_id = user.getId();
		System.out.println("User ID: " + user_id);

		try {
			// HospitalService 인스턴스 생성
			HospitalService service = new HospitalServiceImpl();

			// user_id를 사용해 hospital 테이블에서 hospital 정보 조회
			Hospital hospital = service.getHospitalByUserId(user_id);

			// 조회한 Hospital 객체를 세션에 저장
			request.getSession().setAttribute("hospital", hospital);
			System.out.println("Hospital Info: " + hospital);

			Integer h_id = hospital.getH_id();
			List<Map> reservList = service.selectReservationList(h_id);
			System.out.println(reservList);
			request.setAttribute("reservList", reservList);

			
			request.getRequestDispatcher("/hmy/hmytodaysreservation.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e.getMessage());
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
