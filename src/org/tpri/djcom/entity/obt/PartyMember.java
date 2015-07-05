
package org.tpri.djcom.entity.obt;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.entity.org.CCParty;
import org.tpri.djcom.entity.uam.User;
/**
 * @description 行政单位bean
 * @author 常华荣
 * @since 2015-05-04
 */
@Entity
@Table(name="OBT_PARTY_MEMBER")
public class PartyMember extends ObjectBase {
	
	protected int objectType = ObjectType.OBT_PARTY_MEMBER;
	public static final int DB_TABLE_STATUS_0 = 0;	//status字段  0 正常
	public static final int DB_TABLE_STATUS_1 = 1;	//status字段  1 挂起
	
	private static final long serialVersionUID = 1L;
	protected String id;
	protected String partyNo;
	protected String name;
	protected String gender;
	protected String idNumber;
	protected String nation;
	protected String occupation;
	protected String education;
	protected String jobTitle;
	protected String birthPlace;
	protected String officePhone;
	protected String mobile;
	protected String email;
	protected String address;	
	public String ccpartyId;
	protected Date joinTime;
	protected int type;
	protected String introducer1;
	protected String introducer2;
	protected String picture;
	protected int status;	
	protected String description;
	protected String createUser;
	protected Timestamp createTime;
	protected String updateUser;
	protected Timestamp updateTime;
	protected User user;
	protected CCParty ccparty;
	
	@OneToOne(mappedBy="partyMember",fetch=FetchType.EAGER)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne(cascade=CascadeType.MERGE,targetEntity=CCParty.class,fetch=FetchType.EAGER)
	@JoinColumn(name="CCPARTYID")
	public CCParty getCcparty() {
		return ccparty;
	}
	public void setCcparty(CCParty ccparty) {
		this.ccparty = ccparty;
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
	@Column(name="ADDRESS")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	
	@Column(name="PARTYNO")
	public String getPartyNo() {
		return partyNo;
	}
	public void setPartyNo(String partyNo) {
		this.partyNo = partyNo;
	}
	@Column(name="GENDER")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name="IDNUMBER")
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	@Column(name="NATION")
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	@Column(name="OCCUPATION")
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	@Column(name="EDUCATION")
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	@Column(name="JOBTITLE")
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	@Column(name="BIRTHPLACE")
	public String getBirthPlace() {
		return birthPlace;
	}
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	@Column(name="OFFICEPHONE")
	public String getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	@Column(name="MOBILE")
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	//@Column(name="CCPARTYID")	
	//仅当参数使用
//	public String getCcpartyId() {
//		return ccpartyId;
//	}
//	public void setCcpartyId(String ccpartyId) {
//		this.ccpartyId = ccpartyId;
//	}
	@Column(name="JOINTIME")
	@Temporal(TemporalType.DATE) 
	public Date getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}
	@Column(name="TYPE")
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Column(name="INTRODUCER1")
	public String getIntroducer1() {
		return introducer1;
	}
	public void setIntroducer1(String introducer1) {
		this.introducer1 = introducer1;
	}
	@Column(name="INTRODUCER2")
	public String getIntroducer2() {
		return introducer2;
	}
	public void setIntroducer2(String introducer2) {
		this.introducer2 = introducer2;
	}
	@Column(name="PICTURE")
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
}
