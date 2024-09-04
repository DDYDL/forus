package service.recruit;

import javax.servlet.http.HttpServletRequest;

import dto.Recruit_post;

public interface RecruitService {
	void recruit_postWrite(HttpServletRequest request) throws Exception;
	Recruit_post recruit_postDatail(Integer num) throws Exception;
}
