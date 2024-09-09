package dao.my;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.Pet;
import util.MybatisSqlSessionFactory;

public class PetDaoImpl implements PetDao {
	
	private SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public void insertPet(Pet pet) throws Exception {
		sqlSession.insert("mapper.Pet.insertPet");
		sqlSession.commit();

	}

	@Override
	public Pet selectPet(Integer pet_id) throws Exception {
		return sqlSession.selectOne("mapper.Pet.selectPet", pet_id);
	}

	@Override
	public void updatePet(Pet pet) throws Exception {
		sqlSession.update("mapper.pet.updatePet",pet);
		sqlSession.commit();
		
	}
	
	@Override
	public List<Pet> selectPetList(Integer user_id) throws Exception {
		return sqlSession.selectList("mapper.pet.selectPetList", user_id);
	}

}
