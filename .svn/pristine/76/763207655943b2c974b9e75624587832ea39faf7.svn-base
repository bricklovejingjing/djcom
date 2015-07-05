
package org.tpri.djcom.entity.zbsc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectType;
/**
 * @description 栏目bean
 * @author 易文俊
 * @since 2015-04-30
 */
@Entity
@Table(name="ZBSC_CATEGORY")
public class Category extends ObjectBase {
	
	protected int objectType = ObjectType.ZBSC_CATEGORY;
	
	/**支部工作法*/
	public static int TYPE_GONGZUOFA=1;
	/**三会一课*/
	public static int TYPE_SANHUIYIKE=2;
	/**自建栏目*/
	public static int TYPE_ZIJIAN=3;
	
	
	private static final long serialVersionUID = 1L;
	protected String id;
	protected String code;
	protected String name;
	protected int type;
	protected String parentId;
	protected String cCPartyId;
	protected String description;
	protected int orderNo;

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
	@Column(name="TYPE")
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Column(name="PARENTID")
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	@Column(name="CCPARTYID")
	public String getcCPartyId() {
		return cCPartyId;
	}
	public void setcCPartyId(String cCPartyId) {
		this.cCPartyId = cCPartyId;
	}
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="ORDERNO")
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	
}
