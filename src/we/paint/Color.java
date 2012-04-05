package we.paint;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Color extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.color);
	}
	
	public void colorRedClicked(View view){
		sendColor("Red");
	}
	
	public void colorBlueClicked(View view){
		sendColor("Blue");
	}
	
	public void colorGreenClicked(View view){
		sendColor("Green");
	}
	
	public void colorYellowClicked(View view){
		sendColor("Yellow");
	}
	
	private void sendColor(String color){
		Toast.makeText(this, "Not implemented yet", Toast.LENGTH_LONG).show();
		/*Communicator.minyDriver.message(color+"");*/
	}
}
