package dao.recruit;

import dto.Recruit_post;

public interface RecruitDao {
	void insertRecruit_post(Recruit_post post) throws Exception;
	Recruit_post selectRecruit_post(Integer num) throws Exception;
}