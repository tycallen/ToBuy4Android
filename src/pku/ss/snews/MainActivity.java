package pku.ss.snews;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * 
 * @source code from avenwu
 * @author Ray
 * 
 */
public class MainActivity extends FragmentActivity {
    private static final String TAG = "MainActivity";
    private ViewPager mPager;
    private ArrayList<Fragment> fragmentsList;
    private ImageView ivBottomLine,settingimg;
    private TextView tvTab1, tvTab2, tvTab3;
    private LinearLayout theme;

    private int currIndex = 0;
    private int bottomLineWidth;
    private int offset = 0;
    private int position_one;
    private int position_two;
    private int position_three;
    private Resources resources;
    
    private int flag=0;
    
  
    //add some change

		
	
    @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		SharedPreferences prefs =PreferenceManager.getDefaultSharedPreferences(this) ;
		String themeColor = prefs.getString("theme","blue");
        System.out.println(themeColor);//默认蓝色主题

        if(themeColor.equalsIgnoreCase("blue"))
        	theme.setBackgroundResource(R.drawable.top_theme_blue);
        else if(themeColor.equalsIgnoreCase("red"))
        	theme.setBackgroundResource(R.drawable.top_theme_red);
        else if(themeColor.equalsIgnoreCase("black"))
        	theme.setBackgroundResource(R.drawable.top_theme_black);
        else if(themeColor.equalsIgnoreCase("white"))
        	theme.setBackgroundResource(R.drawable.top_theme_white);
        
        
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		SharedPreferences prefs =PreferenceManager.getDefaultSharedPreferences(this) ;
				boolean isStartIntroduce = prefs.getBoolean("help", true);
				System.out.println(isStartIntroduce);
				if(isStartIntroduce&& flag ==0){
					Editor e = prefs.edit();
					e.putBoolean("help", false);
					e.commit();
					
					Intent intent = new Intent(MainActivity.this,IntroduceActivity.class);
		    		MainActivity.this.startActivity(intent);
		    		MainActivity.this.finish();
		    		return;
				}
		flag = 1;
		
		
		
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        resources = getResources();
        InitWidth();
        InitTextView();
        InitViewPager();
        
        settingimg = (ImageView)findViewById(R.id.imageView1);
        settingimg.setOnClickListener(new ImageView.OnClickListener(){
        	public void onClick(View v) {
        		Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
        		MainActivity.this.startActivity(intent);
        		
        	}
        });
        
        theme = (LinearLayout)findViewById(R.id.theme);
        
        
        
		
    }

    private void InitTextView() {
        tvTab1 = (TextView) findViewById(R.id.tv_tab_1);
        tvTab2 = (TextView) findViewById(R.id.tv_tab_2);
        tvTab3 = (TextView) findViewById(R.id.tv_tab_3);
     

        tvTab1.setOnClickListener(new MyOnClickListener(0));
        tvTab2.setOnClickListener(new MyOnClickListener(1));
        tvTab3.setOnClickListener(new MyOnClickListener(2));
      
    }

    private void InitViewPager() {
        mPager = (ViewPager) findViewById(R.id.vPager);
        fragmentsList = new ArrayList<Fragment>();
        //LayoutInflater mInflater = getLayoutInflater();
        //View activityView = mInflater.inflate(R.layout.lay1, null);

        //Fragment activityfragment = MainFragment.newInstance("手气不错，摇一摇获取随机推荐的新闻",1);
        //Fragment activityfragment = RandomFragment.newInstance();
        Fragment activityfragment = QueryRequest.newInstance();
        //Fragment groupFragment = MainFragment.newInstance("分类浏览最全的新闻",2);
        Fragment groupFragment = SortFragment.newInstance();
        Fragment friendsFragment=MainFragment.newInstance("查看我的收藏",3);

        fragmentsList.add(activityfragment);
        fragmentsList.add(groupFragment);
        fragmentsList.add(friendsFragment);
        
        mPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(), fragmentsList));
        mPager.setCurrentItem(0);
        mPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    private void InitWidth() {
        ivBottomLine = (ImageView) findViewById(R.id.iv_bottom_line);
        bottomLineWidth = ivBottomLine.getLayoutParams().width;
        Log.d(TAG, "cursor imageview width=" + bottomLineWidth);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        offset = (int) ((screenW / 3.0 - bottomLineWidth) / 2);
        Log.i("MainActivity", "offset=" + offset);

        position_one = (int) (screenW / 3.0);
        position_two = position_one * 2;
        position_three = position_one * 3;
    }

    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        public void onClick(View v) {
            mPager.setCurrentItem(index);
        }
    };

    public class MyOnPageChangeListener implements OnPageChangeListener {

        public void onPageSelected(int arg0) {
            Animation animation = null;
            switch (arg0) {
            case 0:
                if (currIndex == 1) {
                    animation = new TranslateAnimation(position_one, 0, 0, 0);
                    tvTab2.setTextColor(resources.getColor(R.color.lightwhite));
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(position_two, 0, 0, 0);
                    tvTab3.setTextColor(resources.getColor(R.color.lightwhite));
                } 
                tvTab1.setTextColor(resources.getColor(R.color.white));
                break;
            case 1:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, position_one, 0, 0);
                    tvTab1.setTextColor(resources.getColor(R.color.lightwhite));
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(position_two, position_one, 0, 0);
                    tvTab3.setTextColor(resources.getColor(R.color.lightwhite));
                } 
                tvTab2.setTextColor(resources.getColor(R.color.white));
                break;
            case 2:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, position_two, 0, 0);
                    tvTab1.setTextColor(resources.getColor(R.color.lightwhite));
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(position_one, position_two, 0, 0);
                    tvTab2.setTextColor(resources.getColor(R.color.lightwhite));
                } 
                tvTab3.setTextColor(resources.getColor(R.color.white));
                break;
           
            }
            currIndex = arg0;
            animation.setFillAfter(true);
            animation.setDuration(300);
            ivBottomLine.startAnimation(animation);
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageScrollStateChanged(int arg0) {
        }
        
       
    }

    
}