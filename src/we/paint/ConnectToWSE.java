package we.paint;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class ConnectToWSE {

	public ConnectToWSE(Activity activity){
	    	SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
			Communicator.urlServer = preferences.getString("serverUrl", "");
			Communicator.sessionName = preferences.getString("sessionName", "");
			if (Communicator.urlServer.equals("")) {
				Toast.makeText(activity, activity.getString(R.string.noServerURL),
						Toast.LENGTH_LONG).show();
				return;
			}
			if (Communicator.sessionName.equals("")) {
				Toast.makeText(activity, activity.getString(R.string.noSessionName),
						Toast.LENGTH_LONG).show();
				return;
			}	
			Communicator.location = preferences.getString("location", "");
			Communicator.locationParams = preferences.getString("locationParameters", "");
			Toast.makeText(activity, activity.getString(R.string.connected)+" "+Communicator.sessionName+" ("+Communicator.urlServer+")",
					Toast.LENGTH_LONG).show();
			
			Vibrator vibrator = (Vibrator) activity.getSystemService(android.content.Context.VIBRATOR_SERVICE);
			vibrator.vibrate(300);
			
			Communicator.initMinyDriver(vibrator);
			Communicator.minyDriver.start();

			String response ="";
			try{
				HttpClient httpclient = new DefaultHttpClient();
				HttpGet request = new HttpGet("http://"+Communicator.urlServer+"/paint/BDD/user.php?action=get_last_id&session="+
						Communicator.sessionName.split("_")[1]);
		        
		        HttpResponse httpresponse = httpclient.execute(request);
		        
		        BufferedReader in = null;
		        in = new BufferedReader(new InputStreamReader(httpresponse.getEntity().getContent()));
		        JSONObject resp = new JSONObject(in.readLine());
		        in.close();
		        response = resp.getString("last_id");
				
			}
			catch(ClientProtocolException e){ System.out.println("ClientProtocolException");}
			catch(java.io.IOException g){ System.out.println("IOException");}
			catch (JSONException e) { System.out.println("JSONException");}
			
			Communicator.id = Integer.parseInt(response)+1;
			Communicator.minyDriver.joinSessionPaint(Communicator.id);
	}
	
}
