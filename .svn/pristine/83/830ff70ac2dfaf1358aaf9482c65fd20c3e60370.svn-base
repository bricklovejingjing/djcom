package org.tpri.djcom.manager.pub;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.dao.condition.DaoPara;
import org.tpri.djcom.dao.condition.Order;
import org.tpri.djcom.entity.pub.Notice;
import org.tpri.djcom.entity.result.QueryResultArticle;
import org.tpri.djcom.entity.uam.User;
import org.tpri.djcom.entity.zbsc.Article;
import org.tpri.djcom.entity.zbsc.Information;
import org.tpri.djcom.util.PageBean;
/**
 * @description 通知通告管理类
 * @author zhaozijing
 * @since 2015-06-16
 */

@Repository("NoticeManager")
public class NoticeManager extends ManagerBase {
	private static boolean initialized = false;
    public  void initialize() {
        if (initialized)
            return;
        initialized =  true;
        ObjectRegister.registerClass(ObjectType.PUB_NOTICE, Notice.class);
    }
    
    /**
     * 获取通知通告列表
     * @return
     */
    public List<Notice> getNoticeList()  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(Notice.class);
		daoPara.addOrder(Order.desc("createtime"));
		List list = dao.loadList(daoPara);
        return list;
    }
    
    /**
     * 获取我的通知通告展示
     * @param loginUser
     * @param pager
     * @return
     */
    public List<Notice> loadNoticesByMe(User loginUser,PageBean pager)  {
    	DaoPara daoPara = new DaoPara();
    	StringBuffer sql = new StringBuffer();
    	List<Notice> notices = new ArrayList<Notice>();
    	sql.append("SELECT pn.id,pn.name,pn.createtime,IF(FIND_IN_SET('").append(loginUser.getName()).append("',pnr.reader),'已读','未读') reader,IF(pnr.readstatus='1','已读','未读') readstatus\n");
    	sql.append("FROM pub_notice AS pn ,pub_notice_record AS pnr \n");
    	sql.append("WHERE pn.id=pnr.noticeid  AND pn.status=1\n");
    	sql.append("AND (pnr.sendnoticetype='pub' OR pnr.sendpartyid='").append(loginUser.getCcpartyId()).append("')\n");
    	sql.append("ORDER BY pn.createtime DESC\n");
    	sql.append("limit ").append(pager.getStart()).append(" , ").append(pager.getPageSize());
    	List list = new ArrayList();
    	try{
    		Session session=dao.getCurrentSession();
    		Query query=session.createSQLQuery(sql.toString());
    		list=query.list();
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		for(int i=0;i<list.size();i++){
    			Object[] objs = (Object[])list.get(i);
    			Notice notice = new Notice();
    			notice.setId(String.valueOf(objs[0]));
    			notice.setName(String.valueOf(objs[1]));
    			notice.setCreateTime(new Timestamp(sdf.parse(String.valueOf(objs[2])).getTime()));
    			if("已读".equals(String.valueOf(objs[3])) || "已读".equals(String.valueOf(objs[4]))){
    				notice.setIsRead("true");
    			}else{
    				notice.setIsRead("false");
    			}
    			notices.add(notice);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		logger.error("查询我的通知列表异常："+e.getMessage());
    	}
        return notices;
    }
    
   /**
    * 根据ID获取通知通告详情
    * @param id
    * @return
    */
    public Notice getNoticeById(String id)  {
    	Notice notice=(Notice)super.load(id, ObjectType.PUB_NOTICE);
    	return notice;
    }
    
    

}
