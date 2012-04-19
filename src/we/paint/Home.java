package we.paint;

import we.paint.ColorPickerDialog.OnColorChangedListener;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Home extends Activity implements OnColorChangedListener {
	
	protected void onResume() {
		super.onStart();
	}

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.menu, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
           case R.id.connect:
              connectToWSE();
              return true;
           case R.id.quit:
               finish();
               return true;
        }
        return false;
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
    
    public void removeClicked(View view){
    	if (notConnected_ShowMessage())
			return;
    	Communicator.minyDriver.removeCurrent();
    }
    
    public void configClicked(View view){
		startConfigActivity();
    }
    
    void connectToWSE() {
    	SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		Communicator.urlServer = preferences.getString("serverUrl", "");
		Communicator.sessionName = preferences.getString("sessionName", "");
		System.out.println("com : "+ Communicator.urlServer + " pref"+ preferences.getString("serverUrl", ""));
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
		Toast.makeText(this, "Connected on the session "+Communicator.sessionName+" ("+Communicator.urlServer+")",
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
		/*Intent myIntent = new Intent(Home.this, ColorChange.class);
		Home.this.startActivity(myIntent);*/
    	new ColorPickerDialog(this, this, 000000000).show();
	}
    
    private void startMoveActivity() {
    	Intent myIntent = new Intent(Home.this, Move.class);
		Home.this.startActivity(myIntent);
    }
    
    private void startConfigActivity() {
		Intent myIntent = new Intent(Home.this, Configuration.class);
		Home.this.startActivity(myIntent);
	}

	public void colorChanged(int color) {
		if(color !=0){
			this.findViewById(R.id.colorPic).setBackgroundColor(color);
			Communicator.minyDriver.color("#"+Integer.toHexString(color).substring(2));
		}
	}
    
    
}