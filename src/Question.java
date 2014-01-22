import javax.microedition.lcdui.*;
/**
 *
 * @author Francis
 */
public class Question {
  
  public class Response {
    private static final String openVal = "<val>";
    private static final String closeVal = "</val>";
    private static final String end = "</resp>\n";

    private String getOpenResp(){
      return "\t<resp id=\"" + id + "\" type=\"" + type + "\">";
    }

    public String getString(){
      StringBuffer sb = new StringBuffer(getOpenResp());
      if(choices instanceof TextField){
        TextField tf = (TextField)choices;
        sb.append("\n\t")
          .append(openVal)
          .append(tf.getString())
          .append(closeVal)
          .append("\n")
          .append(end);
      }
      else if(choices instanceof ChoiceGroup){
        ChoiceGroup cg = (ChoiceGroup)choices;
        boolean[] selection = new boolean[numberOfChoices];
        cg.getSelectedFlags(selection);
        for(int i = 0; i < numberOfChoices; i++){
          if(selection[i] == true){
            sb.append("\n\t")
              .append(openVal)
              .append(i+1)
              .append(closeVal);
          }
        }
        sb.append("\n" + end);
      }
      else sb.append(end);
      return sb.toString();
    }
  }

  private String id;
  private int type;
  private int numberOfChoices;
  protected String text;
  private String[] choiceString;
    
  private Response response;
  private Item choices;
    
  public static final int SINGLE = 1;
  public static final int MULTI = 2;
  public static final int USER = 3;

    
  public Question(String id){
    this.id = id;
    response = new Response();
  }

  public String getId(){
    return id;
  }
    
  public void createChoices(String[] elements, int type){
    this.choiceString = elements;
    this.type = type;
    numberOfChoices = elements.length;
    switch(type){
      case USER:
        choices = new TextField(text, null, 20, TextField.ANY);
        break;
      case SINGLE:
        choices = new ChoiceGroup(text,ChoiceGroup.EXCLUSIVE,choiceString,null);
        break;
      case MULTI:
        choices = new ChoiceGroup(text,ChoiceGroup.MULTIPLE,choiceString,null);
        break;
      default:
    }
  }
    
  public Item getChoices(){
    return choices;
  }
    
  public Question.Response getResponse(){
    return response;
  }   
}
