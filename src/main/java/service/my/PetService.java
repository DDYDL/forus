package service.my;

import javax.servlet.http.HttpServletRequest;

public interface PetService {
		void petprofile(HttpServletRequest request)throws Exception;
		Integer petModify(HttpServletRequest request) throws Exception;
		
}
