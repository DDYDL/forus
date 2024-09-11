package controller.recruit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Recruit_apply;
import dto.User;
import service.recruit.RecruitApplyService;
import service.recruit.RecruitApplyServiceImpl;
import service.recruit.RecruitService;
import service.recruit.RecruitServiceImpl;

/**
 * Servlet implementation class RecruitApplyList
 */
@WebServlet("/recruitApplyList")
public class RecruitApplyList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecruitApplyList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//Integer post_id = Integer.parseInt(request.getParameter("post_id"));
		Integer post_id = 4;
		try {
			RecruitApplyService service = new RecruitApplyServiceImpl();
			RecruitService serviceRe = new RecruitServiceImpl();
			// 해당하는 Recruit_post에 지원한 Recruit_apply 가져오기
			List<Recruit_apply> recruit_applyList = service.selectRecruit_applyList(post_id);
			// 해당하는 Recruit_post에 지원한 User 가져오기
			List<User> userList = new ArrayList<>();
			for(Recruit_apply apply : recruit_applyList) {
				User user = serviceRe.selectUserId(apply.getUser_id());
				userList.add(user);
			}
			
			System.out.println(recruit_applyList);
			System.out.println(userList);
			
			request.setAttribute("recruit_applyList", recruit_applyList);
			request.setAttribute("userList", userList);
			request.getRequestDispatcher("./recruit/recruitapplylist.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
