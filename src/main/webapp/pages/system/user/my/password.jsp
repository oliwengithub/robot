<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>设置我的密码</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/plug/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" media="all">
</head>
<body>
  <div class="layui-fluid">
    <div class="layui-row layui-col-space15">
      <div class="layui-col-md12">
        <div class="layui-card">
          <div class="layui-card-header">修改密码</div>
          <div class="layui-card-body" pad15>
            
            <div class="layui-form" lay-filter="">
              <div class="layui-form-item">
                <label class="layui-form-label">当前密码</label>
                <div class="layui-input-inline">
                  <input type="password" name="oldPassword" lay-verType="tips" class="layui-input" lay-verify="required">
                </div>
              </div>
              <div class="layui-form-item">
                <label class="layui-form-label">新密码</label>
                <div class="layui-input-inline">
                  <input type="password" name="newPassword" lay-verType="tips" autocomplete="off" class="layui-input" lay-verify="pass">
                </div>
                <div class="layui-form-mid layui-word-aux">6到16个字符</div>
              </div>
              <div class="layui-form-item">
                <label class="layui-form-label">确认新密码</label>
                <div class="layui-input-inline">
                  <input type="password" name="repassword" lay-verType="tips" autocomplete="off" class="layui-input" lay-verify="repass">
                </div>
              </div>
              <div class="layui-form-item">
                <div class="layui-input-block">
                  <button class="layui-btn" lay-submit lay-filter="save">确认修改</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <script src="${pageContext.request.contextPath}/plug/layui/layui.js"></script>
  <%@ include file="../../layuiconfig.jsp"%>
  <script type="application/javascript">
      layui.use(['form'], function(){
          var form = layui.form;
          //自定义验证
          form.verify({
              //既支持上述函数式的方式，也支持下述数组的形式
              //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
              pass: [
                  /^[\S]{6,12}$/
                  ,'密码必须6到12位，且不能出现空格'
              ]

              //确认密码
              ,repass: function(value){
                  if(value !== $('input[name=newPassword]').val()){
                      return '两次密码输入不一致';
                  }
              }
          });

          form.on('submit(save)', function(obj){
              $.ajax({
                  type: 'POST',
                  url: '${pageContext.request.contextPath}/system/user/my/password/update',
                  data: obj.field,
                  success: function (result) {
                      if(result.code === 1){
                          layer.msg(result.msg);
                      }else{
                          layer.msg('修改成功');
                      }
                  }
              });
              return false;
          });

      });
  </script>
</body>
</html>