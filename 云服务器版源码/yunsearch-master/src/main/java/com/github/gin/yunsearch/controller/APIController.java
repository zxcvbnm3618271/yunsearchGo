package com.github.gin.yunsearch.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.gin.yunsearch.model.*;
import com.github.gin.yunsearch.service.els.YunDataEls;
import com.github.gin.yunsearch.service.jpa.YunDataService;
import com.github.gin.yunsearch.util.JsoupParse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/api")
public class APIController {
    @Autowired
    private YunDataService yunDataService;

    @Autowired
    private YunDataEls yunDataEls;

@RequestMapping(value = "/selector",method = RequestMethod.GET)
@ResponseBody
public Selectors fetchSelector(HttpServletRequest request, HttpServletResponse response){

String client_version=request.getParameter("client_version");
Selectors selectors=new Selectors();
return selectors;
}

@RequestMapping(value = "/items",method = RequestMethod.GET)
@ResponseBody
public Item fetchAccessCode(HttpServletRequest request, HttpServletResponse response){
    String uuid=request.getParameter("uuid");
    String client_version=request.getParameter("client_version");
    String type=uuid.substring(0,uuid.indexOf('-'));
    String pid=uuid.substring(uuid.indexOf('-')+1);
    //先测试一下
    //数据从elasticsearch找，不要直接查数据库
    Item item=new Item();
    item.setUuid(uuid);
    item.setType(type);
    item.setPid(pid);
    String access_code=yunDataEls.findAccessByUuid(uuid);
    if (!"null".equals(access_code)){
        item.setAccess_code(access_code);
    }
    return item;
}

@RequestMapping(value = "/items",method = RequestMethod.POST)
@ResponseBody
public Item sendItem(HttpServletRequest request, HttpServletResponse response) throws ParseException {
    Item item=new Item();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    item.setUuid(request.getParameter("uuid"));
    String uuid=request.getParameter("uuid");
    String type=uuid.substring(0,uuid.indexOf('-'));
    String pid=uuid.substring(uuid.indexOf('-')+1);
    item.setUuid(uuid);
    item.setPid(pid);
    item.setType(type);
    item.setCreated_at(df.parse(df.format(new Date())) );
    item.setUpdated_at(df.parse(df.format(new Date())));
    String client_version= request.getParameter("client_version");
    item.setInvalid_count(request.getParameter("invalid_count") ==null ?0:Integer.parseInt(request.getParameter("invalid_count")));
    String method=request.getParameter("method");
    Item item1=new Item();
    switch (method){
        case "sendAccessCode":
            item.setAccess_code(request.getParameter("access_code"));
            item1=yunDataService.updateAccessCodeByUuid(item);
            break;
        case "sendState":
            item.setState(request.getParameter("state"));
            item1=yunDataService.updateStateByUuid(item);
            break;
        case "sendReferrer":
            referrer referrer=new referrer();
            referrer.setTitle(request.getParameter("referrer[title]"));
            referrer.setUrl(request.getParameter("referrer[url]"));
            List<referrer> referrers=new ArrayList<>();
            //先获取原来的List<referrer>
            if (yunDataService.CheckDataByUuid(uuid,"referrers")){
                referrers=yunDataService.findReferrersByUuid(uuid);

            }
            //如果不存在这个referrer就写入
            if (!referrers.contains(referrer)){
                referrers.add(referrer);
            }
            item1=yunDataService.updateReferrerByUuid(item,referrers);
            break;

            default:
                break;
    }
    return item1;

}
@RequestMapping(value = "/resources",method = RequestMethod.POST)
@ResponseBody
public Item sendResource(HttpServletRequest request, HttpServletResponse response) throws ParseException {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    String uuid=request.getParameter("uuid");
    String type=uuid.substring(0,uuid.indexOf('-'));
    String pid=uuid.substring(uuid.indexOf('-')+1);
    String client_version=request.getParameter("client_version");
    Item item=new Item();
    item.setUuid(uuid);
    item.setType(type);
    item.setPid(pid);
    item.setCreated_at(df.parse(df.format(new Date())) );
    item.setUpdated_at(df.parse(df.format(new Date())));
    item.setInvalid_count(request.getParameter("invalid_count") ==null ?0:Integer.parseInt(request.getParameter("invalid_count")));
    Resource resource=new Resource();
    resource.setMode(request.getParameter("resource[mode]"));
    resource.setShare_time(df.parse(request.getParameter("resource[share_time]")));
    resource.setName(request.getParameter("resource[name]"));
    resource.setSize(request.getParameter("resource[size]"));
    resource.setUser_id(request.getParameter("resource[user_id]"));
    resource.setUser_name(request.getParameter("resource[user_name]"));
    //如果uuid不存在就插入，如果存在就更新
    //根据uuid返回查到的item
    Item item1=yunDataService.updateResourceByUuid(resource,item);
    return item1;
}

@RequestMapping(value = "/resources/subnodes",method = RequestMethod.POST)
@ResponseBody
public Item sendSubnodes(HttpServletRequest request, HttpServletResponse response) throws ParseException {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    String uuid=request.getParameter("uuid");
    String type=uuid.substring(0,uuid.indexOf('-'));
    String pid=uuid.substring(uuid.indexOf('-')+1);
    String client_version=request.getParameter("client_version");
    Item item=new Item();
    item.setUuid(uuid);
    item.setPid(pid);
    item.setType(type);
    item.setCreated_at(df.parse(df.format(new Date())) );
    item.setUpdated_at(df.parse(df.format(new Date())));
    item.setInvalid_count(request.getParameter("invalid_count") ==null ?0:Integer.parseInt(request.getParameter("invalid_count")));

    List<subnode> subnodes=new ArrayList<subnode>(JSONArray.parseArray(request.getParameter("subnodes"),subnode.class));
    if (yunDataService.CheckDataByUuid(uuid,"subnodes")){
        List<subnode> oldsubnodes=yunDataService.findSubnodesByUuid(uuid);
        for (subnode s:oldsubnodes
        ) {
            if (!subnodes.contains(s)){
                subnodes.add(s);
            }
        }
    }
    //如果uuid不存在就插入，存在就更新
    //根据uuid返回item
    Item item1=yunDataService.updateSubnodesByUuid(subnodes,item);
    return item1;
}
@RequestMapping(value = "/check-update",method = RequestMethod.POST)
@ResponseBody
public checkVersion checkUpdate(HttpServletRequest request, HttpServletResponse response){
    checkVersion checkVersion=new checkVersion();
    String client_version=request.getParameter("client_version");
    checkVersion.set_new(client_version==checkVersion.getLatest_version()?true:false);
return checkVersion;
}
@RequestMapping(value = "/parseString",method = RequestMethod.GET)
@ResponseBody
public String parseString(String url,int select) throws IOException {
    String parseurl= JsoupParse.ParseUrl(url,select);
    return parseurl;
}
@RequestMapping(value = "/Sendnode",method = RequestMethod.POST)
@ResponseBody
public YunData Sendnode(HttpServletRequest request, HttpServletResponse response) throws ParseException {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    YunData data=new YunData();
    data.setShareName(request.getParameter("sharename"));
    data.setSize(request.getParameter("size"));
    data.setUpdateTime(df.parse(request.getParameter("sharetime")));
    data.setAvatarUrl(request.getParameter("avatarurl"));
    data.setUserName(request.getParameter("username"));
    String pid=request.getParameter("avatarurl").split("/s/1")[1];
    data.setPid(pid);
    data.setUuid("BDY-"+pid);
    data.setAccessCode(request.getParameter("accesscode"));
    String state=request.getParameter("state");
    if (state!=null){
        if ("true".equals(state)||"1".equals(state)){
            data.setState("VALID");
        }else {
            data.setState("INVALID");
        }
    }
    yunDataService.saveIgnore(data);
    return data;

}

}
