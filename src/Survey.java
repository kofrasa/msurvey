import java.util.Vector;

/**
* A description of a survey entity 
*
* @author Francis
*/
public class Survey {
  
  private Question current;
  private Vector questions;

  private String id;
  private int count;
  private StringBuffer responses;

  Survey(String id){
    this.id = id;    
    questions = new Vector();
  }

  public String getSurveyId(){
    return id;
  }

  public String getTitle(){
    return "survey title";
  }

  public String getDateDue(){
    return "date here";
  }

  public int getNumberOfQuestions(){
    return questions.size();
  }

  public String getResponseString(){
    responses = new StringBuffer("<response>\n\t<id>"+id+"</id>\n\t");
    for(int i =0; i < questions.size(); i++)
      responses.append(((Question)questions.elementAt(i)).getResponse().getString());
    responses.append("</response>");
    return responses.toString();
  }

  public void addQuestion(Question q){
    questions.addElement(q);
  }

  public Question getCurrent(){
    return current = (current == null)? (Question)questions.firstElement(): current;
  }

  public Question nextQuestion(){
    return current = (count < questions.size())? (Question)questions.elementAt(++count) : current;
  }

  public Question previousQuestion(){
    return current = (count > 0)? (Question)questions.elementAt(--count) : current;
  }

  public boolean isFirstQuestion(){
    return (count == 0);
  }

  public boolean isLastQuestion(){
    return (count == questions.size()-1);
  }
}
