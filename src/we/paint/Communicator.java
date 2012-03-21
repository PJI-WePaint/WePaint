package we.paint;

import android.os.Vibrator;
import deviceSide.androphone.Androphone;
import deviceSide.androphone.WseDriverForAndrophone;

public class Communicator {

	public static WseDriverForAndrophone minyDriver;
	
	public static String urlServer="";
	public static String sessionName=""; 
	public static String location="";
	public static String locationParams="";

    public static boolean initMinyDriver (final Vibrator vibrator) {
        minyDriver = new WseDriverForAndrophone(urlServer,sessionName,location,locationParams, new Androphone() {
			
			public void vibrate(int position) {
				if (vibrator!=null)
					vibrator.vibrate(300);
			}
		});
		return true;
		
    }
      
}