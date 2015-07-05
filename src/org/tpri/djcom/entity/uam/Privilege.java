package org.tpri.djcom.entity.uam;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
@Table(name="UAM_PRIVILEGE")
public class Privilege extends ObjectBase{
	protected int objectType = ObjectType.UAM_PRIVILEGE;
	
	protected String id;
	protected String name;
	protected String description;
	protected String pgid;
	protected int type;
	protected List<Role> roles;
	
	@ManyToMany(mappedBy="privileges",cascade=CascadeType.MERGE,targetEntity=Role.class,fetch=FetchType.EAGER)
	@JsonBackReference
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
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
	
	@Column(name="NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="PGID")
	public String getPgid() {
		return pgid;
	}
	public void setPgid(String pgid) {
		this.pgid = pgid;
	}
	@Column(name="TYPE")
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	

}
