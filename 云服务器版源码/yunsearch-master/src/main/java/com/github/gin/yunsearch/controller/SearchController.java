package com.github.gin.yunsearch.controller;

import com.github.gin.yunsearch.model.InterfaceItem;
import com.github.gin.yunsearch.model.PageResult;
import com.github.gin.yunsearch.model.YunData;
import com.github.gin.yunsearch.service.els.YunDataEls;
import com.github.gin.yunsearch.util.JsoupParse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GinPonson on 4/8/2017.
 */
@Controller
public class SearchController extends BaseController {

    @Autowired
    private YunDataEls yunDataEls;

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }
    @RequestMapping(value = "/search")
    public String search(Model model, String keyword, Integer pageindex,Integer select)
            throws IOException, ParseException {
        ArrayList<InterfaceItem> interfaceItems=JsoupParse.initInterfaceList();
        if (pageindex==null){
            pageindex=1;
        }
        List<YunData> yunDataList = yunDataEls.findByName(keyword, pageindex, 140);
        //从elasticsear查找
        Long count = yunDataEls.countByName(keyword);
        //调用第三方搜索库提高实用性
        //select 1:盘搜搜 2：大力盘 3：58网盘 4：如风搜 5：Fast搜搜 6：小白盘 7：盘发网盘
        //目前盘搜搜无法直接解析成百度网盘url，需要触发一次click事件，还未修复
        if (select==null){
            select=7;
        }
        List<YunData> ExraDataList=JsoupParse.searchExraByKeyWord(keyword,pageindex,select,interfaceItems);
        PageResult pageResult=new PageResult();
        pageResult.setTotal(count);
        for (YunData data :ExraDataList) {
            yunDataList.add(data);
        }
        count=Long.parseLong(String.valueOf(yunDataList.size()));
        pageResult.setTotal(200);
        pageResult.setPage(pageindex);
        pageResult.setRows(yunDataList);
        pageResult.setPageSize(20);
        model.addAttribute("keyword",keyword);
        model.addAttribute("yunDataList",yunDataList);
        model.addAttribute("pageindex",pageindex);
        model.addAttribute("count",count);
        model.addAttribute("pagingList",pageResult);
        model.addAttribute("JsoupParse",new JsoupParse());
        model.addAttribute("select",select);
        return "index";
    }

}
