package pku.ss.snews;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerDialog extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND, WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        //setContentView(R.layout.customer_dialog);
        
        //ImageView settingimg = (ImageView)findViewById(R.id.customer_dialog);
        //settingimg.setOnClickListener(new ImageView.OnClickListener(){
        	//public void onClick(View v) {
        		/*Intent intent = new Intent(CustomerDialog.this,SettingsActivity.class);
        		CustomerDialog.this.startActivity(intent);
        		CustomerDialog.this.finish();*/
        		//Toast.makeText(CustomerDialog.this, "当前已是最新版本", Toast.LENGTH_SHORT).show();
        	//}
        //});
        Toast.makeText(CustomerDialog.this, "当前已是最新版本", Toast.LENGTH_SHORT).show();
        CustomerDialog.this.finish();
	}
	

}
