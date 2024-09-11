package service.my;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Pet;

public interface PetService {
	void join(Pet pet) throws Exception;

	void petprofile(HttpServletRequest request) throws Exception;

	Integer petModify(HttpServletRequest request) throws Exception;
	
	List<Pet> selectPetList(Integer user_id) throws Exception;

}
