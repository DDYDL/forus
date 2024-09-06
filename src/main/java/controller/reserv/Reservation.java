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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 폼 데이터 가져오기
		String selectedDate = request.getParameter("selectedDate");
		String selectedTime = request.getParameter("selectedTime");
		String reservationItem = request.getParameter("reservationItem");
		String customItem = request.getParameter("customItem");
		String petName = request.getParameter("petName");
		String userName = request.getParameter("userName");
		String userId = request.getParameter("userId");
		String petId = request.getParameter("petId");


		// 넘어온 데이터 출력 (콘솔에서 확인)
		System.out.println("Selected Date: " + selectedDate);
		System.out.println("Selected Time: " + selectedTime);
		System.out.println("Reservation Item: " + reservationItem);
		System.out.println("Custom Item: " + customItem);
		System.out.println("Pet Name: " + petName);
		System.out.println("User Name: " + userName);
		System.out.println("User Id: " + userId);
		System.out.println("Pet Id: " + petId);

		// 클라이언트로 간단한 응답을 보냄
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write("데이터가 정상적으로 전달되었습니다.");


	}
}
