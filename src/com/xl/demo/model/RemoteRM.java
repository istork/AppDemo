package com.xl.demo.model;


import com.xl.demo.GlobalConfig;
import com.xl.demo.http.HttpRequestService;
import com.xl.demo.remotehandle.IRemoteHandle;
import com.xl.demo.response.DefaultResponse;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;


/**
 * 联网类
 * */
public class RemoteRM {

	public Context context;
	/** 请求接口类 */
	private IRemoteHandle remoteHandle;
	private boolean busy = false, cancel = false; // 联网状态
	/** 参数实体类 */
	private RequestEntity re;
	private int s = 0;
	/** 封装的返回参数对象 */
	public DefaultResponse responseParma;

	public RemoteRM(Context context, IRemoteHandle _hr,
			DefaultResponse responseParma) {
		super();
		this.context = context;
		this.remoteHandle = _hr;
		this.responseParma = responseParma;

	}

	/**
	 * 联网调用方法
	 * */
	public void requestRemote(RequestEntity _re) {
		busy = true;
		re = _re;
		new httpConn(context).execute(_re);
		s = 0;
	}

	/**
	 * 取消
	 */
	public void cancel() {
		cancel = true;
		busy = false;
	}

	/**
	 * 状态
	 * 
	 * @return 是否正在联网
	 */
	public boolean busy() {
		return busy;
	}

	/**
	 * AsyncTask异步调用联网类
	 * */
	class httpConn extends AsyncTask<Object, Object, Object> {
		public Context context;

		public httpConn(Context context) {
			this.context = context;
		}

		byte[] in = null; // 联网请求返回来的数据

		@Override
		protected Object doInBackground(Object... params) {
			if (Looper.myLooper() == null) {
				Looper.prepare();
			}
			try {
				if(re.requestType.equals(GlobalConfig.POST)){
					in = new HttpRequestService(context).doRequest((RequestEntity) params[0]);
					System.out.println("doRequest............");
				}else if(re.requestType.equals(GlobalConfig.GET)){
					in = new HttpRequestService(context)
					.doGet((RequestEntity) params[0]);
					System.out.println("doGet..........");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			return in;
		}

		@Override
		protected void onPostExecute(Object result) {

			super.onPostExecute(result);
			busy = false; // 联网结束
			if (cancel)
				return; // 如果取消了, 直接return 出去
			if (result == null) {

				if (s < 3) {

					new httpConn(context).execute(re);
					s++;

				} else {
					if (remoteHandle != null) {
						/***/
						remoteHandle.process(new String("网络连接失败，请检查网络"));
					}
				}
			} else {// 如果返回值不为空

				try {
					if (remoteHandle != null) {
						remoteHandle
								.process(remoteHandle.parseResponseData(in));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
