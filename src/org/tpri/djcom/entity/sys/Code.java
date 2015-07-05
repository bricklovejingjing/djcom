package org.tpri.djcom.entity.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectType;

/**
 * @description 代码表bean
 * @author 易文俊
 * @since 2015-06-30
 */

@Entity
@Table(name="SYS_CODE")
public class Code  extends ObjectBase{
	
	private static final long serialVersionUID = buildSerialVersionUID(ObjectType.SYS_CODE);

	protected String code;
	protected String parentCode;
	protected String description;
	protected int status;
	
	public Code(){
		super();
		objectType = ObjectType.SYS_CODE;
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
	@Column(name="CODE")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	@Column(name="PARENTCODE")
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
}
