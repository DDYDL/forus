package controller.my;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Pet;
import service.my.PetService;
import service.my.PetServiceImpl;

/**
 * Servlet implementation class MyNewPet
 */
@WebServlet("/myNewPet")
public class MyNewPet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyNewPet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Pet pet = new Pet();
		pet.setPet_name(request.getParameter("name"));
		pet.setPet_species(request.getParameter("species"));
		pet.setPet_breed(request.getParameter("breed"));
		pet.setPet_gender(request.getParameter("gender"));
		pet.setPet_age(Integer.parseInt(request.getParameter("age")));
		//pet.setPet_neutering(request.getParameter("neutering"));
		pet.setPet_picture(request.getParameter("picture"));
		pet.setPet_memo(request.getParameter("memo"));
		pet.setPet_num(Integer.parseInt(request.getParameter("pet_num")));
		
		
		
	
		//HttpSession session = request.getSession();
		try {
				PetService service = new PetServiceImpl();
				service.join(pet);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e.getMessage());
		}
	}
}
