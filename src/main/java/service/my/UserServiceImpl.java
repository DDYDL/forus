package service.my;

import dao.my.UserDao;
import dao.my.UserDaoImpl;
import dto.User;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	public UserServiceImpl() {
		userDao = new UserDaoImpl();
	}

	@Override
	public void join(User user) throws Exception {
		System.out.println(user.toString());
		User suser = userDao.selectUser(user.getEmail());
		if(suser!=null) throw new Exception("아이디 중복 오류");
		userDao.insertUser(user);
	}

	@Override
	public User login(String email, String password) throws Exception {
		User suser = userDao.selectUser(email);
		if(suser==null) throw new Exception("아이디 오류");
		if(!password.equals(suser.getPassword())) throw new Exception("비밀번호 오류");
		return suser;
	}

	@Override
	public boolean checkDoubleEmail(String email) throws Exception {
		User suser = userDao.selectUser(email);
		if(suser==null) return false;
		return true;
	}

}
