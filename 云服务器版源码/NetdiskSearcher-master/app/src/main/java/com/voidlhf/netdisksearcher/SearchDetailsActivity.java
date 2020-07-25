package com.voidlhf.netdisksearcher;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.*;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import es.dmoral.toasty.Toasty;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchDetailsActivity extends AppCompatActivity {
    private TextView actionBarTitle ;
    private Intent lastIntent;
    private long backTempTime = 0;
    private Context currentContext = SearchDetailsActivity.this;
    private String detailsUrl;
    private String detailsName;
    private String detailsPass;
    private String detailsDes;
    private int detailsIndex;
    private TextView bdInfo;
    private LinearLayout clearLayout;
    private Button copyButton;
    private Button openButton;
    private Button shareButton;
    private WebView webview1;
    private ImageView effectiveStatus;
    private String bdBaseUri = "";
    private String shareUri = "";
    private String spinnerName;
    private boolean needLoad = true;
    private boolean isFinish = false;
    private ProgressBar infoProgressBar;
    private String ApiServer="http://39.108.55.167:8080/yunsearch/api/Sendnode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_details);
        initView();
        getBdUrl();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // TODO: Implement this method
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getBdUrl() {
        new Thread(new Runnable(){

            @Override
            public void run()
            {
                // TODO: Implement this method
                String spinnerName2 = spinnerName;
                switch(spinnerName2) {
                    case "盘搜搜" : {
                        try
                        {
                            Connection conn1 = Jsoup.connect(detailsUrl);
                            conn1.userAgent("MicroMessenger");
                            Document document1 = conn1.get();

                            final String des;
                            String des1 = "文件名称："+document1.select("h1").text()+"\n\n"+
                                    document1.select(".info ul li").get(2).text()+"\n\n"+
                                    document1.select(".info ul li").get(3).text();
                            if(document1.select("div.key").text().length()>0) {
                                des = des1 + "\n\n"+document1.select("div.key").text();
                            } else {
                                des = des1;
                            }

                            String tempUrl2 = document1.select("a.down_button_link").attr("href");
                            if(tempUrl2.length()==0) {
                                updateNoResult();
                                break;
                            }
                            final Document document3 = Jsoup.connect(tempUrl2).validateTLSCertificates(false).post();
                            final String baseUri = document3.baseUri();
                            bdBaseUri = baseUri;
                            final boolean effectiveResult = isEffective(baseUri);
                            shareUri = des+"\n\n链接："+baseUri;

                            String[] str=des.replace("文件名称：","").replace("文件大小：","").replace("分享时间：","").replace("提取码：","").split("\n\n");
                            String pass=str.length<4?"":str[3];
                            HttpClient.doPost(ApiServer,
                                    "sharename="+str[0] +
                                    "&size="+str[1]+
                                    "&sharetime="+str[2]+
                                    "&avatarurl="+baseUri+
                                            "&accesscode="+pass+
                                            "&state="+effectiveResult);

                            runOnUiThread(new Runnable(){

                                @Override
                                public void run()
                                {
                                    // TODO: Implement this method
                                    infoProgressBar.setVisibility(View.GONE);
                                    showEffectiveState(effectiveStatus,effectiveResult);
                                    bdInfo.setText(des.replace("文件名称：","").replace("文件大小：","").replace("分享时间：",""));
                                }
                            });
                        }
                        catch (Exception e)
                        {
                            updateNoResult();
                            e.printStackTrace();
                        }
                        break;
                    }//end case

                    case "58网盘" : {
                        try
                        {
                            Document document1 = Jsoup.connect(detailsUrl).validateTLSCertificates(false).get();
                            Elements elements1;
                            if(document1.select("a#html5_href_file").size()!=0) {
                                elements1 = document1.select("a#html5_href_file");
                            } else {
                                elements1 = document1.select("a#html5_href_dir");
                            }
                            final String des;
                            String tempUrl1 = elements1.attr("href");
                            String tempUrl2 = "https://m.58wangpan.com"+tempUrl1;
                            Document document2 = Jsoup.connect(tempUrl2).followRedirects(true).validateTLSCertificates(false).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6").referrer(detailsUrl).get();
                            final String baseUri;

                            if(document2.select("#tip_msg p").last().text().startsWith("http")) {
                                baseUri = document2.select("#tip_msg p").last().text();
                                des = "文件名称："+
                                        document1.select("div.file-name").text()+"\n\n"+
                                        document1.select("div.file-time").get(0).text();
                            } else {
                                baseUri = document2.select("#tip_msg a").attr("href");
                                des = "文件名称："+
                                        document1.select("div.file-name").text()+"\n\n"+
                                        document1.select("div.file-time").get(0).text()+"\n\n"+
                                        document2.select("#tip_msg p").last().text();
                            }

                            bdBaseUri = baseUri;
                            final boolean effectiveResult = isEffective(baseUri);
                            shareUri = des+"\n\n链接："+baseUri;

                            String[] str=des.replace("文件名称：","").replace("分享时间：","").split("\n\n");
                            HttpClient.doPost(ApiServer,
                                    "sharename="+str[0] +
                                            "&size=0"+
                                            "&sharetime="+str[1]+
                                            "&avatarurl="+baseUri+
                                            "&state="+effectiveResult);

                            runOnUiThread(new Runnable(){

                                @Override
                                public void run()
                                {
                                    // TODO: Implement this method
                                    infoProgressBar.setVisibility(View.GONE);
                                    bdInfo.setText(des.replace("文件名称：","").replace("分享时间：",""));
                                    showEffectiveState(effectiveStatus,effectiveResult);
                                }
                            });
                        }
                        catch (Exception e)
                        {
                            updateNoResult();
                            e.printStackTrace();
                        }
                        break;
                    }//end case

                    case "如风搜" : {
                        try
                        {
                            Document document1 = Jsoup.connect(detailsUrl).validateTLSCertificates(false).get();
                            final String des = "文件名称："+document1.select("div.resource-page h1").text()+"\n\n"+
                                    document1.select("div.share-info p.sep").text();
                            final String tempUrl1 = document1.select("a#redirect-link").attr("href");
                            Document document2 = Jsoup.connect(tempUrl1).followRedirects(true).validateTLSCertificates(false).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6").referrer(detailsUrl).get();
                            final String baseUri = document2.select("div.main a").attr("href");
                            if(baseUri.length()!=0) {
                                bdBaseUri = baseUri;
                                shareUri = des+"\n\n链接："+baseUri;
                                String[] str=des.replace("文件名称：","").replace("上传用户：","").split("\n\n");
                                HttpClient.doPost(ApiServer,
                                        "sharename="+str[0] +
                                                "&username="+str[1]+
                                                "&avatarurl="+baseUri+
                                                "&state="+isEffective(baseUri));
                            } else {
                                bdBaseUri = tempUrl1;
                                shareUri = des+"\n\n链接："+tempUrl1;
                                String[] str=des.replace("文件名称：","").replace("上传用户：","").split("\n\n");
                                HttpClient.doPost(ApiServer,
                                        "sharename="+str[0] +
                                                "&username="+str[1]+
                                                "&avatarurl="+tempUrl1+
                                                "&state="+isEffective(tempUrl1));
                            }
                            final boolean effectiveResult1 = isEffective(baseUri);
                            final boolean effectiveResult2 = isEffective(tempUrl1);



                            runOnUiThread(new Runnable(){

                                @Override
                                public void run()
                                {
                                    // TODO: Implement this method
                                    infoProgressBar.setVisibility(View.GONE);
                                    bdInfo.setText(des.replace("文件名称：","").replace("上传用户：",""));
                                    if(baseUri.length()!=0) {
                                        showEffectiveState(effectiveStatus,effectiveResult1);
                                    } else {
                                        showEffectiveState(effectiveStatus,effectiveResult2);
                                    }

                                }
                            });
                        }
                        catch (Exception e)
                        {
                            updateNoResult();
                            e.printStackTrace();
                        }
                        break;
                    }//end case

                    case "Fast搜搜" : {
                        try
                        {
                            System.out.println("测试"+detailsUrl);
                            Document document1 = Jsoup.connect(detailsUrl).validateTLSCertificates(false).get();
                            final String des = detailsName+"\n\n分享时间："+detailsDes;
                            final String baseUri = document1.getElementsByAttributeValue("class","btn btn-success").get(0).attr("onclick").replace("window.open('/redirect?url=","").replace("','_blank')","");
                            bdBaseUri = baseUri;
                            shareUri = "文件名称："+des+"\n\n链接："+baseUri;
                            final boolean effectiveResult = isEffective(baseUri);

                             HttpClient.doPost(ApiServer,
                                    "sharename="+detailsName +
                                            "&sharetime="+detailsDes+
                                            "&avatarurl="+baseUri+
                                            "&state="+effectiveResult);

                            runOnUiThread(new Runnable(){

                                @Override
                                public void run()
                                {
                                    // TODO: Implement this method
                                    infoProgressBar.setVisibility(View.GONE);
                                    bdInfo.setText(des.replace("分享时间：",""));
                                    showEffectiveState(effectiveStatus,effectiveResult);
                                }
                            });
                        }
                        catch (IOException e)
                        {
                            updateNoResult();
                            e.printStackTrace();
                        }
                        break;
                    }//endcase

                    case "小白盘" : {
                        try
                        {
                            Document document1 = Jsoup.connect(detailsUrl).validateTLSCertificates(false).get();
                            final String des = "文件名称："+document1.select("h1#tb_kks").text()+"\n\n"+
                                    document1.select("span.date").get(0).text();
                            String tempUrl = "https://www.xiaobaipan.com"+document1.select("div.modal-footer a").attr("href");
                            final String baseUri = Jsoup.connect(tempUrl).followRedirects(true).validateTLSCertificates(false).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6").referrer(detailsUrl).get().baseUri();
                            bdBaseUri = baseUri;
                            shareUri = des+"\n\n链接："+baseUri;
                            final boolean effectiveResult = isEffective(baseUri);

                            String[] str=des.replace("文件名称：","").replace("分享时间：","").split("\n\n");
                            HttpClient.doPost(ApiServer,
                                    "sharename="+str[0] +
                                            "&size=0"+
                                            "&sharetime="+str[1]+
                                            "&avatarurl="+baseUri+
                                            "&state="+effectiveResult);

                            runOnUiThread(new Runnable(){

                                @Override
                                public void run()
                                {
                                    // TODO: Implement this method
                                    infoProgressBar.setVisibility(View.GONE);
                                    bdInfo.setText(des.replace("文件名称：","").replace("分享时间:",""));
                                    if(bdBaseUri.startsWith("https://pan.baidu.com/")
                                            ||bdBaseUri.startsWith("http://pan.baidu.com/")
                                            ||bdBaseUri.startsWith("https://yun.baidu.com/")
                                            ||bdBaseUri.startsWith("http://yun.baidu.com/")) {
                                        showEffectiveState(effectiveStatus,effectiveResult);
                                    } else {
                                        effectiveStatus.setImageResource(R.drawable.effective_unkonw);
                                    }
                                }
                            });
                        }
                        catch (Exception e)
                        {
                            updateNoResult();
                            e.printStackTrace();
                        }
                        break;
                    }//endcase

                    case "盘发网盘" : {
                        try
                        {
                            Document document1 = Jsoup.connect(detailsUrl).validateTLSCertificates(false).get();
                            final String des = "文件名称："+detailsName+"\n\n分享时间："+detailsDes;
                            final String baseUri = document1.select("a#pan_button").attr("href");
                            Connection conn1 = Jsoup.connect(baseUri);
                            Connection.Response res = conn1.execute();
                            String url2 = res.header("Refresh");
                            String baseUri2 = url2.substring(url2.indexOf("http"));
                            bdBaseUri = baseUri2;
                            shareUri = des+"\n\n链接："+bdBaseUri;
                            final boolean effectiveResult = isEffective(bdBaseUri);
                            String[] str=des.replace("分享时间：","").
                                    replace("文件名称：","").split(",");
                            HttpClient.doPost(ApiServer,
                                    "sharename="+detailsName +
                                            "&size=0"+
                                            "&sharetime="+detailsDes+
                                            "&avatarurl="+bdBaseUri+
                                    "&state="+effectiveResult);

                            runOnUiThread(new Runnable(){

                                @Override
                                public void run()
                                {
                                    // TODO: Implement this method
                                    infoProgressBar.setVisibility(View.GONE);
                                    bdInfo.setText(des.replace("分享时间：","").replace("文件名称：",""));
                                    showEffectiveState(effectiveStatus,effectiveResult);
                                }
                            });
                        }
                        catch (Exception e)
                        {
                            updateNoResult();
                            e.printStackTrace();
                        }
                        break;
                    }//endcase

                    case "大力盘" : {
                        try
                        {
                            Document document = Jsoup.connect(detailsUrl).get();
                            String bdurls[] = {
                                    "yun.baidu.com",
                                    "pan.baidu.com"
                            };
                            String tempUrl = "";
                            String url2 = "";
                            for(int a0 =0;a0<bdurls.length;a0++) {
                                if(document.toString().indexOf(bdurls[a0])>0) {
                                    tempUrl = document.toString().substring(document.toString().indexOf(bdurls[a0]));
                                    url2 = unicodeDecode("https://"+tempUrl.substring(0,tempUrl.indexOf("\"")));
                                }
                            }
                            String pass = document.select("span.copy-item").text().replace("提取密码","").replace("点击复制","").replace(" ","");
                            if(url2.length()>0) {
                                final String des ;
                                bdBaseUri = url2;
                                if(pass.length()>0) {
                                    des = detailsName+"\n\n"+detailsDes+"\n\n提取码："+pass;
                                    shareUri = "文件名称："+detailsName+"\n\n分享时间："+detailsDes+"\n\n链接："+bdBaseUri+"\n\n提取码："+pass;
                                } else {
                                    des = detailsName+"\n\n"+detailsDes;
                                    shareUri = "文件名称："+detailsName+"\n\n分享时间："+detailsDes+"\n\n链接："+bdBaseUri;
                                }
                                final boolean effectiveResult = isEffective(bdBaseUri);

                                String[] str=des.replace("文件名称：","").replace("文件大小：","").replace("分享时间：","").split(",");
                                HttpClient.doPost(ApiServer,
                                        "sharename="+detailsName +
                                                "&size=0"+
                                                "&sharetime="+detailsDes+
                                                "&avatarurl="+bdBaseUri+
                                        "&accesscode="+pass+
                                                "&state="+effectiveResult);

                                runOnUiThread(new Runnable(){

                                    @Override
                                    public void run()
                                    {
                                        // TODO: Implement this method
                                        infoProgressBar.setVisibility(View.GONE);
                                        bdInfo.setText(des.replace("文件名称：","").replace("分享时间：",""));
                                        showEffectiveState(effectiveStatus,effectiveResult);
                                    }
                                });
                            }//endif
                            else{
                                updateNoResult();
                            }

                        }
                        catch (Exception e)
                        {
                            updateNoResult();
                            e.printStackTrace();
                        }
                        break;
                    }//end case
                    default :
                        updateNoResult();
                        break;
                }
            }
        }).start();
    }

    private void initView(){

        lastIntent = getIntent();
        detailsUrl = lastIntent.getStringExtra("detailsUrl");
        detailsName = lastIntent.getStringExtra("detailsName");
        detailsPass = lastIntent.getStringExtra("detailsPass");
        detailsDes = lastIntent.getStringExtra("detailsDes");
        detailsIndex = lastIntent.getIntExtra("detailsIndex",0);
        spinnerName = lastIntent.getStringExtra("spinnerName");
        infoProgressBar = findViewById(R.id.info_progress_bar);
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
        actionBarTitle.setText(getString(R.string.search_result));
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

        bdInfo = findViewById(R.id.bdInfo);
        effectiveStatus = findViewById(R.id.effectiveStatus);
        clearLayout = findViewById(R.id.clearLayout);
        clearLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View p1)
            {
                // TODO: Implement this method
                bdInfo.clearFocus();
            }
        });
        copyButton = findViewById(R.id.copyButton);
        openButton = findViewById(R.id.openButton);
        shareButton = findViewById(R.id.shareButton);
        webview1 = findViewById(R.id.search_detailsWebView);
        webview1.getSettings().setJavaScriptEnabled(true);
        webview1.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                if(isFinish) {

                } else {
                    super.onPageFinished(view, url);
                    getBdUrl();
                    isFinish = true;
                }
            }
        });
        copyButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View p1)
            {
                // TODO: Implement this method
                if(bdBaseUri.length()!=0) {
                    ClipboardManager clip = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    clip.setText(bdBaseUri);
                    if(System.currentTimeMillis()-backTempTime<2000) {

                    }else {
                        Toast a = Toasty.success(currentContext,getString(R.string.copy_success),Toast.LENGTH_SHORT,true);
                        a.setGravity(Gravity.TOP,0,150);
                        a.show();
                    }
                    backTempTime = System.currentTimeMillis();
                }
            }
        });
        openButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View p1)
            {
                // TODO: Implement this method
                if(bdBaseUri.length()!=0) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(bdBaseUri));
                        startActivity(intent);
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        shareButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View p1)
            {
                // TODO: Implement this method
                if(shareUri.length()!=0) {
                    try {
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT,shareUri);
                        sendIntent.setType("text/plain");
                        startActivity(sendIntent);
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private boolean isEffective(String uri) {
        try
        {
            Document document = Jsoup.connect(uri).get();
            if(document.getElementsByClass("share-error-right").size()!=0||
                    document.getElementsByClass("share-error-left").size()!=0||
                    document.getElementsByClass("error-404").size()!=0) {
                return false;
            } else {
                return true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    private void showEffectiveState(ImageView i,boolean b) {
        if(b) {
            i.setImageResource(R.drawable.effective);
        } else {
            i.setImageResource(R.drawable.effective_false);
        }
    }

    private void updateNoResult() {
        runOnUiThread(new Runnable(){

            @Override
            public void run()
            {
                // TODO: Implement this method
                infoProgressBar.setVisibility(View.GONE);
                bdInfo.setText("获取数据失败");
                effectiveStatus.setImageResource(R.drawable.effective_false);
            }
        });
    }

    public String unicodeDecode(String string) {
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
