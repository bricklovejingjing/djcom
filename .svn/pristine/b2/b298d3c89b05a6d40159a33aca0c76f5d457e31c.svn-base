package org.tpri.djcom.entity.obt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectType;

/**
 * @description 换届选举班子成员bean
 * @author 易文俊
 * @since 2015-07-01
 */
@Entity
@Table(name = "OBT_ELECTION_MEMBER")
public class ElectionMember extends ObjectBase {
	private static final long serialVersionUID = -820143208534988235L;
	protected int objectType = ObjectType.OBT_ELECTION_MEMBER;

	protected String electionId;
	protected String userId;
	protected String userName;

	@Id
	public String getId() {
		return id;
	}
	@Column(name = "ELECTIONID")
	public String getElectionId() {
		return electionId;
	}
	public void setElectionId(String electionId) {
		this.electionId = electionId;
	}
	@Column(name = "USERID")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name = "USERNAME")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setId(String id) {
		this.id = id;
	}

}
