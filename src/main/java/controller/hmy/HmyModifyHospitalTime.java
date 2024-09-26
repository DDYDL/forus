package controller.hmy;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Hospital;
import dto.Hospital_time;
import service.hmy.HospitalService;
import service.hmy.HospitalServiceImpl;

/**
 * Servlet implementation class HmyModifyHospitalTime
 */
@WebServlet("/modifyHospitalTime")
public class HmyModifyHospitalTime extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HmyModifyHospitalTime() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		try {
			Hospital hospital = (Hospital) request.getSession().getAttribute("hospital");
			if (hospital == null)
				throw new Exception("병원 관계자가 아닙니다.");
			Integer intervalTime = Integer.parseInt(request.getParameter("intervalTime"));
			List<Hospital_time> htList = new ArrayList<>();

			String mon = request.getParameter("mon");
			Hospital_time monH = new Hospital_time();
			monH.setH_id(hospital.getH_id());
			monH.setWeek_no(1);
			if (mon == null) {
				monH.setHtime_isable(false);
			} else {
				monH.setHtime_isable(true);
				String htimeOpening = request.getParameter("htime_opening_mon");
				String htimeClosing = request.getParameter("htime_closing_mon");
				monH.setHtime_opening(LocalTime.parse(htimeOpening));
				monH.setHtime_closing(LocalTime.parse(htimeClosing));
			}
			htList.add(monH);

			String tue = request.getParameter("tue");
			Hospital_time tueH = new Hospital_time();
			tueH.setWeek_no(2);
			tueH.setH_id(hospital.getH_id());
			if (tue == null) {
				tueH.setHtime_isable(false);
			} else {
				tueH.setHtime_isable(true);
				String htimeOpening = request.getParameter("htime_opening_tue");
				String htimeClosing = request.getParameter("htime_closing_tue");
				tueH.setHtime_opening(LocalTime.parse(htimeOpening));
				tueH.setHtime_closing(LocalTime.parse(htimeClosing));
			}
			htList.add(tueH);

			String wed = request.getParameter("wed");
			Hospital_time wedH = new Hospital_time();
			wedH.setWeek_no(3);
			wedH.setH_id(hospital.getH_id());
			if (wed == null) {
				wedH.setHtime_isable(false);
			} else {
				wedH.setHtime_isable(true);
				String htimeOpening = request.getParameter("htime_opening_wed");
				String htimeClosing = request.getParameter("htime_closing_wed");
				wedH.setHtime_opening(LocalTime.parse(htimeOpening));
				wedH.setHtime_closing(LocalTime.parse(htimeClosing));
			}
			htList.add(wedH);

			String thur = request.getParameter("thur");
			Hospital_time thurH = new Hospital_time();
			thurH.setWeek_no(4);
			thurH.setH_id(hospital.getH_id());
			if (thur == null) {
				thurH.setHtime_isable(false);
			} else {
				thurH.setHtime_isable(true);
				String htimeOpening = request.getParameter("htime_opening_thur");
				String htimeClosing = request.getParameter("htime_closing_thur");
				thurH.setHtime_opening(LocalTime.parse(htimeOpening));
				thurH.setHtime_closing(LocalTime.parse(htimeClosing));
			}
			htList.add(thurH);

			String fri = request.getParameter("fri");
			Hospital_time friH = new Hospital_time();
			friH.setWeek_no(5);
			friH.setH_id(hospital.getH_id());
			if (fri == null) {
				friH.setHtime_isable(false);
			} else {
				friH.setHtime_isable(true);
				String htimeOpening = request.getParameter("htime_opening_fri");
				String htimeClosing = request.getParameter("htime_closing_fri");
				friH.setHtime_opening(LocalTime.parse(htimeOpening));
				friH.setHtime_closing(LocalTime.parse(htimeClosing));
			}
			htList.add(friH);

			String sat = request.getParameter("sat");
			Hospital_time satH = new Hospital_time();
			satH.setWeek_no(6);
			satH.setH_id(hospital.getH_id());
			if (sat == null) {
				satH.setHtime_isable(false);
			} else {
				satH.setHtime_isable(true);
				String htimeOpening = request.getParameter("htime_opening_sat");
				String htimeClosing = request.getParameter("htime_closing_sat");
				satH.setHtime_opening(LocalTime.parse(htimeOpening));
				satH.setHtime_closing(LocalTime.parse(htimeClosing));
			}
			htList.add(satH);

			String sun = request.getParameter("sun");
			Hospital_time sunH = new Hospital_time();
			sunH.setWeek_no(7);
			sunH.setH_id(hospital.getH_id());
			if (sun == null) {
				sunH.setHtime_isable(false);
			} else {
				sunH.setHtime_isable(true);
				String htimeOpening = request.getParameter("htime_opening_sun");
				String htimeClosing = request.getParameter("htime_closing_sun");
				sunH.setHtime_opening(LocalTime.parse(htimeOpening));
				sunH.setHtime_closing(LocalTime.parse(htimeClosing));
			}
			htList.add(sunH);
			System.out.println(htList);
			HospitalService hospitalService = new HospitalServiceImpl();
			hospitalService.modifyHospitalTime(hospital.getH_id(), intervalTime, htList);
			
			((Hospital)request.getSession().getAttribute("hospital")).setH_interval_time(intervalTime);
			response.sendRedirect("hmyManagerReservationTime");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e.getMessage());
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}
	}

}
