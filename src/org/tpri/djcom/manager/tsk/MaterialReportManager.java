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
import org.tpri.djcom.entity.tsk.MaterialReport;

/**
 * @description 材料报送管理类
 * @author 易文俊
 * @since 2015-06-10
 */

@Repository("MaterialReportManager")
public class MaterialReportManager extends ManagerBase {
	private static boolean initialized = false;

    public  void initialize() {
        if (initialized)
            return;
        initialized =  true;
        ObjectRegister.registerClass(ObjectType.TSK_MATERIAL_REPORT, MaterialReport.class);
    }
    /**
     * 获取报送材料
     * @return
     */
    public List<MaterialReport> getMaterialReportList(String userId)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(MaterialReport.class);
		daoPara.addCondition(Condition.EQUAL("createuserId", userId));
		daoPara.addOrder(Order.desc("createTime"));
        List list = dao.loadList(daoPara);
        return list;
    }
    /**
     * 获取报送目的部门下的报送材料
     * @return
     */
    public List<MaterialReport> getMaterialReportListByToDepartment(String toDepartment)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(MaterialReport.class);
		daoPara.addCondition(Condition.EQUAL("toDepartment", toDepartment));
		daoPara.addOrder(Order.desc("createTime"));
        List list = dao.loadList(daoPara);
        return list;
    }
    /**
     * 根据ID获取报送材料
     * @return
     */
	public MaterialReport getMaterialReportById(String id)  {
    	Object obj=this.loadOne(ObjectType.TSK_MATERIAL_REPORT, new String[] { "id" }, new Object[] { id });
    	if (obj == null) {
			return null;
		}
    	MaterialReport o=(MaterialReport)obj;
        return o;
    }
	/**
     * 删除报送材料
     * @return
     */
    public boolean deleteMaterialReport(String id)  {
    	return super.delete(id, ObjectType.TSK_MATERIAL_REPORT);
    }
	public MaterialReport getMaterialReportByProcessInstanceId(String processInstanceId) {
		DaoPara daoPara = new DaoPara();
		daoPara.setClazz(MaterialReport.class);
		daoPara.addCondition(Condition.EQUAL("processInstanceId", processInstanceId));
		MaterialReport materialReport = (MaterialReport)dao.loadOne(daoPara);
        return materialReport;
		
	}
	
	public boolean updateMaterialReport(String id, Map<String, Object> fieldValues) {
		super.update(id, ObjectType.TSK_MATERIAL_REPORT, fieldValues);
		return true;
	}
}
