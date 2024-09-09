package hmycontroller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.hospital;

/**
 * Servlet implementation class hmyHospital
 */
@WebServlet("/hmyHospital")
public class hmyHospital extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public hmyHospital() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("hmyNewHospital.jsp").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		hospital hos = new hospital();
		hos.setH_id(Integer.parseInt(request.getParameter("h_id")));
		hos.setUser_id(Integer.parseInt(request.getParameter("user_id")));
		hos.setH_exponent_name(request.getParameter("h_exponent_name"));
		hos.setH_num(Integer.parseInt(request.getParameter("h_num")));		
		hos.setH_license(request.getParameter("h_license"));
		hos.setH_name(request.getParameter("h_name"));
		hos.setH_manager_name(request.getParameter("manager_name"));
		 hos.setH_phone(request.getParameter("h_phone"));
		 hos.setH_address(request.getParameter("h_address"));
		 hos.setH_picture(request.getParameter("h_picture"));
		 hos.setH_memo(request.getParameter("h_memo"));
		 hos.setH_memo_road(request.getParameter("h_memo_road"));
		 //hos.setH_pay(request.getParameterValues("h_pay"));
		 hos.setH_sns(request.getParameter("h_sns"));
		 //hos.setH_parking(request.getParameter("h_parking"));
		 //hos.setH_isopen(request.getParameter("h_isopen"));
		 //hos.setH_lunch_time_start(request.getParameter("h_lunch_time_start"));
		 //hos.setH_lunch_time_end(request.getParameter("h_lunch_time_end"));
		 //hos.setH_interval_time(request.getParameter("h_interval_time"));

	}

}
