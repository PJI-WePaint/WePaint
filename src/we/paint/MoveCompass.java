package we.paint;

import java.util.List;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;


public class MoveCompass extends MoveAction {

	protected void setSensor() {
		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
    	List<Sensor> sensors =sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
    	if (sensors.size() > 0) {
        	sensor = sensors.get(0);
    	} 
	}
	
	protected void sendActionToWSE() {
		Communicator.minyDriver.compass(getPreviousZ(), getPreviousY(), getPreviousX());
	}
	
	
	
}
