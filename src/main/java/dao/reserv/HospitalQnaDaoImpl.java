package dao.reserv;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.Hospital_qna;
import util.MybatisSqlSessionFactory;

public class HospitalQnaDaoImpl implements HospitalQnaDao {

	private SqlSession sqlSession;

	public HospitalQnaDaoImpl() {
		sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
	}

	@Override
	public void insertHospitalQna(Hospital_qna hospitalQna) {
		try {
			sqlSession.insert("mapper.hospital_qna.insertQuestion", hospitalQna);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}

	}

	@Override
	public List<Hospital_qna> findHospitalQnaListByHospitalId(Integer hospitalId) {
		return sqlSession.selectList("mapper.hospital_qna.findHospitalQnaListByHospitalId" , hospitalId);
	}



}
