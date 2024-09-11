package dao.recruit;

import java.util.List;

import dto.Recruit_apply;

public interface RecruitApplyDao {
	// Recruit_apply 데이터베이스에 insert
	void insertRecruit_apply(Recruit_apply apply) throws Exception;
	// user_id에 해당하는 Recruit_apply 데이터베이스에서 select
	List<Recruit_apply> selectRecruit_apply(Integer user_id) throws Exception;
	// post_id에 해당하는 Recruit_apply 데이터베이스에서 select
	List<Recruit_apply> selectRecruit_applyList(Integer post_id) throws Exception;
	// apply_id에 해당하는 Recruit_apply 데이터베이스에서 select
	Recruit_apply selectRecruit_applyId(Integer apply_id) throws Exception;
	// apply_id에 해당하는 Recruit_apply 지원상태 수정하기
	void updateRecruit_apply_status(Recruit_apply apply) throws Exception;
}
