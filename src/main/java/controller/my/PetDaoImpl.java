package controller.my;

import org.apache.ibatis.session.SqlSession;

import dao.my.PetDao;
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

}
