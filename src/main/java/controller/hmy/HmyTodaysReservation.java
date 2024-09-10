package controller.hmy;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Reservation;
import dto.User;
import service.hmy.HospitalService;
import service.hmy.HospitalServiceImpl;

/**
 * Servlet implementation class HmyTodaysReservation
 */
@WebServlet("/hmyTodaysReservation")
public class HmyTodaysReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HmyTodaysReservation() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
		User user =  (User) request.getSession().getAttribute("user"); //user 세션 가져오기
    	
		try {
		HospitalService service = new HospitalServiceImpl();
		//List<Reservation> reservList = service.get(user.getId());
		//request.setAttribute("reservList", reservList);
		request.getRequestDispatcher("/hmy/hmytodaysreservation.jsp").forward(request, response);
		
		}
		catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("err",e.getMessage());
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}
		
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
