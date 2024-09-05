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
@WebServlet("/hmyNewHospital")
public class HmyNewHospital extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HmyNewHospital() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/hmy/hmyNewHospital.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Hospital hospital = new Hospital();
		hospital.setH_id(Integer.parseInt(request.getParameter("h_id")));
		hospital.setUser_id(Integer.parseInt(request.getParameter("user_id")));
		hospital.setH_exponent_name(request.getParameter("h_exponent_name"));
		hospital.setH_num(Integer.parseInt(request.getParameter("h_num")));
		hospital.setH_license(request.getParameter("h_license"));
		hospital.setH_name(request.getParameter("h_name"));
		hospital.setH_manager_name(request.getParameter("manager_name"));
		hospital.setH_phone(request.getParameter("h_phone"));
		hospital.setH_address(request.getParameter("h_address"));
		hospital.setH_picture(request.getParameter("h_picture"));
		hospital.setH_memo(request.getParameter("h_memo"));
		hospital.setH_memo_road(request.getParameter("h_memo_road"));
		// hospital.setH_pay(request.getParameterValues("h_pay"));
		hospital.setH_sns(request.getParameter("h_sns"));
		// hospital.setH_parking(request.getParameter("h_parking"));
		// hospital.setH_isopen(request.getParameter("h_isopen"));
		// hospital.setH_lunch_time_start(request.getParameter("h_lunch_time_start"));
		// hospital.setH_lunch_time_end(request.getParameter("h_lunch_time_end"));
		// hospital.setH_interval_time(request.getParameter("h_interval_time"));

		try {
			HospitalService service = new HospitalServiceImpl();
			service.join(hospital);
			response.sendRedirect("success");

		} catch (Exception e) { //?
			e.printStackTrace();
			request.setAttribute("err", e.getMessage());
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}

	}

}
