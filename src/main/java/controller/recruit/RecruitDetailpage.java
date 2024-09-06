package controller.recruit;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Recruit_post;
import dto.User;
import service.recruit.RecruitService;
import service.recruit.RecruitServiceImpl;

/**
 * Servlet implementation class RecruitDetailpage
 */
@WebServlet("/recruitDetailpage")
public class RecruitDetailpage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecruitDetailpage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer post_id = Integer.parseInt(request.getParameter("post_id"));
		
		try {
			RecruitService service = new RecruitServiceImpl();
			Recruit_post post = service.recruit_postDatail(post_id); // Recruit_post 객체 생성
			request.setAttribute("post", post); // request에 객체 넣기
			User user = (User)request.getSession().getAttribute("user");
			
			if(user!=null) { // 로그인 되어 있으면 지원하기 버튼 띄우기 위해 설정
				request.setAttribute("apply", service.checkApply(user.getId(), post_id));
			}
			request.getRequestDispatcher("./recruit/recruitdetailpage.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
