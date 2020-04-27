package com.oliwen.util;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: olw
 * @date: 2019/12/31 0031 11:32
 * @description:  请求发起类
 */
public class HttpConnectionPoolUtil {
	
	private static PoolingClientConnectionManager conMgr = null;

    static {
        HttpParams params = new BasicHttpParams();
        Integer CONNECTION_TIMEOUT = 2 * 1000;
        Integer SO_TIMEOUT = 2 * 1000;
        Long CONN_MANAGER_TIMEOUT = 500L;

        params.setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, CONNECTION_TIMEOUT);
        params.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, SO_TIMEOUT);
        params.setLongParameter(ClientPNames.CONN_MANAGER_TIMEOUT, CONN_MANAGER_TIMEOUT);
        params.setBooleanParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK, true);

        conMgr = new PoolingClientConnectionManager();
        conMgr.setMaxTotal(2000);

        conMgr.setDefaultMaxPerRoute(conMgr.getMaxTotal());
    }

    @SuppressWarnings({ "resource", "unchecked" })
	public static String post(String url, JSONObject params) {

        DefaultHttpClient httpClient = new DefaultHttpClient(conMgr);

        httpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(0, false));

        HttpResponse httpResponse = null;
        try {
            HttpPost httpPost = new HttpPost(url);
			if(params!=null){
				List<NameValuePair> lists = new ArrayList<NameValuePair>();
				Iterator<String> it = params.keys();
				while(it.hasNext()){
					String key = it.next(); 
					String value = params.getString(key);    
					lists.add(new BasicNameValuePair(key,value));
				}
				HttpEntity httpEntity = new UrlEncodedFormEntity(lists, "UTF-8");
				httpPost.setEntity(httpEntity);
			}
            
            
            httpResponse = httpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            if (null != entity) {
                String response = EntityUtils.toString(entity);
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                if (statusCode == HttpStatus.SC_OK) {
                   return response;
                } else {
                    return null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (httpResponse != null) {
                try {
                    EntityUtils.consume(httpResponse.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    @SuppressWarnings("resource")
	public static String get(String url, String param) {

        DefaultHttpClient httpClient = new DefaultHttpClient(conMgr);
        httpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
        HttpResponse httpResponse = null;
        try {
            HttpGet get = new HttpGet(url + URLEncoder.encode(param, "UTF-8"));
            get.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/51.0.2704.79 Chrome/51.0.2704.79 Safari/537.36");
            httpResponse = httpClient.execute(get);
            HttpEntity entity = httpResponse.getEntity();
            if (null != entity) {
                String response = EntityUtils.toString(entity);
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                if (statusCode == HttpStatus.SC_OK) {
                   return response;
                } else {
                    return null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("httpclient请求失败");
            return null;
        } finally {
            if (httpResponse != null) {
                try {
                    EntityUtils.consume(httpResponse.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
