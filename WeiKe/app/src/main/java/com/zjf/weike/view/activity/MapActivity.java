package com.zjf.weike.view.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapActivity extends BaseActivity implements LocationSource, AMapLocationListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.map)
    MapView mMap;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.bottom_sheet)
    FrameLayout mBottomSheet;
    @BindView(R.id.fab_done)
    FloatingActionButton mFabDone;
    @BindView(R.id.activity_map)
    CoordinatorLayout mActivityMap;

    private AMap aMap;//定义一个地图对象
    private OnLocationChangedListener mListener;
    private AMapLocationClient mClient;
    private AMapLocationClientOption mOption;
    private ProgressDialog mDialog;


    @Override
    public void initVariables() {
        mDialog = new ProgressDialog(this);
        mDialog.setCancelable(false);
        mDialog.setTitle(getString(R.string.hint));
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setMessage(getString(R.string.getlocation));
        mDialog.show();
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        mToolbar.setTitle(getString(R.string.chooselocation));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mMap.onCreate(mBundle);
        if (aMap == null) {
            aMap = mMap.getMap();
        }

        aMap.setLocationSource(this);
        aMap.setMyLocationEnabled(true);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);

        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.
                fromResource(R.drawable.ic_location_on_blue_500_24dp));
        aMap.setMyLocationStyle(myLocationStyle);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mClient == null) {
            mClient = new AMapLocationClient(this);
            mOption = new AMapLocationClientOption();
            mClient.setLocationListener(this);
            mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            mOption.setOnceLocationLatest(true);
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
                SnackBarUtil.ShortSnackbar(mActivityMap, getString(R.string.locationfailure), 1).show();
            }
            mDialog.dismiss();
        }
    }

    @OnClick(R.id.fab_done)
    public void onClick() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMap.onDestroy();
        if (null != mClient) {
            mClient.onDestroy();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mMap.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        deactivate();
        mMap.onPause();
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
        mMap.onSaveInstanceState(outState);
    }
}
