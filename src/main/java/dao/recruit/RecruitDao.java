package dao.recruit;

import java.util.List;

import dto.Pet;
import dto.Recruit_post;

public interface RecruitDao {
	// Recruit_post 데이터베이스에 insert
	void insertRecruit_post(Recruit_post post) throws Exception;
	// Recruit_post 데이터베이스에서 select
	Recruit_post selectRecruit_post(Integer post_id) throws Exception;
	// Recruit_post 수정시 데이터베이스 update
	void updateRecruit_post(Recruit_post post) throws Exception;
}