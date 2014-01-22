import java.io.*;
import java.util.Vector;
import javax.microedition.lcdui.*;

import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.*;

/**
* Displays a list of on-going surveys of interest to the user.
*
* @author Francis
*/
public class SurveyList extends List implements CommandListener, Runnable {
  private KXmlParser parser;
  private Vector surveyIds;

  SurveyList(String name){
    super(name, List.EXCLUSIVE);
    addCommand(new Command("Select",Command.ITEM,1));
    addCommand(new Command("Exit",Command.EXIT,1));
    registerListeners();
  }

  public void commandAction(Command cmd, Displayable disp) {
    if(cmd.getCommandType() == Command.ITEM){
      SurveyMidlet.SCREEN.setCurrent(new WaitCanvas());
      Survey survey = new Survey(surveyIds.elementAt(getSelectedIndex()).toString());
      (new Thread(new SurveyParser(survey))).start();
    } else {
      SurveyMidlet.getInstance().exit();
    }
  }

  public void run() {
    parser = new KXmlParser();
    surveyIds = new Vector();
    try {
      parser.setInput(new InputStreamReader(getClass().getResourceAsStream("/surveylist.xml")));
      //parser.setInput(new InputStreamReader(StreamHandler.openStream("http://www.surveymobile/getsurveys.php")));
      while(parser.nextToken() != XmlPullParser.END_DOCUMENT){
        if(parser.getEventType() == XmlPullParser.START_TAG){
          if(parser.getName().equalsIgnoreCase("title")){
            surveyIds.addElement(parser.getAttributeValue("", "id"));
            append(parser.nextText(), null);
          }
        }
      }
      try {
        Thread.sleep(2000);
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
      SurveyMidlet.SCREEN.setCurrent(this);
    } catch (XmlPullParserException ex) {
      ex.printStackTrace();
    } catch(IOException ex){
      ex.printStackTrace();
    }
  }

  private void registerListeners() {
    setCommandListener(this);
  }
}
