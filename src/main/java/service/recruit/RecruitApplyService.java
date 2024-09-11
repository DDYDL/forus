package service.recruit;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Recruit_apply;

public interface RecruitApplyService {
	// Recruit_apply 작성하기
	void insertRecruit_apply(HttpServletRequest request) throws Exception;
	// post_id가 같은 Recruit_apply 리스트 가져오기
	List<Recruit_apply> selectRecruit_applyList(Integer post_id) throws Exception;
	// apply_id로 해당하는 Recruit_apply 하나 가져오기
	Recruit_apply selectRecruit_apply(Integer apply_id) throws Exception;
}
