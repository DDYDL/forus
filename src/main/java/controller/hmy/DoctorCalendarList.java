package controller.hmy;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.Reservation;
import service.hmy.DoctorCalendarService;
import service.hmy.DoctorCalendarServiceImpl;

@WebServlet("/doctorCalendarList")
public class DoctorCalendarList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DoctorCalendarList() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		int userId = Integer.parseInt(request.getParameter("userId"));

		// String startDate = "2024-09-01";
		// String endDate = "2024-09-30";
		// int userId = 6;


		DoctorCalendarService doctorCalendarService = new DoctorCalendarServiceImpl();
		List<Reservation> reservationList = doctorCalendarService.getReservationList(startDate, endDate, userId);

		System.out.println("reservationList = " + reservationList);

		Gson gson = new Gson();
		String json = gson.toJson(reservationList);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);



	}
}
