package kddi.kddilabs.uig2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StartupReceiver extends BroadcastReceiver {  
  
    @Override  
    public void onReceive(Context context, Intent intent) {
    	
    	//Sample of Auto Boot Start
    	//receive Android.intent.action.BOOT_COMPLETED
        Intent i = new Intent(context, profhuangTest2Activity.class);  
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
        context.startActivity(i);  
    }  
  
}  
