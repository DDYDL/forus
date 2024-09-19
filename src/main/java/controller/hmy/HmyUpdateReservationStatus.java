package controller.hmy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.hmy.DoctorCalendarService;
import service.hmy.DoctorCalendarServiceImpl;

@WebServlet("/updateReservationStatus")
public class HmyUpdateReservationStatus extends HttpServlet {
	private  static final long serialVersionUID = 1L;

	public HmyUpdateReservationStatus() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		int reservationId = Integer.parseInt(request.getParameter("reservationId"));
		String reservationStatus = request.getParameter("reservationStatus");

		try {
			System.out.println("reservationId = " + reservationId);
			System.out.println("reservationStatus = " + reservationStatus);

			DoctorCalendarService doctorCalendarService = new DoctorCalendarServiceImpl();
			boolean updatedReservationStatus = doctorCalendarService.updateReservationStatus(reservationId, reservationStatus);

			if (!updatedReservationStatus) {
				throw new RuntimeException("예약 상태 수정에 실패했습니다.");
			}
			response.getWriter().write("success");
		}catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("fail");
		}


	}
}
