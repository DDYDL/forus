package dao.my;

import java.util.List;

import dto.Pet;

public interface PetDao {
	void insertPet(Pet pet) throws Exception;

	Pet selectPet(Integer pet_id) throws Exception;

	void updatePet(Pet pet) throws Exception;
	
	// user_id에 해당하는 Pet 리스트 가져오기
	List<Pet> selectPetList(Integer user_id) throws Exception;
}
