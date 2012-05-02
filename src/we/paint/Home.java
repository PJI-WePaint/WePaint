package we.paint;

import java.util.ArrayList;
import java.util.List;

import we.paint.ColorPickerDialog.OnColorChangedListener;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Bundle;
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
        Communicator.home = this;
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
			Toast.makeText(this, getString(R.string.notConnected), Toast.LENGTH_LONG).show();
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
			Toast.makeText(this, getString(R.string.noMatches), Toast.LENGTH_LONG).show();
	}
    
    private boolean vocalMessage(String allSentences){
    	if(allSentences.indexOf(getString(R.string.vocalAdd))!=-1){
    		if(allSentences.indexOf(getString(R.string.vocalCircle))!=-1){
    			Communicator.minyDriver.message("Circle");
    			return true;
    		}
    		if(allSentences.indexOf(getString(R.string.vocalSquare))!=-1){
    			Communicator.minyDriver.message("Square");
    			return true;
    		}
    		if(allSentences.indexOf(getString(R.string.vocalSquare))!=-1){
    			Communicator.minyDriver.message("Rectangle");
    			return true;
    		}
    		if(allSentences.indexOf(getString(R.string.vocalEllipse))!=-1){
    			Communicator.minyDriver.message("Ellipse");
    			return true;
    		}
    	}
    	if(allSentences.indexOf(getString(R.string.vocalColor))!=-1){
    		if(allSentences.indexOf(getString(R.string.vocalBlue))!=-1){
    			colorChanged(Color.BLUE);
    			return true;
    		}
    		if(allSentences.indexOf(getString(R.string.vocalRed))!=-1){
    			colorChanged(Color.RED);
    			return true;
    		}
    		if(allSentences.indexOf(getString(R.string.vocalGreen))!=-1){
    			colorChanged(Color.GREEN);
    			return true;
    		}
    		if(allSentences.indexOf(getString(R.string.vocalBlack))!=-1){
    			colorChanged(Color.BLACK);
    			return true;
    		}
    		if(allSentences.indexOf(getString(R.string.vocalGray))!=-1){
    			colorChanged(Color.GRAY);
    			return true;
    		}
    		if(allSentences.indexOf(getString(R.string.vocalYellow))!=-1){
    			colorChanged(Color.YELLOW);
    			return true;
    		}
    	}if(allSentences.indexOf(getString(R.string.vocalRemove))!=-1){
    		Communicator.minyDriver.removeCurrent();
			return true;
		}
    	return false;
    }
    
    private void startAddActivity() {
		Intent myIntent = new Intent(Home.this, Add.class);
		Home.this.startActivity(myIntent);
	}
    
    private void startColorActivity() {
    	ColorPickerDialog colorPicker = new ColorPickerDialog(this, this, this.findViewById(R.id.colorPic).getDrawingCacheBackgroundColor());
    	colorPicker.show();
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
        	Toast.makeText(this, getString(R.string.noSpeechRecognition), Toast.LENGTH_LONG).show();
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