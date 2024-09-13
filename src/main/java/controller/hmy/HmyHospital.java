package controller.hmy;

import java.io.IOException;

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
 * Servlet implementation class HmyHospital
 */
@WebServlet("/hmyHospital")
public class HmyHospital extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HmyHospital() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		User user = (User) request.getSession().getAttribute("user"); // user 세션 가져오기

		int user_id = user.getId();

		try {
			// HospitalService 인스턴스 생성
			HospitalService service = new HospitalServiceImpl();

			// user_id를 사용해 hospital 테이블에서 hospital 정보 조회
			Hospital hospital = service.getHospitalByUserId(user_id);
			System.out.println("hospital new:"+hospital);

			// 조회한 Hospital 객체를 세션에 저장
			request.getSession().setAttribute("hospital", hospital);

			request.setAttribute("hospital", hospital);
			
			request.getRequestDispatcher("hmy/hmyhospital.jsp").forward(request, response);
			System.out.println(hospital);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "병원 정보 오류");
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}