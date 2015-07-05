package org.tpri.djcom.entity.result;
/**
 * 统计分析查询结果集
 * @author zhaozijing
 *
 */
public class QueryResultArticle {
	
	protected String orgId = "";	//组织ID
	protected String name = "";	//模块名称
	protected int slmb = 0; 	//树立目标帖子数
	protected int czwt = 0;	//查找问题帖子数
	protected int mqzr = 0;	//明确热衷帖子数
	protected int jjwt = 0;	//解决问题帖子数
	protected int zjpy = 0;	//总结评议帖子数

	protected int hit = 0;	//浏览数
	protected int reply = 0;	//回复数
	protected int filescount = 0;	//帖子文件数
	
	protected String year = "";	//文章的年份

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSlmb() {
		return slmb;
	}

	public void setSlmb(int slmb) {
		this.slmb = slmb;
	}

	public int getCzwt() {
		return czwt;
	}

	public void setCzwt(int czwt) {
		this.czwt = czwt;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getMqzr() {
		return mqzr;
	}

	public void setMqzr(int mqzr) {
		this.mqzr = mqzr;
	}

	public int getJjwt() {
		return jjwt;
	}

	public void setJjwt(int jjwt) {
		this.jjwt = jjwt;
	}

	public int getZjpy() {
		return zjpy;
	}

	public void setZjpy(int zjpy) {
		this.zjpy = zjpy;
	}

	public int getReply() {
		return reply;
	}

	public void setReply(int reply) {
		this.reply = reply;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getFilescount() {
		return filescount;
	}

	public void setFilescount(int filescount) {
		this.filescount = filescount;
	}
	
	
}

