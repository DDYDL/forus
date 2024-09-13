package service.my;

import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

public interface ImageService {
	public void imageView(HttpServletRequest request, OutputStream out, String filename, String type) throws Exception;
}

