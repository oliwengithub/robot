<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>用户角色分配</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/plug/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" media="all">
    <style type="text/css">
        html,body,.layui-fluid{
            height: 100%;
            box-sizing: border-box;
        }
        .layui-button-bottom{
            position: absolute;
            bottom: 10px;
            width: 100%;
            left: 0;
        }

        .layui-form .layui-form-item{
            width: 90%;
            margin: 0 auto;
        }

        .layui-form-item .layui-form-checkbox{
            margin-bottom: 6px;
        }

        .layui-form-item .layui-form-checkbox {
            width: 30%;
        }

    </style>
</head>
<body>
  <div class="layui-fluid">
        <blockquote class="layui-elem-quote">【${userName}】- 角色分配</blockquote>
        <form class="layui-form" lay-filter="" action="">
            <div class="layui-form-item">
                <input type="hidden" name="userId" value="${userId}">
                <c:if test="${not empty roleArray}">
                    <c:forEach items="${roleArray}" var="role">
                        <input type="checkbox" name="roles" value="${role.roleId}" lay-skin="primary" title="${role.roleName}" <c:if test="${role.checked == true}">checked</c:if>>
                    </c:forEach>
                </c:if>
            </div>
            <div class="layui-button-bottom">
                <hr class="layui-bg-gray">
                <div style="text-align: center">
                    <button class="layui-btn" lay-submit lay-filter="save">确认修改</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重新填写</button>
                </div>
            </div>
        </form>

  </div>
  <script src="${pageContext.request.contextPath}/plug/layui/layui.js"></script>
  <%@ include file="../layuiconfig.jsp"%>
  <script type="application/javascript">
      layui.use(['form'], function(){
          var form = layui.form;

          form.on('submit(save)', function(obj){
              var arr = new Array();
              $("input:checkbox[name='roles']:checked").each(function(i){
                  arr[i] = $(this).val();
              });
              obj.field.roleIds = arr.join(",");
              $.ajax({
                  type: 'POST',
                  url: '${pageContext.request.contextPath}/system/auth/insert',
                  data: obj.field,
                  success: function (result) {
                      if(result.code === 1){
                          layer.msg(result.msg,{time: 1000});
                      }else{
                          layer.msg('修改成功',{time: 1000},function () {
                              parent.layer.closeAll();
                          });
                      }
                  }
              });
              return false;
          });
      });

  </script>
</body>
</html>