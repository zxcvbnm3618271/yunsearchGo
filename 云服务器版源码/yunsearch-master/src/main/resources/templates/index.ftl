<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>云盘搜索</title>

    <link href="public/css/site.css" rel="stylesheet">
    <link href="public/css/SearchArea.css" rel="stylesheet">
    <link href="public/css/Search.AppList.css" rel="stylesheet">
    <link href="public/css/Header.css" rel="stylesheet">
    <link href="public/css/Search.Options.css" rel="stylesheet">
    <link href="public/css/search.css" rel="stylesheet">
    <link href="public/css/SearchResultItem.css" rel="stylesheet">
    <link href="public/css/CustomDateOption.css" rel="stylesheet">
    <link href="public/css/font-awesome.min.css" rel="stylesheet">
    <link href="public/css/pikaday.css" rel="stylesheet">
    <link rel="stylesheet" href="public/css/font_1476601022_2729867.css">
</head>
<body>
<header>
<#-- <div class="sitenav">
        <a href="">首页</a>
    </div>
    <div class="user-info">
        <a href="" class="user-alias">Gin.p</a>
        <div class="ul">
            <a href=""></a>
        </div>
    </div>-->

</header>
<div class="body">
    <div class="searcher">
        <div class="logo">
        </div>
        <div class="searcharea">

            <form action="search" autocomplete="off" method="get">
                <div class="inputarea">
                    <input id="Keywords" name="keyword" type="text" value="${keyword!}">
                    <input type="submit" id="submit"  value="">
                </div>
            </form>
        </div>

        <div class="applist">
            <a class="selected " href="javascript:void(0);">全部</a>
        </div>
    </div>

    <#if yunDataList??>
    <div class="results">
        <div class="result-head">
            <div class="options">
                <div class="option" data-dropdown="type">
                    <div class="option-value" href="#">类型 : 全部<span class="caret"></span></div>
                </div>


                <div class="option" data-dropdown="date">
                    <div class="option-value" href="#">时间 : 不限<span class="caret"></span></div>
                </div>
            </div>
            <#if count??>
            <div class="statistics">
                <span>找到</span>
                ${pagingList.total}
            <span> 个结果</span>
            </div>
            </#if>
        </div>

        <#list yunDataList as data>
            <div class="result-item">
                <div style="float: left;">
<#--                    http://pan.baidu.com/share/home?uk=${data.uk?c}#category/type=0-->
                        <a  href="javascript:;" onclick="checkurl(<#if data.uuid??>'${data.uuid}'<#else>''</#if>,'${data.avatarUrl}','${select}');">
                        </a>
                </div>
                <div class="result-title" style=" padding-left: 10px;width: 80%">
<#--                    http://pan.baidu.com/share/link?uk=${data.uk?c}&third=0&shareid=${data.shareId?c}&adapt=pc&fr=ftw-->
                        <a  href="javascript:;" onclick="checkurl(<#if data.uuid??>'${data.uuid}'<#else>''</#if>,'${data.avatarUrl}','${select}');">
                            ${data.shareName}
                        </a>
                </div>
            <#--<a target="_blank" class="result-url" href="http://www.cnblogs.com/ginponson/articles/5093243.html">
                http://www.cnblogs.com/ginponson/articles/5093243.html
            </a>-->
                <div class="result-content">
                ${data.description!""}
                </div>
                <div class="result-widget">
                <span>
                    <i class="iconfont icon-shijian" aria-hidden="true"></i>
                <#-- 2016-1-1-->
                ${data.shareTime?string("yyyy-MM-dd")}
                </span>
                <#--<span><i class="iconfont icon-dianzan" aria-hidden="true"></i>0</span>
                <span><i class="iconfont icon-pinglun" aria-hidden="true"></i>0</span>
                <span><i class="iconfont icon-liulan" aria-hidden="true"></i>2</span>-->
                </div>
            </div>
        </#list>

    </div>
    </#if>



    <#if yunDataList?? && yunDataList?size != 0>
    <div id="paging_block">
    <#--计算最大页码-->
        <#if pagingList.total % pagingList.pageSize == 0>
            <#assign maxPageIndex = pagingList.total / pagingList.pageSize>
        <#else>
            <#assign maxPageIndex = (pagingList.total / pagingList.pageSize)?floor + 1>
        </#if>
        <div class="pager">
            <#--第一页，禁用“上一页”按钮-->
            <#if pagingList.total == 0 || pagingList.page == 1>
                <span class="prev-disabled">上一页</span>
            <#else>
                <#if pagingList.page == 2>
                    <a href="search?keyword=${keyword!}&pageindex=${pagingList.page-1}" class="p_1">上一页</a>
                <#else>
                    <a href="search?keyword=${keyword!}&pageindex=${pagingList.page-1}" class="p_1">上一页</a>
                </#if>
            </#if>

            <#--第一页-->
            <#if (pagingList.total > 0)>
                <a href="search?keyword=${keyword!}&pageindex=1" <#if pagingList.page == 1>class="p_1"</#if>>1</a>
            </#if>

            <#--如果不只有一页-->
            <#if (maxPageIndex > 1)>
            <#--如果当前页往前查3页不是第2页-->
                <#if ((pagingList.page - 3) > 2)>
                    <span class="text">…</span>
                </#if>

            <#--当前页的前3页和后3页-->
                <#list (pagingList.page - 3)..(pagingList.page + 3) as index>
                <#--如果位于第一页和最后一页之间-->
                    <#if (index > 1) && (index < maxPageIndex)>
                        <a href="search?keyword=${keyword!}&pageindex=${index}" <#if pagingList.page == index>class="current_page"</#if>>${index}</a>
                    </#if>
                </#list>

            <#--如果当前页往后查3页不是倒数第2页-->
                <#if (pagingList.page + 3) < (maxPageIndex - 1)>
                    <span class="text">…</span>
                </#if>

            <#--最后页-->
                <a href="search?keyword=${keyword!}&pageindex=${maxPageIndex}" <#if pagingList.page == maxPageIndex>class="current_page"</#if>>${maxPageIndex}</a>
            </#if>

            <#--最后页，禁用“下一页”按钮-->
            <#if pagingList.total == 0 || pagingList.page == maxPageIndex>
                <span class="prev-disabled">下一页</span>
            <#else>
                <a href="search?keyword=${keyword!}&pageindex=${pagingList.page+1}">下一页</a>
        </div>
            </#if>



    </#if>
    </div>

<footer>
<#-- ©2004-2017 <a href="http://www.cnblogs.com/"></a>-->
</footer>
<script src="public/js/menu.js"></script>
<script src="public/js/jquery.js" type="text/javascript"></script>
    <script type="text/javascript">
    $(function () {
        Dropdown.Initialize();

        <#if pageindex??>
            $(".p_${pageindex}").addClass("current").removeAttr('href')

            <#else >
                $(".p_1" ).addClass("current").removeAttr('href')
        </#if>


    });

    checkurl= function(uuid, url,select) {

        if (uuid==''||uuid==null){
            var query = 'url='+url+"&select="+select;
            var url = 'http://39.108.55.167:8080/yunsearch/api/parseString' + '?' + query;
            var xhr = new XMLHttpRequest();
            xhr.open("GET", url, false);
            xhr.send(null);
            var response = xhr.responseText;
            console.log(response);
            window.location.href=xhr.response;
        }else
        {
            window.location.href=url;
        }
    }

</script>
</body>
</html>