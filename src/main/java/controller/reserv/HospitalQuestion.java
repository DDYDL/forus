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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
		ServletException,
		IOException {
		try {
			request.setCharacterEncoding("UTF-8");

			HospitalQnaService hospitalQnaService = new HospitalQnaServiceImpl();

			Integer hospitalId = Integer.parseInt(request.getParameter("hospitalId"));
			int page = Integer.parseInt(request.getParameter("page"));
			int limit = 3; // 한 페이지에 보여줄 QnA 개수
			int offset = (page - 1) * limit;

			List<Hospital_qna> hospitalQnaList = hospitalQnaService.getHospitalQnaList(hospitalId, limit, offset);
			

			Gson gson = new Gson();
			String json = gson.toJson(hospitalQnaList);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);

		} catch (NumberFormatException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("잘못된 병원 ID입니다.");
			e.printStackTrace();
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("QnA 목록을 가져오는 중 오류가 발생했습니다.");
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
		ServletException,
		IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);

		Integer userId = null;
		String userEmail = null;
		try {
			if (session != null) {
				User sessionUser = (User)session.getAttribute("user");
				if (sessionUser != null) {
					userId = sessionUser.getId();
					userEmail = sessionUser.getEmail();
				} else {
					System.out.println("세션에서 유저 정보를 찾을 수 없습니다.");
				}
			} else {
				System.out.println("세션이 없습니다.");
			}

			String questionContent = request.getParameter("qnaInput");
			String hospitalId = request.getParameter("hospitalId");

			HospitalQnaService hospitalQnaService = new HospitalQnaServiceImpl();

			Hospital_qna hospitalQna = hospitalQnaService.createHospitalQna(hospitalId, userId, userEmail,
				questionContent);

			hospitalQnaService.insertHospitalQuestion(hospitalQna);

			Gson gson = new Gson();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(gson.toJson(hospitalQna));

		} catch (NumberFormatException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("잘못된 형식의 입력이 포함되어 있습니다.");
			e.printStackTrace();
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("QnA 처리 중 오류가 발생했습니다.");
			e.printStackTrace();
		}
	}

}
