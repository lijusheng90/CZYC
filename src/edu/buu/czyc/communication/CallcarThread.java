package edu.buu.czyc.communication;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Message;

public class CallcarThread 
{
	/**呼吸，不断请求服务器*/
	/**是否停止呼吸*/
	boolean Stop = false;
	MyHandler myHandler;
	JSONObject jsonObj;
	ApplyManager myApplyManager = new ApplyManager();
	String DEVICE_ID;
	public CallcarThread(String DEVICE_ID,MyHandler myHandler) {
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
				if (jsonObj.getInt("cmd")==7)
				{
					 String carspositions = "";
					 String stationspositions = "";
					
					int carspositionsSize = jsonObj.getInt("carspositionsSize");
					//ttt += carspositionsSize; 
					for (int i = 0;i<carspositionsSize;i++)
					{
						double lat = jsonObj.getDouble("carspositionlat"+i);
						double lon = jsonObj.getDouble("carspositionlon"+i);
						
						carspositions += (""+lat+","+lon+";"); 
					}
					
					int stationNUM = jsonObj.getInt("stationNUM");
					for (int i = 0;i<stationNUM;i++)
					{
						double lat = jsonObj.getDouble("stationlat"+i);
						double lon = jsonObj.getDouble("stationlon"+i);
						stationspositions += (""+lat+","+lon+";");
					}
					 bundle.putString("carspositions",carspositions);  //往Bundle中存放数据 
					 bundle.putString("stationspositions",stationspositions);  //往Bundle中存放数据 
					 bundle.putString("cmd2",jsonObj.getString("cmd2"));  //往Bundle中存放数据 
				}
			}
		} 
		catch (JSONException e) 
		{
		}
		 msg.setData(bundle);//mes利用Bundle传递数据  
		msg.what =10;
    	myHandler.sendMessage(msg);
		
	}
	public void run()
	{
		Stop = false;
		while (!Stop)
		{
	        try 
	        {  
	        	 jsonObj = myApplyManager.do_breath(DEVICE_ID);
	        	 jiexi();
	        } 
	        catch (Exception e) 
	        {  
	            e.printStackTrace();  
	        }
	        finally
	        {  
	        }  
			
//			try 
//			{
//				sleep(Appconfig.BREATHTIME*1000);
//			} 
//			catch (InterruptedException e) 
//			{
//			}
		}
	}
}
