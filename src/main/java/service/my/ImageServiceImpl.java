package service.my;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

public class ImageServiceImpl implements ImageService {

	public void imageView(HttpServletRequest request, OutputStream out, String filename, String type) throws Exception {
		FileInputStream fis = null;
		String path = request.getServletContext().getRealPath("upload" + File.separator + type);
		System.out.println("Image Path: " + path);

		try {
			fis = new FileInputStream(new File(path, filename));
			byte[] buff = new byte[4096];
			int len;
			while((len=fis.read(buff))>0) {
				out.write(buff,0,len);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(fis!=null) fis.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
