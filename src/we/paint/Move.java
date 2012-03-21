package we.paint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Move extends Activity {

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.move);
	}
     
	public void compassClicked(View view){
		Intent myIntent = new Intent(Move.this, MoveCompass.class);
		Move.this.startActivity(myIntent);
	}
	
	public void accelerometerClicked(View view){
		Intent myIntent = new Intent(Move.this, MoveAccelerometer.class);
		Move.this.startActivity(myIntent);
	}
}
