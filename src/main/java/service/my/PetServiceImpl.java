package service.my;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.my.PetDao;
import dao.my.PetDaoImpl;
import dto.Pet;
import dto.Recruit_post;
import dto.User;

public class PetServiceImpl implements PetService {

	private PetDao petDao;

	public PetServiceImpl() {
		petDao = new PetDaoImpl();
	}
	
	@Override
	public void insertPet(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		String path = request.getServletContext().getRealPath("upload");
		int size = 10*1024*1024;
		
		MultipartRequest multi = new MultipartRequest(request,path,size,"utf-8",
				new DefaultFileRenamePolicy());
		
		Pet pet = new Pet();
		pet.setPet_id(pet.getPet_id());
		
		request.setCharacterEncoding("utf-8");
		// request session의 인자 user
		User user = (User)request.getSession().getAttribute("user");
		// RecruitWriting에서 파라미터로 넘어온 value들을 지정
		pet.setUser_id(user.getId());
		
		pet.setPet_name(multi.getParameter("pet_name"));
		pet.setPet_species(multi.getParameter("pet_species"));
		pet.setPet_breed(multi.getParameter("pet_breed"));
		//pet.setPet_num(Integer.parseInt(multi.getParameter("pet_num")));
		pet.setPet_gender(multi.getParameter("pet_gender"));
		pet.setPet_age(Integer.parseInt(multi.getParameter("pet_age")));
		pet.setPet_memo(multi.getParameter("pet_memo"));
		System.out.println(pet);
		petDao.insertPet(pet);
		
	}


	@Override
	public Integer petModify(HttpServletRequest request) throws Exception {
		String path = request.getServletContext().getRealPath("upload");
		int size = 10*1024*1024;
		
		MultipartRequest multi = new MultipartRequest(request,path,size,"utf-8",
				new DefaultFileRenamePolicy());
		
		Pet pet = new Pet();
		pet.setPet_name(multi.getParameter("pet_name"));
		pet.setPet_species(multi.getParameter("pet_species"));
		pet.setPet_breed(multi.getParameter("pet_breed"));
		//pet.setPet_num(Integer.parseInt(multi.getParameter("pet_num")));
		pet.setPet_gender(multi.getParameter("pet_gender"));
		//pet.setPet_age(Integer.parseInt(multi.getParameter("pet_age")));
		pet.setPet_memo(multi.getParameter("pet_memo"));
		
		//파일 수정
		if(multi.getFile("file") != null) {
			pet.setPet_picture(multi.getFilesystemName("file"));
		}
		
		petDao.updatePet(pet);
		return pet.getPet_id();
	}

	@Override
	public Pet petDetail(Integer pet_id) throws Exception {
		Pet pet = petDao.selectPet(pet_id);
		if (pet == null)
			throw new Exception("펫 아이디 오류");
		return pet;
	}



}
