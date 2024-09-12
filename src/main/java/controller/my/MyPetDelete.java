package controller.my;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.my.PetService;
import service.my.PetServiceImpl;

/**
 * Servlet implementation class MyPetDelete
 */
@WebServlet("/myPetDelete")
public class MyPetDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyPetDelete() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pet_Id = Integer.parseInt(request.getParameter("pet_id"));

		System.out.println("petId" + pet_Id);

		try {
			PetService service = new PetServiceImpl();
			service.petDelete(pet_Id);
			request.getRequestDispatcher("/myPetList").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e.getMessage());
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}

	}

}
