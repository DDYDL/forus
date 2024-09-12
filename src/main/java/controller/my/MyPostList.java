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

import dto.Recruit_post;
import dto.User;
import service.recruit.RecruitService;
import service.recruit.RecruitServiceImpl;
import service.reserv.ReservationService;
import service.reserv.ReservationServiceImpl;

/**
 * Servlet implementation class MyPostList
 */
@WebServlet("/myPostList")
public class MyPostList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPostList() {
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
			RecruitService service = new RecruitServiceImpl();
			List<Map<String,Object>> myPostList = service.selectPostListByUserId(id);
			System.out.println(myPostList);
			
			request.setAttribute("myPostList", myPostList);
			request.getRequestDispatcher("/my/mypostlist.jsp").forward(request, response);
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
		doGet(request, response);
	}

}
