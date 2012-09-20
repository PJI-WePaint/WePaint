package deviceSide.androphone;
import org.json.JSONObject;
public interface Androphone {

  public void returnColor (String code_color,String code_retour) ;
  public void notification (String message) ;
  public void vibrate (int position) ;
}
