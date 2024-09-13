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
 * Servlet implementation class HmyHospitalModify
 */
@WebServlet("/hmyHospitalModify")
public class HmyHospitalModify extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HmyHospitalModify() {
		super();
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
			HospitalService service = new HospitalServiceImpl();
			Hospital hospital = service.getHospitalByUserId(user_id);
			request.getSession().setAttribute("hospital", hospital);

			request.setAttribute("hospital", hospital);

			request.getRequestDispatcher("hmy/hmyhospitalmodify.jsp").forward(request, response);
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
		request.setCharacterEncoding("utf-8");
		User user = (User) request.getSession().getAttribute("user"); // user 세션 가져오기

		int user_id = user.getId();
		System.out.println("User ID: " + user_id);
		try {
			System.out.println("병원 정보 수정");
			HospitalService service = new HospitalServiceImpl();
			Integer hospital = service.hospitalModify(request);
			System.out.println("ss");
			request.setAttribute("hospital", hospital);

			// 수정 완료 후 다시 수정 폼으로 리디렉션
			response.sendRedirect("./hmyHospital");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "병원 정보 오류");
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}
	}
}
