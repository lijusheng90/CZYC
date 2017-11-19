package edu.buu.czyc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.CoordinateConverter;
import com.amap.api.maps.CoordinateConverter.CoordType;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;

import edu.buu.czyc.conf.MapConfig;

public class GaoDeMap {

	  Polyline polyline = null;
	  AMap aMap;
	  CoordinateConverter converter;
	  
	  /**全部线路*/
	  List< List<LatLng> > latLngs_list;
	  
	  MainActivity myMainActivity;
	  
	  public GaoDeMap(AMap aMap,CoordinateConverter converter,MainActivity myMainActivity) 
	  {
		  this.myMainActivity = myMainActivity;
		  this.aMap = aMap;
		  this.converter = converter;
		  
		  ini();
		  
		  amap_move_center_and_setZoom(MapConfig.lat_center , MapConfig.lon_center , MapConfig.ZOOM);
	  }
	 
	  public void ini()
	  {
	        // CoordType.GPS 待转换坐标类型
	        converter.from(CoordType.GPS); 
		  
	        List<LatLng> latLngs_0_1;
	  	  List<LatLng> latLngs_1_2;
	  	  List<LatLng> latLngs_2_3;
	  	  List<LatLng> latLngs_3_4;
	  	  List<LatLng> latLngs_4_5;
	  	  List<LatLng> latLngs_5_6;
	  	  List<LatLng> latLngs_6_7;
	  	  List<LatLng> latLngs_7_0;
	  	  
	  	latLngs_list = new ArrayList< List<LatLng> >();
	  	
		  //全部线路
	       latLngs_0_1 = new ArrayList<LatLng>();
	      for (int i = 0;i <  MapConfig.lat_list_0_1.size();i++)
	      {
	    	   converter.coord(new LatLng(MapConfig.lat_list_0_1.get(i),MapConfig.lon_list_0_1.get(i))); 
	    	   // 执行转换操作
	    	   LatLng desLatLng = converter.convert();
	    	   latLngs_0_1.add(desLatLng);
	      }
	      latLngs_1_2 = new ArrayList<LatLng>();
	      for (int i = 0;i <  MapConfig.lat_list_1_2.size();i++)
	      {
	    	   converter.coord(new LatLng(MapConfig.lat_list_1_2.get(i),MapConfig.lon_list_1_2.get(i))); 
	    	   // 执行转换操作
	    	   LatLng desLatLng = converter.convert();
	    	   latLngs_1_2.add(desLatLng);
	      }
	      
	      latLngs_2_3 = new ArrayList<LatLng>();
	      for (int i = 0;i <  MapConfig.lat_list_2_3.size();i++)
	      {
	    	   converter.coord(new LatLng(MapConfig.lat_list_2_3.get(i),MapConfig.lon_list_2_3.get(i))); 
	    	   // 执行转换操作
	    	   LatLng desLatLng = converter.convert();
	    	   latLngs_2_3.add(desLatLng);
	      }
	      
	      latLngs_3_4 = new ArrayList<LatLng>();
	      for (int i = 0;i <  MapConfig.lat_list_3_4.size();i++)
	      {
	    	   converter.coord(new LatLng(MapConfig.lat_list_3_4.get(i),MapConfig.lon_list_3_4.get(i))); 
	    	   // 执行转换操作
	    	   LatLng desLatLng = converter.convert();
	    	   latLngs_3_4.add(desLatLng);
	      }
	      
	      latLngs_4_5 = new ArrayList<LatLng>();
	      for (int i = 0;i <  MapConfig.lat_list_4_5.size();i++)
	      {
	    	   converter.coord(new LatLng(MapConfig.lat_list_4_5.get(i),MapConfig.lon_list_4_5.get(i))); 
	    	   // 执行转换操作
	    	   LatLng desLatLng = converter.convert();
	    	   latLngs_4_5.add(desLatLng);
	      }
	      
	      
	      
	      latLngs_5_6 = new ArrayList<LatLng>();
	      for (int i = 0;i <  MapConfig.lat_list_5_6.size();i++)
	      {
	    	   converter.coord(new LatLng(MapConfig.lat_list_5_6.get(i),MapConfig.lon_list_5_6.get(i))); 
	    	   // 执行转换操作
	    	   LatLng desLatLng = converter.convert();
	    	   latLngs_5_6.add(desLatLng);
	      }
	      
	      latLngs_6_7 = new ArrayList<LatLng>();
	      for (int i = 0;i <  MapConfig.lat_list_6_7.size();i++)
	      {
	    	   converter.coord(new LatLng(MapConfig.lat_list_6_7.get(i),MapConfig.lon_list_6_7.get(i))); 
	    	   // 执行转换操作
	    	   LatLng desLatLng = converter.convert();
	    	   latLngs_6_7.add(desLatLng);
	      }
	      latLngs_7_0 = new ArrayList<LatLng>();
	      for (int i = 0;i <  MapConfig.lat_list_7_0.size();i++)
	      {
	    	   converter.coord(new LatLng(MapConfig.lat_list_7_0.get(i),MapConfig.lon_list_7_0.get(i))); 
	    	   // 执行转换操作
	    	   LatLng desLatLng = converter.convert();
	    	   latLngs_7_0.add(desLatLng);
	      }
	      

	      latLngs_list.add(latLngs_0_1 );
	  	latLngs_list.add(latLngs_1_2 );
	  	latLngs_list.add(latLngs_2_3 );
	  	latLngs_list.add(latLngs_3_4 );
	  	latLngs_list.add(latLngs_4_5 );
	  	latLngs_list.add(latLngs_5_6 );
	  	latLngs_list.add(latLngs_6_7 );
	  	latLngs_list.add(latLngs_7_0 );
	  }
	  
    /**高德地图移动中定位中心*/
    public void  amap_move_center_and_setZoom(double lat, double lon,int zoomsize)
    {
    	LatLng marker1 = new LatLng(lat,lon);   
        converter.coord(marker1); 
        // 执行转换操作
 	   	LatLng desLatLng = converter.convert();
        //设置中心点和缩放比例  
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(desLatLng));  
        aMap.moveCamera(CameraUpdateFactory.zoomTo(zoomsize)); 
	}
	  
//    public void draw_car(boolean f ,double car_lat,double car_lon)
//    {
//    	aMap.clear();
//    	LatLng marker1 = new LatLng(car_lat,car_lon);   
//	      converter.coord(marker1); 
//	      LatLng desLatLng = converter.convert();
//	    aMap.addMarker(new MarkerOptions().position(desLatLng).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
//		            .decodeResource(myMainActivity.getResources(),R.drawable.car_mark))));
//    }
    
    public void clear_all()
   {
	   aMap.clear();
   }
    
    Marker marker_car_1 = null;
    Marker marker_car_2 = null;
    /**f 为true 就是  刷新 车辆位置     */
  public void showMap_line_and_mark(boolean f ,double car_lat_1,double car_lon_1,double car_lat_2,double car_lon_2,String car_mes1,String car_mes2)
  {
		 if (f)
		 {
			 LatLng marker1 = new LatLng(car_lat_1,car_lon_1);   
		      converter.coord(marker1); 
		      LatLng desLatLng1 = converter.convert();
		      if (marker_car_1!=null)
		    	  marker_car_1.destroy();
		      marker_car_1 =  aMap.addMarker(new MarkerOptions().position(desLatLng1).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
			            .decodeResource(myMainActivity.getResources(),R.drawable.car_mark))).title(car_mes1)   );	 
		      
		      
		      LatLng marker2 = new LatLng(car_lat_2,car_lon_2);   
		      converter.coord(marker1); 
		      LatLng desLatLng2 = converter.convert();
		      if (marker_car_2!=null)
		    	  marker_car_2.destroy();
		      
		      marker_car_2 =  aMap.addMarker(new MarkerOptions().position(desLatLng2).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
			            .decodeResource(myMainActivity.getResources(),R.drawable.car_mark))).title(car_mes2)   );	 
		 }
		 else
		 {
			 showMap_line_stations_all();
//			 showMap_line_stations(1 ,7);
		 }
		 
  }
  
//画线路全部    和站点
  public void showMap_line_stations_all()
  {
	 for (int i = 0;i<MapConfig.lon_station_list.size();i++)
	{
		 LatLng marker1 = new LatLng(MapConfig.lat_station_list.get(i),MapConfig.lon_station_list.get(i));   
	      converter.coord(marker1); 
	      // 执行转换操作
		   	LatLng desLatLng = converter.convert();
		  aMap.addMarker(new MarkerOptions().position(desLatLng).title(MapConfig.lat_station_name_list.get(i)));
		  if (i==0)
		  aMap.addMarker(new MarkerOptions().position(desLatLng).title(MapConfig.lat_station_name_list.get(i)));
	}
	 
	  for (int i = 0;i<latLngs_list.size();i++)
			{
			  polyline =aMap.addPolyline(new PolylineOptions().addAll(latLngs_list.get(i)).width(MapConfig.road_line_width).color(Color.argb(MapConfig.road_line_color_a,MapConfig.road_line_color_r,MapConfig.road_line_color_g,MapConfig.road_line_color_b)));
			}
  }
  //画线路
  public void showMap_line_stations(int start_station_1 ,int start_station_2)
  {
	for (int i = start_station_1;i<start_station_2+1;i++)
	{
		System.out.println("pageIndex="+i); 
		if ( i>=latLngs_list.size() )
		{
			break;
		}
		polyline =aMap.addPolyline(new PolylineOptions().addAll(latLngs_list.get(i)).width(MapConfig.road_line_width).color(Color.argb(MapConfig.road_line_wait_color_a,MapConfig.road_line_wait_color_r,MapConfig.road_line_wait_color_g,MapConfig.road_line_wait_color_b)));
	}
  }
}
