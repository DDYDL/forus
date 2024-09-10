package service.recruit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.recruit.RecruitApplyDao;
import dao.recruit.RecruitApplyDaoImpl;
import dto.Recruit_apply;
import dto.User;

public class RecruitApplyServiceImpl implements RecruitApplyService {
	private RecruitApplyDao recruitApplyDao;
	public RecruitApplyServiceImpl() {
		recruitApplyDao = new RecruitApplyDaoImpl();
	}
	
	@Override
	public void insertRecruit_apply(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		// request session의 인자 user
		User user = (User)request.getSession().getAttribute("user");
		Integer post_id = Integer.parseInt(request.getParameter("post_id"));
		
		List<Recruit_apply> applys = recruitApplyDao.selectRecruit_apply(user.getId());
		for(Recruit_apply apply: applys) {
			if(apply.getPost_id()==post_id) throw new Exception("이미 지원한 구인글입니다");
		}
		
		Recruit_apply apply = new Recruit_apply();
		apply.setUser_id(user.getId());
		apply.setPost_id(post_id);
		
		// 현재 날짜 구하기
		LocalDateTime now = LocalDateTime.now();
		// 날짜 형식 지정
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		apply.setApply_time(now.format(formatter)); // 등록일 설정
		
		apply.setApply_title(request.getParameter("apply_title"));
		apply.setApply_content(request.getParameter("apply_content"));
		apply.setApply_status("지원완료");
		
		System.out.println("apply:" + apply);
		recruitApplyDao.insertRecruit_apply(apply);
	}

}
