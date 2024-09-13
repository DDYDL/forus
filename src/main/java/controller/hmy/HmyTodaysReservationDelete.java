package controller.hmy;

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
@WebServlet("/hmyTodaysReservationDelete")
public class HmyTodaysReservationDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HmyTodaysReservationDelete() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int reserv_id = Integer.parseInt(request.getParameter("reserv_id"));

		System.out.println("reserv_id" + reserv_id);

		try {
			PetService service = new PetServiceImpl();
			service.petDelete(reserv_id);
			request.getRequestDispatcher("/hmyTodaysReservation").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e.getMessage());
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}

	}

}
