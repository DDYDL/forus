package service.my;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.my.UserDao;
import dao.my.UserDaoImpl;
import dto.User;
import util.FileUploadRename;

public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public UserServiceImpl() {
		userDao = new UserDaoImpl();
	}

	@Override
	public void join(User user) throws Exception {
		System.out.println(user.toString());
		User suser = userDao.selectUser(user.getEmail());
		if (suser != null)
			throw new Exception("이메일 중복 오류");
		userDao.insertUser(user);
	}

	@Override
	public User login(String email, String password) throws Exception {
		User suser = userDao.selectUser(email);
		if (suser == null)
			throw new Exception("아이디 오류");
		if (!password.equals(suser.getPassword()))
			throw new Exception("비밀번호 오류");
		return suser;
	}

	@Override
	public boolean checkDoubleEmail(String email) throws Exception {
		User suser = userDao.selectUser(email);
		if (suser == null)
			return false;
		return true;
	}

	@Override
	public User userDetail(Integer id) throws Exception {
		User user = userDao.selectUserId(id);
		if (user == null)
			throw new Exception("유저 조회 오류");
		return user;
	}

	@Override
	public Integer userModify(HttpServletRequest request, String newfilename) throws Exception {
		String path = request.getServletContext().getRealPath("upload" + "\\" + "user");
		int size = 10 * 1024 * 1024;
		
		MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new FileUploadRename(newfilename));

		User user = new User();
		user.setId(Integer.parseInt(multi.getParameter("id")));
		user.setName(multi.getParameter("name"));
		user.setNickname(multi.getParameter("nickname"));
		user.setEmail((String) request.getSession().getAttribute("email"));
		user.setPassword(multi.getParameter("password"));
		user.setPhone(multi.getParameter("phone"));
		user.setBirthday(multi.getParameter("birthday"));
		user.setGender(multi.getParameter("gender"));

		// 파일 수정
		if (multi.getFile("file") != null) {
			user.setPicture(multi.getFilesystemName("file"));
		}

		System.out.println(user);
		userDao.updateUser(user);
		User suser = userDao.selectUserId(user.getId());
		System.out.println(suser);
		return user.getId();
	}

}
