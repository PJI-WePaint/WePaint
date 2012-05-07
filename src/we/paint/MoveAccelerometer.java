package we.paint;

import java.util.List;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

public class MoveAccelerometer extends MoveAction{
	
	protected void setSensor() {
		selectedDelta = 2;
		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
    	List<Sensor> sensors =sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
    	if (sensors.size() > 0) {
        	sensor = sensors.get(0);
    	}
	}
	
	protected void sendActionToWSE() {
		Communicator.minyDriver.accelerometer(Communicator.id, getPreviousZ(), getPreviousY(), getPreviousX());
	}
}
