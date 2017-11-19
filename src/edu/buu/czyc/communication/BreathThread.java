package edu.buu.czyc.communication;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.json.JSONException;
import org.json.JSONObject;

import edu.buu.czyc.conf.ViewCallCarConfig;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class BreathThread  extends Thread{
	/**呼吸，不断请求服务器*/
	/**是否停止呼吸*/
	boolean Stop = false;
	MyHandler myHandler;
	JSONObject jsonObj;
	ApplyManager myApplyManager = new ApplyManager();
	
	public BreathThread(MyHandler myHandler) {
		this.myHandler = myHandler;
	}
	public void jiexi()
	{
		try
	    {
			if (jsonObj.getInt("ret") ==1)
			{
				if (jsonObj.getInt("cmd")==3)
				{
					Message msg = new Message();
					Bundle bundle = new Bundle(); 
					if (jsonObj.has("BAIC1lat"))
						bundle.putDouble("BAIC1lat",jsonObj.getDouble("BAIC1lat"));  //往Bundle中存放数据 
					else
						bundle.putDouble("BAIC1lat",0);
					
					if (jsonObj.has("BAIC1lon"))
						bundle.putDouble("BAIC1lon",jsonObj.getDouble("BAIC1lon")); 
					else
						bundle.putDouble("BAIC1lon",0);
					
					if (jsonObj.has("BAIC2lat"))
						bundle.putDouble("BAIC2lat",jsonObj.getDouble("BAIC2lat"));  
					else
						bundle.putDouble("BAIC2lat",0);
					
					if (jsonObj.has("BAIC2lon"))
						bundle.putDouble("BAIC2lon",jsonObj.getDouble("BAIC2lon"));  //往Bundle中存放数据 
					else
						bundle.putDouble("BAIC2lon",0);
					
					if (jsonObj.has("arrivetime"))
						bundle.putInt("arrivetime",jsonObj.getInt("arrivetime")); 
					if (jsonObj.has("distance"))
						bundle.putDouble("distance",jsonObj.getDouble("distance"));  
					 msg.setData(bundle);
					 //画车的在地图上的位置
					msg.what =0;
			    	myHandler.sendMessage(msg);
			    	
			    	if (jsonObj.has("show_arrive"))
					{
			    		Message msg2 = new Message();
						Bundle bundle2 = new Bundle(); 
			    		
						bundle2.putInt("show_arrive",jsonObj.getInt("show_arrive"));  
						msg2.setData(bundle2);
						msg2.what =44;
				    	myHandler.sendMessage(msg2);
					}
				}
				if (jsonObj.getInt("cmd")==4)
				{////////////////////////  用户到达车下车位置
					Message msg = new Message();
//					Bundle bundle = new Bundle(); 
					
					msg.what = 4;
			    	myHandler.sendMessage(msg);
			    	
			    	Message msg2 = new Message();
					Bundle bundle2 = new Bundle(); 
			    	//还是要画车的位置
			    	if (jsonObj.has("BAIC1lat"))
						bundle2.putDouble("BAIC1lat",jsonObj.getDouble("BAIC1lat"));
			    	else
						bundle2.putDouble("BAIC1lat",0);
			    	
					if (jsonObj.has("BAIC1lon"))
						bundle2.putDouble("BAIC1lon",jsonObj.getDouble("BAIC1lon"));
					else
						bundle2.putDouble("BAIC1lon",0);
					
					if (jsonObj.has("BAIC2lat"))
						bundle2.putDouble("BAIC2lat",jsonObj.getDouble("BAIC2lat"));
					else
						bundle2.putDouble("BAIC2lat",0);
					if (jsonObj.has("BAIC2lon"))
						bundle2.putDouble("BAIC2lon",jsonObj.getDouble("BAIC2lon"));
					else
						bundle2.putDouble("BAIC2lon",0);

					 msg2.setData(bundle2);//mes利用Bundle传递数据  
					 /////////////////画车的在地图上的位置
					 msg2.what = 0;
			    	myHandler.sendMessage(msg2);
				}
			}
		} 
		catch (JSONException e) 
		{

		}
	}

	public void run()
	{
		Stop = false;
		while (!Stop)
		{
	        try 
	        {  
	        	 jsonObj = myApplyManager.do_breath(ViewCallCarConfig.DEVICE_ID);
	        	 jiexi();
	        } 
	        catch (Exception e) 
	        {  
	            e.printStackTrace();  
	        }
	        finally
	        {  
	        }  
			
			try 
			{
				sleep(UrlConfig.breath_thread_sleep_time*1000);
			} 
			catch (InterruptedException e) 
			{
			}
		}
	}
}
