import java.io.*;
import java.util.Vector;
import org.kxml2.io.*;
import org.xmlpull.v1.*;

/**
*
* @author Francis
*/
public class SurveyParser implements Runnable {

  private KXmlParser parser;
  private Survey survey;
  private String id;
  private int type;
  private Question q;
  private Vector strs;
  private String[] choices;

  SurveyParser(Survey survey){
    this.survey = survey;
    parser = new KXmlParser();
  }

  public void run() {
    try {
      strs = new Vector();
      parser.setInput(new InputStreamReader(getClass().getResourceAsStream("/survey.xml")));
      //parser.setInput(new InputStreamReader(StreamHandler.openStream("http://www.surveymobile.com/survey.php?id="+survey.getSurveyId())));
      while(parser.nextToken() != XmlPullParser.END_DOCUMENT) {
        if (parser.getEventType() == XmlPullParser.START_TAG) {
          if (parser.getName().equalsIgnoreCase("ques")) {
            id = parser.getAttributeValue("", "id");
            type = Integer.parseInt(parser.getAttributeValue("", "type"));
            q = new Question(id);
            //Loop questions
            while(parser.nextTag() != XmlPullParser.END_TAG || !parser.getName().equalsIgnoreCase("ques")){
              if(parser.getName().equalsIgnoreCase("text"))
                q.text = parser.nextText();
              else if(parser.getName().equalsIgnoreCase("choice"))
                strs.addElement(parser.nextText());
            }
            choices = new String[strs.size()];
            strs.copyInto(choices);
            strs.removeAllElements();
            q.createChoices(choices,type);
            survey.addQuestion(q);
          }
        }
      }
      SurveyMidlet.SCREEN.setCurrent(new QuestionForm(survey));
    } catch(XmlPullParserException ex){
      //Fix: handle exception properly
      ex.printStackTrace();
    } catch(IOException ex){
      //Fix: handle exception properly
      ex.printStackTrace();
    }
  }
}
