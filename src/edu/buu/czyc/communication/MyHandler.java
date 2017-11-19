package edu.buu.czyc.communication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.buu.czyc.MainActivity;
import edu.buu.czyc.conf.CallCarInfor;
import edu.buu.czyc.conf.MapConfig;

import android.os.Handler;
import android.os.Message;


public class MyHandler extends Handler
{
	MainActivity myActivity;
	public MyHandler(MainActivity myActivity) 
	{
		this.myActivity = myActivity;
	}
	
	@Override
	public void handleMessage(Message msg) 
	{
		   switch(msg.what){
		   case 0:
		   {
/////////////////画车的在地图上的位置
			   double sss = 0;
			   if (msg.getData().containsKey("BAIC1lat") && msg.getData().containsKey("BAIC1lon") && 
					   msg.getData().containsKey("BAIC2lat") && msg.getData().containsKey("BAIC2lon"))
			   {
				   double lat1 = msg.getData().getDouble("BAIC1lat");
				   double lon1 = msg.getData().getDouble("BAIC1lon");
				   
				   double lat2 = msg.getData().getDouble("BAIC2lat");
				   double lon2 = msg.getData().getDouble("BAIC2lon");
				   
				   myActivity.myGaoDeMap.showMap_line_and_mark(true ,lat1,lon1,lat2,lon2,"BAIC1","BAIC2");
			   }
//			   myActivity.ttt("111111111111111111"+"&&&&&&&&&&&&&&&&&&&&&2");
			   if (msg.getData().containsKey("arrivetime") && msg.getData().containsKey("distance"))
			   {
				   int arrivetime = msg.getData().getInt("arrivetime");
				   double distance = msg.getData().getDouble("distance");
				   
				   int distance_integer = (int)distance;
				   int distance_float = (int) Math.round( (distance-distance_integer)*10  );
				   
				   CallCarInfor.arrivetime = arrivetime;
				   CallCarInfor.distance_integer = distance_integer;
				   CallCarInfor.distance_float = distance_float;
				   
//				   myActivity.ttt("111111111111111111"+"&&&&&&&&&&&&&&&&&&&&&");
				   myActivity.mviewczyc.invalidate();
				   
//				   myActivity.ttt("1111111133333331111111111"+"&&&&&&&&&&&&&&&&&&&&&  "+arrivetime+" - "+distance);
			   }
			   else
			   {
//				   myActivity.ttt("1111111133333331111111111"+"&&&&&&&&&&&&&&&&&&&&&");
			   }
			   break;
		   }
		   case 44:
		   {////////////////车到达上车站
			   int show_arrive =  msg.getData().getInt("show_arrive");
			   if (show_arrive==1)
			   {
				   CallCarInfor.show = true;
			   }
			   else
			   {
				   CallCarInfor.show = false;
			   }
			   myActivity.mviewczyc.invalidate();
			   break;
		   }
		   
		   case 4:
		   {/////////////////重置为可约车
//			   myActivity.ttt("111111111111111111"+"&&&&&&&&&&&&&&&&&&&&&");
			   myActivity.mviewczyc.callcartype = myActivity.mviewczyc.TYPE_CALL_CAR;
			   //让下一波可以重新显示
			   CallCarInfor.show = true;
			   myActivity.mviewczyc.invalidate();
			   break;
		   }
		   case 10:
		   {
//			   myActivity.myGaoDeMap.showMap_line_and_mark(false ,0,0,"");
			   break;
		   }
		   case 11110:
		   {
			   myActivity.ttt("111111111111111111");
			   break;
		   }
		   
	   }
	}
}
