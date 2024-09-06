package controller.recruit;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dto.Pet;
import dto.User;
import service.recruit.RecruitService;
import service.recruit.RecruitServiceImpl;

/**
 * Servlet implementation class RecruitWritingPetList
 */
@WebServlet("/recruitWritingPetList")
public class RecruitWritingPetList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecruitWritingPetList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		try {
			RecruitService service = new RecruitServiceImpl();
			User user = (User)request.getSession().getAttribute("user");
			List<Pet> pets = service.getPetList(user.getId());
			System.out.println("pets:" + pets);
			
			JSONObject resJson = new JSONObject();
			JSONArray jsonPosts = new JSONArray();
			for(Pet pet : pets) {
				JSONObject jsonPost = new JSONObject();
				jsonPost.put("pet_id", pet.getPet_id());
				jsonPost.put("user_id", pet.getUser_id());
				jsonPost.put("pet_name", pet.getPet_name());
				jsonPost.put("pet_species", pet.getPet_species());
				jsonPost.put("pet_breed", pet.getPet_breed());
				jsonPost.put("pet_gender", pet.getPet_gender());
				jsonPost.put("pet_age", pet.getPet_age());
				jsonPost.put("pet_neutering", pet.isPet_neutering());
				jsonPost.put("pet_picture", pet.getPet_picture());
				jsonPost.put("pet_memo", pet.getPet_memo());
				jsonPost.put("pet_num", pet.getPet_num());
				jsonPosts.add(jsonPost);
			}
			resJson.put("petList", jsonPosts);
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(resJson.toJSONString());
		} catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e.getMessage());
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
