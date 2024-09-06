package service.my;

import java.util.List;

import dao.my.PetDao;
import dto.Pet;

public class MyPetListImpl implements MyPetListService {

	@Override
	public List<Pet> getPetByUserid(String user_id) {
		System.out.println("user_id: "+user_id);
		//return PetDao.findPetByUserid(user_id);
		return null;
	}

}
