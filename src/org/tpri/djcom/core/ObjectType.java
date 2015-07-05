package org.tpri.djcom.core;
/**
 * @description 对象注册值表
 * @author 易文俊
 * @since 2015-04-02
 */
public class ObjectType {
	
	public static final int UNKNOWN = -1;
	public static final int OBJECTREGISTER = 0;
	
	//公共表
    public static final int PUB_NOTICE = 10;
    public static final int PUB_ASSESSMENT = 11;
    public static final int PUB_REMINDER = 12;
    public static final int PUB_MESSAGE = 13;
    public static final int PUB_ASSESSTARGET = 14;
    public static final int PUB_NOTICE_RECORD = 15;
    public static final int PUB_ASSESSMENT_TOPIC = 16;
    public static final int PUB_TOPIC_OPTION = 17;
    public static final int PUB_TOPIC_ANSWER = 18;
    public static final int PUB_ASSESSRESULT = 19;
    
    //通用资源管理表
    public static final int COM_BASEINFO = 20;
    public static final int COM_RESOURCETYPE = 21;
    public static final int COM_COMMENT = 22;
    
    
    //用户权限管理表
    public static final int UAM_USER = 30;
    public static final int UAM_ROLE = 31;
    public static final int UAM_PRIVILEGE = 32;
    public static final int UAM_USERROLE = 33;
    public static final int UAM_ROLEPRIVILEGE = 34;
    
    //系统表
    public static final int SYS_NAVIGATION = 40;
    public static final int SYS_ENVIRONMENT = 41;
    public static final int SYS_ENUMERATION = 42;
    public static final int SYS_CODE = 43;
    
    //党政管理表
    public static final int ORG_CCPARTY = 50;
    public static final int ORG_ORGANIZATION = 51;
    public static final int ORG_PARTY_LEADER = 53;
    
    //任务表
    public static final int TSK_MATERIAL_REPORT = 60;
    public static final int TSK_INFO_REPORT = 61;
    
    //计划、工作、会议相关表
    public static final int SCH_WORK_APPLICATION = 70;
    public static final int SCH_WORK_APPLICANTS = 71;
    
    //组织建设
    public static final int OBT_PARTY_MEMBER = 80;
    public static final int OBT_ORG_ACTIVITY = 81;
    public static final int OBT_ELECTION = 82;
    public static final int OBT_ELECTION_MEMBER = 83;
    public static final int OBT_ORG_ACTIVITY_APPLICANTS = 84;
    
    
    //支部工作法
    public static final int ZBSC_ARTICLE = 100;
    public static final int ZBSC_CATEGORY = 101;
    public static final int ZBSC_ARTICLECATEGORY = 102;
    public static final int ZBSC_INFOMATION = 103;
    public static final int ZBSC_UPLOADFILE = 104;
    public static final int ZBSC_UPLOADFILEDATA = 105;
    public static final int ZBSC_COMMENT = 106;
    public static final int ZBSC_PRAISE = 107;
    
    //查询结果集表
    public static final int CountResult = 200;
}
