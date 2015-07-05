package org.tpri.djcom.manager.zbsc;

import java.util.Map;

import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.entity.zbsc.UploadFileData;

/**
 * @description 文件数据管理类
 * @author 易文俊
 * @since 2015-05-04
 */

@Repository("UploadFileDataManager")
public class UploadFileDataManager extends ManagerBase {
	private static boolean initialized = false;

    public  void initialize() {
        if (initialized)
            return;
        initialized =  true;
        ObjectRegister.registerClass(ObjectType.ZBSC_UPLOADFILEDATA, UploadFileData.class);
    }
    /**
     * 获取文件数据
     * @return
     */
    public UploadFileData getUploadFileDataById(String id)  {
    	UploadFileData uploadFileData=(UploadFileData)super.load(id, ObjectType.ZBSC_UPLOADFILEDATA);
    	return uploadFileData;
    }
    /**
     * 更新文件数据
     * @return
     */
    public boolean editUploadFileData(String id, Map<String, Object> fieldValues)  {
    	return super.update(id, ObjectType.ZBSC_UPLOADFILEDATA, fieldValues);
    }
    /**
     * 删除文件数据
     * @return
     */
    public boolean deleteUploadFileData(String id)  {
    	return super.delete(id, ObjectType.ZBSC_UPLOADFILEDATA);
    }
}
