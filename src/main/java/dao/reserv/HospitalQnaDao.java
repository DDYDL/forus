package dao.reserv;

import java.util.List;

import dto.Hospital_qna;

public interface HospitalQnaDao {

	public void insertHospitalQna(Hospital_qna hospitalQna);

	public List<Hospital_qna> findHospitalQnaListByHospitalId(Integer hospitalId);

	public void insertHospitalAnswer(int qnaId, Hospital_qna hospitalQna);






}
