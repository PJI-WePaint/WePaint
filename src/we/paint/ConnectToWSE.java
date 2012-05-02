package we.paint;

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
	}
	
}
