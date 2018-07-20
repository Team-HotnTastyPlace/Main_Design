package com.example.do_11.Doctorsfab_Android.Naver_Map;

import android.app.ActionBar;
import android.os.Bundle;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapView;

/**
 * Created by DO-11 on 2018-03-06.
 */

public class Naver_Map extends NMapActivity {

    private NMapView mMapView;// 지도 화면 View
    private final String CLIENT_ID = "txyw4vIIcbTVVIQK0Tiz";// 애플리케이션 클라이언트 아이디 값
  //txyw4vIIcbTVVIQK0Tiz
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();

        mMapView = new NMapView(this);
        setContentView(mMapView);

        mMapView.setClientId(CLIENT_ID); // 클라이언트 아이디 값 설정
        mMapView.setClickable(true);
        mMapView.setEnabled(true);
        mMapView.setFocusable(true);
        mMapView.setFocusableInTouchMode(true);
        mMapView.requestFocus();


    }

}