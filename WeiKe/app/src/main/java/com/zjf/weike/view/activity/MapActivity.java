package com.zjf.weike.view.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.zjf.weike.R;
import com.zjf.weike.util.SnackBarUtil;
import com.zjf.weike.view.activity.base.BaseActivity;

public class MapActivity extends BaseActivity implements LocationSource, AMapLocationListener {

    private AMap aMap;//定义一个地图对象
    private MapView mMapView;
    private OnLocationChangedListener mListener;
    private AMapLocationClient mClient;
    private AMapLocationClientOption mOption;
    private CoordinatorLayout mLayout;
    private Toolbar mToolbar;


    @Override
    public void initVariables() {

    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_map);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.chooselocation));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mLayout = (CoordinatorLayout) findViewById(R.id.activity_map);
        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(mBundle);
        if (aMap == null) {
            aMap = mMapView.getMap();
        }

        aMap.setMyLocationEnabled(true);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);

        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.
                fromResource(R.drawable.ic_location_on_blue_500_24dp));
        aMap.setMyLocationStyle(myLocationStyle);

    }

    @Override
    public void setListener() {
        aMap.setLocationSource(this);
    }

    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mClient == null) {
            mClient = new AMapLocationClient(this);
            mOption = new AMapLocationClientOption();
            mClient.setLocationListener(this);
            mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            mOption.setOnceLocation(true);
            mClient.setLocationOption(mOption);
            mClient.startLocation();
        }
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null
                    && amapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(amapLocation);
                aMap.moveCamera(CameraUpdateFactory.zoomTo(18));
            } else {
                SnackBarUtil.ShortSnackbar(mLayout, "定位失败", 1).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        if (null != mClient) {
            mClient.onDestroy();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        deactivate();
        mMapView.onPause();
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mClient != null) {
            mClient.stopLocation();
            mClient.onDestroy();
        }
        mClient = null;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }
}
