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
	public List<Hospital_qna> selectHospitalQnaList(Integer hospitalId) {
		return hospitalQnaDao.findHospitalQnaListByHospitalId(hospitalId);
	}
}
