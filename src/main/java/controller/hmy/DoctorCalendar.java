package controller.hmy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dto.Reservation;
import dto.User;
import service.hmy.DoctorCalendarService;
import service.hmy.DoctorCalendarServiceImpl;

@WebServlet("/doctorCalendar")
public class DoctorCalendar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DoctorCalendar() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
		ServletException,
		IOException {
		try {
			HttpSession session = request.getSession(false);
			if(session == null || session.getAttribute("user") == null) {
				response.sendRedirect("/login");
				return;
			}

			User sessionUser = (User) session.getAttribute("user");
			if (sessionUser.getIshospital() != 1) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "이 페이지에 접근할 권한이 없습니다.");
				return;
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("/hmy/hmyscheduledcalendar.jsp");
			dispatcher.forward(request, response);

		} catch (ServletException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("서버 오류가 발생했습니다: JSP 페이지를 처리하는 중 오류가 발생했습니다.");
			e.printStackTrace();

		} catch (IOException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("서버 오류가 발생했습니다: 입력/출력 처리 중 오류가 발생했습니다.");
			e.printStackTrace();

		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("예기치 않은 오류가 발생했습니다.");
			e.printStackTrace();
		}

	}
}
