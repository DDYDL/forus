package service.recruit;

import java.util.List;

import dto.Recruit_post;

public interface SearchService {
	List<Recruit_post> getRecruit_postList(List<String> areas, List<String> species, Integer page) throws Exception;
	Integer getMaxPage(List<String> areas, List<String> species) throws Exception;
}
