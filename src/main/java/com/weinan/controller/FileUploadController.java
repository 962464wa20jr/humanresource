package com.weinan.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.weinan.common.result.CommResult;
import com.weinan.service.UserService;

@RestController
public class FileUploadController {
	
	@Resource
	private UserService userService;
	
	@PostMapping("/imageUpload")
	//@RequiresPermissions("user:uploadImage")
	public CommResult imageUpload(@RequestParam(value="file",required=false)MultipartFile file,HttpServletRequest request) throws IllegalStateException, IOException {
		 String uuid = UUID.randomUUID().toString().trim();
	        String fileN=file.getOriginalFilename();
	        int index=fileN.indexOf(".");
	        String fileName=uuid+fileN.substring(index);
	        try {
	            File fileMkdir=new File("E:\\VueExample\\humanresource\\src\\assets\\avarter");
	            
	            if(!fileMkdir.exists()) {
	                fileMkdir.mkdir();
	            }
	            FileOutputStream os = new FileOutputStream(fileMkdir.getPath()+"\\"+fileName);
	            InputStream in = file.getInputStream();
	            int b = 0;
	            while((b=in.read())!=-1){ //读取文件 
	                os.write(b);
	            }
	            os.flush(); //关闭流 
	            in.close();
	            os.close();
	        } catch (Exception e) {
	            return CommResult.error("上传失败");
	        }
		return CommResult.ok();
		
	}

}
