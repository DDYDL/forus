package controller.hmy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Hospital;
import dto.User;
import service.hmy.HospitalService;
import service.hmy.HospitalServiceImpl;
import service.my.UserService;
import service.my.UserServiceImpl;

/**
 * Servlet implementation class HmyNewHospitalSuccess
 */
@WebServlet("/hmyNewHospitalSuccess")
public class HmyNewHospitalSuccess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HmyNewHospitalSuccess() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			User puser = (User) request.getSession().getAttribute("user"); // user 세션 가져오기

			UserService uservice = new UserServiceImpl();
			User user = uservice.userDetail(puser.getId());

			HospitalService service = new HospitalServiceImpl();

			Hospital hospital = service.getHospitalByUserId(user.getId());

			HttpSession session = request.getSession();
			session.setAttribute("hospital", hospital);
			session.setAttribute("user", user);

			request.getRequestDispatcher("/hmy/hmynewhospitalsuccess.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
