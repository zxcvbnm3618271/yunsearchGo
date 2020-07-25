package com.voidlhf.netdisksearcher;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class SearchListActivity extends AppCompatActivity {
    private Context currentContext = SearchListActivity.this;
    private TextView actionBarTitle ;
    private Intent lastIntent;
    private ListView searchResultListView ;
    private TextView searchNoResult;
    private ProgressBar progressBar;
    private SearchListAdapter adapter;
    private List<SearchListItem> datas1;
    private List<SearchListItem> datas2;
    private String searchUrl1;
    private int index = 1;
    private int listIndex ;
    private String spinnerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lastIntent = getIntent();
        searchUrl1 = lastIntent.getStringExtra("searchUrl1");
        listIndex = lastIntent.getIntExtra("listIndex",0);
        spinnerName = lastIntent.getStringExtra("spinnerName");
        View customActionBar = LayoutInflater.
                from(currentContext).
                inflate(R.layout.custom_action_bar,null);
        View backLayout = customActionBar.findViewById(R.id.back_layout);
        backLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View p1)
            {
                // TODO: Implement this method
                finish();
            }
        });
        actionBarTitle = customActionBar.findViewById(R.id.actionBarTitle);
        actionBarTitle.setGravity(Gravity.CENTER);
        actionBarTitle.setText(getString(R.string.search_list));
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(customActionBar,layoutParams);
        getSupportActionBar().setElevation(0);
        if(Build.VERSION.SDK_INT>=21) {
            Toolbar parent = (Toolbar) customActionBar.getParent();
            parent.setContentInsetsAbsolute(0, 0);
        }


        setContentView(R.layout.search_list);

        searchResultListView = findViewById(R.id.search_result_list);
        searchNoResult = findViewById(R.id.search_no_result);
        progressBar = findViewById(R.id.progress_bar);
        datas1 = new ArrayList<SearchListItem>();
        datas2 = new ArrayList<SearchListItem>();
        load();
        adapter = new SearchListAdapter(currentContext,datas1);
        searchResultListView.setAdapter(adapter);
        searchResultListView.setOnScrollListener(new AbsListView.OnScrollListener(){

            @Override
            public void onScrollStateChanged(AbsListView p1, int p2)
            {
                if (p1.getLastVisiblePosition() == p1.getCount() - 1) {
                    progressBar.setVisibility(View.VISIBLE);
                    load();
                }
            }

            @Override
            public void onScroll(AbsListView p1, int p2, int p3, int p4)
            {
                // TODO: Implement this method
            }
        });
        searchResultListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
            {
                Intent intent = new Intent(currentContext,SearchDetailsActivity.class);
                intent.putExtra("detailsUrl",datas1.get(p3).getItemUrl());
                intent.putExtra("detailsName",datas1.get(p3).getItemName());
                intent.putExtra("detailsPass",datas1.get(p3).getItemPassword());
                intent.putExtra("detailsDes",datas1.get(p3).getItemDescription());
                intent.putExtra("detailsIndex",listIndex);
                intent.putExtra("spinnerName",lastIntent.getStringExtra("spinnerName"));
                startActivity(intent);
            }
        });
    }

    private void load() {
        new Thread(new Runnable(){

            @Override
            public void run()
            {
                // TODO: Implement this method
                try
                {

                    for(int i=0;i<2;i++) {
                        String searchUrl;
                        searchUrl = searchUrl1.replace("%index%",String.valueOf(index));
                        String spinnerName2 = spinnerName;
                        switch(spinnerName2) {
                            case "盘搜搜" : {
                                Connection conn1 = Jsoup.connect(searchUrl);
                                conn1.userAgent("MicroMessenger");
                                Document document = conn1.get();
                                Elements elements = document.select("div#con>a");
                                if(elements.size()!=0) {
                                    index++;
                                    for(Element e:elements) {
                                        String str = e.select(".des").text().toString();
                                        SearchListItem item = new SearchListItem(
                                                e.select(".pss h2").text(),
                                                str,
                                                "http://m2.pansoso.com"+e.attr("href"),
                                                "");
                                        datas1.add(item);
                                    }
                                    updateProgressAndAdapter();
                                } else {
                                    updateProgress();
                                }
                                break;
                            }//end case

                            case "58网盘" : {
                                Document document = Jsoup.connect(searchUrl).validateTLSCertificates(false).get();
                                Elements elements = document.select("li.tl_shadow");
                                if(elements.size()!=0) {
                                    index++;
                                    for(Element e:elements) {
                                        SearchListItem item = new SearchListItem(
                                                e.select("h3").text(),
                                                e.select(".ti_author_time").text(),
                                                "http://m.58wangpan.com"+e.select("a").attr("href"),
                                                "");
                                        if(item.getItemName().length()>0) {
                                            datas1.add(item);
                                        }
                                    }
                                    updateProgressAndAdapter();
                                } else {
                                    updateProgress();
                                }
                                break;
                            }//end case

                            case "如风搜" : {
                                Document document = Jsoup.connect(searchUrl).validateTLSCertificates(false).get();
                                Elements elements = document.select("ul.list>li div.content");
                                if(elements.size()!=0) {
                                    index++;
                                    for(Element e:elements) {
                                        SearchListItem item = new SearchListItem(
                                                e.select("h3").text(),
                                                e.select(".list-content").text(),
                                                "http://m.rufengso.net"+e.select(">a").attr("href"),
                                                "");
                                        if(item.getItemName().length()>0) {
                                            datas1.add(item);
                                        }
                                    }
                                    updateProgressAndAdapter();
                                } else {
                                    updateProgress();
                                }
                                break;
                            }//end case

                            case "Fast搜搜" : {
                                Document document = Jsoup.connect(searchUrl).validateTLSCertificates(false).get();
                                Elements elements  = document.getElementsByAttributeValue("style","clear: both").prev("div");
                                if(elements.size()!=0) {
                                    index++;
                                    for(Element e:elements) {
                                        SearchListItem item = new SearchListItem(
                                                e.select("strong").text(),
                                                e.getElementsByAttributeValueContaining("style","color: #105207;").text()
                                                        .substring(0,e.getElementsByAttributeValueContaining("style","color: #105207;").text().indexOf("|"))
                                                        .replace("时间： ","").
                                                        replace("",""),
                                                "https://www.fastsoso.cn"+e.select("a").attr("href"),
                                                "");
                                        if(item.getItemName().length()>0) {
                                            datas1.add(item);
                                        }
                                    }
                                    updateProgressAndAdapter();
                                } else {
                                    updateProgress();
                                }
                                break;
                            }//endcase

                            case "盘发网盘" : {
                                Document document = Jsoup.connect(searchUrl).validateTLSCertificates(false).get();
                                Elements elements = document.select("ul.itemid_keyword_ul");
                                if(elements.size()!=0) {
                                    index++;
                                    for(Element e:elements) {
                                        SearchListItem item = new SearchListItem(
                                                e.select("a").text().replace("网盘链接",""),
                                                e.select("p").text().replace("收录时间:","").replace("文件大小:","• "),
                                                e.select("a").attr("href"),
                                                "");
                                        if(item.getItemName().length()>0) {
                                            datas1.add(item);
                                        }
                                    }
                                    updateProgressAndAdapter();
                                } else {
                                    updateProgress();
                                }
                                break;
                            }//endcase

                            case "小白盘" : {
                                Document document = Jsoup.connect(searchUrl).validateTLSCertificates(false).get();
                                Elements elements  = document.select("div.jobs-item");
                                if(elements.size()!=0) {
                                    index++;
                                    for(Element e:elements) {
                                        SearchListItem item = new SearchListItem(
                                                e.select("h4.job-title").text(),
                                                e.select("span.info-row").text(),
                                                "https://www.xiaobaipan.com"+e.select("h4.job-title a").attr("href"),
                                                "");
                                        if(item.getItemName().length()>0) {
                                            if(!item.getItemUrl().contains("bbs.xiaobaipan.com")) {
                                                datas1.add(item);
                                            }
                                        }
                                    }
                                    updateProgressAndAdapter();
                                } else {
                                    updateProgress();
                                }
                                break;
                            }//endcase

                            case "大力盘" : {
                                Document document = Jsoup.connect(searchUrl).validateTLSCertificates(false).get();
                                Elements elements = document.select("div.resource-item");
                                if(elements.size()!=0) {
                                    index++;
                                    for(Element e:elements) {
                                        SearchListItem item = new SearchListItem(
                                                e.select("a.valid").text(),
                                                e.select("p.time").text(),
                                                "https://www.dalipan.com"+e.select("a.valid").attr("href"),
                                                "");
                                        if(item.getItemName().length()>0) {
                                            datas1.add(item);
                                        }
                                    }
                                    updateProgressAndAdapter();
                                } else {
                                    updateProgress();
                                }
                                break;
                            } //end case

                            default :
                                updateProgressAndAdapter();
                                break;
                        }//end switch
                    }

                }
                catch (Exception e)
                {
                    updateProgress();
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void updateProgress() {
        runOnUiThread(new Runnable(){
            @Override
            public void run()
            {
                // TODO: Implement this method
                if(!(datas1.size()>0)) {
                    searchNoResult.setVisibility(View.VISIBLE);
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void updateProgressAndAdapter() {
        runOnUiThread(new Runnable(){
            @Override
            public void run()
            {
                // TODO: Implement this method
                if(!(datas1.size()>0)) {
                    searchNoResult.setVisibility(View.VISIBLE);
                }
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO: Implement this method
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
