package org.tpri.djcom.service.org;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.poi.util.StringUtil;
import org.springframework.stereotype.Service;
import org.tpri.djcom.entity.org.CCParty;
import org.tpri.djcom.entity.uam.User;
import org.tpri.djcom.manager.org.CCPartyManager;
import org.tpri.djcom.manager.org.OrganizationManager;
import org.tpri.djcom.util.StringUtils;
import org.tpri.djcom.view.ZTreeView;
import org.tpri.djcom.view.org.CCPartyView;

/**
 * @description 党组织服务类
 * @author 易文俊
 * @since 2015-04-24
 */

@Service("CCpartyService")
public class CCpartyService {
	
	@Resource(name="CCPartyManager")
	CCPartyManager cCPartyManager;
	
	@Resource(name="OrganizationManager")
	OrganizationManager organizationManager;
	
	
	public List getCCPartyList(String parentId,String showEnabledStatus) {
		List<CCParty> cCPartylist=cCPartyManager.getCCPartyListByParentId(parentId,showEnabledStatus);
		List<CCPartyView> viewList=new ArrayList<CCPartyView>();
		if(cCPartylist!=null){
			for (CCParty cCParty:cCPartylist) {
				//判断是否显示该组织
				
				CCPartyView view=new CCPartyView();
				view.setId(cCParty.getId());
				view.setName(cCParty.getName());
				if(null!=cCParty.getOrganization()){					
					view.setOrgId(cCParty.getOrganization().getId());
					view.setOrgName(cCParty.getOrganization().getName());
				}
				
				int type=cCParty.getType();
				view.setType(type);	
				switch(type){
				case 0:
					view.setTypeName("党委");
					break;
				case 1:
					view.setTypeName("总支");
					break;
				case 2:
					view.setTypeName("党支部");
					break;
				}
				view.setParentId(cCParty.getParentId());
				view.setStatus(cCParty.getStatus());
				view.setLeaf(cCParty.getIsLeaf()==1? true : false);
				view.setCreateUser(cCParty.getCreateUser());
				view.setCreateTime(cCParty.getCreateTime());
				view.setUpdateUser(cCParty.getUpdateUser());
				view.setUpdateTime(cCParty.getUpdateTime());
				view.setDocumentTime(cCParty.getDocumentTime());
				view.setDocumentNo(cCParty.getDocumentNo());
				view.setExpirationTime(cCParty.getExpirationTime());
				
				viewList.add(view);
			}
		}
		return viewList;
	}
	
	public List getCCPartyListAndSun(String parentId,String showEnabledStatus,User loginUser) {
		List<CCParty> cCPartylist=cCPartyManager.getCCPartyListByParentId(parentId,showEnabledStatus);
		List<CCPartyView> viewList=new ArrayList<CCPartyView>();
		if(cCPartylist!=null){
			CCParty ccparty = null;
			int decollNum = 0;
			//decollNum = currentId.split("\\.").length-1;	//几个分号
			if(loginUser==null){
				return null;
			}else{
				ccparty=cCPartyManager.getCCPartyById(loginUser.getCcpartyId());
				if(ccparty==null || ccparty.getParentId()==null || "".equals(ccparty.getParentId())){
					return null;
				}else{
					decollNum = ccparty.getParentId().split("\\.").length-1;
					parentId = ccparty.getParentId();
				}
			}
			for (CCParty cCParty:cCPartylist) {
				//判断是否显示该组织
				
				int ccppartyDecollNum = cCParty.getId().split("\\.").length-1;
				if(decollNum<ccppartyDecollNum || (decollNum==ccppartyDecollNum && ccparty.getId().equals(cCParty.getId()))){
					CCPartyView view=new CCPartyView();
					view.setId(cCParty.getId());
					view.setName(cCParty.getName());
					if(null!=cCParty.getOrganization()){					
						view.setOrgId(cCParty.getOrganization().getId());
						view.setOrgName(cCParty.getOrganization().getName());
					}
					
					int type=cCParty.getType();
					view.setType(type);	
					switch(type){
					case 0:
						view.setTypeName("党委");
						break;
					case 1:
						view.setTypeName("总支");
						break;
					case 2:
						view.setTypeName("党支部");
						break;
					}
					view.setParentId(cCParty.getParentId());
					view.setStatus(cCParty.getStatus());
					view.setLeaf(cCParty.getIsLeaf()==1? true : false);
					view.setCreateUser(cCParty.getCreateUser());
					view.setCreateTime(cCParty.getCreateTime());
					view.setUpdateUser(cCParty.getUpdateUser());
					view.setUpdateTime(cCParty.getUpdateTime());
					view.setDocumentTime(cCParty.getDocumentTime());
					view.setDocumentNo(cCParty.getDocumentNo());
					view.setExpirationTime(cCParty.getExpirationTime());
					
					viewList.add(view);
				}
			}
		}
		return viewList;
	}
	public boolean addCCParty(User user,JSONObject json) throws ParseException {
		CCParty party=new CCParty();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//验证党组织是否被占用
		String orgId = json.getString("parentId")+"."+json.getString("id");
		//TODO
		List<CCParty> lists = cCPartyManager.getCcpartyListByOrgId(orgId);
		if(lists!=null && lists.size()>0){
			return false;
		}
		party.setId(orgId);
		party.setName(json.getString("name"));
		party.setType(json.getInt("type"));
		party.setParentId(json.getString("parentId"));
		party.setType(StringUtils.counter(party.getId(), '.'));
		if(json.getString("orgId")!=null && !"".equals(json.getString("orgId"))){
			party.setOrganization(organizationManager.getOrganization(json.getString("orgId")));
		}
		party.setCreateUser(user.getName());
		party.setCreateTime(new Timestamp(System.currentTimeMillis()));
		if(json.getString("documentTime")!=null && !"".equals(json.getString("documentTime"))){
			Date documentTime = (Date) f.parseObject(json.getString("documentTime"));
			Timestamp documentTimeTs = new Timestamp(documentTime.getTime());
			party.setDocumentTime(documentTimeTs);
		}
		if(json.getString("expirationTime")!=null && !"".equals(json.getString("expirationTime"))){
			Date expirationTime = (Date) f.parseObject(json.getString("expirationTime"));
			Timestamp expirationTimeTs = new Timestamp(expirationTime.getTime());
			party.setExpirationTime(expirationTimeTs);
		}
		party.setDocumentNo(json.getString("documentNo"));
		cCPartyManager.add(party);
		return true;
	}
	
	/**
	 * @despriton 根据树节点找其子节点
	 * @Author guojing
	 */
	
	public List<CCParty> getCCPartyListByParentId(String ccPartyParentId) {
	    return cCPartyManager.getCCPartyListByParentId(ccPartyParentId, "");
	}
	
	
	public List<ZTreeView> getTreeCCParty(String ccpartyId,String loadType) {
		List<ZTreeView> tree = new ArrayList<ZTreeView>();
		CCParty ccparty=cCPartyManager.getCCPartyById(ccpartyId);	//根据id获取组织信息
		List<CCParty> cCPartylist=cCPartyManager.getAllCCParty();	//获取所有组织信息
		if(ccparty!=null && cCPartylist!=null){
			addChildCCParty(cCPartylist,tree,ccparty.getParentId());
		}
		return tree;
	}
	
	/**
	 * 根据文章ID获取已经转发的党组织
	 * @param ccpartyId
	 * @param articleId
	 * @return
	 */
	public List<CCParty> getExistTreeCCPartyByArticleId(String articleId) {
		List<CCParty> cpartys = cCPartyManager.getExistTreeCCPartyByArticleId(articleId);	//根据id获取组织信息
		return cpartys;
	}
	
	/**
	 * 获取当前组织的子节点及平级组织
	 * @param ccpartyId
	 * @return
	 */
	public List<ZTreeView> getTreeCCPartyStatisticsCount(String ccpartyId) {
		List<ZTreeView> tree = new ArrayList<ZTreeView>();
		CCParty ccparty=cCPartyManager.getCCPartyById(ccpartyId);	//根据id获取组织信息
		List<CCParty> cCPartylist=cCPartyManager.getAllCCParty();	//获取所有组织信息
		if(ccparty!=null && cCPartylist!=null){
			addChildCCPartySunAndRate(cCPartylist,tree,ccparty.getParentId(),ccparty.getParentId(),ccparty.getId());
		}
		return tree;
	}
	
	/**
	 * 获取当前组织的子节点
	 * @param ccpartyId
	 * @return
	 */
	public List<ZTreeView> getTreeCCPartyById(String ccpartyId) {
		List<ZTreeView> tree = new ArrayList<ZTreeView>();
		CCParty ccparty=cCPartyManager.getCCPartyById(ccpartyId);	//根据id获取组织信息
		List<CCParty> cCPartylist=cCPartyManager.getAllCCParty();	//获取所有组织信息
		if(ccparty!=null && cCPartylist!=null){
			addChildCCPartySun(cCPartylist,tree,ccparty.getParentId(),ccparty.getId());
		}
		return tree;
	}
	
	public List<ZTreeView> getTreeAllCCParty() {
		List<ZTreeView> tree = new ArrayList<ZTreeView>();
		List<CCParty> cCPartylist=cCPartyManager.getAllCCParty();
		if(cCPartylist!=null){
			for(CCParty ccparty: cCPartylist){
				ZTreeView node = new ZTreeView();
				node.setId(ccparty.getId());
				node.setName(ccparty.getName());
				node.setpId(ccparty.getParentId());
				node.setOpen(true);
				tree.add(node);
			}
		}
		return tree;
	}
	
	/**
	 * 
	 * @param cCPartylist	所有组织
	 * @param tree	组织的树
	 * @param parendId	//当前组织的父id
	 */
	private void addChildCCParty(List<CCParty> cCPartylist,List<ZTreeView>tree,String parendId){
		boolean hasChild=false;
		for(CCParty ccparty: cCPartylist){
			if(ccparty.getParentId().equals(parendId)){
				hasChild=true;
				break;
			}
		}
		if(!hasChild){
			return;
		}
		for(CCParty ccparty: cCPartylist){
			if(ccparty.getParentId().equals(parendId)){
				ZTreeView node = new ZTreeView();
				node.setId(ccparty.getId());
				node.setName(ccparty.getName());
				node.setpId(ccparty.getParentId());
				node.setOpen(true);
				tree.add(node);
				addChildCCParty(cCPartylist,tree,ccparty.getId());
			}
		}
	}
	
	/**
	 * 获取当前组织的子节点及平级组织
	 * @param cCPartylist
	 * @param tree
	 * @param parendId
	 * @param orgParentId 登陆用户的所属组织
	 * @param currentOrgId 登陆用户的所属父组织
	 */
	private void addChildCCPartySunAndRate(List<CCParty> cCPartylist,List<ZTreeView>tree,String parendId,String currentOrgId,String orgParentId){
		boolean hasChild=false;
		for(CCParty ccparty: cCPartylist){
			if(ccparty.getParentId().equals(parendId) &&(ccparty.getId().indexOf(orgParentId)!=-1 || ccparty.getParentId().equals(currentOrgId))){
				hasChild=true;
				break;
			}
		}
		if(!hasChild){
			return;
		}
		for(CCParty ccparty: cCPartylist){
			if(ccparty.getParentId().equals(parendId) &&(ccparty.getId().indexOf(orgParentId)!=-1 || ccparty.getParentId().equals(currentOrgId))){
				ZTreeView node = new ZTreeView();
				node.setId(ccparty.getId());
				node.setName(ccparty.getName());
				node.setpId(ccparty.getParentId());
				node.setOpen(true);
				tree.add(node);
				addChildCCPartySunAndRate(cCPartylist,tree,ccparty.getId(),currentOrgId,orgParentId);
			}
		}
	}
	
	/**
	 * 获取当前组织的子节点
	 * @param cCPartylist	所有组织
	 * @param tree	组织的树
	 * @param parendId	//当前组织的父id
	 */
	private void addChildCCPartySun(List<CCParty> cCPartylist,List<ZTreeView>tree,String parendId,String currentId){
		boolean hasChild=false;
		for(CCParty ccparty: cCPartylist){
			if(ccparty.getParentId().equals(parendId)){
				hasChild=true;
				break;
			}
		}
		if(!hasChild){
			return;
		}
		for(CCParty ccparty: cCPartylist){
			if(ccparty.getParentId().equals(parendId)){
				ZTreeView node = new ZTreeView();
				node.setId(ccparty.getId());
				node.setName(ccparty.getName());
				node.setpId(ccparty.getParentId());
				node.setOpen(true);
				tree.add(node);
				addChildCCParty(cCPartylist,tree,ccparty.getId());
			}
		}
	}
	
	public CCParty getCCParty(String parentId) {
		return cCPartyManager.getCCPartyById(parentId);
	}
	public boolean editCCParty(User user,JSONObject json) throws ParseException {
		
		CCParty party=cCPartyManager.getCCPartyById(json.getString("id"));
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		party.setName(json.getString("name"));
		party.setType(json.getInt("type"));
		party.setParentId(json.getString("parentId"));
		party.setStatus(json.getInt("status"));
		
		party.setOrganization(organizationManager.getOrganization(json.getString("orgId")));
		
		if(json.getString("documentTime")!=null && !"".equals(json.getString("documentTime"))){
			Date documentTime = (Date) f.parseObject(json.getString("documentTime"));
			Timestamp documentTimeTs = new Timestamp(documentTime.getTime());
			party.setDocumentTime(documentTimeTs);
		}
		if(json.getString("expirationTime")!=null && !"".equals(json.getString("expirationTime"))){
			Date expirationTime = (Date) f.parseObject(json.getString("expirationTime"));
			Timestamp expirationTimeTs = new Timestamp(expirationTime.getTime());
			party.setExpirationTime(expirationTimeTs);
		}
		party.setDocumentNo(json.getString("documentNo"));
		//修改时间和修改人
		party.setUpdateTime(new Timestamp(System.currentTimeMillis()));	//更新时间
		party.setUpdateUser(user.getName());	//更新人
		
		cCPartyManager.update(party);
		return true;
	}
	public boolean deleteCCParty(User user,String id) {
		
		CCParty party=cCPartyManager.getCCPartyById(id);	
		cCPartyManager.delete(party);
		return true;
	}
	
}