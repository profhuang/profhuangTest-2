package kddi.kddilabs.uig2;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//commit by git first 33445566778899
public class profhuangTest2Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TextView tv = (TextView)this.findViewById(R.id.textView1);
        tv.setText("swordwu");  
        
        Button btn = (Button)this.findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		      	Intent intent = new Intent(profhuangTest2Activity.this, secondActivity.class);
		      	Bundle bundle = new Bundle();
	            bundle.putString("name", "profhuang");
	            bundle.putInt("age", 35);
	            intent.putExtras(bundle);
		      	startActivity(intent);
		      	//profhuangTest2Activity.this.finish(); 
		    }        
	    });
        
        Button btn2 = (Button)this.findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	//new myHttpGet().execute("http://www.google.co.jp");
		    	new GrabURL().execute("http://www.google.co.jp");
		    }        
	    });
        
        //Sample of Combobox
        Spinner spinner = (Spinner)this.findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = 
        		ArrayAdapter.createFromResource(this, R.array.planets, android.R.layout.simple_spinner_item);
       	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //ListItem Style
       	spinner.setAdapter(adapter);     
       	
       	//ListView listview = (ListView)this.findViewById(R.id.listView1); 
        
       	//Sample of Contacts Loop Reading �F unComment to retrieve all contact name 
       	/*
       	 ContactsContract.Contacts.CONTENT_URI���IURL�^PhoneLookup.DISPLAY_NAME�s����
       	*/
       	/*
       	Cursor c = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
       			null, null, null, null); //managedQuery
       	int i = 1;
       	while (c.moveToNext()) { 
       		String name = c.getString(c.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));         
       		String number = c.getString(c.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));         
   		 	Toast.makeText(this, (i++) + ":" + name + ", " + number, Toast.LENGTH_SHORT).show(); //for debug
       	}
       	c.close();
       	*/
    }
    
    @Override
    //Sample of Menu
    public boolean onCreateOptionsMenu(Menu menu) {
	    super.onCreateOptionsMenu(menu);
	    
	    int base=Menu.FIRST;
	    menu.add(base, 1, 1, "Invoke WebBrowser");
	    menu.add(base, 2, 2, "Invoke Map");
	    menu.add(base, 3, 3, "Invoke Contact");
	    menu.add(base, 4, 4, "Invoke CMail");
	    menu.add(base, 5, 5, "Invoke Tel");
	    menu.add(base, 6, 6, "Invoke Search");
	    
	    //Sample of Submenu
	    /*	   
	    base=Menu.FIRST + 100;
	    SubMenu sm = menu.addSubMenu(base,base+1,Menu.NONE,"submenu");
	    sm.add(base,base+2,base+2,"sub item1");
	    sm.add(base,base+3,base+3, "sub item2");
	    sm.add(base,base+4,base+4, "sub item3");
	    //sm.setIcon(R.drawable.icon48x48_1);
	    */
	    
	    //Sample of Menu from XML
	    /*
	    MenuInflater inflater = getMenuInflater(); //from activity
	    inflater.inflate(R.menu.my_menu, menu);
	    */
	    
	    return true;
    }
    
    @Override
    //Sample of Invoke Web Browser/Map/Tel/Pick
    public boolean onOptionsItemSelected(MenuItem item) {
    	if (item.getItemId() == 1) { // == R.id.menu_dial
    		//Web
    		Intent intent = new Intent(Intent.ACTION_VIEW);
    		intent.setData(Uri.parse("http://www.google.com"));
    		this.startActivity(intent);
    	} else if (item.getItemId() == 2) {
    		//Map
    		Intent intent = new Intent(Intent.ACTION_VIEW);
    		intent.setData(Uri.parse("geo:0,0?z=4&q=business+near+city"));
    		this.startActivity(intent);
    	} else if (item.getItemId() == 3) {
        	//Contact : Sample of ACTION_Pick   
    		//			Return the Result in onActivityResult();
    		Intent intent = new Intent(Intent.ACTION_PICK);
    		int requestCode = 3;
    		intent.setData(ContactsContract.Contacts.CONTENT_URI);
    		startActivityForResult(intent, requestCode); 
    	}  else if (item.getItemId() == 4) {
    		//SMS
			Intent intent = new Intent(Intent.ACTION_VIEW);
			//Intent intent = new Intent(Intent.ACTION_SENDTO); //another way
			//intent.setData(Uri.parse("smsto:" + "09093482548"));
			intent.putExtra("address", "09093482548");
    		intent.putExtra("sms_body", "The SMS text");
			intent.setType("vnd.android-dir/mms-sms");
			this.startActivity(intent);
		}  else if (item.getItemId() == 5) {
			//Tel
			Intent intent = new Intent(Intent.ACTION_DIAL);
			intent.setData(Uri.parse("telto:" + "09093482548"));
			this.startActivity(intent);
		} else if (item.getItemId() == 6) {
			//Search
			Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
			intent.putExtra(SearchManager.QUERY,"KDDI Labs");
			startActivity(intent);
		} else {
	    	return super.onOptionsItemSelected(item);
	    }
	
    	
    	return true;
	}
	    
    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data)
    { 	
		//Sample of Pick Data Return From Contacts Activity 
    	super.onActivityResult(reqCode, resultCode, data); 
		    switch (reqCode) {
		         case (3) : //"Invoke Contact"    
			         if (resultCode == Activity.RESULT_OK) {       
			        	 Uri contactData = data.getData();       
			        	 Cursor c =  managedQuery(contactData, null, null, null, null);       
			        	 if (c.moveToFirst()) {         
			        		 String name = c.getString(c.getColumnIndexOrThrow(PhoneLookup.DISPLAY_NAME));
			        		 //String number = c.getString(c.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
			        		 //ContactsContract.Contacts.DISPLAY_NAME));         
			        		 // TODO Whatever you want to do with the selected contact name.       
			        		 Toast.makeText(this, "'" + name + "' is selected.", Toast.LENGTH_SHORT).show();
			        	 }     
			         }
			         break;
		         default:
		        	 break;
		    }
    } 
    
	/****************************************
	 * 
	 * Sample of innernal HTTP in AsyncTask: ok
	   Another sample is myHTTPGet outside
	 *
	 */
	private class GrabURL extends AsyncTask<String, Void, Void> {
	    private final HttpClient Client = new DefaultHttpClient();
	    private String Content;
	    private String Error = null;
	    private ProgressDialog Dialog = new ProgressDialog(profhuangTest2Activity.this);
	    
	    protected void onPreExecute() {
	        Dialog.setMessage("Downloading source..");
	        Dialog.show();
	    }
	
	    protected Void doInBackground(String... urls) {
	        try {
	            HttpGet httpget = new HttpGet(urls[0]);
	            ResponseHandler<String> responseHandler = new BasicResponseHandler();
	            Content = Client.execute(httpget, responseHandler);
	        } catch (ClientProtocolException e) {
	            Error = e.getMessage();
	            cancel(true);
	        } catch (IOException e) {
	            Error = e.getMessage();
	            cancel(true);
	        }
	        
	        return null;
	    }
	    
	    protected void onPostExecute(Void unused) {
	        Dialog.dismiss();
	        if (Error != null) {
	            Toast.makeText(profhuangTest2Activity.this, Error, Toast.LENGTH_LONG).show();
	        } else {
	            Toast.makeText(profhuangTest2Activity.this, "Source: " + Content, Toast.LENGTH_LONG).show();
	        }
	    }
	}
}

