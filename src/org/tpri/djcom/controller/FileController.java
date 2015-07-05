package org.tpri.djcom.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.tpri.djcom.entity.sys.EnvironmentId;
import org.tpri.djcom.entity.zbsc.UploadFileData;
import org.tpri.djcom.service.FileService;
import org.tpri.djcom.service.sys.EnvironmentService;
import org.tpri.djcom.util.BaseConfig;
import org.tpri.djcom.util.FileUtil;
import org.tpri.djcom.util.ResponseUtils;
/**
 * @description 文件控制器
 * @author 易文俊
 * @since 2015-05-03
 */
@Controller
@RequestMapping("/file")
public class FileController extends BaseController {
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private EnvironmentService environmentService;
	
	/**
	 * 上传文件
	 * @throws IOException 
	 */
	@RequestMapping("uploadFile")
	@ResponseBody
	public String uploadFile(HttpServletRequest request) throws IOException {
		logger.debug("FileController uploadFile begin");
		MultipartFile mf = getUploadFile(request);
		logger.debug("OriginalFilename:"+mf.getOriginalFilename());
		logger.debug("Name:"+mf.getName());
		logger.debug("Size:"+mf.getSize());
		Integer filesStorageType=(Integer)getEnvironmentValueById(EnvironmentId.FILES_STORAGE_TYPE);
		String filesRootPath=(String)getEnvironmentValueById(EnvironmentId.FILES_ROOT_PATH);
		String filePath=fileService.addUploadFileData(mf,filesStorageType,filesRootPath);
		
		logger.debug("FileController uploadFile end");
		return filePath;
	}
	/**
	 * 下载文件
	 * @throws IOException 
	 */
	@RequestMapping("getFileData")
	@ResponseBody
	public void getFileData(HttpServletRequest request, HttpServletResponse response) throws IOException{
		logger.debug("FileController getFileData begin");
		
		String filePath=getString(request,"filePath");
		int storageType=getInt(request,"storageType");
		if(storageType==1){
			String filesRootPath=(String)getEnvironmentValueById(EnvironmentId.FILES_ROOT_PATH);
			FileUtil.copyFile(filesRootPath+File.separator+filePath,response.getOutputStream());
		}else{
			UploadFileData fileData=fileService.getUploadFileDataById(filePath);
			ResponseUtils.putFileResponse(request, response, fileData.getName(), null);
			response.getOutputStream().write(fileData.getFileData());
		}
		
		logger.debug("FileController getFileData end");
	}
}
