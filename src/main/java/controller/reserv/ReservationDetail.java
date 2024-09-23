package controller.reserv;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Pet;
import dto.User;
import service.my.PetService;
import service.my.PetServiceImpl;
import service.reserv.ReservationService;
import service.reserv.ReservationServiceImpl;

/**
 * Servlet implementation class ReservationDetail
 */
@WebServlet("/reservDetail")
public class ReservationDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			Integer reserv_id  = Integer.parseInt(request.getParameter("reserv_id"));
			
			ReservationService rservice = new ReservationServiceImpl();
			Map<String, Object> reservation = rservice.selectReservByReservId(reserv_id);
			
			request.setAttribute("reserv", reservation);
			request.setAttribute("user", user);
			System.out.println(reservation.toString());
		request.getRequestDispatcher("/reserv/reservationdetail.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "게시글 목록 오류");
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
