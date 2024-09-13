package controller.recruit;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.recruit.RecruitApplyService;
import service.recruit.RecruitApplyServiceImpl;

/**
 * Servlet implementation class RecruitApply
 */
@WebServlet("/recruitApply")
public class RecruitApply extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecruitApply() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		try {
			RecruitApplyService service = new RecruitApplyServiceImpl();
			service.insertRecruit_apply(request);
			// recruitDetailpage에서 post_id가 필요하므로 request에 세팅
			request.setAttribute("post_id", request.getParameter("post_id"));
			// post로 보냄
			request.getRequestDispatcher("recruitDetailpage").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e.getMessage());
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}
	}

}
