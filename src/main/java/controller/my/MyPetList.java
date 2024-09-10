package controller.my;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Pet;
import dto.User;
import service.my.MyPetListImpl;
import service.my.MyPetListService;

/**
 * Servlet implementation class MyPetList
 */
@WebServlet("/myPetList")
public class MyPetList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MyPetList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		User user =  (User) request.getSession().getAttribute("user"); //user 세션 가져오기
	
		
		try {
		MyPetListService service = new MyPetListImpl();
		List<Pet> petList = service.getPetByUserid(user.getId());
		request.setAttribute("petList", petList);
		request.getRequestDispatcher("/my/myPetList.jsp").forward(request, response);
		
		}
		catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("err",e.getMessage());
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
