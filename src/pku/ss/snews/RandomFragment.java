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
	
	//ҡһҡ��ز���
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
		sensorManager = (SensorManager)main.getSystemService(Context.SENSOR_SERVICE); // ��ȡ�������������
		vibrator = (Vibrator)main.getSystemService(Context.VIBRATOR_SERVICE); // ��
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
	        		mBundle.putString("URL", rn.getUrl());//ѹ������   
	        		intent.putExtras(mBundle); 
	        		RandomFragment.this.startActivity(intent);
	        	}
	        };
	        
	        viewlb = (RandomNews)view.findViewById(R.id.viewlb);
	        viewlb.setImageResource(R.drawable.randomnews1);
	        viewlb.setTextViewText("��Ů���Զ�������ä");
	        viewlb.setUrl("http://3g.sina.com.cn/nc.php");
	        viewlb.setOnClickListener(newsClickListener);
	        
	        
	        viewrb = (RandomNews)view.findViewById(R.id.viewrb);
	        viewrb.setImageResource(R.drawable.randomsquare1);
	        viewrb.setTextViewText("�ɰ���С��");
	        
	        //viewm = (RandomNews)view.findViewById(R.id.viewm);
	        viewm = (RandomNewsOther)view.findViewById(R.id.viewm);
	        viewm.setImageResource(R.drawable.randomnews3);
	        viewm.setTextViewText("���������̵�");
	        viewm.setTextView2Text("  �����������ǳ�֮�࣬��Ȼ�����ԡ�����Ϊ˳�������������ԡ��ȡ�Ϊ������������������һ��������̵�Ķ�������ɣ�");
	        
	        
	        viewlt = (RandomNews)view.findViewById(R.id.viewlt);
	        viewlt.setImageResource(R.drawable.randomsquare2);
	        viewlt.setTextViewText("�ڲٿ��Գ���");
	        
	        viewrt = (RandomNews)view.findViewById(R.id.viewrt);
	        viewrt.setImageResource(R.drawable.randomnews2);
	        viewrt.setTextViewText("�����ֶ�����K-ON��ר���ų�");
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
	    
	        viewhello.setText("����˴���ҡ����Ļ");
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
		if (sensorManager != null) {// ע�������
			sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
			// ��һ��������Listener���ڶ������������ô��������ͣ�����������ֵ��ȡ��������Ϣ��Ƶ��
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
		
		//������ڶ����򲻼����������¼�
		if(fallin.hasStarted()&&!fallin.hasEnded()) return;

		//Log.i("uri", "x�᷽����������ٶ�" + x + ";y�᷽����������ٶ�" + y + ";z�᷽����������ٶ�" + z);
		// һ����������������������ٶȴﵽ40�ʹﵽ��ҡ���ֻ���״̬��
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
