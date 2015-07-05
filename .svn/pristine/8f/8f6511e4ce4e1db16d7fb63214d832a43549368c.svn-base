package org.tpri.djcom.entity.uam;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.entity.obt.PartyMember;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @description 用户bean
 * @author 易文俊
 * @since 2015-04-09
 */

@Entity
@Table(name="UAM_USER")
public class User extends ObjectBase{
	protected int objectType = ObjectType.UAM_USER;
	
	public static final int DB_TABLE_USER_FLAG_0 = 0;	//falg字段含义  0非党员
	public static final int DB_TABLE_USER_FLAG_1 = 1;	//falg字段含义  1党员
	
	public static final int DB_TABLE_STATUS_0 = 0;	//status字段  0 正常
	public static final int DB_TABLE_STATUS_1 = 1;	//status字段  1 挂起
	
	protected String id;
	protected String workNo;
	protected String name;
	protected String password;
	protected Timestamp lastLoginTime;
	protected String lastLoginIp;
	protected int loginCount;
	protected int status;
	protected String description;
	protected String createUser;
	protected Timestamp createTime;
	protected String updateUser;
	protected Timestamp updateTime;	
	protected String idNumber;
	protected int flag;
	protected String ccpartyId;
	protected String jobTitle;
	protected String mobile;
	private Set<Role> roles;
	private PartyMember partyMember;
	
	protected String ccpartyName = "";
	
	/**是否系统管理员*/
	private boolean isSystemAdmin = false;
	
	@OneToOne(cascade={CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE},targetEntity=PartyMember.class,fetch=FetchType.EAGER)
	@JoinColumn(name="PARTYMEMBERID")
	@JsonBackReference
	public PartyMember getPartyMember() {
		return partyMember;
	}	
	public void setPartyMember(PartyMember partyMember) {
		this.partyMember = partyMember;
	}
	
	@ManyToMany(cascade=CascadeType.MERGE,targetEntity=Role.class,fetch=FetchType.EAGER)	
	@JoinTable(name="UAM_USERROLE",  joinColumns={@JoinColumn(name="USERID")},  inverseJoinColumns={@JoinColumn(name="ROLEID")} )
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	@Id
	@Column(name="ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="WORKNO")
	public String getWorkNo() {
		return workNo;
	}
	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}
	@Column(name="NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="PASSWORD")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="LASTLOGINTIME")
	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	@Column(name="LASTLOGINIP")
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	@Column(name="LOGINCOUNT")
	public int getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}
	@Column(name="STATUS")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	/*@Column(name="PARTYMEMBERID")
	public String getPartyMemberId() {
		return partyMemberId;
	}
	public void setPartyMemberId(String partyMemberId) {
		this.partyMemberId = partyMemberId;
	}*/
	
	@Column(name="FLAG")
	public int getFlag() {
		return flag;
	}
	public String getCcpartyId() {
		return ccpartyId;
	}
	@Column(name="CCPARTYID")
	public void setCcpartyId(String ccpartyId) {
		this.ccpartyId = ccpartyId;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	@Column(name="JOBTITLE")
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	@Column(name="MOBILE")
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public HashSet<String> loadAllPrivilegeIds() {
		HashSet<String> allPrivilegeIds = new HashSet<String>();
		for (Role role : roles) {
			List<Privilege> privileges=role.getPrivileges();
			for (Privilege privilege : privileges) {
				allPrivilegeIds.add(privilege.getId());
			}
		}
		return allPrivilegeIds;
	}
	@Transient
	public boolean isSystemAdmin() {
		for (Role role : roles) {
			if(role.getId().equals("ADMINISTRATOR")){
				isSystemAdmin=true;
			}
		}
		return isSystemAdmin;
	}
	/**
	 * 判断是否有权限
	 **/
	public boolean hasPrivilege(String privilegeId){
		HashSet<String> allPrivilegeIds = new HashSet<String>();
		for (Role role : roles) {
			List<Privilege> privileges=role.getPrivileges();
			for (Privilege privilege : privileges) {
				allPrivilegeIds.add(privilege.getId());
			}
		}
	    return allPrivilegeIds.contains(privilegeId);
	 }
	
	@Transient
	public String getCcpartyName() {
		return ccpartyName;
	}
	public void setCcpartyName(String ccpartyName) {
		this.ccpartyName = ccpartyName;
	}
	

}
