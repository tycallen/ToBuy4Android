package pku.ss.snews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RandomNews extends RelativeLayout {
	
	private TextView tv;
	private ImageView iv;
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public RandomNews(Context context) {
		//super(context);
		this(context,null);
		// TODO Auto-generated constructor stub
	}

	public RandomNews(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.random_layout_1, this,true);
		tv = (TextView)findViewById(R.id.rl1_text);
		iv = (ImageView)findViewById(R.id.rl1_img);
		// TODO Auto-generated constructor stub
	}

	public RandomNews(Context context, AttributeSet attrs, int defStyle) {
		//super(context, attrs, defStyle);
		this(context,attrs);
		// TODO Auto-generated constructor stub
	}
	
	 /** 
     * 设置显示的文字 
     */  
    public void setTextViewText(String text) {  
        tv.setText(text);  
    }  
    
    public void setImageResource(int resId) {  
        iv.setBackgroundResource(resId);
    }  
}
