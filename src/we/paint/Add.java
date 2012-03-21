package we.paint;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class Add extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add);
	}
	
	public void circleClicked(View view){
		sendShape("Circle");
	}
	
	public void squareClicked(View view){
		sendShape("Square");
	}
	
	public void triangleClicked(View view){
		sendShape("Triangle");
	}
	
	private void sendShape(String shape){
		Communicator.minyDriver.message(shape+"");
	}
}