package org.tpri.djcom.util;
/**
 * 分页帮助类
 * @author zhaozijing
 *
 */
public class PageBean {
	
	//每页显示记录数
	private int pageSize=10;
	//查询的起始记录
	private int start = 0;
	//总记录数
	private int totalCount=0;
	//总页数
	private int totalPage=0;
	//当前页号
	private int curPage=0;
	//是否是最后一页
	private boolean isLastPage=false;
	/**
	 * 获取每页显示记录数
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * 设置每页显示记录数
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * 获取总记录数
	 * @return
	 */
	public int getTotalCount() {
		return totalCount;
	}
	/**
	 * 设置总记录数,根据总记录数和每页显示记录数来计算出总页数并对属性进行初始化
	 * @param totalCount
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		this.totalPage=computeTotalPages(totalCount,pageSize);
		if(isLastPage){
			setCurPage(totalPage);
		}
	}
	/**
	 * 根据总记录数和每页记录数计算出总页数
	 */
	public int  computeTotalPages(int totalCount,int pageSize){
		int result=0;
		if(totalCount==0){ //总记录数为0，总页数也为0
			return 0;
		}
		if(pageSize==0){//总记录数不为0，每页显示记录数为0，那么总页数为1
			return 1;
		}
		int temp=totalCount/pageSize;
		if(temp==0){//总记录数小于每页显示记录数，总页数为1
			result=1;
		}else if(totalCount%pageSize!=0){ //总记录数不是每页显示记录数的倍数，总页数+1
			result=temp+1;
		}else{//总记录数是每页显示记录数的倍数，那么总记录数=temp
			result=temp;
		}
		return result;
	}
	/**
	 * 获取总页数
	 * @return
	 */
	public int getTotalPage() {
		return totalPage;
	}
	/**
	 * 设置总页数
	 * @param totalPage
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	/**
	 * 获取当前页号
	 * @return
	 */
	public int getCurPage() {
		return curPage;
	}
	/**
	 * 获取当期页记录数
	 * @return
	 */
	public int getCurPageRows(){
		if(pageSize==0){//如果pageSize为0,总记录数即当前页的记录数
			return totalCount;
		}
		if(totalCount==0){//如果总记录数为0当前页的记录数也为0
			return 0;
		}
		if(this.getCurPage()!=this.getTotalPage()){//如果不是最后一页当前页的记录数等于每页的记录数
			return pageSize;
		}
		return  totalCount-(getTotalPage()-1)*pageSize; //最后一页的记录数
	}
	/**
	 * 设置当前页号
	 * @param curPage
	 */
	public void setCurPage(int curPage) {
		this.curPage = curPage;
		//计算起始记录
		if(curPage==0 || curPage==1){
			start = 0;
		}else{
			start = (this.curPage-1)*pageSize;
		}
		
	}
	/**
	 * 转到第一页
	 */
	public void gotoFirst(){
		setCurPage(1);
	}
	/**
	 * 转到最后一页
	 */
	public void gotoLast(){
		isLastPage=true;
		setCurPage(totalPage);
	}
	/**
	 * 转到指定页
	 */
	public void gotoPage(int page){
		setCurPage(page);
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	
}
