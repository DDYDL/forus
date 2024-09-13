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
			User post_user = service.selectUserId(post.getUser_id());
			User user = (User)request.getSession().getAttribute("user");
			request.setAttribute("post", post); // request에 객체 넣기
			request.setAttribute("post_user", post_user);
			
			if(user!=null && user.getId() == post_user.getId()) { // 로그인 되어 있고, 작성자와 같으면 수정하기 버튼 띄우기 위해 설정
				request.setAttribute("modify", service.checkModify(post_user.getId(), post_id));
			}
			request.getRequestDispatcher("./recruit/recruitdetailpage.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e.getMessage());
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Integer post_id = Integer.parseInt(request.getParameter("post_id"));
		
		try {
			RecruitService service = new RecruitServiceImpl();
			Recruit_post post = service.recruit_postDatail(post_id); // Recruit_post 객체 생성
			User post_user = service.selectUserId(post.getUser_id());
			User user = (User)request.getSession().getAttribute("user");
			request.setAttribute("post", post); // request에 객체 넣기
			request.setAttribute("post_user", post_user);
			
			if(user!=null && user.getId() == post_user.getId()) { // 로그인 되어 있고, 작성자와 같으면 수정하기 버튼 띄우기 위해 설정
				request.setAttribute("modify", service.checkModify(post_user.getId(), post_id));
			}
			request.getRequestDispatcher("./recruit/recruitdetailpage.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e.getMessage());
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}
	}

}
