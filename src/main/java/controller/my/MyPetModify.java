package controller.my;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Pet;
import dto.User;
import service.my.PetService;
import service.my.PetServiceImpl;

/**
 * Servlet implementation class MyPetModify
 */
@WebServlet("/myPetModify")
public class MyPetModify extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyPetModify() {
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
			request.setAttribute("pet", pet);
			request.getRequestDispatcher("/my/myPetModify.jsp").forward(request, response);

		} catch (Exception e) {
			request.setAttribute("err", e.getMessage());
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("펫 정보 수정");
			PetService service = new PetServiceImpl();

			String petid = request.getParameter("pet_id");
			String newfilename = "pet_" + petid;

			Integer pet_id = service.petModify(request, newfilename);
			System.out.println(pet_id);
			Pet pet = service.petDetail(pet_id);

			request.setAttribute("pet", pet);
			request.getRequestDispatcher("/myPetList").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "펫 수정 오류");
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}
	}
}
