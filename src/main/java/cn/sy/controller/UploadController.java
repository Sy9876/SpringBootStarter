package cn.sy.controller;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	private static Logger logger = LoggerFactory.getLogger(UploadController.class);

	private static final String FILE_SAVE_PATH = "D:\\";
	
    @RequestMapping("/upload.do")
    public String upload(
			@RequestParam(value="file", required=true) MultipartFile file) {

    	logger.info("start.");
    	
    	if(file.isEmpty()) {
    		logger.info("file is empty.");
    		return "false";
    	}
    	try {
    		String fileName = file.getOriginalFilename();
            int size = (int) file.getSize();
            logger.info(fileName + "-->" + size);
    		
            File dst = new File(FILE_SAVE_PATH + "uploadFiles/" + fileName);
            logger.info("upload.do.  save to ", dst.getAbsolutePath());
            if(dst.getParentFile().isDirectory()) {
            	file.transferTo(dst);
            }
            
            
    		logger.info("upload.do.  save ok ");
		} catch (Exception e) {

			logger.error("", e);
		}

    	logger.info("end");
    	return "uploadOk";
    }

}
