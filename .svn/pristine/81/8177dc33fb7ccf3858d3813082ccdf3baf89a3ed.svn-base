package org.tpri.djcom.controller.obt;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tpri.djcom.controller.BaseController;
import org.tpri.djcom.entity.obt.PartyMember;
import org.tpri.djcom.service.obt.PartyMemberService;

/**O
 * @description 党员信息控制器
 * @author 常华荣
 * @since 2015-05-04
 */
@Controller
@RequestMapping("/org")
public class PartyMemberController  extends BaseController{

	@Autowired
	private PartyMemberService partyMemberService;
	
	/**
	 * 加载党员列表
	 */
	@RequestMapping("loadPartyMember")
	@ResponseBody
	public Map loadPartyMember(HttpServletRequest request) {
		logger.debug("PartyMemberController loadPartyMember begin");
		
		JSONObject jo = getJsonPara(request);
		Integer start=jo.optString("start",null)==null?null:jo.getInt("start");
		Integer limit=jo.optString("limit",null)==null?null:jo.getInt("limit");
		
		List list=partyMemberService.loadPartyMember(start,limit);
		Map ret = new HashMap();
		ret.put("items", list);
		ret.put("totalCount", list.size());
		
		logger.debug("PartyMemberController loadPartyMember end");
		return ret;
	}
	/**
	 * 加载所有党员列表
	 */
	@RequestMapping("loadAllPartyMember")
	@ResponseBody
	public Map loadAllPartyMember(HttpServletRequest request) {
		logger.debug("PartyMemberController loadAllPartyMember begin");
		
		List list=partyMemberService.loadAllPartyMember();
		Map ret = new HashMap();
		ret.put("items", list);
		
		logger.debug("PartyMemberController loadAllPartyMember end");
		return ret;
	}
	 /** 
	  * 加载某党组织的党员列表
	 */
	@RequestMapping("loadPartyMemberByCcpartyId")
	@ResponseBody
	public Map loadPartyMemberByCcpartyId(HttpServletRequest request) {
		logger.debug("PartyMemberController loadPartyMember begin");
		String ccpartyId=getString(request,"ccpartyId");
		JSONObject jo = getJsonPara(request);
		Integer start=jo.optString("start",null)==null?null:jo.getInt("start");
		Integer limit=jo.optString("limit",null)==null?null:jo.getInt("limit");
		
		List list=partyMemberService.loadPartyMemberByCcpartyId(start,limit,ccpartyId);
		Map ret = new HashMap();
		ret.put("items", list);
		ret.put("totalCount", list.size());
		
		logger.debug("PartyMemberController loadPartyMember end");
		return ret;
	}
	/**
	 * 新增党员用户
	 */
	@RequestMapping("addPartyMember")
	@ResponseBody
	public Map addPartyMember(HttpServletRequest request) {
		logger.debug("PartyMemberController addPartyMember begin");
		Map ret = new HashMap();
		JSONObject json=new JSONObject();
		json.put("name", getString(request,"name"));				//姓名
		json.put("gender", getString(request,"gender"));			//性别
		json.put("nation", getString(request,"nation"));			//民族
		json.put("birthPlace", getString(request,"birthPlace"));	//籍贯
		json.put("idNumber", getString(request,"idNumber"));		//身份证号
		json.put("education", getString(request,"education"));		//教育
		json.put("occupation", getString(request,"occupation"));	//职业
		json.put("jobTitle", getString(request,"jobTitle"));		//行政职务
		json.put("officePhone", getString(request,"officePhone"));	//办公电话
		json.put("mobile", getString(request,"mobile"));			//手机
		json.put("email", getString(request,"email"));				//电子邮件
		json.put("address", getString(request,"address"));			//单位地址
		json.put("status", getString(request,"status"));			//状态
		json.put("picture", getString(request,"picture"));			//照片  暂时不用
		json.put("partyNo", getString(request,"partyNo"));			//党员代号
		json.put("joinTime", getString(request,"joinTime"));		//入党时间
		json.put("type", getString(request,"type"));				//党员类型
		json.put("ccpartyId", getString(request,"ccpartyId"));		//所属党组织
		json.put("introducer1", getString(request,"introducer1"));	//入党介绍人1
		json.put("introducer2", getString(request,"introducer2"));	//入党介绍人2
		
		try {
			//验证身份证号是否存在
			List<PartyMember> members = partyMemberService.loadPartyMemberByIdNumber(json.getString("idNumber"));
			if(members!=null && members.size()>0){
				ret.put("success", false);
				ret.put("msg", "该身份证号已存在，请勿重复添加。");
				return ret;
			}
			partyMemberService.addPartyMember(loadUser(request),json);
		} catch (ParseException e) {
			ret.put("success", false);
			ret.put("msg", "保存失败");
			logger.error("PartyMemberController addPartyMember 异常："+e.getMessage());
			return ret;
		}
		ret.put("success", true);
		ret.put("msg", "保存成功");
		logger.debug("PartyMemberController addPartyMember end");
		return ret;
	}
	
	/**
	 * 编辑党员用户
	 */
	@RequestMapping("editPartyMember")
	@ResponseBody
	public Map editPartyMember(HttpServletRequest request) {
		logger.debug("PartyMemberController editPartyMember begin");
		String id=getString(request,"id");
		String name=getString(request,"name");
		String partyNo=getString(request,"partyNo");
		String gender=getString(request,"gender");
		String idNumber=getString(request,"idNumber");
		String nation=getString(request,"nation");
		String occupation=getString(request,"occupation");
		String education=getString(request,"education");
		String jobTitle=getString(request,"jobTitle");
		String birthPlace=getString(request,"birthPlace");
		String officePhone=getString(request,"officePhone");
		String mobile=getString(request,"mobile");
		String email=getString(request,"email");
		String address=getString(request,"address");
		String ccpartyId=getString(request,"ccpartyId");
		String joinTime=getString(request,"joinTime");
		String type=getString(request,"type");
		String introducer1=getString(request,"introducer1");
		String introducer2=getString(request,"introducer2");		
		String picture=getString(request,"picture");
		String status=getString(request,"status");
		String description=getString(request,"description");
		
		try {
			partyMemberService.editPartyMember(loadUser(request),id,name,partyNo,gender,idNumber,nation,occupation,education,jobTitle,birthPlace,officePhone,mobile,email,address,ccpartyId,joinTime,type,introducer1,introducer2,picture,status,description);
		} catch (ParseException e) {
			logger.error("editPartyMember 保存异常"+e.getMessage());
		}
		Map ret = new HashMap();
		ret.put("success", true);
		ret.put("msg", "保存成功");
		logger.debug("PartyMemberController editPartyMember end");
		return ret;
	}
	/**
	 * 编辑用户
	 */
	@RequestMapping("delPartyMember")
	@ResponseBody
	public Map delPartyMember(HttpServletRequest request) {
		logger.debug("PartyMemberController editPartyMember begin");
		String id=getString(request,"id");	
		Map ret = new HashMap();
		try {
			partyMemberService.deletePartyMember(id);
			ret.put("success", true);
			ret.put("msg", "保存成功");
			logger.debug("PartyMemberController editPartyMember end");
			return ret;
			
		} catch (Exception e) {
			ret.put("success", false);
			ret.put("msg", "删除失败！");
			logger.debug("PartyMemberController editPartyMember exception",e);
			return ret;
			
		}
	}
}
