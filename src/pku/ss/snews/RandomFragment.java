package pku.ss.snews;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RandomFragment extends Fragment implements SensorEventListener {
	
	//摇一摇相关参数
    Random r = new Random();
	private Vibrator vibrator;
	private static final int SENSOR_SHAKE = 10;
	private SensorManager sensorManager;
	private MediaPlayer player;
	private Activity main;
	private RandomNews viewlb,viewrb,viewlt,viewrt;
	private RandomNewsOther viewm;
	private int currentView=1;
	private Animation fallin;
	private Animation flyout;
	
	static RandomFragment newInstance() {
		RandomFragment newFragment = new RandomFragment();
        return newFragment;
    }
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		main = this.getActivity();
		sensorManager = (SensorManager)main.getSystemService(Context.SENSOR_SERVICE); // 获取传感器管理服务
		vibrator = (Vibrator)main.getSystemService(Context.VIBRATOR_SERVICE); // 震动
		player = MediaPlayer.create(main, R.raw.shake);
		
	}
	
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
	 
	        
	        View view;
	        final TextView viewhello;
	      
	        view = inflater.inflate(R.layout.random_fragment, container, false);
	        viewhello = (TextView) view.findViewById(R.id.tv_lay1);
	        
	        OnClickListener newsClickListener =  new OnClickListener(){
	        	public void onClick(View v) {
	        		RandomNews rn = (RandomNews)v;
	        		Intent intent = new Intent(getActivity(),DetailPage.class);
	        		Bundle mBundle = new Bundle();   
	        		mBundle.putString("URL", rn.getUrl());//压入数据   
	        		intent.putExtras(mBundle); 
	        		RandomFragment.this.startActivity(intent);
	        	}
	        };
	        
	        viewlb = (RandomNews)view.findViewById(R.id.viewlb);
	        viewlb.setImageResource(R.drawable.randomnews1);
	        viewlb.setTextViewText("少女沉迷动漫致眼盲");
	        viewlb.setUrl("http://3g.sina.com.cn/nc.php");
	        viewlb.setOnClickListener(newsClickListener);
	        
	        
	        viewrb = (RandomNews)view.findViewById(R.id.viewrb);
	        viewrb.setImageResource(R.drawable.randomsquare1);
	        viewrb.setTextViewText("可爱的小熊");
	        
	        //viewm = (RandomNews)view.findViewById(R.id.viewm);
	        viewm = (RandomNewsOther)view.findViewById(R.id.viewm);
	        viewm.setImageResource(R.drawable.randomnews3);
	        viewm.setTextViewText("超萌萝莉盘点");
	        viewm.setTextView2Text("  动漫界的萝莉非常之多，自然不是以“美”为顺序排名，而是以“萌”为主，下面让我们来看一看由玩家盘点的动漫萝莉吧！");
	        
	        
	        viewlt = (RandomNews)view.findViewById(R.id.viewlt);
	        viewlt.setImageResource(R.drawable.randomsquare2);
	        viewlt.setTextViewText("节操可以吃吗");
	        
	        viewrt = (RandomNews)view.findViewById(R.id.viewrt);
	        viewrt.setImageResource(R.drawable.randomnews2);
	        viewrt.setTextViewText("著名乐队团体K-ON新专辑放出");
	        viewrt.setUrl("http://www.baidu.com");
	        viewrt.setOnClickListener(newsClickListener);
	        
	       
	        
	        viewlb.setVisibility(View.INVISIBLE);
	        viewrb.setVisibility(View.INVISIBLE);
	        viewm.setVisibility(View.INVISIBLE);
	        viewlt.setVisibility(View.INVISIBLE);
	        viewrt.setVisibility(View.INVISIBLE);
	        
	        currentView =1;
	        
	        fallin = AnimationUtils.loadAnimation(main, R.anim.drop_down);
	        fallin.setInterpolator(AnimationUtils.loadInterpolator(main,android.R.anim.bounce_interpolator));
	        flyout = AnimationUtils.loadAnimation(main, R.anim.fly_out);
	        flyout.setInterpolator(AnimationUtils.loadInterpolator(main, android.R.anim.accelerate_interpolator));
	    
	        viewhello.setText("点击此处或摇晃屏幕");
	        viewhello.setOnClickListener(new TextView.OnClickListener(){
	        	public void onClick(View v) {
	        		startFallIn();
	        	}
	        });
	        return view;

	    }
	 
	public void startFallIn(){
		
		if(!(fallin.hasStarted()&&!fallin.hasEnded())){
    		switch(currentView){
    		case 1:
    			
    			viewlb.startAnimation(fallin);
        		viewlb.setVisibility(View.VISIBLE);
        		currentView++;
        		break;
    		case 2:
    			
    			viewrb.startAnimation(fallin);
        		viewrb.setVisibility(View.VISIBLE);
        		currentView++;
        		break;
    		case 3:
    			
    			viewm.startAnimation(fallin);
        		viewm.setVisibility(View.VISIBLE);
        		currentView++;
        		break;
    		case 4:
    			
    			viewlt.startAnimation(fallin);
        		viewlt.setVisibility(View.VISIBLE);
        		currentView++;
        		break;
    		case 5:
    			
    			viewrt.startAnimation(fallin);
        		viewrt.setVisibility(View.VISIBLE);
        		currentView++;
        		break;
    		case 6:
    			
    			viewlb.startAnimation(flyout);
    			viewlb.setVisibility(View.INVISIBLE);
    			viewrb.startAnimation(flyout);
    	        viewrb.setVisibility(View.INVISIBLE);
    	        viewm.startAnimation(flyout);
    	        viewm.setVisibility(View.INVISIBLE);
    	        viewlt.startAnimation(flyout);
    	        viewlt.setVisibility(View.INVISIBLE);
    	        viewrt.startAnimation(flyout);
    	        viewrt.setVisibility(View.INVISIBLE);
    	        currentView = 1;
        		break;
        	default:
        		
        		currentView = 1;
        		break;
    		}
		}
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		sensorManager.unregisterListener(this);
		super.onPause();
		player.stop();
		Log.i("url", "player.stop()");
	}

	@Override
	public void onStop() {
		sensorManager.unregisterListener(this);
		// TODO Auto-generated method stub
		super.onStop();
	}
	
	Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case SENSOR_SHAKE:
				startFallIn();
				break;
			}
		}
	};

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (sensorManager != null) {// 注册监听器
			sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
			// 第一个参数是Listener，第二个参数是所得传感器类型，第三个参数值获取传感器信息的频率
		}
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		float[] values = event.values;
		float x = values[0];
		float y = values[1];
		float z = values[2];
		
		//如果正在动画则不监听传感器事件
		if(fallin.hasStarted()&&!fallin.hasEnded()) return;

		//Log.i("uri", "x轴方向的重力加速度" + x + ";y轴方向的重力加速度" + y + ";z轴方向的重力加速度" + z);
		// 一般在这三个方向的重力加速度达到40就达到了摇晃手机的状态。
		int medumValue = 19;
		if (Math.abs(x) > medumValue || Math.abs(y) > medumValue || Math.abs(z) > medumValue) {
			try {
				player.start();
				player.setOnCompletionListener(new OnCompletionListener() {

					public void onCompletion(MediaPlayer mp) {
						// TODO Auto-generated method stub
						player.pause();
						player.seekTo(0);
					}
				});
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			vibrator.vibrate(500);
			Message msg = new Message();
			msg.what = SENSOR_SHAKE;
			handler.sendMessage(msg);
		}
	}

}
