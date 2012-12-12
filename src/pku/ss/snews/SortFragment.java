package pku.ss.snews;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class SortFragment extends Fragment {

	private WebView mWebView;       
    private String url;
    private ProgressBar progressBar;
    
    static SortFragment newInstance() {
    	SortFragment newFragment = new SortFragment();
        return newFragment;
    }
       
    public void onCreate(Bundle bundle) {       
        super.onCreate(bundle);         
        url = "http://3g.sina.com.cn/nc.php";
        
    }
    
    

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
    	
    	View view = inflater.inflate(R.layout.sort_fragment, container, false);
    	
    	progressBar = (ProgressBar)view.findViewById(R.id.sort_ProgressBar);
  
        mWebView = (WebView) view.findViewById(R.id.sort_webview);       
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
       
		return view;
	}
    
}
