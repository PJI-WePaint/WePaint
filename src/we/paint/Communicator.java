package we.paint;

import android.os.Vibrator;
import android.widget.Toast;
import deviceSide.androphone.Androphone;
import deviceSide.androphone.WseDriverForAndrophone;

public class Communicator {

	public static WseDriverForAndrophone minyDriver;
	
	public static String urlServer="";
	public static String sessionName=""; 
	public static String location="";
	public static String locationParams="";

	public static Home home;

    public static boolean initMinyDriver (final Vibrator vibrator) {
        minyDriver = new WseDriverForAndrophone(urlServer,sessionName,location,locationParams, new Androphone() {
			
			public void vibrate(int position) {
				if (vibrator!=null)
					vibrator.vibrate(300);
			}

			public void returnColor(String code_color, String code_retour) {
				home.findViewById(R.id.colorPic).setBackgroundColor(Integer.valueOf(code_color.substring(1)));
			}
			
			public void notification(String message) {
				Toast.makeText(home, message, Toast.LENGTH_LONG).show();
			}
		});
		return true;
		
    }
      
}