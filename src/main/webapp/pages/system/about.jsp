<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 版本信息面板的模板 -->
<div class="layui-card-header">版本信息</div>
<div class="layui-card-body layui-text layadmin-about">
  <script type="text/html" template>
    <p>系统名称：${sessionScope.sessionSystemConf.name}</p>
    <p>授权主体：${sessionScope.sessionSystemConf.customer}</p>
    <p>技术支持：${sessionScope.sessionSystemConf.author}</p>
    <p>技术支持电话：${sessionScope.sessionSystemConf.mobile}</p>
  </script>
</div>

<div class="layui-card-header">关于版权</div>
<div class="layui-card-body layui-text layadmin-about">
  
  <blockquote class="layui-elem-quote" style="border: none;">
      <script type="text/html" template>
    《${sessionScope.sessionSystemConf.name}》产品为${sessionScope.sessionSystemConf.author}开发，授权给${sessionScope.sessionSystemConf.customer}所有，未经官网正规渠道授权擅自公开产品源文件、以及直接对产品二次出售的，我们将保留追究法律责任的权利。
      </script>
    </blockquote>
  <p>${sessionScope.sessionSystemConf.copyright}</p>
</div>