package org.tpri.djcom.controller.org;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tpri.djcom.controller.BaseController;
import org.tpri.djcom.entity.obt.PartyMember;
import org.tpri.djcom.entity.org.CCParty;
import org.tpri.djcom.entity.org.OrgPartyLeader;
import org.tpri.djcom.entity.sys.EnvironmentId;
import org.tpri.djcom.entity.uam.User;
import org.tpri.djcom.manager.org.CCPartyManager;
import org.tpri.djcom.manager.org.OrgPartyLeaderManager;
import org.tpri.djcom.service.obt.PartyMemberService;
import org.tpri.djcom.service.org.CCpartyService;
import org.tpri.djcom.view.ZTreeView;

/**
 * @description 党组织控制器
 * @author 易文俊
 * @since 2015-04-24
 */
@Controller
@RequestMapping("/org")
public class CCPartyController extends BaseController {

    @Resource(name = "CCpartyService")
    private CCpartyService cCpartyService;

    @Resource(name = "CCPartyManager")
    CCPartyManager cCPartyManager;

    @Resource(name = "PartyMemberService")
    private PartyMemberService partyMemberService;

    @Resource(name = "OrgPartyLeaderManager")
    OrgPartyLeaderManager orgPartyLeaderManager;

    private Logger logger = Logger.getLogger(CCPartyController.class);
    public static String DEFAULT_ROOT_PARTY_ID = "000";

    /**
     * 根据parentId获取下级党组织列表
     */
    @RequestMapping("getCCPartyList")
    @ResponseBody
    public Map getCCPartyList(HttpServletRequest request) {
	logger.debug(this.getClass() + " getCCPartyList begin");
	Map ret = new HashMap();
	String parentId = getString(request, "parentId");
	String queryType = getString(request, "queryType"); // 查询类型

	String showEnabledStatus = getString(request, "showEnabledStatus"); // 判断是否查询挂起的组织
	User user = loadUser(request);
	List list = null;
	if ("querySun".equals(queryType) && user.getCcpartyId() != null
		&& !"".equals(user.getCcpartyId())) {
	    // 查询子节点
	    list = cCpartyService.getCCPartyListAndSun(parentId,
		    showEnabledStatus, user);
	    CCParty ccparty = cCPartyManager
		    .getCCPartyById(user.getCcpartyId());
	    ret.put("parentId", ccparty.getParentId());
	} else {
	    // 查询所有
	    list = cCpartyService.getCCPartyList(parentId, showEnabledStatus);
	    ret.put("parentId", "root");
	}

	ret.put("items", list);
	logger.debug(this.getClass() + " getCCPartyList end");
	return ret;
    }

    /**
     * 根据parentId获取下级党组织列表及子节点
     */
    @RequestMapping("getCCPartyListAndSun")
    @ResponseBody
    public Map getCCPartyListAndSun(HttpServletRequest request) {
	logger.debug(this.getClass() + " getCCPartyListAndSun begin");

	String parentId = getString(request, "parentId");
	String showEnabledStatus = getString(request, "showEnabledStatus"); // 判断是否查询挂起的组织
	List list = cCpartyService.getTreeCCPartyById(parentId);

	Map ret = new HashMap();
	ret.put("items", list);
	logger.debug(this.getClass() + " getCCPartyListAndSun end");
	return ret;
    }

    /**
     * 获取党组织的树形结构
     */
    @RequestMapping("getTreeCCParty")
    @ResponseBody
    public Map getTreeCCParty(HttpServletRequest request) {
	logger.debug(this.getClass() + " getTreeCCParty begin");
	User user = loadUser(request);
	String ccpartyId = getString(request, "ccpartyId");
	// 加载类型 统计分析 只列出统计节点和子节点
	String loadType = getString(request, "loadType");
	List<ZTreeView> tree = new ArrayList<ZTreeView>();
	if ((ccpartyId == null || ccpartyId.equals("")) && user.isSystemAdmin()) {
	    tree = cCpartyService.getTreeAllCCParty();
	} else {
	    tree = cCpartyService.getTreeCCParty(ccpartyId, loadType);
	}

	Map ret = new HashMap();
	ret.put("items", tree);
	logger.debug(this.getClass() + " getTreeCCParty end");
	return ret;
    }

    /**
     * 根据系统初始化党组织编号获取党组织的树形结构展示
     * 
     * @author guojing
     * 
     */

    @RequestMapping("getTreeCCPartyShow")
    @ResponseBody
    public Map<String, List<CCParty>> getTreeCCPartyShow(
	    HttpServletRequest request) {
	String rootPartyID = null;
	logger.debug("getTreeCCPartyShow begin");
	User user = loadUser(request);
	
	if (user == null) {
	    rootPartyID = (String) getEnvironmentValueById(EnvironmentId.INITIAL_CCPARTYID);
	}else {
	    rootPartyID = user.getCcpartyId();
	}
	
	List<CCParty> ccParties = cCpartyService
		.getCCPartyListByParentId(rootPartyID);

	Map<String, List<CCParty>> ret = new HashMap<String, List<CCParty>>();
	ret.put("items", ccParties);
	return ret;
    }

    /**
     * 根据文章ID获取组织
     */
    @RequestMapping("getTreeCCPartyByArticleId")
    @ResponseBody
    public Map getTreeCCPartyByArticleId(HttpServletRequest request) {
	logger.debug(this.getClass() + " getTreeCCPartyByArticleId begin");
	User user = loadUser(request);
	String ccpartyId = getString(request, "ccpartyId");
	String articleId = getString(request, "articleId");

	List<ZTreeView> tree = new ArrayList<ZTreeView>();
	List<CCParty> existCpartys = new ArrayList<CCParty>();
	// 获取所有组织
	tree = cCpartyService.getTreeAllCCParty();
	existCpartys = cCpartyService.getExistTreeCCPartyByArticleId(articleId);

	Map ret = new HashMap();
	ret.put("items", tree);
	ret.put("existCpartys", existCpartys);
	logger.debug(this.getClass() + " getTreeCCPartyByArticleId end");
	return ret;
    }

    /**
     * 获取党组织的树形结构 只查询平级 和所属组织的子节点
     */
    @RequestMapping("getTreeCCPartyStatisticsCount")
    @ResponseBody
    public Map getTreeCCPartyStatisticsCount(HttpServletRequest request) {
	logger.debug(this.getClass() + " getTreeCCPartyStatisticsCount begin");
	User user = loadUser(request);
	String ccpartyId = getString(request, "ccpartyId");
	List<ZTreeView> tree = new ArrayList<ZTreeView>();
	if ((ccpartyId == null || ccpartyId.equals("")) && user.isSystemAdmin()) {
	    tree = cCpartyService.getTreeAllCCParty();
	} else {
	    tree = cCpartyService.getTreeCCPartyStatisticsCount(ccpartyId);
	}

	Map ret = new HashMap();
	ret.put("items", tree);
	logger.debug(this.getClass() + " getTreeCCPartyStatisticsCount end");
	return ret;
    }

    /**
     * 新增党组织
     */
    @RequestMapping("addCCParty")
    @ResponseBody
    public Map addCCParty(HttpServletRequest request) {
	logger.debug(this.getClass() + " addCCParty begin");

	String id = getString(request, "id");
	String name = getString(request, "name");
	String parentId = getString(request, "parentId");
	String orgId = getString(request, "orgId");
	int type = getInt(request, "type");
	String documentNo = getString(request, "documentNo");
	String documentTime = getString(request, "documentTime");
	String expirationTime = getString(request, "expirationTime");
	JSONObject json = new JSONObject();
	json.put("id", id);
	json.put("name", name);
	json.put("parentId", parentId);
	json.put("orgId", orgId);
	json.put("type", type);
	json.put("documentNo", documentNo);
	json.put("documentTime", documentTime);
	json.put("expirationTime", expirationTime);

	try {
	    if (!cCpartyService.addCCParty(loadUser(request), json)) {
		Map ret = new HashMap();
		ret.put("success", false);
		ret.put("msg", "保存失败，组织ID已被占用，请更换。");
		logger.debug(this.getClass() + " addCCParty begin");
		return ret;
	    }
	} catch (ParseException e) {
	    e.printStackTrace();
	    logger.error(" addCCParty 党组织新增保存信息异常：" + e.getMessage());
	    // TODO
	    // 统一跳转出错页面
	}
	Map ret = new HashMap();
	ret.put("success", true);
	ret.put("msg", "保存成功");
	logger.debug(this.getClass() + " addCCParty begin");
	return ret;
    }

    /**
     * 修改党组织
     */
    @RequestMapping("editCCParty")
    @ResponseBody
    public Map editCCParty(HttpServletRequest request) {
	logger.debug(this.getClass() + " editCCParty begin");

	String id = getString(request, "id");
	String name = getString(request, "name");
	String parentId = getString(request, "parentId");
	String orgId = getString(request, "orgId");
	String status = getString(request, "status");
	int type = getInt(request, "type");
	String documentNo = getString(request, "documentNo");
	String documentTime = getString(request, "documentTime");
	String expirationTime = getString(request, "expirationTime");
	JSONObject json = new JSONObject();
	json.put("id", id);
	json.put("name", name);
	json.put("parentId", parentId);
	json.put("orgId", orgId);
	json.put("type", type);
	json.put("status", status);
	json.put("documentNo", documentNo);
	json.put("documentTime", documentTime);
	json.put("expirationTime", expirationTime);
	try {
	    cCpartyService.editCCParty(loadUser(request), json);
	} catch (ParseException e) {
	    e.printStackTrace();
	    logger.error(" editCCParty 党组织修改保存信息异常：" + e.getMessage());
	    // TODO
	    // 统一跳转出错页面
	}
	Map ret = new HashMap();
	ret.put("success", true);
	ret.put("msg", "更新成功");
	logger.debug(this.getClass() + " editCCParty begin");
	return ret;
    }

    /**
     * 新增党组织 注：该方法暂不使用
     */
    @RequestMapping("delCCParty")
    @ResponseBody
    public Map delCCParty(HttpServletRequest request) {
	logger.debug(this.getClass() + " delCCParty begin");

	String id = getString(request, "id");

	JSONObject json = new JSONObject();

	cCpartyService.deleteCCParty(loadUser(request), id);
	Map ret = new HashMap();
	ret.put("success", true);
	ret.put("msg", "删除成功");
	logger.debug(this.getClass() + " delCCParty end");
	return ret;
    }

    /**
     * 获取党组织
     */
    @RequestMapping("getCCParty")
    @ResponseBody
    public Map getCCParty(HttpServletRequest request) {
	logger.debug(this.getClass() + " getCCParty begin");

	String parentId = getString(request, "parentId");
	CCParty c = cCpartyService.getCCParty(parentId);
	Map ret = new HashMap();
	ret.put("items", c);
	ret.put("success", true);
	ret.put("msg", "获取成功");
	logger.debug(this.getClass() + " getCCParty begin");
	return ret;
    }

    /**
     * 获取组织领导班子成员和可以设置的成员
     */
    @RequestMapping("getCCPartyLeadderInfos")
    @ResponseBody
    public Map getCCPartyLeadderInfos(HttpServletRequest request) {
	logger.debug(this.getClass() + " getCCPartyLeadderInfos begin");
	Map ret = new HashMap();

	String ccpartyId = getString(request, "ccpartyId");

	List<PartyMember> noSettLeaders = partyMemberService
		.loadPartyMemberByPartyId(ccpartyId);
	List<OrgPartyLeader> settLeaders = orgPartyLeaderManager
		.loadPartyLeaderByPartyId(ccpartyId);

	ret.put("noSettLeaders", noSettLeaders);
	ret.put("settLeaders", settLeaders);
	ret.put("success", true);
	ret.put("msg", "获取成功");
	logger.debug(this.getClass() + " getCCPartyLeadderInfos begin");
	return ret;
    }

    /**
     * 保存领导班子
     * 
     * @param request
     * @return
     */
    @RequestMapping("savePartyLeader")
    @ResponseBody
    public Map savePartyLeader(HttpServletRequest request) {
	logger.debug(this.getClass() + " savePartyLeader begin");
	Map ret = new HashMap();

	String orgId = getString(request, "orgId");
	String leaderIds = getString(request, "leaderIds");
	try {
	    if (orgPartyLeaderManager.updatePartyLeaderByRoleIds(orgId,
		    leaderIds, loadUser(request))) {
		ret.put("success", true);
		ret.put("msg", "保存成功！");
	    } else {
		ret.put("success", false);
		ret.put("msg", "保存失败！");
	    }

	} catch (Exception e) {
	    ret.put("success", false);
	    ret.put("msg", "保存失败！");
	    logger.debug(this.getClass() + "savePartyLeader exception", e);
	}
	logger.debug(this.getClass() + " savePartyLeader begin");
	return ret;
    }

}
