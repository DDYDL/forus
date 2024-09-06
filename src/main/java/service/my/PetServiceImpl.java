package service.my;

import javax.servlet.http.HttpServletRequest;

import dao.my.PetDao;
import dao.my.PetDaoImpl;
import dto.Pet;

public class PetServiceImpl implements PetService {
	
	private PetDao petDao;
	public PetServiceImpl() {
		petDao = new PetDaoImpl();
	}


	@Override
	public void petprofile(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer petModify(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void join(Pet pet) throws Exception {
		Pet spet = petDao.selectPet(pet.getPet_id());
		petDao.insertPet(pet);
		
	}

}
