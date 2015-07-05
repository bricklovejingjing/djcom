
package org.tpri.djcom.view.org;

import org.tpri.djcom.entity.org.CCParty;

/**
 * @description 党组织界面视图
 * @author 易文俊
 * @since 2015-04-24
 */
public class CCPartyView extends CCParty {
	
	private static final long serialVersionUID = 1L;
	protected String typeName;
	protected String orgName;
	protected String StatusName;
	protected String orgId;
	protected boolean leaf;
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getStatusName() {
		return StatusName;
	}
	public void setStatusName(String statusName) {
		StatusName = statusName;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	
}
