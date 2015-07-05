package org.tpri.djcom.listener;

import java.util.Properties;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.tpri.djcom.core.MemoryCacheFactory;
import org.tpri.djcom.manager.com.BaseInfoManager;
import org.tpri.djcom.manager.obt.ElectionManager;
import org.tpri.djcom.manager.obt.OrgActivityApplicantsManager;
import org.tpri.djcom.manager.obt.OrgActivityManager;
import org.tpri.djcom.manager.obt.PartyMemberManager;
import org.tpri.djcom.manager.org.CCPartyManager;
import org.tpri.djcom.manager.org.OrgPartyLeaderManager;
import org.tpri.djcom.manager.org.OrganizationManager;
import org.tpri.djcom.manager.pub.AssessmentManager;
import org.tpri.djcom.manager.pub.AssessmentTopicManager;
import org.tpri.djcom.manager.pub.AssessresultManager;
import org.tpri.djcom.manager.pub.AssesstargetManager;
import org.tpri.djcom.manager.pub.MessageManager;
import org.tpri.djcom.manager.pub.NoticeManager;
import org.tpri.djcom.manager.pub.NoticeRecordManager;
import org.tpri.djcom.manager.pub.ReminderManager;
import org.tpri.djcom.manager.pub.TopicAnswerManager;
import org.tpri.djcom.manager.pub.TopicOptionManager;
import org.tpri.djcom.manager.sch.WorkApplicationManager;
import org.tpri.djcom.manager.sys.CodeManager;
import org.tpri.djcom.manager.sys.EnumerationManager;
import org.tpri.djcom.manager.sys.EnvironmentManager;
import org.tpri.djcom.manager.sys.NavigationManager;
import org.tpri.djcom.manager.tsk.InfoReportManager;
import org.tpri.djcom.manager.tsk.MaterialReportManager;
import org.tpri.djcom.manager.uam.PrivilegeManager;
import org.tpri.djcom.manager.uam.RoleManager;
import org.tpri.djcom.manager.uam.UserManager;
import org.tpri.djcom.manager.uam.UserRoleManager;
import org.tpri.djcom.manager.zbsc.ArticleCategoryManager;
import org.tpri.djcom.manager.zbsc.ArticleManager;
import org.tpri.djcom.manager.zbsc.CategoryManager;
import org.tpri.djcom.manager.zbsc.CommentManager;
import org.tpri.djcom.manager.zbsc.InformationManager;
import org.tpri.djcom.manager.zbsc.PraiseManager;
import org.tpri.djcom.manager.zbsc.UploadFileDataManager;
import org.tpri.djcom.manager.zbsc.UploadFileManager;
import org.tpri.djcom.util.BeanFactory;
/**
 * @description 应用监听器
 * @author 易文俊
 * @since 2015-04-02
 */
public class DataPretreater extends ContextLoaderListener{
	public static Logger logger = Logger.getLogger(DataPretreater.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.debug("**********djcom begin start**********");
		MemoryCacheFactory.initialize();
		super.contextInitialized(event);
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		BeanFactory.setApplicationContext(applicationContext);
		initManager(applicationContext);
		initOsName();
        logger.debug("**********djcom started**********");
	}
	private void initManager(ApplicationContext applicationContext){
		
        BaseInfoManager baseInfoManager =(BaseInfoManager) applicationContext.getBean("BaseInfoManager");
        NavigationManager navigationManager =(NavigationManager) applicationContext.getBean("NavigationManager");
        UserManager userManager =(UserManager) applicationContext.getBean("UserManager");
        RoleManager roleManager =(RoleManager) applicationContext.getBean("RoleManager");
        UserRoleManager userRoleManager =(UserRoleManager) applicationContext.getBean("UserRoleManager");
        PrivilegeManager privilegeManager =(PrivilegeManager) applicationContext.getBean("PrivilegeManager");
        InformationManager informationManager =(InformationManager) applicationContext.getBean("InformationManager");
        OrganizationManager organizationManager =(OrganizationManager) applicationContext.getBean("OrganizationManager");
        ArticleManager articleManager =(ArticleManager) applicationContext.getBean("ArticleManager");
        CategoryManager categoryManager =(CategoryManager) applicationContext.getBean("CategoryManager");
        ArticleCategoryManager articleCategoryManager =(ArticleCategoryManager) applicationContext.getBean("ArticleCategoryManager");
        UploadFileDataManager uploadFileDataManager =(UploadFileDataManager) applicationContext.getBean("UploadFileDataManager");
        UploadFileManager uploadFileManager =(UploadFileManager) applicationContext.getBean("UploadFileManager");
        CommentManager commentManager =(CommentManager) applicationContext.getBean("CommentManager");
        PartyMemberManager partyMemberManager =(PartyMemberManager) applicationContext.getBean("PartyMemberManager");
        CCPartyManager cCPartyManager =(CCPartyManager) applicationContext.getBean("CCPartyManager");
        EnvironmentManager environmentManager =(EnvironmentManager) applicationContext.getBean("EnvironmentManager");
        CodeManager codeManager =(CodeManager) applicationContext.getBean("CodeManager");
        EnumerationManager enumerationManager =(EnumerationManager) applicationContext.getBean("EnumerationManager");
        PraiseManager praiseManager =(PraiseManager) applicationContext.getBean("PraiseManager");
        MaterialReportManager materialReportManager =(MaterialReportManager) applicationContext.getBean("MaterialReportManager");
        InfoReportManager infoReportManager =(InfoReportManager) applicationContext.getBean("InfoReportManager");
        WorkApplicationManager workApplicationManager =(WorkApplicationManager) applicationContext.getBean("WorkApplicationManager");
        NoticeManager noticeManager =(NoticeManager) applicationContext.getBean("NoticeManager");
        AssessmentManager assessmentManager =(AssessmentManager) applicationContext.getBean("AssessmentManager");
        ReminderManager reminderManager =(ReminderManager) applicationContext.getBean("ReminderManager");
        MessageManager messageManager =(MessageManager) applicationContext.getBean("MessageManager");
        OrgPartyLeaderManager orgPartyLeaderManager =(OrgPartyLeaderManager) applicationContext.getBean("OrgPartyLeaderManager");
        AssesstargetManager assesstargetManager =(AssesstargetManager) applicationContext.getBean("AssesstargetManager");
        NoticeRecordManager noticeRecordManager =(NoticeRecordManager) applicationContext.getBean("NoticeRecordManager");
        AssessmentTopicManager assessmentTopicManager =(AssessmentTopicManager) applicationContext.getBean("AssessmentTopicManager");
        TopicOptionManager topicOptionManager =(TopicOptionManager) applicationContext.getBean("TopicOptionManager");
        TopicAnswerManager topicAnswerManager =(TopicAnswerManager) applicationContext.getBean("TopicAnswerManager");
        AssessresultManager assessresultManager =(AssessresultManager) applicationContext.getBean("AssessresultManager");
        OrgActivityManager orgActivityManager =(OrgActivityManager) applicationContext.getBean("OrgActivityManager");
        OrgActivityApplicantsManager orgActivityApplicantsManager =(OrgActivityApplicantsManager) applicationContext.getBean("OrgActivityApplicantsManager");
        ElectionManager electionManager =(ElectionManager) applicationContext.getBean("ElectionManager");
        
        baseInfoManager.initialize();
        navigationManager.initialize();
        userManager.initialize();
        roleManager.initialize();
        userRoleManager.initialize();
        privilegeManager.initialize();
        informationManager.initialize();
        organizationManager.initialize();
        articleManager.initialize();
        categoryManager.initialize();
        articleCategoryManager.initialize();
        uploadFileDataManager.initialize();
        uploadFileManager.initialize();
        commentManager.initialize();
        partyMemberManager.initialize();
        cCPartyManager.initialize();
        environmentManager.initialize();
        codeManager.initialize();
        enumerationManager.initialize();
        praiseManager.initialize();
        materialReportManager.initialize();
        infoReportManager.initialize();
        workApplicationManager.initialize();
        noticeManager.initialize();
        assessmentManager.initialize();
        reminderManager.initialize();
        messageManager.initialize();
        orgPartyLeaderManager.initialize();
        assesstargetManager.initialize();
        noticeRecordManager.initialize();
        assessmentTopicManager.initialize();
        topicOptionManager.initialize();
        topicAnswerManager.initialize();
        assessresultManager.initialize();
        orgActivityManager.initialize();
        orgActivityApplicantsManager.initialize();
        electionManager.initialize();
        
	}
	private void initOsName() {
		Properties properties = new Properties(System.getProperties());
		String osName = properties.getProperty("os.name");
		logger.debug("current os name:" + osName);
	}
}
