package org.tpri.djcom.manager.tsk;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.dao.condition.Condition;
import org.tpri.djcom.dao.condition.DaoPara;
import org.tpri.djcom.dao.condition.Order;
import org.tpri.djcom.entity.tsk.InfoReport;

/**
 * @description 党务信息报送管理类
 * @author 易文俊
 * @since 2015-06-15
 */

@Repository("InfoReportManager")
public class InfoReportManager extends ManagerBase {
	private static boolean initialized = false;

    public  void initialize() {
        if (initialized)
            return;
        initialized =  true;
        ObjectRegister.registerClass(ObjectType.TSK_INFO_REPORT, InfoReport.class);
    }
    /**
     * 获取报送党务信息
     * @return
     */
    public List<InfoReport> getInfoReportList(String userId)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(InfoReport.class);
		daoPara.addCondition(Condition.EQUAL("createuserId", userId));
		daoPara.addOrder(Order.desc("createTime"));
        List list = dao.loadList(daoPara);
        return list;
    }
    /**
     * 获取报送目的部门下的报送党务信息
     * @return
     */
    public List<InfoReport> getInfoReportListByToDepartment(String toDepartment)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(InfoReport.class);
		daoPara.addCondition(Condition.EQUAL("toDepartment", toDepartment));
		daoPara.addOrder(Order.desc("createTime"));
        List list = dao.loadList(daoPara);
        return list;
    }
    /**
     * 根据ID获取报送党务信息
     * @return
     */
	public InfoReport getInfoReportById(String id)  {
    	Object obj=this.loadOne(ObjectType.TSK_INFO_REPORT, new String[] { "id" }, new Object[] { id });
    	if (obj == null) {
			return null;
		}
    	InfoReport o=(InfoReport)obj;
        return o;
    }
	/**
     * 删除报送党务信息
     * @return
     */
    public boolean deleteInfoReport(String id)  {
    	return super.delete(id, ObjectType.TSK_INFO_REPORT);
    }
	public InfoReport getInfoReportByProcessInstanceId(String processInstanceId) {
		DaoPara daoPara = new DaoPara();
		daoPara.setClazz(InfoReport.class);
		daoPara.addCondition(Condition.EQUAL("processInstanceId", processInstanceId));
		InfoReport infoReport = (InfoReport)dao.loadOne(daoPara);
        return infoReport;
		
	}
	
	public boolean updateInfoReport(String id, Map<String, Object> fieldValues) {
		super.update(id, ObjectType.TSK_INFO_REPORT, fieldValues);
		return true;
	}
}
