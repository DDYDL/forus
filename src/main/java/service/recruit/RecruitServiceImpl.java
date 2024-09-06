package service.recruit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.my.PetDao;
import dao.my.PetDaoImpl;
import dao.my.UserDao;
import dao.my.UserDaoImpl;
import dao.recruit.RecruitDao;
import dao.recruit.RecruitDaoImpl;
import dto.Pet;
import dto.Recruit_post;
import dto.User;

public class RecruitServiceImpl implements RecruitService {
	private RecruitDao recruitDao;
	private PetDao petDao;
	private UserDao userDao;
	public RecruitServiceImpl() {
		recruitDao = new RecruitDaoImpl();
		petDao = new PetDaoImpl();
		userDao = new UserDaoImpl();
	}

	@Override
	public void recruit_postWrite(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		// request session의 인자 user
		User user = (User)request.getSession().getAttribute("user");
		// RecruitWriting에서 파라미터로 넘어온 value들을 지정
		Recruit_post post = new Recruit_post();
		post.setUser_id(user.getId());
		
		// 해당 user의 pet 리스트 가져오기
		List<Pet> pets = getPetList(user.getId());
		for(Pet pet: pets) {
			if(pet.getPet_name().equals(request.getParameter("pet_name"))) {
				post.setPet_id(pet.getPet_id());
			}
		}
		
		// 현재 날짜 구하기
		LocalDate now = LocalDate.now();
		// 날짜 형식 지정
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		post.setPost_time(now.format(formatter)); // 등록일 설정
		
		post.setPost_title(request.getParameter("post_title"));
		post.setPost_content(request.getParameter("post_content"));
		post.setPost_address(request.getParameter("post_address"));
		post.setPost_form(request.getParameter("post_form"));
		post.setPost_pay(request.getParameter("post_pay"));
		post.setPost_start_time(request.getParameter("post_start_time"));
		post.setPost_end_time(request.getParameter("post_end_time"));
		post.setPost_status("게시중");
		System.out.println("post:"+post);
		recruitDao.insertRecruit_post(post);
	}

	@Override
	public Recruit_post recruit_postDatail(Integer post_id) throws Exception {
		Recruit_post post = recruitDao.selectRecruit_post(post_id);
		if(post==null) throw new Exception("구인 글 없음");
		return post;
	}

	@Override
	public boolean checkApply(Integer user_id, Integer post_id) throws Exception {
		Recruit_post post = recruitDao.selectRecruit_post(post_id);
		if(post==null) throw new Exception("구인 글 없음");
		
		// 현재 recruit_post와 user id가 같으면 수정하기 버튼 활성화
		if(post.getUser_id().equals(user_id)) return true;
		return false;
	}

	@Override
	public List<Pet> getPetList(Integer user_id) throws Exception {
		List<Pet> pets = petDao.selectPetList(user_id);
		if(pets==null) throw new Exception("애완동물 없음");
		return pets;
	}

	@Override
	public User selectUserId(Integer user_id) throws Exception {
		User user = userDao.selectUserId(user_id);
		if(user==null) throw new Exception("사용자 없음");
		return null;
	}

}
