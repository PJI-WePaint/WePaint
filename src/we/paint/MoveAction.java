package we.paint;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public abstract class MoveAction extends Activity implements OnTouchListener {

	protected SensorManager sensorManager;
	protected Sensor sensor;
	protected int previousX=0, previousY=0, previousZ=0;
	protected int selectedDelta=10;
	protected boolean pressed;
	protected ImageView press;
	
	private final SensorEventListener sensorListener = new SensorEventListener(){
		public void onSensorChanged(SensorEvent event){
			int currentX=(int) event.values[SensorManager.DATA_X];
			int currentY=(int) event.values[SensorManager.DATA_Y];
			int currentZ=(int) event.values[SensorManager.DATA_Z];
			if (delta(currentX, currentY, currentZ) && pressed){
				setPreviousX(currentX);
				setPreviousY(currentY);
				setPreviousZ(currentZ);
				sendActionToWSE();
			}
		}
		
		public void onAccuracyChanged(Sensor sensor, int accuracy){}
	};
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.move_action);
		setSensor();
		press = (ImageView) this.findViewById(R.id.pressButton);
		press.setOnTouchListener(this);
		this.listenSensorEvents();
	}
	
	protected void onStop() {
		super.onStop();
		this.unlistenSensorEvents();
	}
	
	protected void onResume() {
		super.onResume();
		this.listenSensorEvents();
	}
	
	public boolean onTouch(View v, MotionEvent event){

		if (event.getAction() == MotionEvent.ACTION_DOWN){ 
			if(this.pressed!=true){
				Communicator.minyDriver.beginMove(Communicator.id);
				this.pressed = true;
			}
			press.setImageResource(R.drawable.press_button_pushed);
			
        }else if(event.getAction() == MotionEvent.ACTION_UP){
        	if(this.pressed!=false){
				Communicator.minyDriver.endMove(Communicator.id);
				this.pressed = false;
			}
    		press.setImageResource(R.drawable.press_button);
        }
		return true;
	}
	
	public void listenSensorEvents () {
		sensorManager.registerListener(sensorListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	public void unlistenSensorEvents () {
		sensorManager.unregisterListener(sensorListener);
	}
	
	protected void setSensor(){
		
	}
	
	protected void sendActionToWSE(){
		
	}
	
	public int getPreviousX(){
		return this.previousX;
	}

	public void setPreviousX(int previousX){
		this.previousX = previousX;
	}

	public int getPreviousY(){
		return this.previousY;
	}

	public void setPreviousY(int previousY){
		this.previousY = previousY;
	}

	public int getPreviousZ(){
		return this.previousZ;
	}
	
	public void setPreviousZ(int previousZ) {
		this.previousZ = previousZ;
	}
	
	public int deltaX(int currentX){
		return Math.abs(Math.abs(previousX)-Math.abs(currentX));
	}
	
	public int deltaY(int currentY){
		return Math.abs(Math.abs(previousY)-Math.abs(currentY));
	}
	
	public int deltaZ(int currentZ){
		return Math.abs(Math.abs(previousZ)-Math.abs(currentZ));
	}
	
	public int getSelectedDelta(){
		return this.selectedDelta;
	}
	
	public boolean delta(int currentX, int currentY, int currentZ){
		return (deltaX(currentX)>=getSelectedDelta() ||
				deltaY(currentY)>=getSelectedDelta() ||
				deltaZ(currentZ)>=getSelectedDelta() );
	}
	
}
