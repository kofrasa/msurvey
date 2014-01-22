import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

/**
 *
 * @author Francis
 */
public final class QuestionForm extends Form implements CommandListener {

  private final Command back;
  private final Command next;
  private final Command send;
  private Survey survey;
    
  QuestionForm(Survey s){
    super("");
    survey = s;
    back = new Command("Back", Command.BACK, 1);
    next = new Command("Next", Command.OK, 1);
    send = new Command("Send", Command.OK, 1);
    setQuestion(survey.getCurrent());
    addCommand(next);
    addCommand(back);
    setCommandListener(this);
  }
    
  public void setQuestion(Question q) {
    this.deleteAll();
    this.setTitle("Question " + q.getId());
    this.append(q.getChoices());
  }
    
  public void commandAction(Command cmd, Displayable disp) {
    if(disp == this){
      if(cmd == next){
        setQuestion(survey.nextQuestion());
        if(survey.isLastQuestion()){
          removeCommand(next);
          addCommand(send);
        }
      }
      else if (cmd == back){
        if(survey.isLastQuestion()){
          removeCommand(send);
          addCommand(next);
        }
        else if(survey.isFirstQuestion()){
          return;
        }
        setQuestion(survey.previousQuestion());
      }
      else if(cmd == send){
        //test code
        deleteAll();
        StringItem it = new StringItem(survey.getResponseString(), "");
        this.append(it);
      }
    }
  }
}
