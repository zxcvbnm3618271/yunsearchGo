package com.voidlhf.netdisksearcher;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


public class HttpClient {
    /**
     * 表单格式传输
     */
    private static final String FORM_CONTEXT_TYPE ="application/x-www-form-urlencoded";

    /**
     * json 默认的编码类型
     */
    private static final  String JSON_CONTENT_TYPE = "application/json";

    /**
     * 默认的编码格式
     */
    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 请求配置对象
     */
    private static RequestConfig requestConfig;

    /**
     *  get 请求，将参数包含在 url 路径中
     *  url : 路径
     *  return: json 对象
     */

    public static JSONObject doGet(String url){
        JSONObject jsonObject = null;
        try(CloseableHttpClient client = HttpClients.createDefault();){
            HttpGet request  = new HttpGet(url);
//            request.setConfig(requestConfig);
            try(CloseableHttpResponse response = client.execute(request);){
                int statusCode = response.getStatusLine().getStatusCode();
                if(statusCode == HttpStatus.SC_OK){
                    HttpEntity entity = response.getEntity();
                    String responseContent = EntityUtils.toString(entity);
                    jsonObject = JSONObject.parseObject(responseContent);
                }else{
//                    log.info("Get请求失败：{"+url+"},状态码：{"+statusCode+"}".toString());
                }
            }
        }catch (IOException e){
//            log.info("Get 请求异常：{"+url+"}，状态码：{"+e.getMessage()+"}");
            e.printStackTrace();
        }
        return  jsonObject;
    }





    /**
     *  post 请求，请求参数被封装在 JSONObject
     *  url ： 请求地址
     *  jsonParam :请求参数
     *
     */
    public static JSONObject doPost(String url,JSONObject jsonParam){
        return doPost(url,jsonParam,null);
    }

    /**
     * doPost ，以表单提交
     */
    public static JSONObject doPost(String url,String params){
        return  doPost(url,params,FORM_CONTEXT_TYPE,null);
    }

    /**
     * post 请求，请求参数被封装在 JSONObject 中，可以设置字符编码
     * url: 请求地址
     * jsonParam : 请求参数
     * charset 字符编码方法
     */
    public static JSONObject doPost(String url,JSONObject jsonParam,String charset){
        return  doPost(url,jsonParam.toJSONString(),JSON_CONTENT_TYPE,charset);
    }



    public static JSONObject doPost(String url,JSONObject jsonParam,boolean isJsonParam,String charset){
        return  doPost(url,jsonParam.toJSONString(),JSON_CONTENT_TYPE,charset);
    }


    /**
     * post 请求，参数为字符串，可以为 JSON ,可以为普通格式，可以设置字符编码
     * 如果为 json 格式， isJsonStringParam = true
     * 如果是普通格式: name  =Jack&age =10 ,则 isJsonStringParam = false
     *
     * url : 请求地址
     * stringParam 请求参数字符串
     * isJsonStringParam : 请求是否为 json 格式
     * charset 字符编码格式
     */
    public static JSONObject doPost(String url,String stringParam,boolean isJsonStringParam,String charset){
        JSONObject jsonResult = null;
        if(isJsonStringParam){
            jsonResult = doPost(url,stringParam,JSON_CONTENT_TYPE,charset);
        }else{
            jsonResult = doPost(url,stringParam,FORM_CONTEXT_TYPE,charset);
        }
        return jsonResult;
    }

    /**
     * Post 请求
     * url: 请求地址
     * requestParam 请求参数，字符串格式
     * contentType 内容编码格式
     * charset 字符编码格式
     *
     */
    public static JSONObject doPost(String url,String requestParam,String contentType,String charset){
        charset = charset==null?DEFAULT_CHARSET:charset;
        JSONObject jsonResult = null;
        try(CloseableHttpClient httpClient = HttpClients.createDefault();){
            HttpPost httpPost = new HttpPost(url);
            //构造实体请求
            StringEntity requestEntity = new StringEntity(requestParam,charset);
            requestEntity.setContentEncoding(charset);
            requestEntity.setContentType(contentType);
            httpPost.setEntity(requestEntity);
//            httpPost.setConfig(requestConfig);
            try(CloseableHttpResponse response = httpClient.execute(httpPost);){
                int statusCode = response.getStatusLine().getStatusCode();
                if(statusCode  == HttpStatus.SC_OK){
                    HttpEntity responseEntity  = response.getEntity();
                    String responseContent = EntityUtils.toString(responseEntity,charset);
                    jsonResult = JSONObject.parseObject(responseContent);
                }else{
//                    log.error("post 请求失败:{},状态码：{}",url,statusCode);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return jsonResult;
    }
}
