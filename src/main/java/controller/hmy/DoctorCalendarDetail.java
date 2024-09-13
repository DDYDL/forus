package controller.hmy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.UserPetInfo;
import service.hmy.DoctorCalendarService;
import service.hmy.DoctorCalendarServiceImpl;

@WebServlet("/doctorCalendarDetail")
public class DoctorCalendarDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DoctorCalendarDetail() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int reservationId = Integer.parseInt(request.getParameter("reservationId"));

		DoctorCalendarService doctorCalendarService = new DoctorCalendarServiceImpl();
		UserPetInfo userPetInfo = doctorCalendarService.getUserPetInfo(reservationId);

		Gson gson = new Gson();
		String json = gson.toJson(userPetInfo);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);


	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		int reservationId = Integer.parseInt(request.getParameter("reservationId"));
		String memo = request.getParameter("memo");

		DoctorCalendarService doctorCalendarService = new DoctorCalendarServiceImpl();
		boolean isUpdated = doctorCalendarService.updateReservationMemo(reservationId, memo);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		if (isUpdated) {
			response.setStatus(HttpServletResponse.SC_OK); // 명시적으로 상태 코드를 200으로 설정
			response.getWriter().write("{\"message\": \"메모가 수정되었습니다.\"}"); // JSON 형식으로 응답
		} else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 에러 상태 코드 설정
			response.getWriter().write("{\"message\": \"메모 수정에 실패했습니다.\"}"); // JSON 형식으로 응답
		}
	}
}
