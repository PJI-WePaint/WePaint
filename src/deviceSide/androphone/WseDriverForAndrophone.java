package deviceSide.androphone;
import java.net.*;
import org.json.JSONObject;
import java.util.Vector;
public class WseDriverForAndrophone {
  protected String _location_ = null;
  protected String _locationParams_ = null;
  protected String _object_ = "Androphone";
  protected String webServerIP = null;
  protected String session = null;
  protected wse.Bus bus = null;
  protected Androphone androphone = null;

  public WseDriverForAndrophone (String webServerIP,String session,String _location_,String _locationParams_,Androphone androphone) {
    this.webServerIP = webServerIP;
    this.session = session;
    this.androphone = androphone;
    String url = "http://"+this.webServerIP+"/WSE/traceSession.php";
    try {
      this.bus = new wse.Bus(url,this.session);
    }
    catch ( java.net.ConnectException ex ) {
      System.out.println ( "A network problem occured : cannot connect to the bus " );
    }
    this._location_ = _location_;
    this._locationParams_ = _locationParams_;
  }

  public void joinSessionPaint (int idUser) {
    try {
      JSONObject objectMessage = new org.json.JSONObject();
      objectMessage.accumulate ( "location" , _location_ );
      objectMessage.accumulate ( "locationParams" , _locationParams_ );
      objectMessage.accumulate ( "object" , "Androphone" );
      objectMessage.accumulate ( "action" , "joinSessionPaint" );
      JSONObject actionParams = new org.json.JSONObject();
      actionParams.accumulate ( "idUser" , idUser );
      objectMessage.accumulate ( "actionParams" , actionParams );
      bus.sendBusMessage ( objectMessage );
    }
    catch ( org.json.JSONException ex ) {
      System.out.println ( "Message has been constructed incorrectly." );
    }
    catch ( java.net.ConnectException ex ) {
      System.out.println ( "A network problem occured." );
    }
  }

  public void textRecognised (String text,int idUser) {
    try {
      JSONObject objectMessage = new org.json.JSONObject();
      objectMessage.accumulate ( "location" , _location_ );
      objectMessage.accumulate ( "locationParams" , _locationParams_ );
      objectMessage.accumulate ( "object" , "Androphone" );
      objectMessage.accumulate ( "action" , "textRecognised" );
      JSONObject actionParams = new org.json.JSONObject();
      actionParams.accumulate ( "text" , text );
      actionParams.accumulate ( "idUser" , idUser );
      objectMessage.accumulate ( "actionParams" , actionParams );
      bus.sendBusMessage ( objectMessage );
    }
    catch ( org.json.JSONException ex ) {
      System.out.println ( "Message has been constructed incorrectly." );
    }
    catch ( java.net.ConnectException ex ) {
      System.out.println ( "A network problem occured." );
    }
  }

  public void color (String code_color,int idUser) {
    try {
      JSONObject objectMessage = new org.json.JSONObject();
      objectMessage.accumulate ( "location" , _location_ );
      objectMessage.accumulate ( "locationParams" , _locationParams_ );
      objectMessage.accumulate ( "object" , "Androphone" );
      objectMessage.accumulate ( "action" , "color" );
      JSONObject actionParams = new org.json.JSONObject();
      actionParams.accumulate ( "code_color" , code_color );
      actionParams.accumulate ( "idUser" , idUser );
      objectMessage.accumulate ( "actionParams" , actionParams );
      bus.sendBusMessage ( objectMessage );
    }
    catch ( org.json.JSONException ex ) {
      System.out.println ( "Message has been constructed incorrectly." );
    }
    catch ( java.net.ConnectException ex ) {
      System.out.println ( "A network problem occured." );
    }
  }

  public void removeCurrent (int idUser) {
    try {
      JSONObject objectMessage = new org.json.JSONObject();
      objectMessage.accumulate ( "location" , _location_ );
      objectMessage.accumulate ( "locationParams" , _locationParams_ );
      objectMessage.accumulate ( "object" , "Androphone" );
      objectMessage.accumulate ( "action" , "removeCurrent" );
      JSONObject actionParams = new org.json.JSONObject();
      actionParams.accumulate ( "idUser" , idUser );
      objectMessage.accumulate ( "actionParams" , actionParams );
      bus.sendBusMessage ( objectMessage );
    }
    catch ( org.json.JSONException ex ) {
      System.out.println ( "Message has been constructed incorrectly." );
    }
    catch ( java.net.ConnectException ex ) {
      System.out.println ( "A network problem occured." );
    }
  }

  public void gpsLocation (int minutes,int seconds,int idUser,int degrees) {
    try {
      JSONObject objectMessage = new org.json.JSONObject();
      objectMessage.accumulate ( "location" , _location_ );
      objectMessage.accumulate ( "locationParams" , _locationParams_ );
      objectMessage.accumulate ( "object" , "Androphone" );
      objectMessage.accumulate ( "action" , "gpsLocation" );
      JSONObject actionParams = new org.json.JSONObject();
      actionParams.accumulate ( "minutes" , minutes );
      actionParams.accumulate ( "seconds" , seconds );
      actionParams.accumulate ( "idUser" , idUser );
      actionParams.accumulate ( "degrees" , degrees );
      objectMessage.accumulate ( "actionParams" , actionParams );
      bus.sendBusMessage ( objectMessage );
    }
    catch ( org.json.JSONException ex ) {
      System.out.println ( "Message has been constructed incorrectly." );
    }
    catch ( java.net.ConnectException ex ) {
      System.out.println ( "A network problem occured." );
    }
  }

  public void qrCode (String content,int idUser) {
    try {
      JSONObject objectMessage = new org.json.JSONObject();
      objectMessage.accumulate ( "location" , _location_ );
      objectMessage.accumulate ( "locationParams" , _locationParams_ );
      objectMessage.accumulate ( "object" , "Androphone" );
      objectMessage.accumulate ( "action" , "qrCode" );
      JSONObject actionParams = new org.json.JSONObject();
      actionParams.accumulate ( "content" , content );
      actionParams.accumulate ( "idUser" , idUser );
      objectMessage.accumulate ( "actionParams" , actionParams );
      bus.sendBusMessage ( objectMessage );
    }
    catch ( org.json.JSONException ex ) {
      System.out.println ( "Message has been constructed incorrectly." );
    }
    catch ( java.net.ConnectException ex ) {
      System.out.println ( "A network problem occured." );
    }
  }

  public void compass (int idUser,int z,int y,int x) {
    try {
      JSONObject objectMessage = new org.json.JSONObject();
      objectMessage.accumulate ( "location" , _location_ );
      objectMessage.accumulate ( "locationParams" , _locationParams_ );
      objectMessage.accumulate ( "object" , "Androphone" );
      objectMessage.accumulate ( "action" , "compass" );
      JSONObject actionParams = new org.json.JSONObject();
      actionParams.accumulate ( "idUser" , idUser );
      actionParams.accumulate ( "z" , z );
      actionParams.accumulate ( "y" , y );
      actionParams.accumulate ( "x" , x );
      objectMessage.accumulate ( "actionParams" , actionParams );
      bus.sendBusMessage ( objectMessage );
    }
    catch ( org.json.JSONException ex ) {
      System.out.println ( "Message has been constructed incorrectly." );
    }
    catch ( java.net.ConnectException ex ) {
      System.out.println ( "A network problem occured." );
    }
  }

  public void light (int idUser,int lumens) {
    try {
      JSONObject objectMessage = new org.json.JSONObject();
      objectMessage.accumulate ( "location" , _location_ );
      objectMessage.accumulate ( "locationParams" , _locationParams_ );
      objectMessage.accumulate ( "object" , "Androphone" );
      objectMessage.accumulate ( "action" , "light" );
      JSONObject actionParams = new org.json.JSONObject();
      actionParams.accumulate ( "idUser" , idUser );
      actionParams.accumulate ( "lumens" , lumens );
      objectMessage.accumulate ( "actionParams" , actionParams );
      bus.sendBusMessage ( objectMessage );
    }
    catch ( org.json.JSONException ex ) {
      System.out.println ( "Message has been constructed incorrectly." );
    }
    catch ( java.net.ConnectException ex ) {
      System.out.println ( "A network problem occured." );
    }
  }

  public void addObject (String message,int idUser) {
    try {
      JSONObject objectMessage = new org.json.JSONObject();
      objectMessage.accumulate ( "location" , _location_ );
      objectMessage.accumulate ( "locationParams" , _locationParams_ );
      objectMessage.accumulate ( "object" , "Androphone" );
      objectMessage.accumulate ( "action" , "addObject" );
      JSONObject actionParams = new org.json.JSONObject();
      actionParams.accumulate ( "message" , message );
      actionParams.accumulate ( "idUser" , idUser );
      objectMessage.accumulate ( "actionParams" , actionParams );
      bus.sendBusMessage ( objectMessage );
    }
    catch ( org.json.JSONException ex ) {
      System.out.println ( "Message has been constructed incorrectly." );
    }
    catch ( java.net.ConnectException ex ) {
      System.out.println ( "A network problem occured." );
    }
  }

  public void accelerometer (int idUser,int z,int y,int x) {
    try {
      JSONObject objectMessage = new org.json.JSONObject();
      objectMessage.accumulate ( "location" , _location_ );
      objectMessage.accumulate ( "locationParams" , _locationParams_ );
      objectMessage.accumulate ( "object" , "Androphone" );
      objectMessage.accumulate ( "action" , "accelerometer" );
      JSONObject actionParams = new org.json.JSONObject();
      actionParams.accumulate ( "idUser" , idUser );
      actionParams.accumulate ( "z" , z );
      actionParams.accumulate ( "y" , y );
      actionParams.accumulate ( "x" , x );
      objectMessage.accumulate ( "actionParams" , actionParams );
      bus.sendBusMessage ( objectMessage );
    }
    catch ( org.json.JSONException ex ) {
      System.out.println ( "Message has been constructed incorrectly." );
    }
    catch ( java.net.ConnectException ex ) {
      System.out.println ( "A network problem occured." );
    }
  }

  public void start () {
    wse.Listener listener = new wse.Listener ( ) {
    
      public void newMessageReceive (String idOriginSession,JSONObject objectMessage) {
        try {
          String _location = objectMessage.getString ("location");
          String _locationParams = objectMessage.getString ("locationParams");
          String _object = objectMessage.getString ("object");
          String action = objectMessage.getString ("action");
          JSONObject actionParams = objectMessage.getJSONObject ("actionParams");
          if ( _location.toUpperCase().equals(_location_.toUpperCase())  && 
            _locationParams.toUpperCase().equals(_locationParams_.toUpperCase())  && 
            _object.toUpperCase().equals(_object_.toUpperCase())  ){
            if ( action.toUpperCase().equals("returnColor".toUpperCase())  ){
              String __code_color = actionParams.getString("code_color");
              String __code_retour = actionParams.getString("code_retour");
              androphone.returnColor ( __code_color , __code_retour );
            }
            if ( action.toUpperCase().equals("notification".toUpperCase())  ){
              String __message = actionParams.getString("message");
              androphone.notification ( __message );
            }
            if ( action.toUpperCase().equals("vibrate".toUpperCase())  ){
              int __position = actionParams.getInt("position");
              androphone.vibrate ( __position );
            }
          }
        }
        catch ( org.json.JSONException ex ) {
          System.out.println ( "Message has been constructed incorrectly." );
        }
      }
    
    }
    ;
    bus.addListener ( listener );
    bus.start (  );
  }

  public void stop () {
    // TO DO : remove all listeners;
  }

}
