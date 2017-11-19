package edu.buu.czyc;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.CoordinateConverter;
import com.amap.api.maps.MapView;
import com.amap.api.maps.CoordinateConverter.CoordType;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;

import edu.buu.czyc.communication.ApplyManager;
import edu.buu.czyc.communication.BreathThread;
import edu.buu.czyc.communication.GetStationThread;
import edu.buu.czyc.communication.MyHandler;
import edu.buu.czyc.conf.ViewCallCarConfig;



import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	/** 记录两次按返回键的时间*/
	long touchTime = 0;
	/** 两次按返回键的间隔时间*/
	long WAITTIME = 2000;
	MapView mMapView = null;
	public ViewXCZYC mviewczyc = null; 
	
	/**地图相关*/
	public	GaoDeMap myGaoDeMap;
  AMap aMap;
  CoordinateConverter converter;
  TelephonyManager tm ;  
  public String DEVICE_ID = "123456";  
  
  
  MyHandler myhandler ;
  
  BreathThread myBreathThread;
  
  GetStationThread myGetStationThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        
        setContentView(R.layout.activity_main);
        
        tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);  
        DEVICE_ID = tm.getDeviceId(); 
        ViewCallCarConfig.DEVICE_ID =tm.getDeviceId();
        
        
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.gd_map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        converter  = new CoordinateConverter(this); 
        aMap = mMapView.getMap();
        myGaoDeMap = new GaoDeMap( aMap, converter,MainActivity.this);
        myGaoDeMap.showMap_line_and_mark(false  ,0,0,0,0,"","");
        
        mviewczyc = (ViewXCZYC) findViewById(R.id.viewczyc);
        
        myhandler = new MyHandler(MainActivity.this);
        myBreathThread = new BreathThread(myhandler);
        myBreathThread.start();
        
        myGetStationThread = new GetStationThread(DEVICE_ID, myhandler);
        myGetStationThread.start();
        
        // Toast.makeText(MainActivity.this,"=_="+DEVICE_ID, Toast.LENGTH_LONG).show();
    }
    
    public void ttt(String s)
    {
    	Toast.makeText(MainActivity.this,"=_="+s, Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onDestroy() {
      super.onDestroy();
      //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
      mMapView.onDestroy();
    }
   @Override
   protected void onResume() {
      super.onResume();
      //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
      mMapView.onResume();
      }
   @Override
   protected void onPause() {
      super.onPause();
      //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
      mMapView.onPause();
      }
   @Override
   protected void onSaveInstanceState(Bundle outState) {
      super.onSaveInstanceState(outState);
      mMapView.onSaveInstanceState(outState);
    } 

   	/**再按一次退出  只有主界面可以*/
    @Override
    public void onBackPressed() 
    {
    	if (!mviewczyc.can_quit())
    	{
    		return;
    	}
    	long currentTime = System.currentTimeMillis();
		if ((currentTime - touchTime) >= WAITTIME) 
		{
			Toast.makeText(MainActivity.this,"再按一次退出", Toast.LENGTH_SHORT).show();
			touchTime = currentTime;
			return;
		}
		else 
		{
			System.exit(0);
			return ;
		}
    }
}
