package com.oliwen.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * @author: liht
 * @date: 2018/12/5 9:54 AM
 * @description: 返回JSON结果
 */
public class ResultBody {

    /**状态：0、成功，1、失败*/
    private int code;
    private String msg;
    private Object data;
    private int count;
    private long time;

    private Map<String,Object> content;

    private long star_time;
    private long end_time;

    public ResultBody() {
        this.star_time = System.currentTimeMillis();
        this.code = 0;
    }
    public void put(String key,Object value){
        if(this.content == null){
            this.content = new HashMap<>();
        }
        this.content.put(key,value);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public ResultBody error(String msg) {
        this.code = 1;
        this.msg = msg;
        return this;
    }

    public boolean isSuccess() {
        return this.code == 0;
    }

    public ResultBody error(Exception e) {
        this.code = 1;
        this.msg = e.getMessage();
        return this;
    }

    public String toJsonString(Object data) {
        this.data = data;
        return this.toJsonString();
    }

    public String toJsonString() {
        this.end_time = System.currentTimeMillis();
        this.setTime(this.end_time - star_time);
        if(this.content != null){
            this.setData(this.content);
        }
        String str =  JSONObject.toJSONString(this, SerializerFeature.WriteMapNullValue, SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteDateUseDateFormat);
        return str == null ? null : str.replaceAll("(:)([0-9]{12,})(,|})","$1\"$2\"$3");
    }
}