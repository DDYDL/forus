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
import dto.UserPetInfo;
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
		try {

			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			
			int userId = Integer.parseInt(request.getParameter("userId"));

			DoctorCalendarService doctorCalendarService = new DoctorCalendarServiceImpl();
			List<Reservation> reservationList = doctorCalendarService.getReservationList(startDate, endDate, userId);

			Gson gson = new Gson();
			String json = gson.toJson(reservationList);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (NumberFormatException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("잘못된 유저 ID입니다.");
			e.printStackTrace();

		} catch (NullPointerException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("잘못된 요청 처리 .");

		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("예약 목록을 가져오는 중 오류가 발생했습니다.");
			e.printStackTrace();
		}



	}
}
