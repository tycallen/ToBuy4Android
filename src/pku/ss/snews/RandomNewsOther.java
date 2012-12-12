package pku.ss.snews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RandomNewsOther extends RelativeLayout {
	
	private TextView tv;
	private TextView tv2;
	private ImageView iv;

	public RandomNewsOther(Context context) {
		//super(context);
		this(context,null);
		// TODO Auto-generated constructor stub
	}

	public RandomNewsOther(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.random_layout_2, this,true);
		tv = (TextView)findViewById(R.id.rl2_text1);
		tv2 = (TextView)findViewById(R.id.rl2_text2);
		iv = (ImageView)findViewById(R.id.rl2_img);
		// TODO Auto-generated constructor stub
	}

	public RandomNewsOther(Context context, AttributeSet attrs, int defStyle) {
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
    
    public void setTextView2Text(String text) {  
        tv2.setText(text);  
    } 
    
    public void setImageResource(int resId) {  
        iv.setBackgroundResource(resId);
    }  
}
