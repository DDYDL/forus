package controller.recruit;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Pet;
import dto.Recruit_apply;
import dto.User;
import service.my.PetService;
import service.my.PetServiceImpl;
import service.recruit.RecruitApplyService;
import service.recruit.RecruitApplyServiceImpl;
import service.recruit.RecruitService;
import service.recruit.RecruitServiceImpl;

/**
 * Servlet implementation class RecruitApplyDetailpage
 */
@WebServlet("/recruitApplyDetailpage")
public class RecruitApplyDetailpage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecruitApplyDetailpage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Integer apply_id = Integer.parseInt(request.getParameter("apply_id"));
		
		try {
			RecruitApplyService service = new RecruitApplyServiceImpl();
			RecruitService uservice = new RecruitServiceImpl();
			PetService pservice = new PetServiceImpl();
			
			// 필요한 객체 가져오기
			Recruit_apply recruit_apply = service.selectRecruit_apply(apply_id);
			User apply_user = uservice.selectUserId(recruit_apply.getUser_id());
			List<Pet> petList = pservice.selectPetList(recruit_apply.getUser_id());
			
			request.setAttribute("recruit_apply", recruit_apply);
			request.setAttribute("apply_user", apply_user);
			request.setAttribute("petList", petList);
			// post로 보냄
			request.getRequestDispatcher("./recruit/recruitapplydetailpage.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e.getMessage());
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}
	}
}
