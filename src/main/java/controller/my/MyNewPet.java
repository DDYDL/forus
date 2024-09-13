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
		request.getRequestDispatcher("/my/myNewPet.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		PetService service = new PetServiceImpl();
		try {
			service.insertPet(request);
			response.sendRedirect("myPetList");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "등록 오류");
			request.getRequestDispatcher("err.jsp").forward(request, response);

		}
	}
}
