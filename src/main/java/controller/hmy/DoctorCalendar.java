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

import com.google.gson.Gson;

import dto.Reservation;
import service.hmy.DoctorCalendarService;
import service.hmy.DoctorCalendarServiceImpl;

@WebServlet("/doctorCalendar")
public class DoctorCalendar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DoctorCalendar() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/hmy/calendar.jsp");
		dispatcher.forward(request, response);


	}
}
