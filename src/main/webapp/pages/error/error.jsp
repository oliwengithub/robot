<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>${empty status ? '500' : status} - ${empty message ? '你访问的页面好像不小心被俺给弄丢了~' : message}</title>
    <style>
        *{font-family:"Microsoft Yahei";margin:0;font-weight:lighter;text-decoration:none;text-align:center;line-height:2.2em;}
        html,body{height:100%;background: #fff;}
        h1{font-size:120px;line-height:1em;}
        table{width:100%;height:100%;border:0;}
    </style>
</head>
<body>
<table cellspacing="0" cellpadding="0">
    <tr>
        <td>
            <table cellspacing="0" cellpadding="0">
                <tr>
                    <td>
                        <h1>${empty status ? '500' : status}</h1>
                        <h3>对不起啦！</h3>
                        <p>${empty message ? '你访问的页面好像不小心被俺给弄丢了~' : message}<br/>
                            <a href="javascript:void(0);" onclick="history.go(-1);">返回上一页 ></a>
                        </p>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
</html>