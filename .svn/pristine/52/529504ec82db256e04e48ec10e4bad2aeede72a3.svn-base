package org.tpri.djcom.manager.zbsc;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.dao.condition.Condition;
import org.tpri.djcom.dao.condition.DaoPara;
import org.tpri.djcom.entity.zbsc.UploadFile;

/**
 * @description 文章文件列表管理类
 * @author 易文俊
 * @since 2015-05-04
 */

@Repository("UploadFileManager")
public class UploadFileManager extends ManagerBase {
	private static boolean initialized = false;

    public  void initialize() {
        if (initialized)
            return;
        initialized =  true;
        ObjectRegister.registerClass(ObjectType.ZBSC_UPLOADFILE, UploadFile.class);
    }
    /**
     * 获取某文章的文件列表
     * @return
     */
    public List<UploadFile> getUploadFileList(String articleId)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(UploadFile.class);
		daoPara.addCondition(Condition.EQUAL("articleId", articleId));
        List list = dao.loadList(daoPara);
        return list;
    }
    /**
     * 获取某文章的某种类型文件列表
     * @return
     */
    public List<UploadFile> getUploadFileList(String articleId,int fileType)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(UploadFile.class);
		daoPara.addCondition(Condition.EQUAL("articleId", articleId));
		daoPara.addCondition(Condition.EQUAL("fileType", fileType));
        List list = dao.loadList(daoPara);
        return list;
    }
    /**
     * 获取文件记录
     * @return
     */
    public UploadFile getUploadFileById(String id)  {
    	UploadFile uploadFile=(UploadFile)super.load(id, ObjectType.ZBSC_UPLOADFILE);
    	return uploadFile;
    }
    /**
     * 更新文件记录
     * @return
     */
    public boolean editUploadFile(String id, Map<String, Object> fieldValues)  {
    	return super.update(id, ObjectType.ZBSC_UPLOADFILE, fieldValues);
    }
    /**
     * 删除文件记录
     * @return
     */
    public boolean deleteUploadFile(String id)  {
    	return super.delete(id, ObjectType.ZBSC_UPLOADFILE);
    }
}
