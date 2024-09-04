package controller.my;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.User;
import service.my.UserService;
import service.my.UserServiceImpl;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String cookie = request.getHeader("Cookie");
		String autoLogin = null;
		String email = null;
		String password = null;
		if(cookie!=null) {
			Cookie[] cookies = request.getCookies();
			for(Cookie c : cookies) {
				if(c.getName().equals("autoLogin")) {
					autoLogin = c.getValue();
				}
				if(c.getName().equals("email")) {
					email = c.getValue();
				}
				if(c.getName().equals("password")) {
					password = c.getValue();
				}
			}
		}
		if(autoLogin!=null) {
			request.setAttribute("autoLogin", autoLogin);
			request.setAttribute("email", email);
			request.setAttribute("password", password);
		}
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String autoLogin = request.getParameter("autologin");
	
		try {
			UserService service = new UserServiceImpl();
			User user = service.login(email, password);
			user.setPassword("");
			request.getSession().setAttribute("user", user);
			if(autoLogin!=null) {
				Cookie cookieEmail = new Cookie("email", email);
				cookieEmail.setMaxAge(60*60*24);
				response.addCookie(cookieEmail);
				Cookie cookiePassword = new Cookie("password",password);
				cookiePassword.setMaxAge(60*60*24);
				response.addCookie(cookiePassword);
				Cookie cookieAutoLogin = new Cookie("autoLogin", autoLogin);
				cookieAutoLogin.setMaxAge(60*60*24);
				response.addCookie(cookieAutoLogin);
			} else {
				Cookie cookieEmail = new Cookie("email",null);
				cookieEmail.setMaxAge(0);
				response.addCookie(cookieEmail);
				Cookie cookiePassword = new Cookie("password",null);
				cookiePassword.setMaxAge(0);
				response.addCookie(cookiePassword);
				Cookie cookieAutoLogin = new Cookie("autoLogin",null);
				cookieAutoLogin.setMaxAge(0);
				response.addCookie(cookieAutoLogin);
			}
			request.getRequestDispatcher("reservhospitallist.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e.getMessage());
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}
	}

}
