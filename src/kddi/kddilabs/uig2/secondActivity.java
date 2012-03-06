package kddi.kddilabs.uig2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class secondActivity  extends Activity implements OnInitListener {

	private static final int REQ_TTS_STATUS_CHECK = 0; //”CˆÓ‚Ì’l
	private TextToSpeech mTts;
	private Button btn3;
	private static final String TAG = "TTS Demo";
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        
        Button btn = (Button)this.findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		      	Intent intent = new Intent(secondActivity.this, profhuangTest2Activity.class);
		      	startActivity(intent);
		    }        
	    });
        
        Button btn2 = (Button)this.findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		      	Intent intent = new Intent(secondActivity.this, CheckBoxPreferenceActivity.class);
		      	startActivity(intent);
		    }        
	    });
        
        btn3 = (Button)this.findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	mTts.speak("What can I do for you?", TextToSpeech.QUEUE_ADD, null);
		    }        
	    });
        btn3.setEnabled(false);
        
        //Check to be sure that TTS exists and is okay to use
        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, REQ_TTS_STATUS_CHECK);
        
        //Show the Param
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        int age = bundle.getInt("age"); 
        
        Toast.makeText(this, "Call by '" + name + "'" + ", Age is:" + age, Toast.LENGTH_SHORT).show();
    }
    
    //startActivityForResult->Call onActivityResult();
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	
    		if (requestCode == REQ_TTS_STATUS_CHECK) {
	    		switch (resultCode) {
	    		case TextToSpeech.Engine.CHECK_VOICE_DATA_PASS:
		    		// TTS is up and running
		    		mTts = new TextToSpeech(this, this);
		    		Log.v(TAG, "Pico is installed okay");
		    		break;
	    		case TextToSpeech.Engine.CHECK_VOICE_DATA_BAD_DATA:
	    		case TextToSpeech.Engine.CHECK_VOICE_DATA_MISSING_DATA:
	    		case TextToSpeech.Engine.CHECK_VOICE_DATA_MISSING_VOLUME:
		    		// missing data, install it
		    		Log.v(TAG, "Need language stuff: " + resultCode);
		    		Intent installIntent = new Intent();
		    		installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
		    		startActivity(installIntent);
		    		break;
	    		case TextToSpeech.Engine.CHECK_VOICE_DATA_FAIL:
	    		default:
	    			Log.e(TAG, "Got a failure. TTS not available");
	    		}
    		} else {
    		// Got something else
    		}
    }
    
    //mTts = new TextToSpeech(this, this)->active onInit()
    public void onInit(int status) {
    	// Now that the TTS engine is ready, we enable the button
    	if( status == TextToSpeech.SUCCESS) {
    		btn3.setEnabled(true);
    	}
    }    
    
    @Override
    public void onPause() {
	    super.onPause();
	    
	    if( mTts != null)
	    	mTts.stop();
	 }
    
    @Override
    protected void onDestroy() {
        super.onDestroy(); 
        mTts.shutdown();
    } 
}
