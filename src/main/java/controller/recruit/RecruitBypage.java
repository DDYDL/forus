package controller.recruit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Servlet implementation class RecruitBypage
 */
@WebServlet("/recruitBypage")
public class RecruitBypage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecruitBypage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String param = request.getParameter("param");
		System.out.println(param);
		response.setCharacterEncoding("utf-8");
		
		List<String> areas = new ArrayList<>(); // 지역
		List<String> species = new ArrayList<>(); // 동물
		Long page = null; // 페이지 수
		
		try { 
			JSONParser parser = new JSONParser();
			JSONObject jsonObj = (JSONObject)parser.parse(param); // JSON 문자열 JSON 객체로 변환
			JSONArray areaArr = (JSONArray)jsonObj.get("areas"); // 객체 안의 배열들 가져오기
			JSONArray speciesArr = (JSONArray)jsonObj.get("species");
			
			for(int i=0; i<areaArr.size(); i++) {
				areas.add((String)areaArr.get(i)); // 배열 String 배열로 변환
			}
			for(int i=0; i<speciesArr.size(); i++) {
				species.add((String)speciesArr.get(i));
			}
			page = (Long)jsonObj.get("page"); // 페이지 가져오기
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
