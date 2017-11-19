package edu.buu.czyc.communication;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

import edu.buu.czyc.conf.CallCarInfor;

public class ApplyManager {
	/**����*/
	public JSONObject do_breath(String DEVICE_ID)
	{
		JSONObject jsonObj = null;
		try
		{
			// ʹ��Map��װ�������
			Map<String, String> map = new HashMap<String, String>();
			map.put("applyid", "2");
			map.put("userid", DEVICE_ID);
			/*******
			2����������ÿ���̶�ʱ������
			 * ***/
			map.put("excmd", "2");
			// ���巢�������URL
			String url = UrlConfig.JIAOCHE;
			// ��������
			jsonObj = new JSONObject(HttpUtil.postRequest(url, map));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return  jsonObj;
	}
	
	//1���г�����
	public JSONObject do_apply(String DEVICE_ID,int station_1,int station_2,int selectcar,int selectstyle)
	{
		JSONObject jsonObj = null;
		try
		{
			// ʹ��Map��װ�������
			Map<String, String> map = new HashMap<String, String>();
			map.put("applyid", "2");
			map.put("userid", DEVICE_ID);
			
			map.put("excmd", "1");
			if (selectcar==1)
				map.put("carid", "BAIC1");
			else
				map.put("carid", "BAIC2");
			
			map.put("style", ""+selectstyle);
			
			map.put("station1", ""+station_1);
			map.put("station2", ""+station_2);
			
			CallCarInfor.StartStation = station_1;
			CallCarInfor.EndStation = station_2;
			CallCarInfor.car_num = selectcar;
			
			// ���巢�������URL
			String url = UrlConfig.JIAOCHE;
			// ��������
			jsonObj = new JSONObject(HttpUtil.postRequest(url, map));
		}
		catch (Exception e)
		{
			jsonObj = new JSONObject();
			e.printStackTrace();
		}
		return  jsonObj;
	}

	///10:��վ
	public JSONObject do_apply_station(String DEVICE_ID)
	{
		JSONObject jsonObj = null;
		try
		{
			// ʹ��Map��װ�������
			Map<String, String> map = new HashMap<String, String>();
			map.put("applyid", "2");
			map.put("userid", DEVICE_ID);
			map.put("excmd", "10");
			
			// ���巢�������URL
			String url = UrlConfig.JIAOCHE;
			// ��������
			jsonObj = new JSONObject(HttpUtil.postRequest(url, map));
		}
		catch (Exception e)
		{
			jsonObj = new JSONObject();
			e.printStackTrace();
		}
		return  jsonObj;
	}
}
