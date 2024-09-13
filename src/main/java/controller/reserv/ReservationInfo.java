package controller.reserv;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dto.Pet;
import dto.User;
import service.reserv.ReservationService;
import service.reserv.ReservationServiceImpl;

@WebServlet("/reservationInfo")
public class ReservationInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReservationInfo() {
		super();
	}


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ReservationService reservationService = new ReservationServiceImpl();
		HttpSession session = request.getSession(false);

		// User user = null;
		// List<Pet> pet = null;
		try {
			if (session != null) {
				User sessionUser = (User) session.getAttribute("user");

				if (sessionUser != null) {
					int userId = sessionUser.getId();

					User user = reservationService.getUserInfo(userId);
					List<Pet>pet  = reservationService.getPetsInfo(userId);

					Gson gson = new Gson();
					Map<String, Object> responseData = new HashMap<>();
					responseData.put("user", user);
					responseData.put("pet", pet);

					String userAndPetInfoJson = gson.toJson(responseData);

					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(userAndPetInfoJson);


				} else {
					System.out.println("세션에서 유저 정보를 찾을 수 없습니다.");
				}
			} else {
				System.out.println("세션이 없습니다.");
			}
		} catch (Exception e) {
			
			System.err.println("오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
		}




	}
}