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
		sendColor("#ff0000");
	}
	
	public void colorBlueClicked(View view){
		sendColor("#0042ff");
	}
	
	public void colorGreenClicked(View view){
		sendColor("#04bb00");
	}
	
	public void colorYellowClicked(View view){
		sendColor("#fffc00");
	}
	
	private void sendColor(String color){
		Communicator.minyDriver.color(color+"");
	}
}
