package service.reserv;

import java.util.List;

import dao.reserv.HospitalQnaDao;
import dao.reserv.HospitalQnaDaoImpl;
import dto.Hospital_qna;

public class HospitalQnaServiceImpl implements HospitalQnaService {

	private HospitalQnaDao hospitalQnaDao;

	public HospitalQnaServiceImpl() {
		hospitalQnaDao = new HospitalQnaDaoImpl();
	}

	@Override
	public void insertHospitalQuestion(Hospital_qna hospitalQna) {
		hospitalQnaDao.insertHospitalQna(hospitalQna);
	}



	@Override
	public List<Hospital_qna> getHospitalQnaList(Integer hospitalId, int limit, int offset) {
		return hospitalQnaDao.findHospitalQnaListByHospitalId(hospitalId, limit, offset);
	}

	@Override
	public Hospital_qna createHospitalQna(String hospitalId, Integer userId, String userEmail, String questionContent) {
		Hospital_qna hospitalQna = new Hospital_qna();
		hospitalQna.setH_id(Integer.parseInt(hospitalId));
		hospitalQna.setUser_id(userId);
		hospitalQna.setQ_writer(userEmail);
		hospitalQna.setQ_title(questionContent);

		return hospitalQna;
	}

	@Override
	public boolean insertHospitalAnswer(int qnaId, Hospital_qna hospitalQna) {
		try {
			hospitalQnaDao.insertHospitalAnswer(qnaId, hospitalQna);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
