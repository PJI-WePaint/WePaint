package we.paint;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Configuration extends Activity {

	private static final int REQUEST_SCAN = 0;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuration);
        ((EditText)findViewById(R.id.configurationUrl)).setText(Communicator.urlServer);
		((EditText)findViewById(R.id.configurationSession)).setText(Communicator.sessionName);
		((EditText)findViewById(R.id.configurationLocation)).setText(Communicator.location);
		((EditText)findViewById(R.id.configurationLocationParams)).setText(Communicator.locationParams);
        behavioriseApplyButton();
        behavioriseScanButton();
	}
	
	protected void onStop() {
		super.onStop();
	}
	
	protected void onResume() {
		super.onResume();
	}
	
	private void behavioriseApplyButton() {
		Button apply=(Button) findViewById(R.id.buttonConfigApply);
		apply.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				apply();
				Configuration.this.finish();
			}
        });
	}
	
	public void apply () {
		Communicator.urlServer=((EditText)findViewById(R.id.configurationUrl)).getText()+"";
		Communicator.sessionName=((EditText)findViewById(R.id.configurationSession)).getText()+"";
		Communicator.location=((EditText)findViewById(R.id.configurationLocation)).getText()+"";
		Communicator.locationParams=((EditText)findViewById(R.id.configurationLocationParams)).getText()+"";
		Communicator.initMinyDriver(null);
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		Editor editor = preferences.edit();
		editor.putString("serverUrl", ((EditText)findViewById(R.id.configurationUrl)).getText()+"");
		editor.putString("sessionName", ((EditText)findViewById(R.id.configurationSession)).getText()+"");
		editor.putString("location", ((EditText)findViewById(R.id.configurationLocation)).getText()+"");
		editor.putString("locationParameters", ((EditText)findViewById(R.id.configurationLocationParams)).getText()+"");
		editor.commit();
	}
	
	private void behavioriseScanButton() {
		Button scan=(Button) findViewById(R.id.buttonConfigScan);
		scan.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startQRCodeScan();
			}
        });
	}
	
	private void startQRCodeScan(){
		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
		startActivityForResult(intent, REQUEST_SCAN);
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
	        if (resultCode == RESULT_OK) {
	            String contents = intent.getStringExtra("SCAN_RESULT");
	        	JSONObject jsonContents;
				try {
					jsonContents = new JSONObject(contents);
					((EditText)findViewById(R.id.configurationUrl)).setText(jsonContents.getString("url"));
					((EditText)findViewById(R.id.configurationSession)).setText(jsonContents.getString("sessionName"));
					((EditText)findViewById(R.id.configurationLocation)).setText(jsonContents.getString("_location"));
					((EditText)findViewById(R.id.configurationLocationParams)).setText(jsonContents.getString("location_parameter"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        } else if (resultCode == RESULT_CANCELED) {
	            // Handle cancel
	        }
	    }
	}
	
}
