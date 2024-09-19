package controller.hmy;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Hospital;
import dto.Hospital_time;
import dto.User;
import service.hmy.HospitalService;
import service.hmy.HospitalServiceImpl;

/**
 * Servlet implementation class HmyManagerReservationTime
 */
@WebServlet("/hmyManagerReservationTime")
public class HmyManagerReservationTime extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HmyManagerReservationTime() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		try {
			Hospital hospital = (Hospital)request.getSession().getAttribute("hospital");
			// HospitalService 인스턴스 생성
			HospitalService service = new HospitalServiceImpl();
			List<Hospital_time> hospitalTimeList = service.selectHospitalTimeList(hospital.getH_id());
			System.out.println(hospitalTimeList);
			request.setAttribute("hospitalTimeList", hospitalTimeList);
			request.getRequestDispatcher("/hmy/hmymanagereservationtime.jsp").forward(request, response);			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "정보 조회 오류");
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			System.out.println("진료시간 정보 수정");
			request.getRequestDispatcher("/hmy/hmyManagerReservation").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "진료시간 수정 오류");
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}
	}

}
