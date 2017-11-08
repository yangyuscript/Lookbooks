package com.lin.lookbooks.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.lin.lookbooks.R;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.mapsdk.raster.model.BitmapDescriptorFactory;
import com.tencent.mapsdk.raster.model.Circle;
import com.tencent.mapsdk.raster.model.CircleOptions;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.mapsdk.raster.model.Marker;
import com.tencent.mapsdk.raster.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.TencentMap;

/**
 * Created by 18374 on 2017/11/5.
 */

public class Nearby extends Fragment implements TencentLocationListener{
    private Context mContext;
    private ImageButton btnShowLocation;
    private TencentLocationManager locationManager;
    private TencentLocationRequest locationRequest;
    private Marker myLocation;
    private Circle accuracy;
    private MapView mapView;
    private TencentMap tencentMap;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_nearby,container,false);
        mContext=view.getContext();
        init(view);
        bindListener();
        return view;
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
        locationManager.removeUpdates(Nearby.this);
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onStop() {
        mapView.onStop();
        super.onStop();
    }

    public void init(View view){
        btnShowLocation=(ImageButton)view.findViewById(R.id.btn_show_location);
        locationManager=TencentLocationManager.getInstance(mContext);
        locationRequest=TencentLocationRequest.create();
        mapView=(MapView)view.findViewById(R.id.mapview);
        tencentMap=mapView.getMap();
        Toast.makeText(mContext,"欢迎使用腾讯地图",Toast.LENGTH_SHORT).show();
    }

    public void bindListener(){
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int error=locationManager.requestLocationUpdates(locationRequest,Nearby.this);
                switch (error) {
                    case 0:
                        Log.e("location", "成功注册监听器");
                        break;
                    case 1:
                        Log.e("location", "设备缺少使用腾讯定位服务需要的基本条件");
                        break;
                    case 2:
                        Log.e("location", "manifest 中配置的 key 不正确");
                        break;
                    case 3:
                        Log.e("location", "自动加载libtencentloc.so失败");
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onLocationChanged(TencentLocation tencentLocation, int i, String s) {
        if(TencentLocation.ERROR_OK==i){
            LatLng latLng=new LatLng(tencentLocation.getLatitude(),tencentLocation.getLongitude());
            if(myLocation==null){
                myLocation=tencentMap.addMarker(new MarkerOptions().position(latLng)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.navigation)).
                anchor(0.5f,0.5f));
            }
            if(accuracy==null){
                accuracy=tencentMap.addCircle(new CircleOptions().center(latLng).
                radius((double)tencentLocation.getAccuracy()).
                fillColor(0x440000ff).
                strokeWidth(0f));
            }
            myLocation.setPosition(latLng);
            myLocation.setRotation(tencentLocation.getBearing());//仅当定位来源于gps有效，或者使用方向传感器
            accuracy.setCenter(latLng);
            accuracy.setRadius(tencentLocation.getAccuracy());
            myLocation.showInfoWindow();
            tencentMap.animateTo(latLng);
            Toast.makeText(mContext,"地址为："+tencentLocation.getLatitude()+" "+tencentLocation.getLongitude()+" "+tencentLocation.getName()+" "
+tencentLocation.getAddress(),Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(mContext,"定位失败!"+i,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStatusUpdate(String s, int i, String s1) {
        String desc = "";
        switch (i) {
            case STATUS_DENIED:
                desc = "权限被禁止";
                break;
            case STATUS_DISABLED:
                desc = "模块关闭";
                break;
            case STATUS_ENABLED:
                desc = "模块开启";
                break;
            case STATUS_GPS_AVAILABLE:
                desc = "GPS可用，代表GPS开关打开，且搜星定位成功";
                break;
            case STATUS_GPS_UNAVAILABLE:
                desc = "GPS不可用，可能 gps 权限被禁止或无法成功搜星";
                break;
            case STATUS_LOCATION_SWITCH_OFF:
                desc = "位置信息开关关闭，在android M系统中，此时禁止进行wifi扫描";
                break;
            case STATUS_UNKNOWN:
                break;
        }
        Toast.makeText(mContext,"location status:" + s + ", " + s + " " + desc,Toast.LENGTH_SHORT).show();
        Log.e("location", "location status:" + s + ", " + s + " " + desc);
    }
}
