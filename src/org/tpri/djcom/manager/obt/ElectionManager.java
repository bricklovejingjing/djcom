package org.tpri.djcom.manager.obt;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.dao.condition.Condition;
import org.tpri.djcom.dao.condition.DaoPara;
import org.tpri.djcom.dao.condition.Order;
import org.tpri.djcom.entity.obt.Election;
import org.tpri.djcom.entity.obt.ElectionMember;

/**
 * @description 换届选举管理类
 * @author 易文俊
 * @since 2015-07-01
 */

@Repository("ElectionManager")
public class ElectionManager extends ManagerBase {
	private static boolean initialized = false;

	public void initialize() {
		if (initialized)
			return;
		initialized = true;
		ObjectRegister.registerClass(ObjectType.OBT_ELECTION, Election.class);
		ObjectRegister.registerClass(ObjectType.OBT_ELECTION_MEMBER, ElectionMember.class);
	}

	/**
	 * 获取某组织换届选举
	 * 
	 * @return
	 */
	public List<Election> getElectionList(Integer limit, Integer offset, String name, String ccpartyId) {
		DaoPara daoPara = new DaoPara();
		daoPara.setClazz(Election.class);
		if (name != null && !name.equals("")) {
			daoPara.addCondition(Condition.LIKE("name", name));
		}
		if (limit != null && offset != null) {
			daoPara.setStart(offset);
			daoPara.setLimit(limit);
		}
		daoPara.addCondition(Condition.EQUAL("ccpartyId", ccpartyId));
		daoPara.addOrder(Order.desc("createTime"));
		List list = dao.loadList(daoPara);
		return list;
	}

	/**
	 * 获取某组织换届选举总数
	 * 
	 * @return
	 */
	public Integer getElectionTotal(String ccpartyId) {
		DaoPara daoPara = new DaoPara();
		daoPara.setClazz(Election.class);
		daoPara.addCondition(Condition.EQUAL("ccpartyId", ccpartyId));
		Integer total = dao.getTotalCount(daoPara);
		return total;
	}

	/**
	 * 根据ID获取换届选举
	 * 
	 * @return
	 */
	public Election getElectionById(String id) {
		Object obj = this.loadOne(ObjectType.OBT_ELECTION, new String[] { "id" }, new Object[] { id });
		if (obj == null) {
			return null;
		}
		Election o = (Election) obj;
		return o;
	}

	/**
	 * 删除换届选举
	 * 
	 * @return
	 */
	public boolean deleteElection(String id) {
		return super.delete(id, ObjectType.OBT_ELECTION);
	}

	/**
	 * 更新换届选举
	 * 
	 * @return
	 */
	public boolean updateElection(String id, Map<String, Object> fieldValues) {
		super.update(id, ObjectType.OBT_ELECTION, fieldValues);
		return true;
	}

	/**
	 * 获取某换届选举下的班子成员
	 * 
	 * @return
	 */
	public List<ElectionMember> getElectionMemberByElectionId(String electionId) {
		DaoPara daoPara = new DaoPara();
		daoPara.setClazz(ElectionMember.class);
		daoPara.addCondition(Condition.EQUAL("electionId", electionId));
		List list = dao.loadList(daoPara);
		return list;
	}

	/**
	 * 删除某换届选举下的班子成员
	 * 
	 * @return
	 */
	public boolean deleteElectionMemberByElectionId(String electionId) {
		String hql = "delete from OBT_ELECTION_MEMBER where ELECTIONID=?";
		Object[] params = new Object[] { electionId };
		dao.deleteNative(hql, params);
		return true;
	}
}
