package com.wotingfm.activity.set.help;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.shenstec.activity.annotation.ViewInject;
import com.wotingfm.R;
import com.wotingfm.main.commonactivity.BaseActivity;
import com.wotingfm.utils.Utils;

public class HelpActivity extends BaseActivity implements OnClickListener{
	@ViewInject(id=R.id.webView,click=true)
	private WebView webview;
	@ViewInject(id=R.id.head_left_btn,click=true)
	private LinearLayout head_left_btn;
	private String url;
	private Dialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		dialog = Utils.Dialogph(this, "正在加载", dialog);
		setweb();

	}

	private void setweb() {
		url="https://www.baidu.com/";
		WebSettings setting = webview.getSettings();  
		setting.setJavaScriptEnabled(true);//支持js  
		webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);//解决缓存问题  
		webview.loadUrl(url);
		webview .setWebViewClient( new  WebViewClient() {
			public   boolean   shouldOverrideUrlLoading(WebView view, String url){
				// 使用当前的WebView加载页面
				view.loadUrl(url);
				return   true ;
			}
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
				if(dialog!=null){
					dialog.dismiss();
				}

			}
		});
		//		webview.setWebChromeClient(new MyWebChromeClient());
		webview.setWebChromeClient(new WebChromeClient());
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.head_left_btn:
			finish();
			break;

		default:
			break;
		}
	}
	
	public boolean dispatchKeyEvent(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.KEYCODE_BACK){  
			if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) { 
				finish();
			}  
			return true;  
		}  
		return super.dispatchKeyEvent(event);  
	}


	final  class MyWebChromeClient extends WebChromeClient{
		@Override
		public boolean onJsAlert(WebView view, String url, String message, final JsResult result) { 
			//message就是wave函数里alert的字符串，这样你就可以在android客户端里对这个数据进行处理
			result.confirm();        
			return true;  
		}}
}