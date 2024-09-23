package dao.reserv;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public List<Hospital_qna> findHospitalQnaListByHospitalId(Integer hospitalId, int limit, int offset) {
		Map<String, Object> params = new HashMap<>();
		params.put("hospitalId", hospitalId);
		params.put("limit", limit);
		params.put("offset", offset);
		return sqlSession.selectList("mapper.hospital_qna.findHospitalQnaListByHospitalId", params);
	}

	@Override
	public void insertHospitalAnswer(int qnaId, Hospital_qna hospitalQna) {

		try {
			Map<String, Object> params = new HashMap<>();
			params.put("qnaId", qnaId);
			params.put("hospitalQna", hospitalQna);

			sqlSession.update("mapper.hospital_qna.insertAnswer", params);
			sqlSession.commit();

		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
			throw new RuntimeException("답변 등록 중 오류가 발생했습니다.", e);
		} finally {
			sqlSession.close();
		}

	}

	@Override
	public Integer findManagerIdByHospitalId(Integer hospitalId) {

		try {
			return sqlSession.selectOne("mapper.hospital_qna.findManagerIdByHospitalId", hospitalId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("사용자 정보 조회 중 오류가 발생했습니다.", e);
		} finally {
			sqlSession.close();
		}

	}
}
