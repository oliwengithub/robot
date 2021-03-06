<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加用户</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plug/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" media="all">
    <style>
        #image-upload-one {
            height: 100px;
            width: 100px;
        }
        #image-upload-one .layui-upload-drag >i{
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body" pad15>
                    <form class="layui-form" action="">
                        <input type="hidden" name="roleIds" value="${roleId}" lay-verify="userRole">
                        <div class="layui-form-item">
                            <label class="layui-form-label">用户头像</label>
                            <div class="layui-image-block">
                                <div class="layui-upload">
                                    <div class="layui-upload-drag" id="image-upload-one">
                                        <i class="layui-icon"></i>
                                        <p>点击上传头像</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="layui-form-item">
                                <label class="layui-form-label">用户名</label>
                                <div class="layui-input-block">
                                    <input type="text" name="userName" autocomplete="off" placeholder="数字、字母、下划线组合" class="layui-input" lay-verify="userName" maxlength="30">
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="layui-form-item">
                                <label class="layui-form-label">真实姓名</label>
                                <div class="layui-input-block">
                                    <input type="text" name="nickName" value="" autocomplete="off" placeholder="真实姓名" class="layui-input" lay-verify="nickName" maxlength="30">
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="layui-form-item">
                                <label class="layui-form-label">密码</label>
                                <div class="layui-input-block">
                                    <input type="password" name="password" autocomplete="off" placeholder="系统登录密码" class="layui-input" lay-verify="password" maxlength="18">
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="layui-form-item">
                                <label class="layui-form-label">确认密码</label>
                                <div class="layui-input-block">
                                    <input type="password" autocomplete="off" placeholder="再次输入密码" class="layui-input" lay-verify="conpassword" maxlength="18">
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="layui-form-item">
                                <label class="layui-form-label">手机</label>
                                <div class="layui-input-block">
                                    <input type="text" name="mobile" maxlength="12" placeholder="有效的联系电话" maxlength="20" autocomplete="off" class="layui-input" lay-verify="phone">
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="layui-form-item">
                                <label class="layui-form-label">邮箱</label>
                                <div class="layui-input-block">
                                    <input type="text" name="email" maxlength="30" placeholder="请谨慎填写，添加成功后，发送账号信息到此邮箱" autocomplete="off" class="layui-input" lay-verify="email">
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="layui-form-item">
                                <label class="layui-form-label">性别</label>
                                <div class="layui-input-block">
                                    <input type="radio" name="sex" value="1" title="男" checked>
                                    <input type="radio" name="sex" value="0" title="女">
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="layui-form-item">
                                <label class="layui-form-label">状态</label>
                                <div class="layui-input-block">
                                    <input type="radio" name="status" value="0" title="正常" checked>
                                    <input type="radio" name="status" value="1" title="禁用">
                                </div>
                            </div>
                        </div>
                        <%-- <c:if test="${empty roleId}">
                             <div class="layui-form-item">
                                 <label class="layui-form-label">用户角色</label>
                                 <div class="layui-input-block">
                                     <input type="radio" name="roleId" value="1" title="超级管理员" >
                                     <input type="radio" name="roleId" value="2" title="普通管理员" checked>
                                 </div>
                             </div>
                         </c:if>
                         <c:if test="${not empty roleId}">
                             <input type="hidden" name="roleId" value="${roleId}">
                         </c:if>--%>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">备注</label>
                            <div class="layui-input-block">
                                <textarea name="remark" placeholder="请用简单的话描述下此用户..." class="layui-textarea" maxlength="500"></textarea>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit lay-filter="save">确认提交</button>
                                <button type="reset" class="layui-btn layui-btn-primary">重新填写</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/plug/layui/layui.js"></script>
<%@ include file="../layuiconfig.jsp"%>
<script type="application/javascript">
    layui.use(['form', 'layer', 'upload'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,upload = layui.upload;

        upload.render({
            elem: '#image-upload-one'
            ,url: '${sessionScope.fileUploadUrl}'
            ,size: 2048 //限制文件大小，单位 KB
            ,acceptMime: 'image/jpg, image/png'
            ,before: function(obj){
                layer.load();
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#image-upload-one img').attr('src', result); //图片链接（base64）
                });
            },done: function(result){
                layer.closeAll('loading');
                if(result.code === 1){
                    layer.msg(result.msg,{time: 1000});
                }else{
                    $('#image-upload-one').html(' <img data-id="'+result.data+'" src="${sessionScope.filePreviewUrl}'+ result.data+'">');
                    layer.msg('上传成功',{time: 1000});
                }
            }
        });

        //自定义验证
        form.verify({
            userRole: function(value, item) {
                if (value == null || value == '' || value == 0 ) {
                    return '请选择所属部门进行添加'
                }
            },
            userName: function(value,item){
                if(value == ""){
                    return "用户名不能为空";
                }
                if(!new RegExp("^[0-9a-zA-Z_]+$").test(value)){
                    return '用户名格式错误';
                }
                var exist = true;
                $.ajax({
                    type: 'POST',
                    url: '${pageContext.request.contextPath}/system/user/check/username',
                    async:false,
                    data: {
                        "userName" : value
                    },
                    success: function (result) {
                        exist = result.data;
                    }
                });
                if(exist){
                    return "用户名已存在";
                }
            },
            nickName: function(value, item){ //value：表单的值、item：表单的DOM对象
                if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
                    return '真实姓名格式错误';
                }
                if(/(^\_)|(\__)|(\_+$)/.test(value)){
                    return '真实姓名格式错误';
                }
                if(/^\d+\d+\d$/.test(value)){
                    return '真实姓名格式错误';
                }
            },
            password: [
                /^[\S]{6,18}$/
                ,'密码必须6到18位，且不能出现空格'
            ],
            conpassword: function (value,item) {
                if(value != $("input[name=password]").val()){
                    return '两次密码输入不一致';
                }
            }
        });
        form.on('submit(save)', function(obj){
            /*if ($('#image-upload-one img').length > 0) {
                obj.field.avatar = $('#image-upload-one img').attr('data-id');
            }*/
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/system/user/insert',
                data: obj.field,
                success: function (result) {
                    if(result.code === 1){
                        layer.msg(result.msg,{time: 1000});
                    }else{
                        layer.msg('添加成功',{time: 1000},function () {
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