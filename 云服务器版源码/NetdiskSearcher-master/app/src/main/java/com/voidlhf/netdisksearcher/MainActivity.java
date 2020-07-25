package com.voidlhf.netdisksearcher;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {

    private Context currentContext = MainActivity.this;
    private long backTempTime = 0;
    private long backTempTime2 = 0;
    private EditText searchEditText;
    private Button searchButton;
    private TextView selectName;
    private TextView versionText;
    private String searchUrl;
    private SharedPreferences preferences;
    private ImageView siteIcon;
    private List<SelectListItem> data1;
    private List<InterfaceItem> interfaceList;
    private int listIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInterfaceList();

        //标题栏部分开始
        View customActionBar = LayoutInflater.
                from(currentContext).
                inflate(R.layout.custom_action_bar,null);
        ImageView back = customActionBar.findViewById(R.id.back);
        back.setVisibility(View.GONE);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(customActionBar,layoutParams);
        getSupportActionBar().setElevation(0);
        if(Build.VERSION.SDK_INT>=21) {
            Toolbar parent = (Toolbar) customActionBar.getParent();
            parent.setContentInsetsAbsolute(0, 0);
        }
        //标题栏部分结束

        checkUpdate();
        setContentView(R.layout.main);
        preferences = currentContext.getSharedPreferences("setting",MODE_PRIVATE);
        int defaultSelec = preferences.getInt("default_interface", 0);
        if(defaultSelec > interfaceList.size()-1) {
            defaultSelec = 0;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("default_interface", defaultSelec);
            editor.apply();
        }
        listIndex = defaultSelec;
        data1 = new ArrayList<SelectListItem>();
        for(int i=0;i<interfaceList.size();i++) {
            InterfaceItem item1 = interfaceList.get(i);
            SelectListItem item = null;
            if(i==listIndex) {
                item = new SelectListItem(item1.getName(),"",true);
            } else {
                item = new SelectListItem(item1.getName(),"",false);
            }

            data1.add(item);
        }

        siteIcon = findViewById(R.id.siteIcon);
        siteIcon.setImageResource(getResources().getIdentifier(interfaceList.get(defaultSelec).getIconName(),"drawable",getPackageName()));

        searchEditText = findViewById(R.id.searchEditText);
        selectName = findViewById(R.id.selectName);

        selectName.setText(interfaceList.get(defaultSelec).getName());

        versionText = findViewById(R.id.version);
        versionText.setText("v"+APKVersionCodeUtils.getVerName(currentContext));
        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener(){

            @Override
            public boolean onEditorAction(TextView p1, int p2, KeyEvent p3)
            {
                // TODO: Implement this method
                if(p2== EditorInfo.IME_ACTION_SEARCH||p3.getKeyCode()==KeyEvent.ACTION_DOWN) {
                    if(searchEditText.getText().toString().replace(" ","").length()>0) {

                        searchUrl = interfaceList.get(listIndex).getRule().
                                replace("%key%",searchEditText.getText().toString());
                        Intent intent = new Intent(currentContext,SearchListActivity.class);
                        intent.putExtra("searchUrl1",searchUrl);
                        intent.putExtra("searchWord",searchEditText.getText().toString());
                        intent.putExtra("listIndex",listIndex);
                        intent.putExtra("spinnerName",interfaceList.get(listIndex).getName());
                        startActivity(intent);
                    } else {
                        if(System.currentTimeMillis()-backTempTime2<2000) {

                        }else {
                            Toast a = Toasty.error(currentContext,getString(R.string.empty_toast_message),Toast.LENGTH_SHORT,true);
                            a.setGravity(Gravity.TOP,0,150);
                            a.show();
                        }
                        backTempTime2 = System.currentTimeMillis();
                    }
                    return true;
                } else {
                    return false;
                }
            }
        });

        siteIcon.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View p1)
            {
                // TODO: Implement this method
                View view = LayoutInflater.from(currentContext)
                        .inflate(R.layout.select_dialog,null);
                ListView selectList = view.findViewById(R.id.select_list);

                SelectListAdapter adapter2 = new SelectListAdapter(currentContext,data1);
                selectList.setAdapter(adapter2);
                final AlertDialog dialog = new AlertDialog.Builder(currentContext,R.style.DialogStyle1)
                        .setView(view)
                        .show();
                WindowManager m = getWindowManager();
                Display d = m.getDefaultDisplay();  //获取屏幕宽、高
                android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
                //p.height = (int) (d.getHeight() * 0.5);
                p.width = (int) (d.getWidth() * 0.8);
                dialog.getWindow().setAttributes(p);
                selectList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
                    {
                        // TODO: Implement this method
                        listIndex = p3;
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putInt("default_interface",listIndex);
                        editor.apply();
                        for(int i=0;i<data1.size();i++) {
                            data1.get(i).setItemSelected(false);
                        }
                        data1.get(p3).setItemSelected(true);
                        selectName.setText(interfaceList.get(p3).getName());
                        siteIcon.setImageResource(getResources().getIdentifier(interfaceList.get(listIndex).getIconName(),"drawable",getPackageName()));
                        dialog.dismiss();
                    }
                });
            }

        });
        searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View p1)
            {
                // TODO: Implement this method
                if(searchEditText.getText().toString().replace(" ","").length()>0) {
                    searchUrl = interfaceList.get(listIndex).getRule().
                            replace("%key%",searchEditText.getText().toString());
                    Intent intent = new Intent(currentContext,SearchListActivity.class);
                    intent.putExtra("searchUrl1",searchUrl);
                    intent.putExtra("searchWord",searchEditText.getText().toString());
                    intent.putExtra("listIndex",listIndex);
                    intent.putExtra("spinnerName",interfaceList.get(listIndex).getName());
                    startActivity(intent);
                } else {
                    if(System.currentTimeMillis()-backTempTime2<2000) {

                    }else {
                        Toast a = Toasty.error(currentContext,getString(R.string.empty_toast_message),Toast.LENGTH_SHORT,true);
                        a.setGravity(Gravity.TOP,0,150);
                        a.show();
                    }
                    backTempTime2 = System.currentTimeMillis();
                }
            }
        });
        selectName.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View p1)
            {
                // TODO: Implement this method
                siteIcon.performClick();
            }
        });
    }


    @Override
    public void onBackPressed() {
        // TODO: Implement this method
        if(System.currentTimeMillis()-backTempTime<2000) {
            finish();
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        }else {
            Toast a = Toasty.warning(currentContext,getString(R.string.exit_toast_message),Toast.LENGTH_SHORT,true);
            a.setGravity(Gravity.TOP,0,150);
            a.show();
        }
        backTempTime = System.currentTimeMillis();
    }

    private void initInterfaceList() {
        interfaceList = new ArrayList<InterfaceItem>();
        interfaceList.add(new InterfaceItem("盘搜搜","http://m2.pansoso.com/zh/%key%_%index%","site_pss"));
        interfaceList.add(new InterfaceItem("大力盘","https://www.dalipan.com/search?keyword=%key%&page=%index%","site_dlp"));
        interfaceList.add(new InterfaceItem("58网盘","http://m.58wangpan.com/so?order=feed_time&feed_time=0&keyword=%key%&page=%index%","site_58wp"));
        interfaceList.add(new InterfaceItem("如风搜","http://m.rufengso.net/s/comb/n-%key%&s-feedtime1/%index%","site_rfs"));
        interfaceList.add(new InterfaceItem("Fast搜搜","https://www.fastsoso.cn/search?page=%index%&k=%key%&s=1&t=-1","site_fastss"));
        interfaceList.add(new InterfaceItem("小白盘","https://www.xiaobaipan.com/list-%key%-p%index%.html","site_xbp"));
        interfaceList.add(new InterfaceItem("盘发网盘","https://wx.panfa.net/s/%key%_%index%","site_pfwp"));
    }

    private void checkUpdate() {
        new Thread(new Runnable(){

            @Override
            public void run()
            {
                // TODO: Implement this method
                try
                {
                    String updateInfo = HttpTools.getJsonByInternet("https://api.github.com/repos/voidlhf/NetdiskSearcher/releases/latest");
                    JSONObject jo = new JSONObject(updateInfo);
                    JSONArray ja = jo.getJSONArray("assets");
                    JSONObject jo2 = ja.getJSONObject(0);
                    final String browser_download_url = jo2.getString("browser_download_url");
                    String tag_name = jo.getString("tag_name");
                    final String name = jo.getString("name");
                    //final String published_at = jo.getString("published_at");
                    final String body = jo.getString("body");
                    System.out.println("更新测试："+browser_download_url);

                    if(CheckTools.needUpdate(Long.parseLong(tag_name),currentContext)) {

                        runOnUiThread(new Runnable(){

                            @Override
                            public void run()
                            {
                                // TODO: Implement this method
                                View view = LayoutInflater.from(currentContext)
                                        .inflate(R.layout.update_dialog,null);
                                TextView newVersion = view.findViewById(R.id.new_version);
                                newVersion.setText(name);
                                TextView updateLog = view.findViewById(R.id.update_log);
                                updateLog.setText(body);
                                Button updateButton = view.findViewById(R.id.update_button);
                                final AlertDialog dialog = new AlertDialog.Builder(currentContext,R.style.DialogStyle1)
                                        .setView(view)
                                        .show();
                                updateButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.setData(Uri.parse(browser_download_url));
                                        startActivity(intent);
                                        dialog.dismiss();
                                    }
                                });
                                WindowManager m = getWindowManager();
                                Display d = m.getDefaultDisplay();  //获取屏幕宽、高
                                android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
                                //p.height = (int) (d.getHeight() * 0.5);
                                p.width = (int) (d.getWidth() * 0.8);
                                dialog.getWindow().setAttributes(p);
                            }
                        });
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
