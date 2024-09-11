package controller.hmy;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Hospital;
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
		Integer h_id = Integer.parseInt(request.getParameter("h_id"));
		System.out.println(h_id);
		try {
			HospitalService service = new HospitalServiceImpl();

			Hospital hospital = service.hmyHospital(h_id);
			System.out.println(h_id);
			request.setAttribute("hospital", hospital);

			request.getRequestDispatcher("/hmy/hmyhospital.jsp").forward(request, response);

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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}