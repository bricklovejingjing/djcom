package org.tpri.djcom.manager.pub;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.dao.condition.Condition;
import org.tpri.djcom.dao.condition.DaoPara;
import org.tpri.djcom.dao.condition.Order;
import org.tpri.djcom.entity.pub.Message;
import org.tpri.djcom.entity.zbsc.ArticleCategory;
/**
 * @description 消息提醒管理类
 * @author yiwenjun
 * @since 2015-06-18
 */

@Repository("MessageManager")
public class MessageManager extends ManagerBase {
	private static boolean initialized = false;
    public  void initialize() {
        if (initialized)
            return;
        initialized =  true;
        ObjectRegister.registerClass(ObjectType.PUB_MESSAGE, Message.class);
    }
    
    /**
     * 获取某用户的消息提醒列表
     * @return
     */
    public List<Message> getMessageList(String userId,Integer status)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(Message.class);
		daoPara.addCondition(Condition.EQUAL("userId", userId));
		if(status!=null){
			daoPara.addCondition(Condition.EQUAL("status", status.intValue()));
		}
		daoPara.addOrder(Order.asc("status"));
		daoPara.addOrder(Order.desc("createTime"));
		List list = dao.loadList(daoPara);
        return list;
    }
    /**
     * 根据ID获取消息提醒
     * @return
     */
	public Message getMessageById(String id)  {
    	Object obj=this.loadOne(ObjectType.PUB_MESSAGE, new String[] { "id" }, new Object[] { id });
    	if (obj == null) {
			return null;
		}
    	Message o=(Message)obj;
        return o;
    }
	/**
     * 删除消息提醒
     * @return
     */
    public boolean deleteMessage(String id)  {
    	return super.delete(id, ObjectType.PUB_MESSAGE);
    }
    /**
     * 更新消息提醒
     * @return
     */
    public boolean updateMessage(String id, Map<String, Object> fieldValues) {
		super.update(id, ObjectType.PUB_MESSAGE, fieldValues);
		return true;
	}
    /**
	 * 全部标记已读
	 */
	public void setAllMessageReaded(String userId) {
		String hql="update PUB_MESSAGE a set a.STATUS=1 where a.USERID=?";
    	Object[] params= new Object[]{ userId};
        dao.updateNative(hql, params);
	}

}
