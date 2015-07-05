package org.tpri.djcom.manager.zbsc;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.dao.condition.Condition;
import org.tpri.djcom.dao.condition.DaoPara;
import org.tpri.djcom.entity.zbsc.Praise;

/**
 * @description 点赞和收藏管理类
 * @author 易文俊
 * @since 2015-05-04
 */

@Repository("PraiseManager")
public class PraiseManager extends ManagerBase {
	private static boolean initialized = false;

    public  void initialize() {
        if (initialized)
            return;
        initialized =  true;
        ObjectRegister.registerClass(ObjectType.ZBSC_PRAISE, Praise.class);
    }
    /**
     * 获取用户对某文章的点赞或收藏
     * @return
     */
    public Praise getPraise(String userId, String articleId,int type)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(Praise.class);
		daoPara.addCondition(Condition.EQUAL("userId", userId));
		daoPara.addCondition(Condition.EQUAL("articleId", articleId));
		daoPara.addCondition(Condition.EQUAL("type", type));
        List list = dao.loadList(daoPara);
        if(list!=null&&list.size()>0){
        	return (Praise)list.get(0);
        }
        return null;
    }
    /**
     * 获取文章的点赞或收藏总数
     * @return
     */
    public int getTotalPraiseByArticleId(String articleId,int type)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(Praise.class);
		daoPara.addCondition(Condition.EQUAL("articleId", articleId));
		daoPara.addCondition(Condition.EQUAL("type", type));
		int total=dao.getTotalCount(daoPara);
        return total;
    }
    /**
     * 删除点赞或收藏总数
     * @return
     */
    public boolean deletePraise(String id)  {
    	return super.delete(id, ObjectType.ZBSC_COMMENT);
    }
}
