import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

/**
 *
 * @author Francis
 */
public final class StreamHandler {

  public static InputStream openStream(String url) {
    try {
      HttpConnection hc = (HttpConnection)Connector.open(url);
      if(hc.getResponseCode() != HttpConnection.HTTP_OK){
        System.out.println("Error: failed connection to server...");
        return null;
      }
      return hc.openInputStream();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return null;
  }
}
