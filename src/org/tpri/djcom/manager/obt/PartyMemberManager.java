package org.tpri.djcom.manager.obt;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.dao.condition.Condition;
import org.tpri.djcom.dao.condition.DaoPara;
import org.tpri.djcom.dao.condition.Order;
import org.tpri.djcom.entity.obt.PartyMember;


/**
 * @description 党组织管理类
 * @author 常华荣
 * @since 2015-05-04
 */

@Repository("PartyMemberManager")
public class PartyMemberManager extends ManagerBase {
	private static boolean initialized = false;

    public  void initialize() {
        if (initialized)
            return;
        initialized =  true;
        ObjectRegister.registerClass(ObjectType.OBT_PARTY_MEMBER, PartyMember.class);
    }
    /**
     * 获取党员
     * @return
     */
    public PartyMember getPartyMember(String id)  {
    	Object obj=this.loadOne(ObjectType.OBT_PARTY_MEMBER, new String[] { "id" }, new Object[] { id });
    	if (obj == null) {
			return null;
		}
		PartyMember PartyMember=(PartyMember)obj;
        return PartyMember;
    }
    
    /**
     * 获取党员列表
     * @return
     */
    public List<PartyMember> getPartyMemberList(Integer start, Integer limit)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(PartyMember.class);
		
		if (start != null && limit != null) {
			daoPara.setStart(start);
			daoPara.setLimit(limit);
		}
		daoPara.addOrder(Order.asc("id"));
        List list = dao.loadList(daoPara);
        return list;
    }
    /**
     * 获取所有党员列表
     * @return
     */
    public List<PartyMember> getAllPartyMember()  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(PartyMember.class);
		daoPara.addOrder(Order.asc("id"));
        List list = dao.loadList(daoPara);
        return list;
    }
    
    
    /**
     * 获取党员列表
     * @param ccpartId 
     * @return
     */
    public List<PartyMember> loadPartyMemberByCcpartyId(Integer start, Integer limit, String ccpartyId)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(PartyMember.class);
		
		if (start != null && limit != null) {
			daoPara.setStart(start);
			daoPara.setLimit(limit);
		}
		daoPara.addCondition(Condition.EQUAL("ccparty.id", ccpartyId));
		daoPara.addOrder(Order.asc("id"));
        List list = dao.loadList(daoPara);
        return list;
    }
    /**
     * 获取某组值下的党员列表
     * @param ccpartId 
     * @return
     */
    public List<PartyMember> loadPartyMemberByPartyId(String ccpartyId)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(PartyMember.class);
		daoPara.addCondition(Condition.EQUAL("ccparty.id", ccpartyId));
		daoPara.addOrder(Order.asc("ccparty.id"));
        List list = dao.loadList(daoPara);
        return list;
    }
    
    /**
     * 根据身份证号码获取党员列表
     * @param idNumber
     * @return
     */
    public List<PartyMember> loadPartyMemberByIdNumber(String idNumber)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(PartyMember.class);
		daoPara.addCondition(Condition.EQUAL("idNumber", idNumber));
        List list = dao.loadList(daoPara);
        return list;
    }
    
}