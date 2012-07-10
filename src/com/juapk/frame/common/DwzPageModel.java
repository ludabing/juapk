package com.juapk.frame.common;

import java.util.List;

/**
 * 项目名称：juapk
 * 类名称：DwzPageModel
 * 类描述：分页组件
 * 创建人：hubin
 * 创建时间：2012-7-8 下午2:52:22
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version v1.0
 */
public class DwzPageModel {
	private List<?> data; //显示列表
	private long allCount; //所有记录数
	private int prePage; //上一页
	private int nextPage; //下一页
	private String linkUrl; //分页URL
	private int currentPage; //当前页号
	private int pageCount; //可以分成多少页
	private int numPerPage; //每一页多少
	private int maxShow = 10; //最多显示多少页码,默认显示页面1-10
	
	/**
	 * 私有、为计算分页数量pageCount
	 * 不允许无参构造函数 
	 */
	DwzPageModel(){
	}
	
	public DwzPageModel(int _allCount,int _currentPage,int _numPerPage){
		this.allCount = _allCount;
		this.currentPage = _currentPage;
		this.numPerPage = _numPerPage;
		this.pageCount = (int) Math.ceil((double)_allCount/(double)_numPerPage);
	}
	
	public List<?> getData() {
		return data;
	}
	
	public void setData(List<?> data) {
		this.data = data;
	}
	
	public long getAllCount() {
		return allCount;
	}
	
	public void setAllCount(long allCount) {
		this.allCount = allCount;
	}
	
	public int getPrePage() {
		return prePage;
	}
	
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}
	
	public int getNextPage() {
		return nextPage;
	}
	
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	
	public String getLinkUrl() {
		return linkUrl;
	}
	
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getPageCount() {
		return pageCount;
	}
	
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	public int getNumPerPage() {
		return numPerPage;
	}
	
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	
	public int getMaxShow() {
		return maxShow;
	}
	
	public void setMaxShow(int maxShow) {
		this.maxShow = maxShow;
	}
	
}