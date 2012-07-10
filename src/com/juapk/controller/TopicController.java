package com.juapk.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.juapk.domain.Topic;
import com.juapk.frame.GlobalConstants;
import com.juapk.frame.common.DwzPageModel;
import com.juapk.frame.common.PageModel;
import com.juapk.frame.common.QueryItem;
import com.juapk.frame.exception.BaseException;
import com.juapk.service.SoftService;
import com.juapk.service.TopicService;

/**
 * 项目名称：juapk
 * 类名称：TopicController
 * 类描述：专题业务处理类 
 * 创建人：hubin
 * 创建时间：2011.12.25
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version v1.0
 */
@Controller
public class TopicController extends BaseController{
	@Autowired
	private SoftService softInfoService;
	@Autowired
	private TopicService topicService;
	
	/**
	 * 专题
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/topic.do")
	public ModelAndView GoTopic(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(GlobalConstants.HEADER_LOCATION, GlobalConstants.HEADER_TOPIC);
		Topic softTopic = null;	// 专题推荐
		try {
			softTopic = topicService.getTopicByWhere("limit 0,1");
			result.put("topic",softTopic);
		} catch (BaseException e) {
			LOGGER.error("GoTopic 查找topic失败" + e.getMessage());
		}
		try {
			result.put("topicList", topicService.getAllTopicList());
		} catch (BaseException e) {
			LOGGER.error("GoTopic 查找topicList失败" + e.getMessage());
		}
		try {
			result.put("softList", softInfoService.getSoftTopicListByWhere("where b.topic_id = " + softTopic.getTopic_id() + " limit 0,10"));
			result.put("softListA", softInfoService.getSoftTopicListByWhere("where b.topic_id = 1 limit 0,10"));
			result.put("softListB", softInfoService.getSoftTopicListByWhere("where b.topic_id = 2 limit 0,10"));
			result.put("softListC", softInfoService.getSoftTopicListByWhere("where b.topic_id = 3 limit 0,10"));
		} catch (BaseException e) {
			LOGGER.error("GoTopic 查询所专题软件信息失败" + e.getMessage());
		}
		return new ModelAndView("topic", result);
	}
	
	/**
	 * 专题列表
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/topicList.do")
	private ModelAndView getTopicList(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(GlobalConstants.HEADER_LOCATION, GlobalConstants.HEADER_TOPIC);
		// 专题推荐软件
		result.put(GlobalConstants.TOPIC_TYPE_FLAG, GlobalConstants.TOPIC_TYPE_SOFT);
		// 专题 id
		String topicId = request.getParameter("topicId");
		PageModel softPageModel = new PageModel();
		try {
			result.put("topic", topicService.getTopicListByTopicId(topicId));
		} catch (BaseException e) {
			LOGGER.error("getTopicList 查找topic失败" + e.getMessage());
		}
		try {
			result.put("topicList", topicService.getAllTopicList());
		} catch (BaseException e) {
			LOGGER.error("getTopicList 查找topicList失败" + e.getMessage());
		}
		try {
			result.put("softList", softInfoService.getSoftTopicListByTopicId(topicId));
		} catch (BaseException e) {
			LOGGER.error("getTopicList 查询所专题软件信息失败" + e.getMessage());
		}
		return new ModelAndView("topic", result);
	}
 
	/**
	 * 添加专题
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/s/addTopic.do")
	public ModelAndView adminTopicAdd(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		return new ModelAndView("admin/admin_topic_add", result);
	}
	
	/**
	 * 专题列表
	 * @param request
	 * @param response
	 */
	@RequestMapping("/s/topicList.do")
	public ModelAndView adminTopicList(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		Map<String, Object> result = new HashMap<String, Object>();
		String currentPage = request.getParameter("pageNum"); //翻页
		String numPerPage = request.getParameter("numPerPage"); //每页数量
		if(currentPage==null){
			currentPage = "1";
		}
		int offset = 0; //0 开始翻页
		int pageSize = 10; //默认每页数量 10
		int currentPageInt = Integer.parseInt(currentPage);
		if(numPerPage != null){
			pageSize = Integer.parseInt(numPerPage);
		}
		offset = (currentPageInt-1)*pageSize;
		QueryItem queryItem = new QueryItem();
		StringBuffer sqlWhere = new StringBuffer();
		queryItem.setOffset(offset);
		queryItem.setPageSize(pageSize); //每页显示数量
		queryItem.setSql(sqlWhere.toString());
		/** 专题总记录数 */
		int topicCount = topicService.getTopicCountByWhere(sqlWhere.toString());
		DwzPageModel topicPageModel = new DwzPageModel(topicCount,currentPageInt,pageSize);
		topicPageModel.setData(topicService.getTopicListByQueryItem(queryItem));
		result.put("topicPageModel", topicPageModel);
		return new ModelAndView("admin/admin_topic_list", result);
	}
	
}