package service.recruit;

import javax.servlet.http.HttpServletRequest;

import dto.Recruit_post;

public interface RecruitService {
	// Recruit_post
	void recruit_postWrite(HttpServletRequest request) throws Exception;
	// Recruit_post 객체 얻어오기
	Recruit_post recruit_postDatail(Integer num) throws Exception;
	// 로그인 되어있을 경우 지원하기 버튼 띄우기
	Integer checkApply(String id, Integer post_id) throws Exception;
}
