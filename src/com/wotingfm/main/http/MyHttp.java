package com.wotingfm.main.http;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import android.util.Log;
/**
 * http�������󽻻� ������ʽ
 * @author shenshilei
 *
 */
public class MyHttp {
	private static String TAG="MyHttp";
	private static final String CHARSET = HTTP.UTF_8;
	public static HttpClient httpClient;
	public static  String cookieStr;
	public static HttpClient getHttp(){
		if(null==httpClient){
			 HttpParams params =new BasicHttpParams();
	            // ����һЩ��������
	            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
	            HttpProtocolParams.setContentCharset(params,
	                    CHARSET);
	            HttpProtocolParams.setUseExpectContinue(params, true);
	            HttpProtocolParams
	                    .setUserAgent(
	                            params,
	                            "Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) "
	                                    +"AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1");
	            // ��ʱ����
	            /* �����ӳ���ȡ���ӵĳ�ʱʱ�� */
	            ConnManagerParams.setTimeout(params, 30000);
	            /* ���ӳ�ʱ */
	            HttpConnectionParams.setConnectionTimeout(params, 100000);
	            /* ����ʱ */
	            HttpConnectionParams.setSoTimeout(params, 80000);
	            
	            // �������ǵ�HttpClient֧��HTTP��HTTPS����ģʽ
	            SchemeRegistry schReg =new SchemeRegistry();
	            schReg.register(new Scheme("http", PlainSocketFactory
	                    .getSocketFactory(), 80));
	            schReg.register(new Scheme("https", SSLSocketFactory
	                    .getSocketFactory(), 443));
	         // ʹ���̰߳�ȫ�����ӹ���������HttpClient
	            ClientConnectionManager conMgr =new ThreadSafeClientConnManager(
	                    params, schReg);
	            httpClient= new DefaultHttpClient(conMgr,params);
		}
		return httpClient;
	}
	/**
	 * ͨ��һ����ַ��ȡ����
	 * @param paramString 
	 * @throws UnsupportedEncodingException
	 * @throws IllegalStateException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String httpGet(String paramString) throws UnsupportedEncodingException, IllegalStateException, ClientProtocolException, IOException{
		HttpClient httpClient = getHttp();
	    HttpUriRequest httpUriRequest = new HttpGet(paramString);
	      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((httpClient).execute(httpUriRequest).getEntity().getContent(), "UTF-8"));
	      StringBuilder stringBuilder = new StringBuilder();
	      String str = bufferedReader.readLine();
	      CookieStore mCookieStore = ((AbstractHttpClient) httpClient).getCookieStore();
          List<org.apache.http.cookie.Cookie> cookies =  mCookieStore.getCookies();
          for (int i = 0; i < cookies.size(); i++) {
               //�����Ƕ�ȡCookie['PHPSESSID']��ֵ���ھ�̬�����У���֤ÿ�ζ���ͬһ��ֵ
        	  cookieStr=cookies.get(i).getName()+"="+cookies.get(i).getValue();
          }
	      if (str == null||str.equals("")){
	    	  stringBuilder.append(str);
		      str = bufferedReader.readLine();
		      return null;
	      }else{
		       return str;
	      }
	     
	}
	/**
	 * ���ݲ�������������
	 * @param paramString
	 * @param paramList
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException
	 */
	public static String httpPost(String paramString, List <NameValuePair> paramList) throws ClientProtocolException, IOException{
	    HttpClient defaultHttpClient = getHttp();
	    HttpUriRequest httpPost = new HttpPost(paramString);
	    if(null!=paramList)
	    	((HttpEntityEnclosingRequestBase) httpPost).setEntity(new UrlEncodedFormEntity((java.util.List<? extends org.apache.http.NameValuePair>) paramList, "UTF-8"));
	     HttpEntity httpEntity = defaultHttpClient.execute(httpPost).getEntity();
	      if (httpEntity == null)
	        return null;
	      String resultStr = EntityUtils.toString(httpEntity);
	      CookieStore mCookieStore = ((AbstractHttpClient) defaultHttpClient).getCookieStore();
          List<org.apache.http.cookie.Cookie> cookies =  mCookieStore.getCookies();
          for (int i = 0; i < cookies.size(); i++) {
               //�����Ƕ�ȡCookie['PHPSESSID']��ֵ���ھ�̬�����У���֤ÿ�ζ���ͬһ��ֵ
        	  cookieStr=cookies.get(i).getName()+"="+cookies.get(i).getValue();
          }
	      return resultStr;
	  }
	/**
	 * �ϴ��ļ�
	 * @param file �ļ�
	 * @param url ��ַ
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String postFile(File file,String url) throws ClientProtocolException, IOException {  
		FileBody bin = null;
        HttpClient httpclient = getHttp();  
        HttpPost httppost = new HttpPost(url);  
        if(file != null) {  
            bin = new FileBody(file);  
        }  
        MultipartEntity reqEntity = new MultipartEntity();  
        reqEntity.addPart("upfile", bin);  
        httppost.setEntity(reqEntity);  
        Log.i(TAG,"ִ��: " + httppost.getRequestLine());  
        HttpResponse response = httpclient.execute(httppost);  
        Log.i(TAG,"statusCode is " + response.getStatusLine().getStatusCode());  
        HttpEntity resEntity = response.getEntity();  
        Log.i(TAG,""+response.getStatusLine());  
        if (resEntity != null) {  
        	 String resultStr = EntityUtils.toString(resEntity);
        	 resEntity.consumeContent();  
        	 return resultStr;
        }  
        return null;  
    }  
	/**
	 * 
	 * @param urlpath
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	 public static String getByUrl( String urlpath,String encoding) throws Exception {
		 URL url = new URL(urlpath);
		 //ʵ����һ��HTTP���Ӷ���conn
		 HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		 //��������ʽΪGET������GET�ĸ�ʽ��Ҫע��
		 conn.setRequestMethod("GET");
		 //��������ʱ�䣬��ANDROID������ǲ��ó���10�롣���򽫱�ϵͳ���ա�
		 conn.setConnectTimeout(6*1000);
		 if(conn.getResponseCode()== 200){
			 InputStream inStream = conn.getInputStream();
			 byte[] data = readStream(inStream);
			 Log.e("ndk", new String(data));
			return new String(data,encoding);
			
		 }	
		 return null;
	 }	
	 
	public static  byte[] readStream(InputStream inStream) throws Exception{
		 //readStream����˴��ݽ�����������
		 //Ҫ����������������Ҫ����һ���������
		 //����һ���ֽ������͵��������ByteArrayOutputStream
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			//����һ��������buffer
			byte[] buffer = new byte[1024];
			int len= -1;
			//�����������ϵĶ������ŵ���������ȥ��ֱ������
			while((len=inStream.read(buffer))!=-1){
				//�������������ݲ��ϵ�д���ڴ���ȥ���߶���д
				outStream.write(buffer, 0, len);
			}
			outStream.close();
			inStream.close();
			//����������ֽ�����ķ�ʽ����
			return outStream.toByteArray();
		} 
	/**
	 * 
	 * @param uploadUrl
	 * @param srcPath
	 * @return
	 */
	public static String uploadFile(String uploadUrl,String srcPath) {
		String end = "\r\n";
		String twoHyphens = "--";
		String boundary = "******";
		try {
			URL url = new URL(uploadUrl);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url
					.openConnection();
			// ����ÿ�δ��������С��������Ч��ֹ�ֻ���Ϊ�ڴ治�����
			// �˷���������Ԥ�Ȳ�֪�����ݳ���ʱ����û�н����ڲ������ HTTP �������ĵ�����
			httpURLConnection.setChunkedStreamingMode(128 * 1024);// 128K
			// �������������
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setUseCaches(false);
			// ʹ��POST����
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
			httpURLConnection.setRequestProperty("Charset", "UTF-8");
			httpURLConnection.setRequestProperty("Content-Type",
					"multipart/form-data;boundary=" + boundary);
			DataOutputStream dos = new DataOutputStream(
					httpURLConnection.getOutputStream());
			dos.writeBytes(twoHyphens + boundary + end);
			dos.writeBytes("Content-Disposition: form-data; name=\"myFile\"; filename=\""
					+  URLEncoder.encode(srcPath.substring(srcPath.lastIndexOf("/") + 1))
					+ "\""
					+ end);
			dos.writeBytes(end);
			FileInputStream fis = new FileInputStream(srcPath);
			byte[] buffer = new byte[8192]; // 8k
			int count = 0;
			// ��ȡ�ļ�
			while ((count = fis.read(buffer)) != -1) {
				dos.write(buffer, 0, count);
			}
			fis.close();
			dos.writeBytes(end);
			dos.writeBytes(twoHyphens + boundary + twoHyphens + end);
			dos.flush();
			InputStream is = httpURLConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String result = br.readLine();
			dos.close();
			is.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
