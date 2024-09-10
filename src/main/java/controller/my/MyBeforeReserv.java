package controller.my;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

import dto.Recruit_post;
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
		request.getRequestDispatcher("my/mybeforereserv.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            
            ReservationService service = new ReservationServiceImpl();
            List<Map<String, Object>> beforeReservList = service.selectMyBeforeReservList(id, pet_name, startDate, endDate, isConsult);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            JSONArray jsonArray = new JSONArray();
			for(Map<String, Object> reserv : beforeReservList) {
				JSONObject jsonReserv = new JSONObject();
				jsonReserv.put("reserv_id", reserv.get("reserv_id"));
				
				Date date = (Date)reserv.get("reserv_date");  // LocalDate 객체
				jsonReserv.put("reserv_date", formatter.format(date));
				
				jsonReserv.put("pet_picture", reserv.get("pet_picture"));
				jsonReserv.put("pet_name", reserv.get("pet_name"));
				jsonReserv.put("h_name", reserv.get("h_name"));
				jsonReserv.put("reserv_status", reserv.get("reserv_status"));
				jsonArray.add(jsonReserv);
			}
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
    		System.out.println(jsonArray.toJSONString());
            response.getWriter().write(jsonArray.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
			request.setAttribute("err", "게시글 목록 오류");
			request.getRequestDispatcher("err.jsp").forward(request, response);
        }
	}

}
