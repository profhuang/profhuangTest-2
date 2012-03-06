package kddi.kddilabs.uig2;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

//Sample of HTTP in AsyncTask
public class myHttpGet extends AsyncTask<String, Void, Void> {
	
    private final HttpClient Client = new DefaultHttpClient();
    private String Content;
    private String Error = null;
   // private ProgressDialog Dialog = new ProgressDialog(profhuangTest2Activity.this);
    
    protected void onPreExecute() {
       // Dialog.setMessage("Downloading source..");
        //Dialog.show();
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
        //Dialog.dismiss();
        if (Error != null) {
            //Toast.makeText(profhuangTest2Activity.this, Error, Toast.LENGTH_LONG).show();        	
        } else {
            //Toast.makeText(profhuangTest2Activity.this, "Source: " + Content, Toast.LENGTH_LONG).show();
        	//Print out the page
        	Log.v("myHttpGet", Content);
        	//Content.writeTo(new FileOutputStream(f));
        }
    }
}

/* Old HTTP in 2.3:deprecated
public class myHttpGet {
	 public void executeHttpGet() throws Exception { //throws Exception
		    BufferedReader in = null;
	        try {
	        	HttpClient client = new DefaultHttpClient();
		    	HttpGet request = new HttpGet("http://www.google.co.jp");
		    	HttpResponse response = client.execute(request);
		    	
		    	in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		    	StringBuffer sb = new StringBuffer("");
		    	String line = "";
		    	String NL = System.getProperty("line.separator");
		    	
		    	while ((line = in.readLine()) != null) {
		    		sb.append(line + NL);
		    	}
		    	in.close();
		    	
		    	String page = sb.toString();
		    	System.out.println(page);
	         	
	        } catch (Exception e) {
		    	e.printStackTrace();
		    } finally {
		    	if (in != null) {
			    	try {
			    		in.close();
			    	} catch (IOException e) {
			    		e.printStackTrace();
			    	}
		    	}
		    }	    	            
	 }
}
*/