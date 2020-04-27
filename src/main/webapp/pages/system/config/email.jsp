<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>邮件服务</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/plug/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" media="all">
  <style>
    .layui-btn{
      margin-left: 20px;
    }
  </style>
</head>
<body>
  <div class="layui-fluid">
    <div class="layui-row layui-col-space15">
      <div class="layui-col-md12">
        <div class="layui-card">
          <div class="layui-card-header">邮件服务</div>
          <div class="layui-card-body">

            <div class="layui-form" wid100 lay-filter="">
              <div class="layui-form-item">
                <label class="layui-form-label">SMTP服务器</label>
                <div class="layui-input-inline">
                  <input type="text" name="host_name" value="${host_name}" class="layui-input" lay-verify="required">
                </div>
                <div class="layui-form-mid layui-word-aux">如：smtp.163.com</div>
              </div>
              <div class="layui-form-item">
                <label class="layui-form-label">SMTP端口号</label>
                <div class="layui-input-inline" style="width: 80px;">
                  <input type="text" name="port" value="${port}" class="layui-input" lay-verify="number" >
                </div>
                <div class="layui-form-mid layui-word-aux">一般为 25 或 465</div>
              </div>
              <div class="layui-form-item">
                <label class="layui-form-label">发件人邮箱</label>
                <div class="layui-input-inline">
                  <input type="text" name="user_name" value="${user_name}" autocomplete="off" class="layui-input" lay-verify="email">
                </div>
              </div>
              <div class="layui-form-item">
                <label class="layui-form-label">发件人昵称</label>
                <div class="layui-input-inline">
                  <input type="text" name="name" value="${name}" autocomplete="off" class="layui-input">
                </div>
              </div>
              <div class="layui-form-item">
                <label class="layui-form-label">邮箱登入密码</label>
                <div class="layui-input-inline">
                  <input type="password" name="password" value="${password}" autocomplete="off" class="layui-input" lay-verify="required">
                </div>
              </div>
              <div class="layui-form-item">
                <div class="layui-input-block">
                  <c:if test="${fn:contains(currMenuChildrenTags, 'edit')}">
                  <button class="layui-btn" lay-submit lay-filter="save">确认保存</button>
                  </c:if>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <script src="${pageContext.request.contextPath}/plug/layui/layui.js"></script>
  <%@ include file="../layuiconfig.jsp"%>
  <script type="application/javascript">
      layui.use(['form'], function(){
          var form = layui.form;

          form.on('submit(save)', function(obj){
              $.ajax({
                  type: 'POST',
                  url: '${pageContext.request.contextPath}/system/config/email/update',
                  data: {
                      'value': JSON.stringify(obj.field),
                  },
                  success: function (result) {
                      if(result.code === 1){
                          layer.msg(result.msg,{time: 500});
                      }else{
                          layer.msg('修改成功',{time: 500});
                      }
                  }
              });
              return false;
          });

      });
  </script>
</body>
</html>