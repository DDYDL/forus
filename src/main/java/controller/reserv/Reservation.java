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

		try {
			int hospitalId = Integer.parseInt(Objects.requireNonNull(request.getParameter("hospitalId")));
			String selectedDate = request.getParameter("dateStr");

			ReservationService reservationService = new ReservationServiceImpl();
			Map<String, Object> availableTimeSlots = reservationService.getAvailableTimeSlots(hospitalId, selectedDate);

			Gson gson = new Gson();
			String jsonResponse = gson.toJson(availableTimeSlots);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonResponse);

		} catch (NumberFormatException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("잘못된 병원 ID입니다..");
			e.printStackTrace();

		}catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("요청 처리 중 오류가 발생했습니다");
			e.printStackTrace();

		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");

			String selectedDate = request.getParameter("selectedDate");
			String selectedTime = request.getParameter("selectedTime");
			String customContent = request.getParameter("customContent");
			String reservationContent = request.getParameter("reservationContent");
			String userId = request.getParameter("userId");
			String petId = request.getParameter("selectedPetId");
			String hospitalId = request.getParameter("hospitalId");

			ReservationService reservationService = new ReservationServiceImpl();

			dto.Reservation reservation = reservationService.createReservation(selectedDate, selectedTime,
				reservationContent, customContent, userId, petId, hospitalId);

			reservationService.insertReservation(reservation);

			// response.setContentType("text/html; charset=UTF-8");
			// response.getWriter().write("예약완료.");
			response.setStatus(HttpServletResponse.SC_OK);

		} catch (NumberFormatException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("잘못된 입력 형식입니다.");
			e.printStackTrace();

		}catch (Exception e){
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("예약 처리 중 오류가 발생했습니다.");
			e.printStackTrace();
		}


	}
}
