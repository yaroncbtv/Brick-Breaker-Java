/* 
 * Ver 0.1.0.0. - PreAlpha
 * 
 * SimpleArkanoid
 * Simple game made for exercise with java swing.
 * By Deovin Italy, Milan ( Deox@libero.it ) 
 * 12/1/2018
 * */
 
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.util.ArrayList;
public class ArkanoidImage {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean vis;
    protected Image image;
   

   

    public ArkanoidImage(int x, int y) {

        this.x = x;
        this.y = y;
        vis = true;
    }

    protected void getImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
        getImageDimensions();
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return vis;
    }

    public void setVisible(Boolean visible) {
        vis = visible;
    }
	
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    public void pallanuova () {
		x=497;
		y=675;
	}
	public void setcursoreposition (int x,int y){
		this.x =x;
		this.y =y;
	}
		  
    
    public void moveDX () {
		x +=15;
	}
	public void moveSX () {
		x -=15;
	}
	
	public void PmoveSU () {
		y -=3;
	}
	public  void PmoveGIU() {
		y +=3;
	}
	 public void PmoveDX () {
		x +=3;
	}
	public void PmoveSX () {
		x -=3;
	}
}
