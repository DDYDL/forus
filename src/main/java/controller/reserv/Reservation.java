package controller.reserv;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.reserv.ReservationService;
import service.reserv.ReservationServiceImpl;

@WebServlet("/reservation")
public class Reservation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Reservation() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
		ServletException,
		IOException {

		int hospitalId = Integer.parseInt(Objects.requireNonNull(request.getParameter("hospitalId")));
		String selectedDate = request.getParameter("dateStr");


		ReservationService reservationService = new ReservationServiceImpl();

		Map<String, Object> availableTimeSlots = reservationService.getAvailableTimeSlots(hospitalId, selectedDate);

		Gson gson = new Gson();
		String jsonResponse = gson.toJson(availableTimeSlots);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonResponse);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
