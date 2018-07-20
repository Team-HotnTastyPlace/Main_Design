package com.example.do_11.Doctorsfab_Android;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.example.do_11.Doctorsfab_Android.Nav_Drawer.NavDrawer;
import com.example.do_11.Doctorsfab_Android.Socket.SocketActivity;
import com.example.do_11.Doctorsfab_Android.Socket.SocketActivity_list;
import com.example.do_11.Doctorsfab_Android.Socket.Socket_Main;
import com.example.do_11.Doctorsfab_Android.WebView.WebViewActivity;
import com.example.do_11.Doctorsfab_Android.Wifi_Search.Wifi_MainActivity;
import com.example.do_11.R;


/**
 * Created by DO-11 on 2018-03-06.
 */

public class TabsActivity extends ActivityGroup {

    //    private TabHost tabHost;
    public static TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);


        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup(getLocalActivityManager());

        tabHost.addTab(tabHost.newTabSpec("Tab1")
                //        .setIndicator("", getResources().getDrawable(R.drawable.ic_book))
                .setIndicator("", getResources().getDrawable(R.drawable.wifi_icon))
                .setContent(new Intent(this, NavDrawer.class)));

        tabHost.addTab(tabHost.newTabSpec("Tab2")
                .setIndicator("AP_List")
                .setContent(new Intent(this, Wifi_MainActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("Tab3")
                .setIndicator("SockMain")
//                .setContent(new Intent(this, SocketActivity.class)));
                .setContent(new Intent(this, Socket_Main.class)));

        tabHost.addTab(tabHost.newTabSpec("Tab4")
                .setIndicator("Socket")
//                .setContent(new Intent(this, SocketActivity.class)));
                .setContent(new Intent(this, SocketActivity_list.class)));
        tabHost.addTab(tabHost.newTabSpec("Tab5")
                .setIndicator("Socket2")
//                .setContent(new Intent(this, SocketActivity.class)));
                .setContent(new Intent(this, SocketActivity.class)));

        tabHost.setCurrentTab(2);

//        tabHost.addTab(tabHost.newTabSpec("Tab5")
//                .setIndicator("D_Care")
//                .setContent(new Intent(this, WebViewActivity.class)));

//        tabHost.setCurrentTab(1);  //탭호스트 이동

//      tabHost.addTab(tabHost.newTabSpec("Tab3")
//                .setIndicator("Map")
//                .setContent(new Intent(this, Naver_Map.class)));


    }

}






