package dao.my;

import dto.Pet;

public interface PetDao {
	void insertPet(Pet pet) throws Exception;

	Pet selectPet(Integer pet_id) throws Exception;

	void updatePet(Pet pet) throws Exception;

}
