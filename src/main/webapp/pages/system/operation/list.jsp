<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>系统操作日志列表</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/plug/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" media="all">
    <style>
        .layui-form-label{
            text-align: left;
            padding-left: 0;
            display: inline-block;
            width: auto;
        }
    </style>
</head>
<body>
    <!-- 内容 内边距-->
    <div class="layui-fluid">
        <!-- 白色背景-->
        <div class="layui-card">
            <!-- 内边距-->
            <div class="layui-card-body">
                <!-- 表单-->
                <form class="layui-form table-form">
                    <div class="layui-form-item">
                        <div class="layui-col-md11 layui-col-xs11 layui-right">
                            <div class="layui-inline">
                                <div class="layui-input-inline">
                                    <input type="text" name="name" placeholder="操作项名称" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline layui-hide-xs">
                                <div class="layui-input-inline">
                                    <input type="text" class="layui-input" readonly id="time" autocomplete="off" placeholder="选择时间范围">
                                    <input type="hidden" name="timeStart">
                                    <input type="hidden" name="timeEnd">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <div class="layui-input-inline">
                                    <input type="text" name="ip" placeholder="操作IP" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-md1 layui-col-xs1 layui-right">
                            <button class="layui-btn" lay-submit="" lay-filter="search">查询</button>
                            <button class="layui-btn hidden" lay-submit="" lay-filter="searchSelf">刷新当前表格</button>
                        </div>
                    </div>
                </form>
                <!-- 表格数据-->
                <table id="layui-table" lay-filter="layui-table"></table>
            </div>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/plug/layui/layui.js"></script>
    <%@ include file="../layuiconfig.jsp"%>
    <script type="application/javascript">
      layui.use(['table','laydate'], function(){
          var table = layui.table
            ,$ = layui.jquery
            ,form = layui.form
            ,laydate = layui.laydate;


          laydate.render({
              elem: '#time'
              ,range: true
              ,type: 'datetime'
              ,done: function(value, date){
                  if(value == ""){
                      $("input[name=timeStart]").val("");
                      $("input[name=timeEnd]").val("");
                  }else{
                      $("input[name=timeStart]").val(value.split(" - ")[0]);
                      $("input[name=timeEnd]").val(value.split(" - ")[1]);
                  }
                  // value.split(" - ");
                  // layer.alert('你选择的日期是：' + value + '<br>获得的对象是' + JSON.stringify(date));
              }
          });

          table.render({
              id: 'layui-table'
              ,elem: '#layui-table'
              ,url:'${pageContext.request.contextPath}/system/operation/list/query'
              ,request: {
                  pageName: 'pageIndex' //页码的参数名称，默认：page
                  ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
              }
              ,height: 'full-100'
              ,cellMinWidth: 80
              ,toolbar: "true" //导出/打印等工具条
              ,cols: [[
                  {field:'id', title: '序号', width:60, align: 'center',type: 'numbers'}
                  ,{field:'name', title: '操作项', width:150,event: 'detail'}
                  ,{field:'url', title: '请求地址', width:240}
                  ,{field:'adminName', title: '操作人', align: 'center',width:120}
                  ,{field:'ip', title: '操作IP', width:120}
                  ,{field:'createTime', title: '操作时间', width:180,templet: "<div>{{layui.util.toDateString(d.createTime, 'yyyy-MM-dd HH:mm:ss')}}</div>"}
                  ,{field:'userAgent', title: '设备信息', minWidth:150}
              ]]
              ,page: true
          });
          //表格操作事件
          table.on('tool(layui-table)', function(obj){
              var data = obj.data;
              if(obj.event === 'detail'){
                  layer.alert(data.params);
              }
          });
          //查询提交事件
          form.on('submit(search)', function(data){
              //表格重载
              table.reload('layui-table', {
                  page: {
                      curr: 1 //重新从第 1 页开始
                  }
                  ,where: data.field
              });
              return false;
          });
          //刷新当页表格
          form.on('submit(searchSelf)', function(data){
              //表格重载
              table.reload('layui-table', {
                 where: data.field
              });
              return false;
          });

      });
    </script>
</body>
</html>