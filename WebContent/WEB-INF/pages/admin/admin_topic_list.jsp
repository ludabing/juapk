<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageHeader">
<form rel="pagerForm" onsubmit="return navTabSearch(this);"
	action="<c:url value="/s/topicList.do"/>" method="get">
<div class="searchBar">
<table class="searchContent">
	<tr>
		<td>&nbsp;&nbsp;角色名称：<input name="keyword" value="" class="required" type="text" /></td>
		<td>
		 <div class="subBar" style="margin-top: -4px;">
			<ul style="float: left;">
			<li>
			<div class="buttonActive">
				<div class="buttonContent">
					<button type="submit">查询</button>
				</div>
			</div>
			</li>
			</ul>
		 </div>
		</td>
	</tr>
</table>
</div>
</form>
</div>
<div class="pageContent">
<div class="panelBar">
<ul class="toolBar">
	<li><a class="add" href="${BaseURL}${listPage.model}/new" target="dialog" width="300" height="200" title="添加"><span>添加</span></a></li>
	<li><a class="edit" href="${BaseURL}${listPage.model}/{pojo_id}/edit" target="dialog" width="300" height="200" warn="请选择一条记录"><span>修改</span></a></li>
	<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids"
		href="${BaseURL}${listPage.model}/batchRemove?_method=delete" class="delete"><span>删除</span></a></li>
	<li class="line">line</li>
	<li><a class="icon" href="javascript:;"><span>导入EXCEL</span></a></li>
</ul>
</div>
<table class="table" width="100%" layoutH="112">
	<thead>
		<tr>
			<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
			<!-- >th width="50">ID</th-->
			<th width="200">标题</th>
			<th>详细说明</th>
			<th width="80">操作</th>
		</tr>
	</thead>
	<tbody>
    <c:if test="${topicPageModel == null}">
    	<tr><td><center>抱歉，没有任何记录。</center></td></tr>
    </c:if>
	<c:forEach items="${topicPageModel.data}" var="item" >
	<tr target="pojo_id" rel="${item.topic_id}" height="43px">
		<td><input id="${item.topic_id}" name="ids"
			value="${item.topic_id}" type="checkbox"></td>
		<td onclick="selectBox('${item.topic_id}')">${item.title}</td>
		<td onclick="selectBox('${item.topic_id}')">${item.details}</td>
		<td><a title="删除" target="ajaxTodo"
			href="${item.topic_id}?_method=delete" class="btnDel">删除</a>
		<a title="编辑" target="dialog" href="${item.topic_id}/edit"
			class="btnEdit" width="300" height="200">编辑</a></td>
	</tr>
	</c:forEach>
	</tbody>
</table>
<div class="panelBar">
<div class="pages">
	<span>每页</span>
	<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})" param="numPerPage">
		<option value="1">调整</option>
		<option value="10">10</option>
		<option value="20">20</option>
		<option value="50">50</option>
		<option value="100">100</option>
		<option value="200">200</option>
	</select>
	<span>条，共${topicPageModel.allCount}条记录，每页${topicPageModel.numPerPage}条，当前第${topicPageModel.currentPage}/${topicPageModel.pageCount}页</span>
</div>
<div class="pagination" targetType="navTab" totalCount="${topicPageModel.allCount}"
	numPerPage="${topicPageModel.numPerPage}" pageNumShown="${topicPageModel.maxShow}"
	currentPage="${topicPageModel.currentPage}">
</div>
<form id="pagerForm" method="get" action="<c:url value="/s/topicList.do"/>">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${topicPageModel.numPerPage}" />
	<input type="hidden" name="keyword" value="1" />
</form>
</div>
</div>