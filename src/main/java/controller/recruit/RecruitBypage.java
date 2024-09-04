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

import dto.Recruit_post;
import service.recruit.SearchService;
import service.recruit.SearchServiceImpl;

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
			page = (Long)jsonObj.get("page"); // 현재 페이지 가져오기
			
			SearchService service = new SearchServiceImpl();
			// 현재 조건의 recruit post 리스트 가져오기
			List<Recruit_post> posts = service.getRecruit_postList(areas, species, page.intValue());
			// 최대 페이지 수 가져오기
			Integer maxPage = service.getMaxPage(areas, species);
			
			// jsp response로 recruit_post 자바 객체를 전달하기 위해 JSON타입 스트링 만들기
			JSONObject resJson = new JSONObject();
			resJson.put("page", page);
			resJson.put("page", maxPage);
			JSONArray jsonPosts = new JSONArray();
			
			for(Recruit_post post : posts) {
				JSONObject jsonPost = new JSONObject();
				jsonPost.put("post_id", post.getPost_id());
				jsonPost.put("user_id", post.getUser_id());
				jsonPost.put("pet_id", post.getPet_id());
				jsonPost.put("post_time", post.getPost_time());
				jsonPost.put("post_title", post.getPost_title());
				jsonPost.put("post_content", post.getPost_content());
				jsonPost.put("post_address", post.getPost_address());
				jsonPost.put("post_form", post.getPost_form());
				jsonPost.put("post_pay", post.getPost_pay());
				jsonPost.put("post_start_time", post.getPost_start_time());
				jsonPost.put("post_end_time", post.getPost_end_time());
				jsonPost.put("post_status", post.getPost_status());
				jsonPosts.add(jsonPost);
			}
			resJson.put("recruit_postList", jsonPosts);
			System.out.println(resJson.toJSONString());
			response.getWriter().write(resJson.toJSONString()); // response에 recruit_post 리스트 담기
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
