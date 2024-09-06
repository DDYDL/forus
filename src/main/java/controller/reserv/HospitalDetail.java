package controller.reserv;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Hospital;
import dto.Hospital_time;
import service.hmy.HospitalService;
import service.reserv.HospitalDetailService;
import service.reserv.HospitalServiceImpl;

@WebServlet("/hospitalDetail")
public class HospitalDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HospitalDetail() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String hospitalId = request.getParameter("hospitalId");

		HospitalDetailService hospitalService = new HospitalServiceImpl();
		Hospital hospital = hospitalService.getHospitalDetailByHospitalId(Integer.parseInt(hospitalId));

		List<Hospital_time> hospitalTimes = hospitalService.getHospitalTimeByHospitalId(Integer.parseInt(hospitalId));

		request.setAttribute("hospital", hospital);
		request.setAttribute("hospitalTimes", hospitalTimes);
		request.getRequestDispatcher("/reserv/hospitaldetail.jsp").forward(request, response);


	}


}
