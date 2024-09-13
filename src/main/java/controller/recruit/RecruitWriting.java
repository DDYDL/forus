package controller.recruit;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dto.Pet;
import dto.Recruit_post;
import dto.User;
import service.recruit.RecruitService;
import service.recruit.RecruitServiceImpl;

/**
 * Servlet implementation class RecruitWriting
 */
@WebServlet("/recruitWriting")
public class RecruitWriting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecruitWriting() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("./recruit/recruitwriting.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			RecruitService service = new RecruitServiceImpl();
			// form태그의 name으로 넘어온 value들을 파라미터로 전송
			service.recruit_postWrite(request);
			// recruitList.jsp로 이동
			response.sendRedirect("recruitList");
		} catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "구인 등록 오류");
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}
	}

}
