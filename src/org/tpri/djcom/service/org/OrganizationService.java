package org.tpri.djcom.service.org;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.tpri.djcom.entity.org.Organization;
import org.tpri.djcom.entity.uam.User;
import org.tpri.djcom.manager.org.OrganizationManager;
import org.tpri.djcom.util.StringUtils;
import org.tpri.djcom.view.org.OrganizationView;

/**
 * @description 党组织服务类
 * @author 易文俊
 * @since 2015-04-24
 */

@Service("OrganizationService")
public class OrganizationService {
	
	@Resource(name="OrganizationManager")
	OrganizationManager organizationManager;

	/**
	 * 获取行政单位
	 * 
	 * @return
	 */
	public Organization getOrganization(String id) {
		Organization Organization = organizationManager.getOrganization(id);
		return Organization;
	}

	public List loadOrganization(Integer start, Integer limit) {
		List<Organization> list = organizationManager.getOrganizationList("Organization", start, limit);
		
		return list;
	}

	public boolean addOrganization(User loadUser, JSONObject json) {
		Organization org = new Organization();
		org.setId(StringUtils.randomForNumbers(32));	//生成32位随机数
		org.setName(json.getString("name"));
		org.setAddress(json.getString("address"));
		org.setPerresentative(json.getString("perresentative"));
		org.setParentId(json.getString("parentId"));
		org.setDescription(json.getString("description"));
		org.setStatus(json.getInt("status"));
		org.setCreateUser(loadUser.getCreateUser());		
		org.setCreateTime(new Timestamp(System.currentTimeMillis()));
		try{
			organizationManager.add(org);
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
		
	}

	

	public boolean deleteOrganization(String id) {

		Organization Organization = organizationManager.getOrganization(id);
		organizationManager.delete(Organization);
		return true;

	}

	public boolean editOrganization(User loadUser,JSONObject json) {
		String id = json.getString("id");
		if(id!=null && !"".equals(id)){
			Organization org = organizationManager.getOrganization(id);
			org.setName(json.getString("name"));
			org.setAddress(json.getString("address"));
			org.setPerresentative(json.getString("perresentative"));
			org.setParentId(json.getString("parentId"));
			org.setDescription(json.getString("description"));
			org.setStatus(json.getInt("status"));
			org.setUpdateUser(loadUser.getCreateUser());		
			org.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			organizationManager.update(org);
			return true;
		}else{
			return false;
		}
		
	}

	public List<OrganizationView> getOrganizationList(String parentId) {
		List<Organization> cCPartylist=organizationManager.getOrganizationListById(parentId);
		List<OrganizationView> viewList=new ArrayList<OrganizationView>();
		if(cCPartylist!=null){
			for (Organization cCParty:cCPartylist) {
				OrganizationView view=new OrganizationView();
				view.setId(cCParty.getId());
				view.setName(cCParty.getName());
				if(null!=cCParty.getParentId()){					
					Organization o=organizationManager.getOrganization(cCParty.getParentId());
					if(null!=o)
						view.setOrgName(o.getName());
				}
				view.setParentId(cCParty.getParentId());
				view.setStatus(cCParty.getStatus());
				view.setLeaf(cCParty.getIsLeaf()==1? true : false);
				view.setAddress(cCParty.getAddress());
				view.setPerresentative(cCParty.getPerresentative());
				view.setDescription(cCParty.getDescription());
				view.setCreateUser(cCParty.getCreateUser());
				view.setCreateTime(cCParty.getCreateTime());
				view.setUpdateUser(cCParty.getUpdateUser());
				view.setUpdateTime(cCParty.getUpdateTime());
				viewList.add(view);
			}
		}
		return viewList;
	}
}
