package service.reserv;

import java.util.List;

import dao.reserv.HospitalQnaDao;
import dto.Hospital_qna;

public interface HospitalQnaService {

	public void insertHospitalQuestion(Hospital_qna hospitalQna);

	public List<Hospital_qna> selectHospitalQnaList(Integer hospitalId);

}
