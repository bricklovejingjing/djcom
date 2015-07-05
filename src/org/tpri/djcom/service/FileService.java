package org.tpri.djcom.service;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.tpri.djcom.controller.BaseController;
import org.tpri.djcom.entity.sys.EnvironmentId;
import org.tpri.djcom.entity.zbsc.UploadFileData;
import org.tpri.djcom.manager.zbsc.UploadFileDataManager;
import org.tpri.djcom.service.sys.EnvironmentService;
import org.tpri.djcom.util.BaseConfig;
import org.tpri.djcom.util.DateUtil;
import org.tpri.djcom.util.FileUtil;
import org.tpri.djcom.util.UUIDUtil;

/**
 * @description 文件服务类
 * @author 易文俊
 * @since 2015-05-04
 */

@Service("FileService")
public class FileService {
	public Logger logger = Logger.getLogger(FileService.class);
	
	@Autowired
	UploadFileDataManager uploadFileDataManager;
	
	@Autowired
	private EnvironmentService environmentService;

	public String addUploadFileData(MultipartFile mf,int filesStorageType,String filesRootPath) throws IOException {
		String filePath="";
		String fileName=mf.getOriginalFilename();
		if(filesStorageType==0){
			UploadFileData uploadFileData=new UploadFileData();
			String fileDataId=UUIDUtil.id();
			uploadFileData.setId(fileDataId);
			uploadFileData.setName(fileName);
			uploadFileData.setFileData(mf.getBytes());
			uploadFileData.setFileSize(mf.getSize());
			uploadFileData.setPostfix(FileUtil.getPostfix(fileName));
			uploadFileData.setCreateTime(new Timestamp(System.currentTimeMillis()));
			uploadFileDataManager.add(uploadFileData);
		}else if(filesStorageType==1){
			String date=DateUtil.getNowDate();
			String uuid=UUIDUtil.id();
			filePath=date+File.separator+uuid+"_"+fileName;
			FileUtil.writeFile(filesRootPath+File.separator+date, uuid+"_"+fileName, mf.getBytes(), false);
		}
		return filePath;
	}

	/**
	 * 获取文件数据
	 */
	public UploadFileData getUploadFileDataById(String fileDataId){
		UploadFileData uploadFileData=uploadFileDataManager.getUploadFileDataById(fileDataId);
		return uploadFileData;
	}
	public boolean deleteUploadFileData(String id) {
		uploadFileDataManager.deleteUploadFileData(id);
		return true;
	}
}
