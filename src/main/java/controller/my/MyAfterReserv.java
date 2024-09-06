package controller.my;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Reservation;
import dto.User;
import service.reserv.ReservationService;
import service.reserv.ReservationServiceImpl;

/**
 * Servlet implementation class MyAfterReserv
 */
@WebServlet("/myAfterReserv")
public class MyAfterReserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyAfterReserv() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("도착");

		try {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			Integer id = user.getId();
			System.out.println(id);
			ReservationService service = new ReservationServiceImpl();
			List<Map<String, Object>> afterReservList = service.myAfterReservList(id);
			request.setAttribute("afterReservList", afterReservList);
			request.getRequestDispatcher("/my/myafterreserv.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "게시글 목록 오류");
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
