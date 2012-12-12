package pku.ss.snews;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class DetailPage extends Activity {

	private WebView mWebView;       
    private Handler mHandler = new Handler();    
    private ImageView imageView;
    private String url;
       
    public void onCreate(Bundle bundle) {       
        super.onCreate(bundle);       
        
        Bundle savedBundle = getIntent().getExtras();
        url = savedBundle.getString("URL");
        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND, WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        setContentView(R.layout.detail_page);  
        
        
        
        mWebView = (WebView) findViewById(R.id.detail_page_webview);       
        WebSettings webSettings = mWebView.getSettings();
        
        //允许调用JS
        webSettings.setJavaScriptEnabled(true);       
       /* mWebView.addJavascriptInterface(new Object() {       
            public void clickOnAndroid() {       
                mHandler.post(new Runnable() {       
                    public void run() {       
                        mWebView.loadUrl("javascript:wave()");       
                    }       
                });       
            }       
        }, "demo"); */      
        
        //保证浏览时在当前页面中跳转
        mWebView.setWebViewClient(new WebViewClient() {  
        	
         @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {  
                view.loadUrl(url);  
                return true;  
            }  
        });  
        
        //页面加载进度
        mWebView.setWebChromeClient(new WebChromeClient() {  
        	
            public void onProgressChanged(WebView view, int progress) {  
            	ProgressBar progressBar = (ProgressBar)findViewById(R.id.dp_ProgressBar);
            	progressBar.setVisibility(ProgressBar.VISIBLE);
            	progressBar.setProgress(progress);
                


                if (progress == 100) {  
                	progressBar.setVisibility(ProgressBar.GONE);
                }  
            }  
        }); 
        
        //设置页面缩放至当前大小
        mWebView.getSettings().setUseWideViewPort(true); 
        mWebView.getSettings().setLoadWithOverviewMode(true);
        
        //mWebView.loadUrl("file:///android_asset/demo.html");     
        mWebView.loadUrl(url);  
       
        imageView = (ImageView)findViewById(R.id.dp_close);
        imageView.setOnClickListener(new TextView.OnClickListener(){
        	public void onClick(View v) {
        		DetailPage.this.finish();
        	}
        });
        
    }

    //点击返回键在网页中返回
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
		if (mWebView.canGoBack() && event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {  
			mWebView.goBack();  
	        return true;  
		} 
		
		return super.onKeyDown(keyCode, event);
	}
    
}
