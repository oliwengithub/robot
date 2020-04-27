package com.oliwen.util.mail;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeUtility;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
/**
 * @descript (邮件发送器)
 * @author 李海涛
 * @createTime 2016年11月16日上午9:57:09
 * @version 1.0
 */
public class MailClient {

    /**
     * 邮箱服务器
     */
    private String hostName;

    /**
     * 邮箱端口
     */
    private String smtPort;

    /**
     * 邮箱用户名
     */
    private String smtUsername;

    /**
     * 邮箱用户名
     */
    private String smtPassword;

    /**
     * 邮箱昵称
     */
    private String smtName;

    public MailClient(String hostName, String smtPort, String smtUsername, String smtPassword, String smtName) {
        this.hostName = hostName;
        this.smtPort = smtPort;
        this.smtUsername = smtUsername;
        this.smtPassword = smtPassword;
        this.smtName = smtName;
	}

	/**
     *
     * @descript (发送带附件的邮件)
     * @author 李海涛
     * @since 2016年11月16日上午10:42:31
     * @param sendTo 接收人 （多个）
     * @param title 邮件标题
     * @param htmlMessage html格式的内容
     * @param attachPath 附件地址
     * @return success为成功  error为失败
     */
    public String sendHtmlEmail(List<String> sendTo,String title,String htmlMessage,String attachPath) {
        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName(this.hostName);
            email.setSSL(true);
            email.setSslSmtpPort(this.smtPort);
            //邮件标题
            email.setSubject(title);
            email.setCharset("GB2312");
            email.setAuthentication(this.smtUsername, this.smtPassword);
            email.setFrom(this.smtName+"<"+this.smtUsername+">");
            //发送附件
            EmailAttachment  attachment = null;
            if(attachPath!=null && attachPath!=""){
                String attachName=attachPath.substring(attachPath.lastIndexOf(File.separator)+1, attachPath.length());
                attachment = new EmailAttachment();
                try {
                    attachment.setPath(attachPath);
                    attachment.setName(MimeUtility.encodeText(attachName));
                    attachment.setDisposition(EmailAttachment.ATTACHMENT);
                    attachment.setDescription(attachName);
                    email.attach(attachment);
                }catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            //循环将多个接收邮箱放入发送器
            for(String aTo : sendTo){
                email.addTo(aTo);
            }
            email.setHtmlMsg(htmlMessage);
            email.send();
            return "success";
        } catch (Exception ee) {
        	ee.printStackTrace();
            return "error";
        }
    }

    /**
     *
     * @descript (发送不带附件的邮件)
     * @author 李海涛
     * @since 2016年11月16日上午10:42:31
     * @param sendTo 接收人 （多个）
     * @param title 邮件标题
     * @param htmlMessage html格式的内容
     * @return success为成功  error为失败
     */
    public String sendHtmlEmail(List<String> sendTo,String title,String htmlMessage) {
        return sendHtmlEmail(sendTo,title,htmlMessage,null);
    }

    public String sendHtmlEmail(String sendTo,String title,String htmlMessage) {
    	List<String> list=new ArrayList<>();
    	list.add(sendTo);
        return sendHtmlEmail(list,title,htmlMessage,null);
    }


    public static void main(String[] args) {
        MailClient mailClient = new MailClient("smtp.139.com","465","qiyihbo@139.com","18070156853hai","江西新云科技有限公司");
        mailClient.sendHtmlEmail("1083573600@qq.com","您有新的邮件，请注意查收","<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=yes\" />" +
                "<meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">" +
                "<title>还款服务</title>" +
                "<style>" +
                "*{" +
                "margin: 0px;" +
                "padding: 0px;" +
                "}" +
                "body{" +
                "height:100%;" +
                "font-family:\"PingFang SC\",\"微软雅黑\",\"Helvetica Neue\",Helvetica,STHeiTi,sans-serif;" +
                "font-weight:200;" +
                "font-size:14px;" +
                "color:#333;" +
                "background:#F5F7F9;" +
                "max-width:700px;" +
                "margin: 0 auto;" +
                "}" +
                "main{" +
                "background: #FFFFFF;" +
                "margin-top: 7px;" +
                "}" +
                "ul, li{" +
                "list-style: none;" +
                "padding: 10px 0;" +
                "}" +
                "main li .content{" +
                "height: 93px;" +
                "margin: 0 15px;" +
                "background: url(https://static.kdz6.cn/m.51kabao.cn/201901repay_service/card_cash.png) no-repeat center;" +
                "background-size: cover;" +
                "border-radius: 8px;" +
                "}" +
                "main li .content.card_cash{" +
                "background-image: url(https://static.kdz6.cn/m.51kabao.cn/201901repay_service/card_cash.png);" +
                "}" +
                "main li .content.card_cash .left label{" +
                "color: #9EE5D5;" +
                "}" +
                "main li .content.card_pay{" +
                "background-image: url(https://static.kdz6.cn/m.51kabao.cn/201901repay_service/card_pay.png);" +
                "}" +
                "main li .content.card_pay .left label{" +
                "color: #ABD4FF;" +
                "}" +
                "main li .content.card_date{" +
                "background-image: url(https://static.kdz6.cn/m.51kabao.cn/201901repay_service/card_date.png);" +
                "}" +
                "main li .content.card_date .left label{" +
                "color: #F6E5C7;" +
                "}" +
                "main li .content .left{" +
                "float: left;" +
                "width: calc(100% - 98px - 23px);" +
                "padding: 22px 0 0 27px;" +
                "box-sizing: border-box;" +
                "}" +
                "main li .content .left span{" +
                "display: block;" +
                "color: #FFFFFF;" +
                "font-size: 17px;" +
                "line-height: 24px;" +
                "font-family: PingFangSC-Medium;" +
                "}" +
                "main li .content .left label{" +
                "font-size: 14px;" +
                "line-height: 20px;" +
                "margin-top: 6.5px;" +
                "}" +
                "main li .content .right{" +
                "width: 98px;" +
                "float: right;" +
                "margin-right: 23px;" +
                "}" +
                "main li .content .right button{" +
                "height: 37px;" +
                "width: 100%;" +
                "color: #2C957D;" +
                "background: #FFFFFF;" +
                "border-radius: 20px;" +
                "border: none;" +
                "outline: none;" +
                "margin: 28px auto;" +
                "font-size: 14px;" +
                "font-weight:200;" +
                "line-height: 20px;" +
                "box-shadow: 2.5px 2.5px #2C957D;" +
                "}" +
                "main dl{" +
                "margin-top: 12px;" +
                "}" +
                "main dd{" +
                "line-height: 20px;" +
                "color: #999999;" +
                "font-size: 14px;" +
                "margin: 6px 0 6px 26.5px;" +
                "}" +
                "main dd label{" +
                "width: 7px;" +
                "height: 7px;" +
                "line-height: 20px;" +
                "display: inline-block;" +
                "border-radius: 50%;" +
                "background-color: #CBCBCB;" +
                "vertical-align: top;" +
                "margin: 6.5px 10.5px;" +
                "}" +
                "" +
                "/**加载中loadding star*/" +
                ".loadding,.prompt{" +
                "position: fixed;" +
                "z-index: 1001;" +
                "width: 100%;" +
                "height: 100%;" +
                "top: 0; " +
                "display: none;" +
                "}" +
                ".loadding .mask,.prompt .mask {" +
                "position: fixed;" +
                "top: 0;" +
                "left: 0;" +
                "right: 0;" +
                "bottom: 0;" +
                "background: rgba(0, 0, 0, .3);" +
                "}" +
                ".loadding .loadding-content {" +
                "position: absolute;" +
                "top: 50%;" +
                "left: 50%;" +
                "transform: translate(-50%, -50%);" +
                "text-align: center;" +
                "background: rgba(0, 0, 0, .69);" +
                "border-radius: 10px;" +
                "padding: 2px 15px 20px;" +
                "}" +
                ".loadding .loadding-content img{" +
                "width: 70px;" +
                "}" +
                ".loadding .loadding-content p{" +
                "color: #fff;" +
                "font-size: 13px;" +
                "margin-top: -7px;" +
                "}" +
                "/**加载中loadding end*/" +
                "/**prompt弹窗 star*/" +
                ".prompt .prompt-content{" +
                "position: absolute;" +
                "top: calc(50% - 50px);" +
                "left: calc(50% - 120px);" +
                "text-align: center;" +
                "background: #fff;" +
                "border-radius: 10px;" +
                "width: 240px;" +
                "height: 100px;" +
                "overflow: hidden;" +
                "}" +
                ".prompt .prompt-content span{" +
                "display: block;" +
                "width: 100%;" +
                "padding: 20px 0px;" +
                "height: 60px;" +
                "box-sizing: border-box;" +
                "font-weight: normal;" +
                "}" +
                ".prompt .prompt-content .btns{" +
                "bottom: 0px;" +
                "left: 0;" +
                "position: absolute;" +
                "width: 100%;" +
                "height: 40px;" +
                "border-top: 1px solid #ccc;" +
                "box-sizing: border-box;" +
                "}" +
                ".prompt .prompt-content .btns button{" +
                "width: 50%;" +
                "padding: 10px 0;" +
                "float: left;" +
                "outline: none;" +
                "border: none;" +
                "height: 100%;" +
                "background: #fff;" +
                "}" +
                ".prompt .prompt-content .btns button:first-child{" +
                "border-right: 1px solid #ccc;" +
                "color: #666;" +
                "}" +
                "/**prompt弹窗 end*/" +
                "" +
                "/* toast提示 */" +
                ".toast {" +
                "position: fixed;" +
                "min-width: 144px;" +
                "max-width: 280px;" +
                "padding: 8px 12px;" +
                "line-height: 26px;" +
                "border-radius: 6px;" +
                "font-size: 14px;" +
                "text-align: center;" +
                "background: rgba(102, 102, 102, 0.9);" +
                "color: #fff;" +
                "top: 50%;" +
                "left: 50%;" +
                "transform: translate(-50%, -50%);" +
                "-webkit-transform: translate(-50%, -50%);" +
                "z-index: 9999;" +
                "}" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<main>" +
                "<ul>" +
                "<li>" +
                "<div class=\"content card_cash\" data-id=\"1\" style=\"background-image: url(https://static.kdz6.cn/m.51kabao.cn/201901repay_service/card_cash.png);\">" +
                "<div class=\"left\">" +
                "<span>信用卡取现</span>" +
                "<label>省时省心 安全无忧</label>" +
                "</div>" +
                "<div class=\"right\">" +
                "<button>立即取现</button>" +
                "</div>" +
                "</div>" +
                "<dl>" +
                "<dd><label></label>支持多家银行 实时到账 手续费低</dd>" +
                "<dd><label></label>单日单卡最高可取现50000元</dd>" +
                "</dl>" +
                "</li>" +
                "<li>" +
                "<div class=\"content card_pay\" data-id=\"2\">" +
                "<div class=\"left\">" +
                "<span>信用卡代还</span>" +
                "<label>快速缓解还款压力</label>" +
                "</div>" +
                "<div class=\"right\">" +
                "<button>立即取现</button>" +
                "</div>" +
                "</div>" +
                "<dl>" +
                "<dd><label></label>手续费低 操作简单</dd>" +
                "<dd><label></label>卡内仅需预留超低额度即可使用</dd>" +
                "</dl>" +
                "</li>" +
                "<li>" +
                "<div class=\"content card_date\" data-id=\"3\">" +
                "<div class=\"left\">" +
                "<span>信用分期</span>" +
                "<label>取现 分期一次搞定</label>" +
                "</div>" +
                "<div class=\"right\">" +
                "<button>立即取现</button>" +
                "</div>" +
                "</div>" +
                "<dl>" +
                "<dd><label></label>当日到账 最多可分12期还款</dd>" +
                "<dd><label></label>无需另外偿还银行分期手续费</dd>" +
                "</dl>" +
                "</li>" +
                "</ul>" +
                "</main>" +
                "" +
                "<div class=\"loadding\">" +
                "<div class=\"mask\"></div>" +
                "<div class=\"loadding-content\">" +
                "  <img src=\"https://static.kdz6.cn/m.51kabao.cn/201901repay_service/load.gif\">" +
                "  <p>正在加载</p>" +
                "</div>" +
                "</div>" +
                "<div class=\"prompt\">" +
                "<div class=\"mask\"></div>" +
                "<div class=\"prompt-content\">" +
                "<span>请先完成身份证</span>" +
                "<div class=\"btns\">" +
                "<button onclick=\"hidePrompt();\">取消</button>" +
                "<button class=\"goto\">前往认证</button>" +
                "</div>" +
                "</div>" +
                "</div>" +
                "<div style=\"display: none\">" +
                "<script src=\"https://hm.baidu.com/hm.js?6c4b4dfb9e3898b66abab45e0be83cd6\"></script>" +
                "<script src=\"https://s11.cnzz.com/z_stat.php?id=1261768606&amp;web_id=1261768606\"></script>" +
                "</div>" +
                "</body>" +
                "" +
                "<script src=\"http://static.kdz6.cn/lib/crypto.js\"></script>" +
                "<script src=\"http://static.kdz6.cn/lib/zepto.js\"></script>" +
                "" +
                "<script>" +
                "function stat_click(key,url){" +
                "try {" +
                "if(_hmt){" +
                "_hmt.push(['_trackEvent', '201901repay_service', key, 1]);" +
                "$.ajax({url:\"https://zmi6.cn/ge2oxu\",data:{event:'201901repay_service'}});" +
                "}" +
                "} catch(e) {" +
                "console.log(e)" +
                "}" +
                "}" +
                "</script>" +
                "<script language=\"javascript\">" +
                "" +
                "var ukey = getQueryString('ukey');" +
                "var ugroupId = getQueryString('ugroupId');" +
                "var lkey = \"\";" +
                "var mobile = \"\"; //手机号码" +
                "" +
                "// 正式参数" +
                "var baseUrl = 'http://sophora.dev.zmapi.cn/CMD/';" +
                "var appId = '10761'; // 渠道ID" +
                "var appKey = 'Q5i0dj9ZTe6ERwzj';" +
                "" +
                "var appVer = '230';" +
                "var encode = 1; // 1-加密 0-不加密" +
                "var authStep = 1;" +
                "$(document).ready(function(){" +
                "" +
                "}); " +
                "" +
                "//立即取现/分期" +
                "$(\"main li .content .right button\").click(function(){" +
                "let that = $(this).parent().parent();" +
                "alert($(that).attr(\"data-id\"));" +
                "showLoadding();" +
                "var params = {" +
                "ukey: ukey," +
                "appId: $(that).attr(\"data-id\")" +
                "};" +
                "//请求接口，判断用户是否已经认证" +
                "postRequest(baseUrl + 'C.C.U.0.2', setParams(params, 'C.C.U.0.2'), function (data) {" +
                "var data = JSON.parse(data);" +
                "hideLoadding();" +
                "if (data.code === 1) {" +
                "mobile = data.data.mobile;" +
                "authStep = data.data.state;" +
                "if(data.data.state === 1){" +
                "$(\".prompt .prompt-content span\").html(\"请先进行身份认证\");" +
                "$(\".prompt .btns .goto\").html(\"前往认证\");" +
                "$(\".prompt\").show();" +
                "}else if(data.data.state === 2){" +
                "$(\".prompt .prompt-content span\").html(\"使用信用分期需进行人脸识别\");" +
                "$(\".prompt .btns .goto\").html(\"前往验证\");" +
                "$(\".prompt\").show();" +
                "}else if(data.data.state === 3){" +
                "//加入统计" +
                "stat_click($(that).find(\".left span\").text(),'');" +
                "document.location.href = data.data.uri;" +
                "}" +
                "} else {" +
                "toast(data.msg)" +
                "}" +
                "}) " +
                "});" +
                "" +
                "//前往认证按钮" +
                "$(\".prompt .btns .goto\").click(function(){" +
                "if(authStep === 1){" +
                "authIdCard();" +
                "}else if(authStep === 2){" +
                "authFace();" +
                "}" +
                "hidePrompt();" +
                "});" +
                "" +
                "//设置参数" +
                "function setParams(data, cmd) {" +
                "data.g_lkey = lkey;" +
                "data.g_encode = encode;" +
                "data.g_time = new Date().getTime();" +
                "data.ugroupId = 1; // 第三方应用标识" +
                "var p = JSON.stringify(data)" +
                "var token = cmd + '#' + p + '#' + appKey" +
                "var k = appId + '.' + appVer + '.' + CryptoJS.SHA1(token)" +
                "var form = {" +
                "p: encode === 1 ? xorEncode(p, appKey) : p," +
                "k: k," +
                "cmd: cmd" +
                "}" +
                "return form" +
                "}" +
                "" +
                "// 接口请求" +
                "function postRequest(url, params, callback) {" +
                "$.ajax({" +
                "url: url," +
                "type: 'POST'," +
                "data: params," +
                "dataType: 'text'," +
                "success: function (data) {" +
                "encode === 1 ? callback(xorDecode(data, appKey)) : callback(data)" +
                "}," +
                "error: function (data) {" +
                "toast(data)" +
                "}" +
                "})" +
                "}" +
                "" +
                "function showPrompt(){" +
                "$(\".loadding\").hide();" +
                "$(\".prompt\").show();" +
                "$('.toast-box').remove();" +
                "}" +
                "function hidePrompt(){" +
                "$(\".prompt\").hide();" +
                "}" +
                "" +
                "function showLoadding(){" +
                "$(\".prompt\").hide();" +
                "$(\".loadding\").show();" +
                "$('.toast-box').remove();" +
                "}" +
                "function hideLoadding(){" +
                "$(\".loadding\").hide();" +
                "}" +
                "" +
                "//跳转原生进行身份认证" +
                "function authIdCard() {" +
                "window.location.href = \"kabao://panda/id?mobile=\" + mobile + \"&ugroupId=\" + ugroupId +\"&callback=authCallBack\";" +
                "}" +
                "//调整原生进行人脸识别" +
                "function authFace() {" +
                "window.location.href = \"kabao://panda/face?mobile=\" + mobile + \"&ugroupId=\" + ugroupId +\"&callback=authCallBack\";" +
                "}" +
                "" +
                "//认证成功后原生回调方法" +
                "function authCallBack() {" +
                "window.location.reload();" +
                "}" +
                "" +
                "// 提示" +
                "function toast(tips) {" +
                "$('.toast-box').remove();" +
                "var html = '<div class=\"toast-box\">' +" +
                "'<div class=\"toast\">' +" +
                "'<p>' + tips + '</p>' +" +
                "'</div>' +" +
                "'</div>';" +
                "" +
                "$('body').append(html);" +
                "setTimeout(function () {" +
                "$('.toast-box').remove();" +
                "}, 1000);" +
                "return;" +
                "}" +
                "" +
                "//获取查询参数" +
                "function getQueryString(name) {" +
                "var reg = new RegExp(\"(^|&)\" + name + \"=([^&]*)(&|$)\", \"i\");" +
                "var r = window.location.search.substr(1).match(reg);" +
                "if (r != null) return unescape(r[2]);" +
                "return null;" +
                "}" +
                "// 加密" +
                "function xorEncode(str, key) {" +
                "str = CryptoJS.enc.Base64.stringify(CryptoJS.enc.Utf8.parse(str))" +
                "var data = stringToByte(str)" +
                "var keyData = stringToByte(key + '')" +
                "var keyIndex = 0" +
                "for (var x = 0; x < data.length; x++) {" +
                "data[x] = (data[x] ^ keyData[keyIndex])" +
                "if (++keyIndex === keyData.length) {" +
                "keyIndex = 0" +
                "}" +
                "}" +
                "str = byteToString(data)" +
                "str = CryptoJS.enc.Base64.stringify(CryptoJS.enc.Utf8.parse(str))" +
                "return str" +
                "}" +
                "// 解密" +
                "function xorDecode(str, key) {" +
                "str = CryptoJS.enc.Base64.parse(str).toString(CryptoJS.enc.Utf8)" +
                "var data = stringToByte(str)" +
                "var keyData = stringToByte(key + '')" +
                "" +
                "var keyIndex = 0" +
                "for (var x = 0; x < data.length; x++) {" +
                "data[x] = (data[x] ^ keyData[keyIndex])" +
                "keyIndex += 1" +
                "if (keyIndex === keyData.length) {" +
                "keyIndex = 0" +
                "}" +
                "}" +
                "str = byteToString(data)" +
                "str = CryptoJS.enc.Base64.parse(str).toString(CryptoJS.enc.Utf8)" +
                "return str" +
                "}" +
                "" +
                "function stringToByte(str) {" +
                "var bytes = []" +
                "var len, c" +
                "len = str.length" +
                "for (var i = 0; i < len; i++) {" +
                "c = str.charCodeAt(i)" +
                "if (c >= 0x010000 && c <= 0x10FFFF) {" +
                "bytes.push(((c >> 18) & 0x07) | 0xF0)" +
                "bytes.push(((c >> 12) & 0x3F) | 0x80)" +
                "bytes.push(((c >> 6) & 0x3F) | 0x80)" +
                "bytes.push((c & 0x3F) | 0x80)" +
                "} else if (c >= 0x000800 && c <= 0x00FFFF) {" +
                "bytes.push(((c >> 12) & 0x0F) | 0xE0)" +
                "bytes.push(((c >> 6) & 0x3F) | 0x80)" +
                "bytes.push((c & 0x3F) | 0x80)" +
                "} else if (c >= 0x000080 && c <= 0x0007FF) {" +
                "bytes.push(((c >> 6) & 0x1F) | 0xC0)" +
                "bytes.push((c & 0x3F) | 0x80)" +
                "} else {" +
                "bytes.push(c & 0xFF)" +
                "}" +
                "}" +
                "return bytes" +
                "}" +
                "" +
                "function byteToString(arr) {" +
                "if (typeof arr === 'string') {" +
                "return arr" +
                "}" +
                "var str = ''" +
                "var _arr = arr" +
                "for (var i = 0; i < _arr.length; i++) {" +
                "var one = _arr[i].toString(2)" +
                "var v = one.match(/^1+?(?=0)/)" +
                "if (v && one.length === 8) {" +
                "var bytesLength = v[0].length" +
                "var store = _arr[i].toString(2).slice(7 - bytesLength)" +
                "for (var st = 1; st < bytesLength; st++) {" +
                "store += _arr[st + i].toString(2).slice(2)" +
                "}" +
                "str += String.fromCharCode(parseInt(store, 2))" +
                "i += bytesLength - 1" +
                "} else {" +
                "str += String.fromCharCode(_arr[i])" +
                "}" +
                "}" +
                "return str" +
                "}" +
                "</script>" +
                "</html>");
    }
}
