package service.my;

import dto.User;

public interface UserService {
	void join(User user) throws Exception;
	User login(String email, String password) throws Exception;
	boolean checkDoubleEmail(String email) throws Exception;
}
