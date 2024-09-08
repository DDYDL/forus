package controller.reserv;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dto.Hospital_qna;
import dto.Pet;
import dto.User;
import service.reserv.HospitalQnaService;
import service.reserv.HospitalQnaServiceImpl;

@WebServlet("/hospitalQuestion")
public class HospitalQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HospitalQuestion() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");


			HospitalQnaService hospitalQnaService = new HospitalQnaServiceImpl();
			request.setCharacterEncoding("UTF-8");
			Integer hospitalId = Integer.parseInt(request.getParameter("hospitalId"));

			List<Hospital_qna> hospitalQnaList = hospitalQnaService.selectHospitalQnaList(hospitalId);

			Gson gson = new Gson();
			String json = gson.toJson(hospitalQnaList);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			response.getWriter().write(json);


		}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
		ServletException,
		IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);

		Integer userId = null;
		try {
			if (session != null) {
				User sessionUser = (User)session.getAttribute("user");
				if (sessionUser != null) {
					userId = sessionUser.getId();
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

		String questionContent = request.getParameter("qnaInput");
		String hospitalId = request.getParameter("hospitalId");

		HospitalQnaService hospitalQnaService = new HospitalQnaServiceImpl();

		Hospital_qna hospitalQna = new Hospital_qna();
		hospitalQna.setH_id(Integer.parseInt(hospitalId));
		hospitalQna.setUser_id(userId);
		hospitalQna.setQ_title(questionContent);

		hospitalQnaService.insertHospitalQuestion(hospitalQna);




		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("{\"status\":\"success\"}");

	}

}
