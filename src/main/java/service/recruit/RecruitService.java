package service.recruit;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Pet;
import dto.Recruit_post;
import dto.User;

public interface RecruitService {
	// 작성한 Recruit_post를 데이터베이스에 저장하기
	void recruit_postWrite(HttpServletRequest request) throws Exception;
	// 해당하는 Recruit_post 객체 얻어오기
	Recruit_post recruit_postDatail(Integer post_id) throws Exception;
	// 로그인 되어있을 경우 수정하기 버튼 띄우기
	boolean checkModify(Integer user_id, Integer post_id) throws Exception;
	// 이미 작성된 Recruit_post 수정하기
	Integer recruit_postModify(HttpServletRequest request) throws Exception;
	// user id로 해당하는 pet 리스트 가져오기
	List<Pet> getPetList(Integer user_id) throws Exception;
	// user id로 해당하는 user 가져오기
	User selectUserId(Integer user_id) throws Exception;
}
