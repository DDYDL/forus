package service.my;

import java.util.List;

import dto.Pet;

public interface MyPetListService {
	List<Pet> getPetByUserid(String user_id);
}
