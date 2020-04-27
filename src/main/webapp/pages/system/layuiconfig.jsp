<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="${pageContext.request.contextPath}/plug/layui/lay/modules/jquery.js"></script>
<script src="${pageContext.request.contextPath}/plug/layui/lay/modules/layer.js"></script>
<script>
    var $ = layui.$
        ,layer = layui.layer;

    //设置ajax加载中
    $.ajaxSetup({
        beforeSend: function () {
            layer.load();
        },
        complete: function () {
            layer.closeAll('loading');
        }
    });

    function imgError(image){
        image.src = "${pageContext.request.contextPath}/images/default.png";
    }

    function avatarError(image){
        image.src = "${pageContext.request.contextPath}/images/avatar.jpg";
    }

</script>