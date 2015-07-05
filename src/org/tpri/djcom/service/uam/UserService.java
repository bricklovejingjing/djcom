package org.tpri.djcom.service.uam;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tpri.djcom.entity.obt.PartyMember;
import org.tpri.djcom.entity.org.CCParty;
import org.tpri.djcom.entity.result.QueryMyInfoResult;
import org.tpri.djcom.entity.uam.Role;
import org.tpri.djcom.entity.uam.User;
import org.tpri.djcom.manager.obt.PartyMemberManager;
import org.tpri.djcom.manager.org.CCPartyManager;
import org.tpri.djcom.manager.uam.RoleManager;
import org.tpri.djcom.manager.uam.UserManager;
import org.tpri.djcom.manager.uam.UserRoleManager;
import org.tpri.djcom.util.MD5Util;

/**
 * @description 用户服务类
 * @author 易文俊
 * @since 2015-04-09
 */

@Service("UserService")
public class UserService {

	@Resource(name = "UserManager")
	UserManager userManager;

	@Resource(name = "RoleManager")
	RoleManager roleManager;
	@Resource(name = "PartyMemberManager")
	PartyMemberManager partyMemberManager;
	
	@Resource(name = "CCPartyManager")
	CCPartyManager cCPartyManager;
	
	@Autowired
	UserRoleManager userRoleManager;
	/**
	 * 获取用户
	 * 
	 * @return
	 */
	public User getUser(String id) {
		User user = userManager.getUser(id);
		return user;
	}
	
	/**
	 * 根据用户名和密码获取用户
	 * 
	 * @return
	 */
	public User getUser(String userId, String password) {
		User user = userManager.getUser(userId,password);
		return user;
	}

	public List loadUser(Integer start, Integer limit) {
		List<User> lists = userManager.getUserList("User", start, limit);
		for (int i=0;i<lists.size();i++) {
			if(lists.get(i).getCcpartyId()!=null && !"".equals(lists.get(i).getCcpartyId()) && 1==lists.get(i).getFlag()){
				CCParty manager = cCPartyManager.getCCPartyById(lists.get(i).getCcpartyId());
				lists.get(i).setCcpartyName(manager.getName());
			}else{
				lists.get(i).setCcpartyName("");
			}
		}
		return lists;
	}
	
	public List loadUserByIdnumber(String idNumber) {
		List<User> lists = userManager.getUsersByIdnumber(idNumber);
		for (int i=0;i<lists.size();i++) {
			if(lists.get(i).getCcpartyId()!=null && !"".equals(lists.get(i).getCcpartyId()) && 1==lists.get(i).getFlag()){
				CCParty manager = cCPartyManager.getCCPartyById(lists.get(i).getCcpartyId());
				lists.get(i).setCcpartyName(manager.getName());
			}else{
				lists.get(i).setCcpartyName("");
			}
		}
		return lists;
	}
	
	/**
	 * 获取个人资料
	 * @param loginUser
	 * @return
	 */
	public List<QueryMyInfoResult> getMyInfo(User loginUser) {
		List<QueryMyInfoResult> lists = userManager.getMyInfo(loginUser);
		return lists;
	}

	public boolean addUser(User loginUser,String id, String name, String workNo, String partyNo, String password, String ccpartyId, String description,String flag,String idNumber,String status) {
		User user = new User();
		user.setId(idNumber);	//使用身份证号作为登陆账号
		user.setName(name);
		user.setWorkNo(workNo);
		//密码是身份证的后六位
		//将密码进行md5加密
		password = MD5Util.md5(idNumber.substring(idNumber.length()-6,idNumber.length()));
		user.setPassword(password);
		user.setDescription(description);
		user.setCreateUser(loginUser.getName());
		user.setCreateTime(new Timestamp(System.currentTimeMillis()));
		user.setFlag(Integer.valueOf(flag));
		user.setIdNumber(idNumber);
		user.setCcpartyId(ccpartyId);
		user.setStatus(Integer.parseInt(status));
		
		if(String.valueOf(User.DB_TABLE_USER_FLAG_1).equals(flag)){//为党员
			PartyMember p=partyMemberManager.getPartyMember(idNumber);
			if(p==null){
				p=new PartyMember();				
				p.setId(idNumber);
			}
			p.setIdNumber(idNumber);
			p.setName(name);
			p.setPartyNo(partyNo);
			p.setCreateUser(loginUser.getName());
			p.setCreateTime(new Timestamp(System.currentTimeMillis()));
			CCParty cCParty = cCPartyManager.getCCPartyById(ccpartyId);
			p.setCcparty(cCParty);
			user.setPartyMember(p);
			p.setStatus(Integer.parseInt(status));
			cCPartyManager.saveOrUpdate(p);
		}else{
			user.setPartyMember(null);
		}
		try{
			
			userManager.add(user);
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;

	}

	public boolean editUser(String id, String name, String workNo,
			String partyNo, String password, String ccpartyId,
			String description,String flag,String idNumber,String status,User loginUser) {
		User user = userManager.getUser(id);
		
		user.setName(name);
		user.setWorkNo(workNo);
		if(password!=null && !"".equals(password)){
			//修改密码
			//将密码进行md5加密
			password = MD5Util.md5(password);
			user.setPassword(password);
		}
		user.setDescription(description);
		user.setUpdateUser(loginUser.getName());
		user.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		user.setFlag(Integer.valueOf(flag));
		user.setIdNumber(idNumber);
		user.setStatus(Integer.parseInt(status));
		user.setCcpartyId(ccpartyId);
		if(String.valueOf(User.DB_TABLE_USER_FLAG_1).equals(flag)){//为党员			
			//PartyMember p=user.getPartyMember();
			PartyMember p=partyMemberManager.getPartyMember(idNumber);
			if(p==null){
				p=new PartyMember();				
				p.setId(idNumber);
			}
			p.setIdNumber(idNumber);
			p.setName(name);
			p.setPartyNo(partyNo);
			p.setUpdateUser(loginUser.getName());
			p.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			user.setPartyMember(p);
			CCParty cCParty = cCPartyManager.getCCPartyById(ccpartyId);
			p.setCcparty(cCParty);
			p.setUser(user);
			p.setStatus(Integer.parseInt(status));	//和用户表状态保持一致
			cCPartyManager.saveOrUpdate(p);
		}else{
			user.setPartyMember(null);
			PartyMember pm=partyMemberManager.getPartyMember(idNumber);
			if(pm!=null){
				pm.setStatus(PartyMember.DB_TABLE_STATUS_1);	//设为挂起
				userManager.saveOrUpdate(pm);
			}
		}
		
		userManager.update(user);
		return true;

	}
	
	/**
	 * 
	 * @param loginUser	登陆用户
	 * @param objs	参数obj
	 * @return true成功，false失败
	 */
	public boolean addOrUpdateUser(User loginUser,JSONObject objs) {
		String id = objs.getString("id");
		String name = objs.getString("name");
		String idNumber = objs.getString("idNumber");
		String password = objs.getString("password");
		String workNo = objs.getString("workNo");
		String status = objs.getString("status");
		String flag = objs.getString("flag");
		String ccpartyId = objs.getString("ccpartyId");
		String desc = objs.getString("desc");
		String partyNo = "";	//党员代号不体现
		if(id!=null && !"".equals(id)){
			//修改操作
			return this.editUser(id, name, workNo, partyNo, password, ccpartyId, desc, flag, idNumber, status, loginUser);
		}else{
			//保存操作
			return this.addUser(loginUser, id, name, workNo, partyNo, password, ccpartyId, desc, flag, idNumber, status);
		}
	}

	public boolean deleteUser(String id) {

		User user = userManager.getUser(id);
		userManager.delete(user);
		return true;

	}
	
	public boolean updatePwd(User loginUser,String newPwd) {
		User user = userManager.getUser(loginUser.getId());
		user.setPassword(newPwd);
		userManager.update(user);
		return true;

	}
	
	public boolean editUserRole(String userId,String roleIds) {
		User user = userManager.getUser(userId);
		List<Role> list=roleManager.getRoleListByIds(roleIds);
		Set set=new HashSet(list);
		user.setRoles(set);
		userManager.update(user);
		return true;
	}

	/**
	 * 获取某角色下的用户
	 */
	public List loadUsersByRoleId(String roleId) {
		Role role = roleManager.getRole(roleId);
		List<User> users=new ArrayList<User>();
		if(role==null){
			return users;
		}
		users =role.getUsers();
		return users;
	}
}
