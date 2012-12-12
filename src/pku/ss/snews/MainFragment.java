package pku.ss.snews;


import java.util.Random;



import android.app.Activity;
import android.content.Context;
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
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainFragment extends Fragment{
    private static final String TAG = "TestFragment";
    private String hello;// = "hello android";
    private String defaultHello = "default value";
    private int flag;
    
    

    static MainFragment newInstance(String s,int f) {
    	MainFragment newFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString("hello", s);
        bundle.putInt("flag", f);
        newFragment.setArguments(bundle);
        return newFragment;

    }

   

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "TestFragment-----onCreate");
        Bundle args = getArguments();
        hello = args != null ? args.getString("hello") : defaultHello;
        flag = args != null ? args.getInt("flag") : 1;
        
       
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        Log.d(TAG, "TestFragment-----onCreateView");
        
        View view;
        TextView viewhello;
        switch(flag){
        case 1:
        	view = inflater.inflate(R.layout.random_fragment, container, false);
            viewhello = (TextView) view.findViewById(R.id.tv_lay1);
            viewhello.setText(hello);
            break;
        case 2:
        	view = inflater.inflate(R.layout.sort_fragment, container, false);
            break;
            
        case 3:
        	view = inflater.inflate(R.layout.function_fragment, container, false);
            viewhello = (TextView) view.findViewById(R.id.tv_lay3);
            viewhello.setText(hello);
            break;
        default:
        	view = inflater.inflate(R.layout.function_fragment, container, false);
            viewhello = (TextView) view.findViewById(R.id.tv_lay1);
            viewhello.setText(hello);
        
        }
        
        
        return view;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "TestFragment-----onDestroy");
    }

	

}
