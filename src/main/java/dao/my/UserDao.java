package dao;

import dto.User;

public interface UserDao {
	void insertUser(User user) throws Exception;
	User selectUser(String email) throws Exception;
	void updateUser(User user) throws Exception;
}
