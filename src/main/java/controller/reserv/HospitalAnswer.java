package controller.reserv;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Hospital_qna;
import dto.User;
import service.reserv.HospitalQnaService;
import service.reserv.HospitalQnaServiceImpl;

@WebServlet("/hospitalAnswer")
public class HospitalAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HospitalAnswer() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
		ServletException,
		IOException {
		request.setCharacterEncoding("UTF-8");

		try {
			HttpSession session = request.getSession(false);
			if (session != null) {
				User sessionUser = (User) session.getAttribute("user");

				if (sessionUser != null && sessionUser.getIshospital() == 1) {

					String answerTitle = request.getParameter("a_title");
					int qnaId = Integer.parseInt(request.getParameter("qna_id"));
					int userId = sessionUser.getId();

					Hospital_qna hospitalQna = new Hospital_qna();
					hospitalQna.setA_title(answerTitle);
					hospitalQna.setUser_id(userId);

					HospitalQnaService hospitalQnaService = new HospitalQnaServiceImpl();
					boolean isInserted = hospitalQnaService.insertHospitalAnswer(qnaId, hospitalQna);


					if (isInserted) {
						response.getWriter().write("답변이 등록되었습니다.");
					} else {
						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						response.getWriter().write("답변 등록에 실패했습니다.");
					}
				} else {

					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					response.getWriter().write("답변 권한이 없습니다.");
				}
			} else {

				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().write("세션이 만료되었습니다.");
			}
		} catch (Exception e) {

			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("처리 중 오류가 발생했습니다.");
			e.printStackTrace();
		}
	}
}