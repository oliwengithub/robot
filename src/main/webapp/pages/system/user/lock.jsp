<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        .avatar{
            text-align: center;
        }
        .avatar img{
            width: 60px;
            height: 60px;
            border-radius: 50%;
            margin: 15px 0 5px 0;
        }
        .avatar p{
            line-height: 35px;
            color: #009688;
        }
        .layui-form-item{
            width: 70%;
            margin: 0 auto;
        }
        .layui-form-item input{
            margin-right: 5px;
            width: calc(100% - 75px);
            display: inline-block;
            float: left;
            background: #009688;
            color: #fff;
        }

        ::-webkit-input-placeholder { /* WebKit browsers */
            color:    #fff;
        }
        :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
            color:    #fff;
        }
        ::-moz-placeholder { /* Mozilla Firefox 19+ */
            color:    #fff;
        }
        :-ms-input-placeholder { /* Internet Explorer 10+ */
            color:    #fff;
        }

        .layui-form-item button{
            width: 70px;
        }

        .tip{
            color: red;
            text-align: center;
            display: block;
            line-height: 40px;
            font-size: 13px;
        }
    </style>
</head>
<body>

    <div class="avatar">
        <c:if test="${empty sessionAdminUser.avatar}">
            <img src="${sessionScope.filePreviewUrl}avatar.jpg">
        </c:if>
        <c:if test="${not empty sessionAdminUser.avatar}">
            <img src="${sessionScope.filePreviewUrl}${sessionAdminUser.avatar}">
        </c:if>
        <p>${sessionAdminUser.nickName}</p>
    </div>
    <form class="layui-form" action="">
      <div class="layui-form-item">
          <input type="password" name="password" autocomplete="off" placeholder="请输入解锁密码" class="layui-input">
          <button class="layui-btn" lay-submit lay-filter="lock">解锁</button>
      </div>
      <span class="tip">您的登录密码即为锁屏密码！</span>
    </form>
  <script src="${pageContext.request.contextPath}/plug/layui/layui.js"></script>
  <%@ include file="../layuiconfig.jsp"%>
  <script type="application/javascript">
      layui.use(['form'], function(){
          var form = layui.form;
          $("input[name=password]").focus();
          form.on('submit(lock)', function(obj){
              if(obj.field.password == ""){
                  layer.msg("请输入解锁密码",{time: 1000})
              }else{
                  $.ajax({
                      type: 'POST',
                      url: '${pageContext.request.contextPath}/system/user/unlock',
                      data: obj.field,
                      success: function (result) {
                          if(result.code === 1){
                              layer.msg("哎呀，这个密码不对啊～",{time: 1000});
                          }else{
                              layer.msg('解锁成功,芝麻开门~',{time: 1000},function () {
                                  layui.data('lock', {
                                      key: 'status',
                                      value: false
                                  });
                                  parent.layer.closeAll();
                              });
                          }
                      }
                  });
              }

              return false;
          });

      });
  </script>
</body>
</html>