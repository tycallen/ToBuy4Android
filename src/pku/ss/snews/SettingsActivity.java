package pku.ss.snews;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.Window;

public class SettingsActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		addPreferencesFromResource(R.xml.setting_preference);
	}

}
