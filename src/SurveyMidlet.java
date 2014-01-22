import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

/**
* @author Francis
*/
public class SurveyMidlet extends MIDlet {
   
  public static Display SCREEN;
  private static SurveyMidlet instance;
  
  public static SurveyMidlet getInstance() {
    return instance;
  }
  
  public void startApp() {    
    instance = this;    
    SCREEN = Display.getDisplay(this);
    SCREEN.setCurrent(new SplashCanvas());
    (new Thread(new SurveyList("On Going Surveys..."))).start();
  }

  public void pauseApp() {
  }

  public void destroyApp(boolean unconditional) {
    notifyDestroyed();
  }

  public void exit(){
    destroyApp(false);
  }
}
