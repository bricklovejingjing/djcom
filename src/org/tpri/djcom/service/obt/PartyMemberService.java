package org.tpri.djcom.service.obt;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.tpri.djcom.entity.obt.PartyMember;
import org.tpri.djcom.entity.org.CCParty;
import org.tpri.djcom.entity.uam.User;
import org.tpri.djcom.manager.obt.PartyMemberManager;
import org.tpri.djcom.manager.org.CCPartyManager;
import org.tpri.djcom.manager.uam.UserManager;
import org.tpri.djcom.util.MD5Util;

/**
 * @description 党组织服务类
 * @author 易文俊
 * @since 2015-04-24
 */

@Service("PartyMemberService")
public class PartyMemberService {

	@Resource(name = "PartyMemberManager")
	PartyMemberManager partyMemberManager;
	
	@Resource(name = "CCPartyManager")
	CCPartyManager cCPartyManager;
	
	@Resource(name = "UserManager")
	UserManager userManager;

	/**
	 * 获取行政单位
	 * 
	 * @return
	 */
	public PartyMember getPartyMember(String id) {
		PartyMember PartyMember = partyMemberManager.getPartyMember(id);
		return PartyMember;
	}

	public List loadPartyMember(Integer start, Integer limit) {
		List<PartyMember> partyMemberlist = partyMemberManager.getPartyMemberList(start, limit);
		return partyMemberlist;
	}

	public List loadPartyMemberByCcpartyId(Integer start, Integer limit, String ccpartyId) {
		List<PartyMember> partyMemberlist = partyMemberManager.loadPartyMemberByCcpartyId(start, limit, ccpartyId);
		List<PartyMember> partyMemberlists = new ArrayList<PartyMember>();
		for (PartyMember partyMember : partyMemberlist) {
			CCParty cCParty = (CCParty) partyMember.getCcparty();
			partyMember.ccpartyId = cCParty.getId();
			partyMemberlists.add(partyMember);
		}
		return partyMemberlists;
	}

	/**
	 * 
	 * @param loginUser
	 *            登陆的用户
	 * @param json
	 *            保存的参数json格式
	 * @return true成功，false失败
	 * @throws ParseException
	 *             日期转换异常
	 */
	public boolean addPartyMember(User loginUser, JSONObject json) throws ParseException {
		PartyMember pm = new PartyMember();
		pm.setId(json.getString("idNumber")); // 使用身份证号作为主键
		pm.setIdNumber(json.getString("idNumber")); // 身份证号
		pm.setName(json.getString("name"));
		pm.setGender(json.getString("gender"));
		pm.setNation(json.getString("nation"));
		pm.setBirthPlace(json.getString("birthPlace"));
		pm.setEducation(json.getString("education"));
		pm.setOccupation(json.getString("occupation"));
		pm.setJobTitle(json.getString("jobTitle"));
		pm.setOfficePhone(json.getString("officePhone"));
		pm.setMobile(json.getString("mobile"));
		pm.setEmail(json.getString("email"));
		pm.setAddress(json.getString("address"));
		pm.setStatus(json.getInt("status"));
		pm.setPicture(json.getString("picture"));
		pm.setPartyNo(json.getString("partyNo"));
		String joinTime = json.getString("joinTime"); // 入党时间处理
		if (joinTime != null && !"".equals(joinTime)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(joinTime);
			pm.setJoinTime(date);
		}
		pm.setType(json.getInt("type"));
		String ccpartyId = json.getString("ccpartyId"); // 党组织处理
		if (ccpartyId != null && !"".equals(ccpartyId)) {
			// pm.setCcpartyId(ccpartyId);
			CCParty cc = cCPartyManager.getCCPartyById(ccpartyId);
			pm.setCcparty(cc);
		}
		pm.setIntroducer1(json.getString("introducer1"));
		pm.setIntroducer2(json.getString("introducer2"));
		pm.setCreateUser(loginUser.getName());
		pm.setCreateTime(new Timestamp(System.currentTimeMillis()));
		partyMemberManager.add(pm);
		User user = userManager.getUserByIdNumber(json.getString("idNumber"));
		if (user != null) {
			user.setPartyMember(pm); // 关联
			user.setStatus(json.getInt("status")); // 状态
			user.setUpdateUser(loginUser.getName());
			user.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			userManager.update(user); // 修改用户
		} else {
			// 创建新的用户信息就行关联
			user = new User();
			user.setId(json.getString("idNumber")); // 设置主键
			user.setIdNumber(json.getString("idNumber")); // 设置身份证号
			user.setName(json.getString("name")); // 设置姓名
			user.setStatus(json.getInt("status")); // 状态
			String idNumber = json.getString("idNumber"); // 身份证处理
			if (idNumber != null && !"".equals(idNumber)) {
				user.setPassword(MD5Util.md5(idNumber.substring(idNumber.length() - 6, idNumber.length()))); // 身份证后六位
			}
			user.setPartyMember(pm); // 关联
			user.setFlag(User.DB_TABLE_USER_FLAG_1); // 党员
			user.setCcpartyId(json.getString("ccpartyId")); // 所属党组织
			user.setCreateUser(loginUser.getName());
			user.setCreateTime(new Timestamp(System.currentTimeMillis()));
			userManager.add(user);
		}
		return true;
	}

	public boolean deletePartyMember(String id) {

		PartyMember PartyMember = partyMemberManager.getPartyMember(id);
		partyMemberManager.delete(PartyMember);
		return true;

	}

	public boolean editPartyMember(User loadUser, String id, String name, String partyNo, String gender, String idNumber, String nation, String occupation, String education, String jobTitle, String birthPlace, String officePhone, String mobile, String email, String address, String ccpartyId, String joinTime, String type, String introducer1, String introducer2, String picture, String status, String description) throws ParseException {

		PartyMember p = partyMemberManager.getPartyMember(id);
		p.setName(name);
		p.setPartyNo(partyNo);
		p.setGender(gender);
		p.setIdNumber(idNumber);
		p.setNation(nation);
		p.setOccupation(occupation);
		p.setEducation(education);
		p.setJobTitle(jobTitle);
		p.setBirthPlace(birthPlace);
		p.setOfficePhone(officePhone);
		p.setMobile(mobile);
		p.setEmail(email);
		p.setAddress(address);

		if (ccpartyId != null && !"".equals(ccpartyId)) {
			CCParty cc = cCPartyManager.getCCPartyById(ccpartyId);
			p.setCcparty(cc);
		}
		if (joinTime != null && !"".equals(joinTime)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(joinTime);
			p.setJoinTime(date);
		}
		p.setType(Integer.valueOf(type));
		p.setIntroducer1(introducer1);
		p.setIntroducer2(introducer2);

		p.setPicture(null);
		p.setStatus(Integer.valueOf(status));
		p.setDescription(description);

		p.setUpdateUser(loadUser.getCreateUser());
		p.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		partyMemberManager.update(p);
		return true;

	}

	public List<PartyMember> loadPartyMemberByPartyId(String ccpartyId) {
		List<PartyMember> partyMembers = new ArrayList<PartyMember>();
		getPartyMember(partyMembers, ccpartyId);
		return partyMembers;
	}

	public List<PartyMember> loadPartyMemberByIdNumber(String idNumber) {
		return partyMemberManager.loadPartyMemberByIdNumber(idNumber);
	}

	private void getPartyMember(List<PartyMember> partyMembers, String ccpartyId) {
		partyMembers.addAll(partyMemberManager.loadPartyMemberByPartyId(ccpartyId));
		List<CCParty> ccpartyList = cCPartyManager.getCCPartyListByParentId(ccpartyId, "false");
		if (ccpartyList != null && ccpartyList.size() > 0) {
			for (CCParty ccparty : ccpartyList) {
				getPartyMember(partyMembers, ccparty.getId());
			}
		}
	}

	public List loadAllPartyMember() {
		List<PartyMember> partyMembers = partyMemberManager.getAllPartyMember();
		return partyMembers;
	}
}