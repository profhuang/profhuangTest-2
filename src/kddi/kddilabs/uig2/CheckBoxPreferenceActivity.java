package kddi.kddilabs.uig2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class CheckBoxPreferenceActivity extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Sample of Preference
		addPreferencesFromResource(R.xml.chkbox);
		//PreferenceManager.setDefaultValues(this, R.xml.flightoptions, false);
		
		//Sample of Get Preference for check out test 111
		getPreferenceManager();
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		boolean option = prefs.getBoolean("show_price_column_pref", false);
		boolean option2 = prefs.getBoolean("tomato_selection_pref", false);
		Toast.makeText(this, "Price is '" + option + "'" + ", Tomato is '" + option2 + "'", Toast.LENGTH_SHORT).show();
	}
}
