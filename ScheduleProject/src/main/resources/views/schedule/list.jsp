<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Schedule</title>
<script type="text/javascript" src="${ctx}/static/js/jquery-3.3.1.min.js"></script>
<style type="text/css">
table{border-collapse: collapse;}
td{border:1px solid #ccc; padding:5px;}
a{text-decoration:none; display:inline-block;padding:5px; border:1px solid #989;}
</style>
<script type="text/javascript">
   function doCommand(name,command) {
	   var data = {command:command+" "+name};
	   $.post("${ctx}/schedule/doCommand",data,function(json){
		   if(json) {
			   $("#"+json.name+" td").each(function(){
				   var name = $(this).attr("data-name");
				   if(name!=null) {
					   $(this).html(json[name]);
				   }
			   });
		   }
	   });
   }
</script>
</head>
<body>
    <table style="width:100%;">
    	<thead>
    		<tr>
    			<th>计划名称</th>
                <th>调度间隔</th>
                <th>调度偏移</th>
                <th>最后一次开始时间</th>
                <th>最后一次结束时间</th>
                <th>当前状态</th>
                <th>线程设置</th>
                <th>操作</th>
    		</tr>
    	</thead>
    	<c:forEach items="${schedules}" var="schedule">
    	   <tr id="${schedule.name}">
    	      <td><c:out value="${schedule.desc}"></c:out> </td>
    	      <td style="width:10%">${schedule.period}</td>
    	      <td style="width:10%">${schedule.offset}</td>
    	      <td data-name="lastStartTime" style="width:10%">${schedule.lastStartTime}</td>
    	      <td data-name="lastEndTime" style="width:10%">${schedule.lastEndTime}</td>
    	      <td data-name="status" style="width:5%">${schedule.status}</td>
    	      <td data-name="enableFlag" style="width:5%">${schedule.enableFlag}</td>
    	      <td style="width:15%;">
    	      	 <a href="javascript:;" onclick='doCommand("<c:out value="${schedule.name}"></c:out>","wakeup")'>启动</a>
    	      	 <a href="javascript:;" onclick='doCommand("<c:out value="${schedule.name}"></c:out>","resume")'>恢复</a>
    	      	 <a href="javascript:;" onclick='doCommand("<c:out value="${schedule.name}"></c:out>","suspend")'>挂起</a>
    	      </td>
    	   </tr>
    	</c:forEach>
    </table>
</body>
</html>