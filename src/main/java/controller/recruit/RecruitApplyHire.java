package controller.recruit;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Recruit_apply;
import dto.Recruit_post;
import service.recruit.RecruitApplyService;
import service.recruit.RecruitApplyServiceImpl;

/**
 * Servlet implementation class RecruitApplyHire
 */
@WebServlet("/recruitApplyHire")
public class RecruitApplyHire extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecruitApplyHire() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		try {
			RecruitApplyService service = new RecruitApplyServiceImpl();
			Integer apply_id = Integer.parseInt(request.getParameter("apply_id"));
			
			Recruit_apply apply = service.selectRecruit_apply(apply_id);
			Integer user_id = apply.getUser_id();
			Integer post_id = apply.getPost_id();
			
			// 해당 post의 지원자 리스트 가져오기
			List<Recruit_apply> applyList = service.selectRecruit_applyList(post_id);
			
			// 지원자들의 status 모두 바꾸기
			for(int i=0; i<applyList.size(); i++) {
				if(applyList.get(i).getUser_id() == user_id) {
					applyList.get(i).setApply_status("채용");
				} else {
					applyList.get(i).setApply_status("지원마감");
				}
				service.updateRecruit_apply_status(applyList.get(i));
			}
			
			// recruit_post status 바꾸기
			Recruit_post post = new Recruit_post();
			post.setPost_id(post_id);
			post.setPost_status("마감");
			service.updateRecruit_post_status(post);
			
			request.setAttribute("post_id", post_id);
			response.sendRedirect("recruitApplyList");
		} catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e.getMessage());
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}
	}

}
