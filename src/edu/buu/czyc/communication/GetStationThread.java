package edu.buu.czyc.communication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import edu.buu.czyc.ListNode;
import edu.buu.czyc.MainActivity;
import edu.buu.czyc.conf.MapConfig;

import android.os.Bundle;
import android.os.Message;

public class GetStationThread  extends Thread{
	JSONObject jsonObj;
	ApplyManager myApplyManager = new ApplyManager();
	String DEVICE_ID;
	MainActivity myMainActivity;
	MyHandler myHandler;
	boolean Stop = false;
	public GetStationThread(String DEVICE_ID,MyHandler myHandler) 
	{
		this.myHandler = myHandler;
		this.DEVICE_ID = DEVICE_ID;
	}
	public void jiexi()
	{
		Message msg = new Message();
		 Bundle bundle = new Bundle(); 
		try
	    {
			if (jsonObj.getInt("ret") ==1)
			{
				if (jsonObj.getInt("cmd")==10)
				{
					if (jsonObj.has("map_center_lat") && jsonObj.has("map_center_lon"))
					{
						MapConfig.lat_center = jsonObj.getDouble("map_center_lat");
						MapConfig.lon_center = jsonObj.getDouble("map_center_lon");
					}
					if ( jsonObj.has("lon_station_list") )
					{
						String config_name_station= jsonObj.getString("lon_station_list");
						
						String[] arrayStr =new String[]{};
						arrayStr=config_name_station.split(",");
						
						List<String> arrayStr_list = java.util.Arrays.asList(arrayStr);
						
						Double[] arrayStr_d =new Double[]{};
						
						for (int i =0;i< arrayStr_list.size();i++ )
							arrayStr_d[i] = Double.valueOf(arrayStr_list.get(i));
						MapConfig.lon_station_list = java.util.Arrays.asList(arrayStr_d);
					}
					
					if ( jsonObj.has("lat_station_list") )
					{
						String config_name_station= jsonObj.getString("lat_station_list");
						
						String[] arrayStr =new String[]{};
						arrayStr=config_name_station.split(",");
						
						List<String> arrayStr_list = java.util.Arrays.asList(arrayStr);
						
						Double[] arrayStr_d =new Double[]{};
						
						for (int i =0;i< arrayStr_list.size();i++ )
							arrayStr_d[i] = Double.valueOf(arrayStr_list.get(i));
						MapConfig.lat_station_list = java.util.Arrays.asList(arrayStr_d);
					}
										
					if ( jsonObj.has("station_num") )
					{
						int num = jsonObj.getInt("station_num");
						
						List< ListNode > lat_lists = new ArrayList<edu.buu.czyc.ListNode>();
						List< ListNode > lon_lists = new ArrayList<edu.buu.czyc.ListNode>();
						for (int i = 0;i<num;i++)
						{
							int next_i = i+1;
							if (next_i==num)
							{
								next_i = 0;
							}
							String key_lon = "lon_list_"+i+"_"+next_i;
							String key_lat = "lat_list_"+i+"_"+next_i;
							
							{
								String config_name_station= jsonObj.getString(key_lon);
								
								String[] arrayStr =new String[]{};
								arrayStr=config_name_station.split(",");
								
								List<String> arrayStr_list = java.util.Arrays.asList(arrayStr);
								
								Double[] arrayStr_d =new Double[]{};
								
								
								for (int ii =0;ii< arrayStr_list.size();ii++ )
								{
									arrayStr_d[i] = Double.valueOf(arrayStr_list.get(ii));
									
								}
								List<Double> mylist = new ArrayList<Double>();
								mylist = java.util.Arrays.asList(arrayStr_d);
								lon_lists.add( new edu.buu.czyc.ListNode( mylist  ) );
							}
							{
								String config_name_station= jsonObj.getString(key_lat);
								
								String[] arrayStr =new String[]{};
								arrayStr=config_name_station.split(",");
								
								List<String> arrayStr_list = java.util.Arrays.asList(arrayStr);
								
								Double[] arrayStr_d =new Double[]{};
								
								for (int ii =0;ii< arrayStr_list.size();ii++ )
								{
									arrayStr_d[i] = Double.valueOf(arrayStr_list.get(ii));
									
								}
								List<Double> mylist = new ArrayList<Double>();
								mylist = java.util.Arrays.asList(arrayStr_d);
								lat_lists.add( new edu.buu.czyc.ListNode( mylist  ) );
							}
						}
						MapConfig.lat_lists = lat_lists;
						MapConfig.lon_lists = lon_lists;
					}
					
					if ( jsonObj.has("config_name_station") )
					{
						String config_name_station= jsonObj.getString("config_name_station");
						String[] arrayStr =new String[]{};
						arrayStr=config_name_station.split(",");//先转化为数组
						MapConfig.lat_station_name_list = java.util.Arrays.asList(arrayStr);
						Stop = true;
					}
				}
			}	
		} 
		catch (JSONException e) 
		{
			msg.what =11110;
	    	myHandler.sendMessage(msg);
		}
		
		msg.what =10;
    	myHandler.sendMessage(msg);
	}
	public void run()
	{
		while (!Stop)
		{
	        try
	        {  
		        jsonObj = myApplyManager.do_apply_station(DEVICE_ID);
	        	jiexi();
	        } 
	        catch (Exception e) 
	        {  
	            e.printStackTrace();  
	        }
	        finally
	        {  
	        }  
//	        try 
//			{
//				sleep(5*1000);
//			} 
//			catch (InterruptedException e) 
//			{
//			}
		}
	}
}
