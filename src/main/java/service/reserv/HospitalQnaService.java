package service.reserv;

import java.util.List;

import dao.reserv.HospitalQnaDao;
import dto.Hospital_qna;

public interface HospitalQnaService {

	public void insertHospitalQuestion(Hospital_qna hospitalQna);



	public List<Hospital_qna> getHospitalQnaList(Integer hospitalId);

	Hospital_qna createHospitalQna(String hospitalId, Integer userId, String userEmail, String questionContent);

	boolean insertHospitalAnswer(int qnaId, Hospital_qna hospitalQna);
}
