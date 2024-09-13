package util;

import java.io.File;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class FileUploadRename implements FileRenamePolicy {

	private String savefilename;
	public FileUploadRename() {}
	
	public FileUploadRename(String savefilename) {
		this.savefilename = savefilename;
	}

	@Override
	public File rename(File file) {
        String parentDir = file.getParent();
        String fileName = file.getName();
        String fileExt = "";
        int i = -1;
        if((i=fileName.lastIndexOf("."))!=-1) {
        	fileExt = fileName.substring(i);
        	fileName = fileName.substring(0,i);
        }
        fileName = savefilename;
        fileName = parentDir + System.getProperty("file.separator")+fileName+fileExt;
        
        File newfile = new File(fileName);
        return newfile;
	}

}
