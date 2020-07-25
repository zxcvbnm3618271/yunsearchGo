package com.github.gin.yunsearch.util;

import com.github.gin.yunsearch.model.InterfaceItem;
import com.github.gin.yunsearch.model.YunData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class JsoupParse implements Serializable {


    public static ArrayList<InterfaceItem> initInterfaceList() {
        ArrayList<InterfaceItem>  interfaceList = new ArrayList<>();
        interfaceList.add(new InterfaceItem("盘搜搜", "http://m2.pansoso.com/zh/%key%_%index%", "site_pss"));
        interfaceList.add(new InterfaceItem("大力盘", "https://www.dalipan.com/search?keyword=%key%&page=%index%", "site_dlp"));
        interfaceList.add(new InterfaceItem("58网盘", "http://m.58wangpan.com/so?order=feed_time&feed_time=0&keyword=%key%&page=%index%", "site_58wp"));
        interfaceList.add(new InterfaceItem("如风搜", "http://m.rufengso.net/s/comb/n-%key%&s-feedtime1/%index%", "site_rfs"));
        interfaceList.add(new InterfaceItem("Fast搜搜", "https://www.fastsoso.cn/search?page=%index%&k=%key%&s=1&t=-1", "site_fastss"));
        interfaceList.add(new InterfaceItem("小白盘", "https://www.xiaobaipan.com/list-%key%-p%index%.html", "site_xbp"));
        interfaceList.add(new InterfaceItem("盘发网盘", "https://wx.panfa.net/s/%key%_%index%", "site_pfwp"));
   return interfaceList;
    }
    //将第三方搜索引擎的url转为百度网盘的url
public static String ParseUrl(String baseurl,int select) throws IOException {
        String parseurl="";
    switch (select){
        //盘搜搜
        //盘搜搜这里需要模拟一次点击事件才可以获取到真正的url，先不改了
        case 1:
            Connection conn1 = Jsoup.connect(baseurl);
            conn1.userAgent("Mozilla");
            Document document1 = conn1.get();
            String tempUrl2 = document1.select("a.down_button_link").attr("href");
            if(tempUrl2.length()==0) {
                break;
            }
            Document document3 = Jsoup.connect(tempUrl2).validateTLSCertificates(false).post();
            parseurl = document3.baseUri();
            break;
        //大力盘
        case 2:
            Document document = Jsoup.connect(baseurl).get();
            String bdurls[] = {
                    "yun.baidu.com",
                    "pan.baidu.com"
            };
            String tempUrl = "";
            for(int a0 =0;a0<bdurls.length;a0++) {
                if(document.toString().indexOf(bdurls[a0])>0) {
                    tempUrl = document.toString().substring(document.toString().indexOf(bdurls[a0]));
                    parseurl = unicodeDecode("https://"+tempUrl.substring(0,tempUrl.indexOf("\"")));
                }
            }
            break;
        //58网盘
        case 3:
           try {
               document1 = Jsoup.connect(baseurl).validateTLSCertificates(false).get();
               Elements elements1;
               if(document1.select("a#html5_href_file").size()!=0) {
                   elements1 = document1.select("a#html5_href_file");
               } else {
                   elements1 = document1.select("a#html5_href_dir");
               }
               String tempUrl1 = elements1.attr("href");
               tempUrl2 = "https://m.58wangpan.com"+tempUrl1;
               Document document2 = Jsoup.connect(tempUrl2).validateTLSCertificates(false).get();

               if(document2.select("#tip_msg p").last().text().startsWith("http")) {
                   parseurl = document2.select("#tip_msg p").last().text();
               } else {
                   parseurl = document2.select("#tip_msg a").attr("href");
               }
           }catch (Exception e){

           }
            break;
        //如风搜
        case 4:
            document1 = Jsoup.connect(baseurl).validateTLSCertificates(false).get();
            final String tempUrl1 = document1.select("a#redirect-link").attr("href");
            Document document2 = Jsoup.connect(tempUrl1).validateTLSCertificates(false).get();
            String baseUri = document2.select("div.main a").attr("href");
            if(baseUri.length()!=0) {
                parseurl = baseUri;
            } else {
                parseurl = tempUrl1;
            }
            break;
        //Fast搜搜
        case 5:
            document1 = Jsoup.connect(baseurl).validateTLSCertificates(false).get();
            parseurl = document1.getElementsByAttributeValue("class","btn btn-success").
                    get(0).attr("onclick").
                    replace("window.open('/redirect?url=","").
                    replace("','_blank')","");
            break;
        //小白盘
        case 6:
             document1 = Jsoup.connect(baseurl).validateTLSCertificates(false).get();
            tempUrl = "https://www.xiaobaipan.com"+document1.select("div.modal-footer a").
                    attr("href");
            parseurl = Jsoup.connect(tempUrl).validateTLSCertificates(false).get().baseUri();

            break;
        //盘发网盘
        case 7:
            document1 = Jsoup.connect(baseurl).validateTLSCertificates(false).get();
            baseUri = document1.select("a#pan_button").attr("href");
            conn1 = Jsoup.connect(baseUri);
            Connection.Response res = conn1.execute();
            String url2 = res.header("Refresh");
            parseurl= url2.substring(url2.indexOf("http"));
            break;
            default:
                break;
    }
    return parseurl;
}

    //index默认是1
    public static List<YunData> searchExraByKeyWord(String key, int index, int select, ArrayList<InterfaceItem> interfaceList) throws IOException, ParseException {

        List<YunData> yunDataList=new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        switch (select) {
            //盘搜搜
            case 1:
                String URL = interfaceList.get(select-1).getRule().replace("%key%", key).replace("%index%", String.valueOf(index));
                Connection conn1 = Jsoup.connect(URL);
                conn1.userAgent("MicroMessenger");
                Document document = conn1.get();
                Elements elements = document.select("div#con>a");
                if (elements.size() != 0) {
                    index++;
                    for (Element e : elements) {
                        String str = e.select(".des").text().toString();
                        //获取url连接
                        String test="http://m2.pansoso.com" + e.attr("href");
//                        String url = ParseUrl(test, select);
                        YunData data = new YunData(test,
                                e.select(".pss h2").text(),
                                df.format(new Date()),
                                str);
                        yunDataList.add(data);
                    }
                }
                    break;
                    //大力盘
                    case 2:
                        String searchUrl = interfaceList.get(select-1).getRule().replace("%key%", key).replace("%index%", String.valueOf(index));
                        document = Jsoup.connect(searchUrl).validateTLSCertificates(false).get();
                         elements = document.select("div.resource-item");
                        if(elements.size()!=0) {
                            index++;
                            for(Element e:elements) {
                                //获取url连接
                              //  String url = ParseUrl("https://www.dalipan.com"+e.select("a.valid").attr("href"), select);
                                //avatarurl,sharename,sharetime,description
                                String test="https://www.dalipan.com"+e.select("a.valid").attr("href");
                                YunData data=new YunData(test,
                                        e.select("a.valid").text(),
                                        e.select("p.time").text(),
                                        e.select("p.time").text());
                              yunDataList.add(data);
                            }
                        }
                        break;
                    //58网盘
                    case 3:
                         searchUrl = interfaceList.get(select-1).getRule().replace("%key%", key).replace("%index%", String.valueOf(index));
                        document = Jsoup.connect(searchUrl).validateTLSCertificates(false).get();
                       elements = document.select("li.tl_shadow");
                        if(elements.size()!=0) {
                            index++;
                            for(Element e:elements) {
                                //获取url连接
                                //String url = ParseUrl("http://m.58wangpan.com"+e.select("a").attr("href"), select);
                                String test="http://m.58wangpan.com"+e.select("a").attr("href");
                                YunData data=new YunData(test,
                                        e.select("h3").text(),
                                        df.format(new Date()),
                                        e.select(".ti_author_time").text()
                                        );
                                    yunDataList.add(data);
                            }
                        }
                        break;
                    //如风搜
                    case 4:
                        searchUrl = interfaceList.get(select-1).getRule().replace("%key%", key).replace("%index%", String.valueOf(index));
                        document = Jsoup.connect(searchUrl).validateTLSCertificates(false).get();
                        elements = document.select("ul.list>li div.content");
                        if(elements.size()!=0) {
                            index++;
                            for(Element e:elements) {
                                //获取url连接
                               // String url = ParseUrl("http://m.rufengso.net"+e.select(">a").attr("href"), select);
                                String test="http://m.rufengso.net"+e.select(">a").attr("href");
                                YunData data=new YunData(test,
                                        e.select("h3").text(),
                                        df.format(new Date()),
                                        e.select(".list-content").text());
                                yunDataList.add(data);
                            }
                        }
                        break;
                    //Fast搜搜
                    case 5:
                        searchUrl = interfaceList.get(select-1).getRule().replace("%key%", key).replace("%index%", String.valueOf(index));
                        document = Jsoup.connect(searchUrl).validateTLSCertificates(false).get();
                        elements  = document.getElementsByAttributeValue("style","clear: both").prev("div");
                        if(elements.size()!=0) {
                            index++;
                            for(Element e:elements) {
                                //获取url连接
                               // String url = ParseUrl( "https://www.fastsoso.cn"+e.select("a").attr("href"), select);
                                String test="https://www.fastsoso.cn"+e.select("a").attr("href");
                                YunData data=new YunData(test,
                                        e.select("strong").text(),
                                        df.format(new Date()),
                                        e.getElementsByAttributeValueContaining("style","color: #105207;").text()
                                                .substring(0,e.getElementsByAttributeValueContaining("style","color: #105207;").text().indexOf("|"))
                                                .replace("时间： ","").
                                                replace("",""));
                                yunDataList.add(data);
                            }
                        }
                        break;
                    //小白盘
                    case 6:
                        searchUrl = interfaceList.get(select-1).getRule().replace("%key%", key).replace("%index%", String.valueOf(index));
                        document = Jsoup.connect(searchUrl).validateTLSCertificates(false).get();
                        elements  = document.select("div.jobs-item");
                        if(elements.size()!=0) {
                            index++;
                            for(Element e:elements) {
                                //String url = ParseUrl(  "https://www.xiaobaipan.com"+e.select("h4.job-title a").attr("href"), select);
                                String test="https://www.xiaobaipan.com"+e.select("h4.job-title a").attr("href");
                                YunData data=new YunData(test,
                                        e.select("h4.job-title").text(),
                                        df.format(new Date()),
                                        e.select("span.info-row").text()
                                );
                                yunDataList.add(data);
                            }
                        }
                        break;
                    //盘发网盘
                    case 7:
                        searchUrl = interfaceList.get(select-1).getRule().replace("%key%", key).replace("%index%", String.valueOf(index));
                        document = Jsoup.connect(searchUrl).validateTLSCertificates(false).get();
                        elements = document.select("ul.itemid_keyword_ul");
                        if(elements.size()!=0) {
                            index++;
                            for(Element e:elements) {
                                //String url = ParseUrl(  e.select("a").attr("href"), select);
                                String test=e.select("a").attr("href");
                                YunData data=new YunData(test,
                                        e.select("a").text().replace("网盘链接",""),
                                        df.format(new Date()),
                                        e.select("p").text().replace("收录时间:","").replace("文件大小:","• ")
                                );
                                yunDataList.add(data);

                            }
                        }
                        break;
                        default:
                            break;
                }

return yunDataList;
        }

    public static String unicodeDecode(String string) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(string);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            string = string.replace(matcher.group(1), ch + "");
        }
        return string;
    }
}