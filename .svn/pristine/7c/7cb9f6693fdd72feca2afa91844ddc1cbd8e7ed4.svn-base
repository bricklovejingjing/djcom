
package org.tpri.djcom.entity.org;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectType;

import com.fasterxml.jackson.annotation.JsonBackReference;
/**
 * @description 党组织bean
 * @author 易文俊
 * @since 2015-04-24
 */
@Entity
@Table(name="ORG_CCPARTY")
public class CCParty extends ObjectBase {
	
	protected int objectType = ObjectType.ORG_CCPARTY;
	
	private static final long serialVersionUID = 1L;
	protected String id;
	protected String name;
	protected int type;
	protected String orgId;
	protected String parentId;
	protected String description;
	protected String documentNo;
	protected Timestamp documentTime;
	protected Timestamp expirationTime;
	protected int isTip;
	protected int status;
	protected String createUser;
	protected Timestamp createTime;
	protected String updateUser;
	protected Timestamp updateTime;
	protected int isLeaf;
	
	private Organization organization;
	
	
	@OneToOne(cascade=CascadeType.MERGE,targetEntity=Organization.class,fetch=FetchType.EAGER)
	@JoinColumn(name="ORGID")
	@JsonBackReference
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="TYPE")
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	/*@Column(name="ORGID")
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}*/
	@Column(name="PARENTID")
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="DOCUMENTNO")
	public String getDocumentNo() {
		return documentNo;
	}
	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
	@Column(name="DOCUMENTTIME")
	public Timestamp getDocumentTime() {
		return documentTime;
	}
	public void setDocumentTime(Timestamp documentTime) {
		this.documentTime = documentTime;
	}
	@Column(name="EXPIRATIONTIME")
	public Timestamp getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(Timestamp expirationTime) {
		this.expirationTime = expirationTime;
	}
	@Column(name="ISTIP")
	public int getIsTip() {
		return isTip;
	}
	public void setIsTip(int isTip) {
		this.isTip = isTip;
	}
	@Column(name="STATUS")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Column(name="CREATEUSER")
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	@Column(name="CREATETIME")
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Column(name="UPDATEUSER")
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	@Column(name="UPDATETIME")
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	@Column(name="ISLEAF")
	public int getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(int isLeaf) {
		this.isLeaf = isLeaf;
	}
}
