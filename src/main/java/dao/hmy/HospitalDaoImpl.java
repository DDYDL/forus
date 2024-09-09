package dao.hmy;

import org.apache.ibatis.session.SqlSession;

import dto.Hospital;
import util.MybatisSqlSessionFactory;

public class HospitalDaoImpl implements HospitalDao {

	private SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public void insertHospital(Hospital hospital) throws Exception {
		// Hospital shospital = selectHospital(hospital.getH_id());
		sqlSession.insert("mapper.Hospital.insertHospital", hospital);
		sqlSession.commit();

	}

	@Override
	public Hospital selectHospital(Integer h_id) throws Exception {
		return sqlSession.selectOne("mapper.hospital.selectHospital", h_id);
	}

	@Override
	public void updateHospital(Hospital hospital) throws Exception {
		sqlSession.update("mapper.Hospital.updateHospital", hospital);
		sqlSession.commit();

	}

}
