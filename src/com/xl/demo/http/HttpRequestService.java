package com.xl.demo.http;


import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.xl.demo.GlobalConfig;
import com.xl.demo.model.RequestEntity;

import android.content.Context;
import android.net.Proxy;
import android.os.Build;
/**
 *  Function:
 *     联网类 非安全（非SSL）
*/
public class HttpRequestService {

	
	public Context context;
	
	public HttpRequestService(Context context) {
		super();
		this.context = context;
	}

	/**
	 * 向服务器发起get请求
	 * @param re
	 * @return
	 */
	public byte[] doGet(RequestEntity re)
    {
	 	HashMap<String,String> requestMap = re.requestMap;
	 	String url = null;
	 	if(re.url.contains("?")){
	 		url = re.url+"&";
	 	}else{
	 		url = re.url+"?";
	 	}
	 	
	 	
	 	if(requestMap != null){
	 		Iterator iter = requestMap.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String key = (String) entry.getKey();
				String val = (String) entry.getValue();
				if(iter.hasNext()){
					url = url +key+"="+val+"&";
				}else{
					url = url +key+"="+val;
				}
				
			}
	 	}
		
    	String result= "";
//    	HttpGet httpRequst = new HttpGet(URI uri);
//    	HttpGet httpRequst = new HttpGet(String uri);
//    	创建HttpGet或HttpPost对象，将要请求的URL通过构造方法传入HttpGet或HttpPost对象。
    	HttpGet httpRequst = new HttpGet(url);

//    	new DefaultHttpClient().execute(HttpUriRequst requst);
    	try {
   //使用DefaultHttpClient类的execute方法发送HTTP GET请求，并返回HttpResponse对象。
			HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);//其中HttpGet是HttpUriRequst的子类
		    if(httpResponse.getStatusLine().getStatusCode() == 200)
		    {
		    	HttpEntity httpEntity = httpResponse.getEntity();
		    	result = EntityUtils.toString(httpEntity);//取出应答字符串
		    // 一般来说都要删除多余的字符 
		    	result.replaceAll("\r", "");//去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格  
		    }else {
		    		httpRequst.abort(); 
		    }
    	}
		  catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.getMessage().toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.getMessage().toString();
		}
    	byte[] bResult=result.getBytes();
		return bResult;
    }

	/**
	 * 向服务器发起POST请求，获取应用信息、新闻资讯等数据
	 * 
	 * @param path
	 *            ：请求地址
	 * @param paramMap
	 *            ：请求的参数集
	 * @return 封装响应数据
	 * @throws Exception
	 */
	public  byte[] doRequest(RequestEntity re) throws HttpException {
		if (GlobalConfig.CURRENT_NETWORK_STATE_TYPE == GlobalConfig.NETWORK_STATE_IDLE) {
			throw new HttpException("Http Invalid!");
		}
		byte[] data = re.requestData;
		
		HttpClient httpClient = null;
		try {
			HttpPost hp = new HttpPost(re.url);
			HttpParams params = new BasicHttpParams();
			if( GlobalConfig.CURRENT_NETWORK_STATE_TYPE == GlobalConfig.NETWORK_STATE_CMWAP || GlobalConfig.CURRENT_NETWORK_STATE_TYPE == GlobalConfig.NETWORK_STATE_CTWAP){
				String proxyHost = null;
				int proxyPort = 80;
				if (Build.VERSION.SDK_INT >= 13) {
					proxyHost = System.getProperties().getProperty("http.proxyHost");
					proxyPort = Integer.parseInt(System.getProperties().getProperty("http.proxyPort"));
				} else {
					proxyHost = Proxy.getHost(context);
					proxyPort = Proxy.getPort(context);
				}
				if (proxyHost != null) {
					HttpHost proxy = new HttpHost(proxyHost, proxyPort);
					params.setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
				} 
			
			}			
			HttpConnectionParams.setConnectionTimeout(params,
					GlobalConfig.HTTP_CONNECTION_TIMEOUT);
			HttpConnectionParams.setSoTimeout(params,
					GlobalConfig.HTTP_SO_TIMEOUT);
			HttpConnectionParams.setSocketBufferSize(params,
					GlobalConfig.HTTP_SOCKET_BUFFER_SIZE);
			HttpClientParams.setRedirecting(params, false);
			
			httpClient = new DefaultHttpClient(params);
//			Loger.i("HttpCommunicator: ", "doPost() url == " + re.url);
			System.out.println("doPost() url ="+" " + re.url);
//			Loger.i("HttpCommunicator: ", "doPost() body == " + data == null?null:new String(data));
			
			
			
			// 包体处理
			if (data != null) {
				hp.setEntity(new ByteArrayEntity(data));
			}
			HttpContext httpContext = new BasicHttpContext();
			HttpResponse response = httpClient.execute(hp, httpContext);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				System.out.println("Content-Length == " + entity.getContentLength());
				return EntityUtils.toByteArray(entity);
			}else if (response.getStatusLine().getStatusCode() == 302
					|| response.getStatusLine().getStatusCode() == 301) {
				String redirectURL = response.getHeaders("location")[0]
						.getValue();
				re.url = redirectURL;
				return doRequest(re);
			} else {
				throw new HttpException("Http Response Code : " + response.getStatusLine().getStatusCode());
			}
		} catch (SocketTimeoutException e) {
			throw new HttpException(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new HttpException(e.getMessage());
		} finally {
			if (httpClient != null) {
				httpClient.getConnectionManager().shutdown();
				httpClient = null;
			}
		}

	}

}
