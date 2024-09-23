package controller.my;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.User;
import service.recruit.RecruitApplyService;
import service.recruit.RecruitApplyServiceImpl;
import service.recruit.RecruitService;
import service.recruit.RecruitServiceImpl;

/**
 * Servlet implementation class MyApplyList
 */
@WebServlet("/myApplyList")
public class MyApplyList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyApplyList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			Integer id = user.getId();
			System.out.println("id");
			RecruitApplyService service = new RecruitApplyServiceImpl();
			List<Map<String,Object>> myApplyList = service.selectApplyListByUserId(id);
			System.out.println(myApplyList);
			
			request.setAttribute("myApplyList", myApplyList);
			request.getRequestDispatcher("/my/myapplylist.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "게시글 목록 오류");
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Integer apply_id = Integer.parseInt(request.getParameter("apply_id"));
		RecruitApplyService service = new RecruitApplyServiceImpl();
		try {
			service.deleteApplyByApplyId(apply_id);
			response.sendRedirect("myApplyList");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
