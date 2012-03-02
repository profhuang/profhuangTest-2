package kddi.kddilabs.uig2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class secondActivity  extends Activity {

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
        
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        int age = bundle.getInt("age"); 
        
        Toast.makeText(this, "Call by '" + name + "'" + ", Age is:" + age, Toast.LENGTH_SHORT).show();
    }
            
}
