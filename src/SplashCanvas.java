import java.io.IOException;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
*
* @author Francis
*/
public class SplashCanvas extends Canvas {

  private Image splash;
  private int x;
  private int y;
  private int progressBarHeight;
  private int progressBarWidth;
  private double percentDone = 0.0;
  private int progressBarIndent = 15;
  private int padding = 10;

  public SplashCanvas(String imageURL) {
    setFullScreenMode(true);
    try{
      splash = Image.createImage(imageURL);
    }
    catch (IOException e){
      splash = Image.createImage(1,1);
    }
    y = (getHeight()>>1)-(splash.getHeight()>>1);
    x = getWidth()>>1;
    progressBarWidth = getWidth() - ( 2 * progressBarIndent );
  }

  public SplashCanvas() {
    this("/splash.png");
  }

  protected void paint(Graphics g) {
    g.setColor(0x000000);
    g.fillRect(0,0,getWidth(),getHeight());
    g.drawImage(splash,x,y,Graphics.HCENTER|Graphics.TOP);
    g.setColor(0xCCCCCC);
    g.fillRoundRect(progressBarIndent,getHeight()-progressBarHeight-padding,(int)(progressBarWidth*(percentDone/100.0)),progressBarHeight,padding,progressBarHeight);
  }

  public void setPercentDone(double percentDone){
    this.percentDone = percentDone;
    repaint();
  }
}
