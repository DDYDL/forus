package service.hmy;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.hmy.HospitalDao;
import dao.hmy.HospitalDaoImpl;
import dto.Hospital;

public class HospitalServiceImpl implements HospitalService {

	private HospitalDao hospitalDao;

	public HospitalServiceImpl() {
		this.hospitalDao = new HospitalDaoImpl();
	}
	

	@Override
	public void hospitalNew(HttpServletRequest request) throws Exception {
		String path = request.getServletContext().getRealPath("upload");
		int size = 10*1024*1024;
		
		MultipartRequest multi = new MultipartRequest(request,path,size,"utf-8",
				new DefaultFileRenamePolicy());
		
		Hospital hospital = new Hospital();

	}

	@Override
	public Integer hospitalModify(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
