package controller.my;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.my.UserService;
import service.my.UserServiceImpl;

/**
 * Servlet implementation class UserDoubleId
 */
@WebServlet("/userDoubleEmail")
public class UserDoubleEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserDoubleEmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		System.out.println(email);
		try {
			UserService service = new UserServiceImpl();
			boolean doubleEmail = service.checkDoubleEmail(email);
			response.getWriter().write(String.valueOf(doubleEmail));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
