package controller.my;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.User;
import service.my.UserService;
import service.my.UserServiceImpl;

/**
 * Servlet implementation class MyProfileModify
 */
@WebServlet("/myProfileModify")
public class MyProfileModify extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyProfileModify() {
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
		Integer id = Integer.parseInt(request.getParameter("id"));

		System.out.println(id);

		try {
			UserService service = new UserServiceImpl();
			User user = service.userDetail(id);
			request.setAttribute("user", user);
			request.getRequestDispatcher("/my/myProfileModify.jsp").forward(request, response);
		} catch (Exception e) {
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
		try {
			HttpSession session = request.getSession();
			User suser = (User)session.getAttribute("user");
			String newfilename = "user_"+suser.getId();
			
			UserService service = new UserServiceImpl();
			Integer num = service.userModify(request, newfilename);
			User user = service.userDetail(num);
			
			request.setAttribute("user", user);
			request.getRequestDispatcher("/myProfile").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "프로필 수정 오류");
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}
	}

}
