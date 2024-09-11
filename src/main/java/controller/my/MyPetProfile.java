package controller.my;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Pet;
import service.my.PetService;
import service.my.PetServiceImpl;

/**
 * Servlet implementation class MyPetProfile
 */
@WebServlet("/myPetProfile")
public class MyPetProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyPetProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Integer pet_id = Integer.parseInt(request.getParameter("pet_id"));
		System.out.println(pet_id);
		try {
			PetService service = new PetServiceImpl();

			Pet pet = service.petDetail(pet_id);
			System.out.println(pet_id);
			request.setAttribute("pet", pet);

			request.getRequestDispatcher("myPetProfile.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e.getMessage());
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}
	}

}
