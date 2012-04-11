package we.paint;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;

public class Home extends Activity {
	
	protected void onResume() {
		super.onStart();
	}

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    private boolean notConnected_ShowMessage() {
		boolean connected = Communicator.minyDriver!=null;
		if (!connected)
			Toast.makeText(this, "You are not connected to a WSE session. See Menu button",
					Toast.LENGTH_LONG).show();
		return !connected;
	}
    
    public void addClicked(View view){
    	if (notConnected_ShowMessage())
			return;
		startAddActivity();
    }
    
    public void colorClicked(View view){
    	if (notConnected_ShowMessage())
			return;
		startColorActivity();
    }
    
    public void moveClicked(View view){
    	if (notConnected_ShowMessage())
			return;
		startMoveActivity();
    }
    
    public void microClicked(View view){
    	if (notConnected_ShowMessage())
			return;
		//startMicroActivity();
    	Toast.makeText(this, "Not implemented yet", Toast.LENGTH_LONG).show();
    }
    
    public void configClicked(View view){
		startConfigActivity();
    }
    
    void connectToWSE() {
		SharedPreferences preferences = getPreferences(0);
		Communicator.urlServer = preferences.getString("serverUrl", "");
		Communicator.sessionName = preferences.getString("sessionName", "");
		if (Communicator.urlServer.equals("")) {
			Toast.makeText(this, "Url of server is not indicated. Please set it in preferences",
					Toast.LENGTH_LONG).show();
			return;
		}
		if (Communicator.sessionName.equals("")) {
			Toast.makeText(this, "Session name is not indicated. Please set it in preferences",
					Toast.LENGTH_LONG).show();
			return;
		}	
		Communicator.location = preferences.getString("location", "");
		Communicator.locationParams = preferences.getString("locationParameters", "");
		Toast.makeText(this, "Connected with value "+Communicator.urlServer+" , "+Communicator.sessionName+" , "+Communicator.location+" , "+Communicator.locationParams,
				Toast.LENGTH_LONG).show();
		
		Vibrator vibrator = (Vibrator) getSystemService(android.content.Context.VIBRATOR_SERVICE);
		vibrator.vibrate(300);
		
		Communicator.initMinyDriver(vibrator);
		Communicator.minyDriver.start();
	}
    
    private void startAddActivity() {
		Intent myIntent = new Intent(Home.this, Add.class);
		Home.this.startActivity(myIntent);
	}
    
    private void startColorActivity() {
		Intent myIntent = new Intent(Home.this, Color.class);
		Home.this.startActivity(myIntent);
	}
    
    private void startMoveActivity() {
    	Intent myIntent = new Intent(Home.this, Move.class);
		Home.this.startActivity(myIntent);
    }
    
    private void startConfigActivity() {
		Intent myIntent = new Intent(Home.this, Configuration.class);
		Home.this.startActivity(myIntent);
	}
    
    
}