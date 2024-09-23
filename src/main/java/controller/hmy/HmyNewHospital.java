package controller.hmy;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

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
		request.getRequestDispatcher("/hmy/hmynewhospital2.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");


		HospitalService service = new HospitalServiceImpl();
		try {

			String hid = request.getParameter("h_id");
			String newfilename = "hospital_" + hid;

			service.insertHospital(request);
			response.sendRedirect("hmyNewHospitalSuccess");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "등록 오류");
			request.getRequestDispatcher("err.jsp").forward(request, response);

		}
	}

}
