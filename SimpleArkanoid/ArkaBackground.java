import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;


class ArkaBackground  {
	
    private BufferedImage image;
   

    private int x;
    private int y;

    public ArkaBackground() {
        this(0,0);
    }
	
    public ArkaBackground(int x, int y) {
       
        this.x = x;
        this.y = y;
        
        try {
			image = ImageIO.read(new File("sfondo.png"));
			}
			catch (Exception e) { System.out.println(e); }
		}
	
	
	

    
    public void draw(Graphics window) {
    
        window.drawImage(image, getX(), getY(), image.getWidth(), image.getHeight(), null);
         
        this.x -= 1;
        
        if (this.x <= -1 * image.getWidth()) { 
            
            this.x = this.x + image.getWidth() * 2;//           
        }

    }   
    
   

    public void setY (int y) {
		this.y = y;
	}
      
    public void setX(int x) {
        this.x = x;
    }
    public int getX() { 
        return this.x;
    }
    public int getY() { 
        return this.y;
    }
    
    public int getImageHeight () {
		return image.getHeight();
	}
    public int getImageWidth() { 
        return image.getWidth();
    }
}
