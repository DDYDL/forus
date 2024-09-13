package controller.my;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import service.my.ImageService;
import service.my.ImageServiceImpl;

/**
 * Servlet implementation class ImageView
 */
@WebServlet("/image")
public class ImageView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String file = request.getParameter("file");
		String type = request.getParameter("type");

        if (file == null || type == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "파일이 없습니다.");
            return;
        }

        try {
        	ImageService service = new ImageServiceImpl();
			service.imageView(request, response.getOutputStream(), file, type);
		} catch(Exception e) {
			e.printStackTrace();
		}
    
	}
}
