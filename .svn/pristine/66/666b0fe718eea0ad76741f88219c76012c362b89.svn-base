package org.tpri.djcom.manager.pub;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.dao.condition.Condition;
import org.tpri.djcom.dao.condition.DaoPara;
import org.tpri.djcom.entity.pub.NoticeRecord;
import org.tpri.djcom.entity.zbsc.Article;

/**
 * @description 通知通告发送管理类
 * @author zhaozijing
 * @since 2015-06-23
 */

@Repository("NoticeRecordManager")
public class NoticeRecordManager extends ManagerBase {
	private static boolean initialized = false;
    public  void initialize() {
        if (initialized)
            return;
        initialized =  true;
        ObjectRegister.registerClass(ObjectType.PUB_NOTICE_RECORD, NoticeRecord.class);
    }

    /**
     * 根据通知通告ID获取通知对象集合
     * @param noticeId
     * @return
     */
    public List<NoticeRecord> getNoticeRecordByNoticeId(String noticeId,String loginUserOrg){
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(NoticeRecord.class);
		daoPara.addCondition(Condition.EQUAL("noticeId", noticeId));
		if(!StringUtils.isEmpty(loginUserOrg)){
			daoPara.addCondition(Condition.EQUAL("sendPartyId", loginUserOrg));
		}
        List list = dao.loadList(daoPara);
        return list;
    }
    
    public List<NoticeRecord> getNoticeRecordByNoticeId(String noticeId){
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(NoticeRecord.class);
		daoPara.addCondition(Condition.EQUAL("noticeId", noticeId));
        List list = dao.loadList(daoPara);
        return list;
    }
    
    /**
     * 删除通知对象
     * @param noticeId
     * @return
     */
    public boolean deleteNoticeRecordByNoticeId(String noticeId){
    	DaoPara daoPara = new DaoPara();
    	Class clazz = ObjectRegister.getClassByClassType(ObjectType.PUB_NOTICE_RECORD);
    	daoPara.setClazz(clazz);
    	daoPara.addCondition(Condition.EQUAL("noticeId", noticeId));
    	dao.delete(daoPara);
        return true;
    }
}
