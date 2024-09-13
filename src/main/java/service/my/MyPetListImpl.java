package service.my;

import java.util.List;

import dao.my.PetDao;
import dao.my.PetDaoImpl;
import dto.Pet;

public class MyPetListImpl implements MyPetListService {

	private PetDao petDao;

	public MyPetListImpl() {
		this.petDao = new PetDaoImpl();
	}

	@Override
	public List<Pet> getPetByUserid(Integer user_id) throws Exception {
		List<Pet> petList = petDao.selectPetList(user_id);
		if(petList==null)throw new Exception("펫 리스트 없음");
		return petList;

	}

}
