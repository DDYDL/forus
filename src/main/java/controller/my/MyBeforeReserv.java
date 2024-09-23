package controller.my;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
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

import dto.Pet;
import dto.User;
import service.my.PageService;
import service.my.PageServiceImpl;
import service.my.PetService;
import service.my.PetServiceImpl;
import service.reserv.ReservationService;
import service.reserv.ReservationServiceImpl;
import util.PageInfo;

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
		
		try {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			Integer id = user.getId();
			
			// 드롭다운에 넣어줄 유저 펫리스트 조회
			PetService service = new PetServiceImpl();
			List<Pet> petList = service.selectPetList(id);
			request.setAttribute("petList", petList);
			
			request.getRequestDispatcher("my/mybeforereserv.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "게시글 목록 오류");
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Integer id = user.getId();
		String sparam = request.getParameter("param");
		System.out.println("sparam: "+sparam);
		Integer pet_id = null;
		String startDate = null;
		String endDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		
        try {
        	JSONParser parser = new JSONParser();
        	JSONObject jsonObj = (JSONObject)parser.parse(sparam);
        	
        	// JSON > String 변환
        	String spet_id = (String)jsonObj.get("pet_id");
        	System.out.println(spet_id);
            if(spet_id != null && !spet_id.isEmpty()) {
            	pet_id = Integer.parseInt(spet_id);
            } 
            
            String dateRange = (String)jsonObj.get("dateRange");
            System.out.println(dateRange);
            if (dateRange != null && dateRange != "") {
                String[] dates = dateRange.split(" ~ ");
                startDate = dates[0].trim();
                if (dates.length == 2) {
		            endDate = dates[1].trim();
                } else {
                	endDate = dates[0].trim();
                }
            }
            
            String sIsConsult = (String)jsonObj.get("isConsult");
            boolean isConsult = "true".equals(sIsConsult);
            System.out.println(isConsult);
            
            Long page = (Long)jsonObj.get("page");
            System.out.println(page);
            
            PageInfo pageInfo = new PageInfo();
            pageInfo.setCurPage(page.intValue());
            
            ReservationService service = new ReservationServiceImpl();
            List<Map<String, Object>> beforeReservList = service.selectMyBeforeReservList(id, pet_id, startDate, endDate, isConsult, pageInfo);
    		System.out.println(beforeReservList);
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
			
			
			JSONObject responseJson = new JSONObject();
			System.out.println(pageInfo);
			responseJson.put("result", jsonArray);

			Integer listcnt = service.selectMyBeforeReservCount(id, pet_id, startDate, endDate, isConsult);
			System.out.println(listcnt);
			responseJson.put("listcnt", listcnt.toString());
			
			JSONObject pageJson = new JSONObject();
			pageJson.put("curPage", pageInfo.getCurPage());
			pageJson.put("allPage", pageInfo.getAllPage());
			pageJson.put("startPage", pageInfo.getStartPage());
			pageJson.put("endPage", pageInfo.getEndPage());
			responseJson.put("pageInfo", pageJson);
            
            System.out.println(responseJson.toJSONString());
            response.getWriter().write(responseJson.toString());
        } catch (Exception e) {
            e.printStackTrace();
			request.setAttribute("err", "게시글 목록 오류");
			request.getRequestDispatcher("err.jsp").forward(request, response);
        }
	}

}
