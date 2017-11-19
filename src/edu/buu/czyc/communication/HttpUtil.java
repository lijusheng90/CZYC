package edu.buu.czyc.communication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class HttpUtil
{
	// ����HttpClient����
	public static HttpClient httpClient = new DefaultHttpClient();
	
//	public HttpUtil() 
//	{
//		HttpParams httpParameters = new BasicHttpParams();
//		int timeoutConnection = 3000;
//		HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
//		int timeoutSocket = 5000;
//		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
//		httpClient = new DefaultHttpClient(httpParameters);
		
//		httpClient = new DefaultHttpClient();
//	}
	
	/**
	 *
	 * @param url ���������URL
	 * @return ��������Ӧ�ַ���
	 * @throws Exception
	 */
	public static String getRequest(final String url)
		throws Exception
	{
		FutureTask<String> task = new FutureTask<String>(
		new Callable<String>()
		{
			@Override
			public String call() throws Exception
			{
				// ����HttpGet����
				HttpGet get = new HttpGet(url);
				// ����GET����
				HttpResponse httpResponse = httpClient.execute(get);
				// ����������ɹ��ط�����Ӧ
				if (httpResponse.getStatusLine()
					.getStatusCode() == 200)
				{
					// ��ȡ��������Ӧ�ַ���
					String result = EntityUtils
						.toString(httpResponse.getEntity());
					return result;
				}
				return null;
			}
		});
		new Thread(task).start();
		return task.get();
	}

	/**
	 * @param url ���������URL
	 * @param params �������
	 * @return ��������Ӧ�ַ���
	 * @throws Exception
	 */
	public static String postRequest(final String url
		, final Map<String ,String> rawParams)throws Exception
	{
		FutureTask<String> task = new FutureTask<String>(
		new Callable<String>()
		{
			@Override
			public String call() throws Exception
			{
				// ����HttpPost����
				HttpPost post = new HttpPost(url);
				// ������ݲ��������Ƚ϶�Ļ����ԶԴ��ݵĲ������з�װ
				List<NameValuePair> params = 
					new ArrayList<NameValuePair>();
				for(String key : rawParams.keySet())
				{
					//��װ�������
					params.add(new BasicNameValuePair(key 
						, rawParams.get(key)));
				}
				// �����������
				post.setEntity(new UrlEncodedFormEntity(
					params, "gbk"));
				
				
				// ����POST����
				HttpResponse httpResponse = httpClient.execute(post);
				// ����������ɹ��ط�����Ӧ
				if (httpResponse.getStatusLine()
					.getStatusCode() == 200)
				{
					// ��ȡ��������Ӧ�ַ���
					String result = EntityUtils
						.toString(httpResponse.getEntity());
					return result;
				}
				return null;
			}
		});
		new Thread(task).start();
		return task.get();
	}
}

//
//Toast.makeText(MainActivity.this,"=_="+DEVICE_ID, Toast.LENGTH_SHORT).show();
//ApplyManager myApplyManager = new ApplyManager();
//JSONObject jsonObj = null;
//try
//{
//	jsonObj = myApplyManager.do_apply(DEVICE_ID);
//	if (jsonObj !=null)
//	{
//		Toast.makeText(MainActivity.this,"�ٰ�һ��1111111111  ++ "+DEVICE_ID, Toast.LENGTH_SHORT).show();
//	}
////		jc_diaodu_backinfor(jsonObj);
//}
//catch (Exception e)
//{
////	mainActivity.ToastMessage("������æ�������磡", 1);
//}

