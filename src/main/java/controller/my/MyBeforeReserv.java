package controller.my;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dto.Reservation;
import dto.User;
import service.reserv.ReservationService;
import service.reserv.ReservationServiceImpl;

/**
 * Servlet implementation class MyAfterReserv
 */
@WebServlet("/myBeforeReserv")
public class MyBeforeReserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyBeforeReserv() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// String paramPage = request.getParameter("page");
		
        try {
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("user");
            Integer id = user.getId();
            String pet_name = request.getParameter("pet_name");
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            boolean isConsult = "on".equals(request.getParameter("isConsult"));
            System.out.println(isConsult);
            ReservationService service = new ReservationServiceImpl();
            List<Map<String, Object>> beforeReservList = service.myBeforeReservList(id, pet_name, startDate, endDate, isConsult);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(new Gson().toJson(beforeReservList));
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "게시글 목록 오류");
			request.setAttribute("err", "게시글 목록 오류");
			request.getRequestDispatcher("err.jsp").forward(request, response);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
