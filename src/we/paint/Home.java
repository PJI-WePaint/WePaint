package we.paint;

import java.util.ArrayList;
import java.util.List;

import we.paint.ColorPickerDialog.OnColorChangedListener;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Home extends Activity implements OnColorChangedListener {
	
	private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;
	
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
        	   new ConnectToWSE(this);
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
		startMicroActivity();
    	//Toast.makeText(this, "Not implemented yet", Toast.LENGTH_LONG).show();
    }
    
    public void removeClicked(View view){
    	if (notConnected_ShowMessage())
			return;
    	Communicator.minyDriver.removeCurrent();
    }
    
    public void configClicked(View view){
		startConfigActivity();
    }
    
    public void onActivityResult(int reqCode, int resCode, Intent intent) {
	    if (reqCode == VOICE_RECOGNITION_REQUEST_CODE && resCode == RESULT_OK) {
            vocalRecognisedTextToWSE(intent);
        }
	}
    
    private void vocalRecognisedTextToWSE(Intent intent) {
		// Fill the list view with the strings the recognizer thought it could have heard
		ArrayList<String> matches = intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
		String allSentences="";
		for (String sentence : matches) {
			allSentences+=sentence+" ";
		}
		System.out.println(allSentences);
		if(!vocalMessage(allSentences))
			Toast.makeText(this, "No matches, please retry", Toast.LENGTH_LONG).show();
		//Communicator.minyDriver.textRecognised(allSentences);
	}
    
    private boolean vocalMessage(String allSentences){
    	if(allSentences.indexOf("ajouter")!=-1){
    		if(allSentences.indexOf("cercle")!=-1){
    			Communicator.minyDriver.message("Circle");
    			return true;
    		}
    		if(allSentences.indexOf("carré")!=-1){
    			Communicator.minyDriver.message("Square");
    			return true;
    		}
    		if(allSentences.indexOf("rectangle")!=-1){
    			Communicator.minyDriver.message("Rectangle");
    			return true;
    		}
    		if(allSentences.indexOf("ellipse")!=-1){
    			Communicator.minyDriver.message("Ellipse");
    			return true;
    		}
    	}
    	if(allSentences.indexOf("couleur")!=-1){
    		if(allSentences.indexOf("bleu")!=-1){
    			colorChanged(Color.BLUE);
    			return true;
    		}
    		if(allSentences.indexOf("rouge")!=-1){
    			colorChanged(Color.RED);
    			return true;
    		}
    		if(allSentences.indexOf("verte")!=-1){
    			colorChanged(Color.GREEN);
    			return true;
    		}
    		if(allSentences.indexOf("noir")!=-1){
    			colorChanged(Color.BLACK);
    			return true;
    		}
    		if(allSentences.indexOf("grise")!=-1){
    			colorChanged(Color.GRAY);
    			return true;
    		}
    		if(allSentences.indexOf("jaune")!=-1){
    			colorChanged(Color.YELLOW);
    			return true;
    		}
    	}if(allSentences.indexOf("supprimer")!=-1){
    		Communicator.minyDriver.removeCurrent();
			return true;
		}
    	return false;
    }
    
    /*void connectToWSE() {
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
	}*/
    
    private void startAddActivity() {
		Intent myIntent = new Intent(Home.this, Add.class);
		Home.this.startActivity(myIntent);
	}
    
    private void startColorActivity() {
		/*Intent myIntent = new Intent(Home.this, ColorChange.class);
		Home.this.startActivity(myIntent);*/
    	new ColorPickerDialog(this, this, this.findViewById(R.id.colorPic).getDrawingCacheBackgroundColor()).show();
	}
    
    private void startMoveActivity() {
    	Intent myIntent = new Intent(Home.this, Move.class);
		Home.this.startActivity(myIntent);
    }
    
    private void startMicroActivity() {
    	PackageManager pm = getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(
                new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if (activities.size() == 0) {
        	Toast.makeText(this, "Speech recognition is not installed",
					Toast.LENGTH_LONG).show();
        }
        
        try {
        	Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say what you want");
            startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
        	
       } catch (ActivityNotFoundException e) {
         
       }
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