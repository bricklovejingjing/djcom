package org.tpri.djcom.entity.uam;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectType;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @description 角色bean
 * @author 常华荣
 * @since 2015-04-09
 */

@Entity
@Table(name="UAM_ROLE")
public class Role extends ObjectBase{
	/** */
	private static final long serialVersionUID = 1L;

	protected int objectType = ObjectType.UAM_ROLE;
	//材料报送审核角色
	public static String MATERIAL_REPORT_AUDIT="MATERIAL_REPORT_AUDIT";
	
	//党务信息报送报送审核角色1
	public static String INFO_REPORT_AUDIT1="INFO_REPORT_AUDIT1";
	
	//党务信息报送报送审核角色2
	public static String INFO_REPORT_AUDIT2="INFO_REPORT_AUDIT2";
	
	protected String id;
	protected String name;
	protected String description;
	protected int status;
	protected String system;
	
	protected List<User> users;	
	protected List<Privilege> privileges;
	
	
	@ManyToMany(cascade=CascadeType.MERGE,targetEntity=Privilege.class,fetch=FetchType.EAGER)
	@JoinTable(name="UAM_ROLEPRIVILEGE",  joinColumns={@JoinColumn(name="ROLEID")},  inverseJoinColumns={@JoinColumn(name="PRIVILEGEID")} )
	public List<Privilege> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}
	
	@ManyToMany(mappedBy="roles",cascade=CascadeType.ALL,targetEntity=User.class,fetch=FetchType.EAGER)	
	@JsonBackReference
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	@Id
	@Column(name="ID")
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
	@Column(name="SYSTEM")
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	
	

}
