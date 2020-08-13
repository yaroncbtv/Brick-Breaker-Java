/* 
 * Ver 0.1.0.0. - PreAlpha
 * 
 * SimpleArkanoid
 * Simple game made for exercise with java swing.
 * By Deovin Italy, Milan ( Deox@libero.it ) 
 * 12/1/2018
 * */

import java.util.Random;

public class ArkanoidEnemy extends ArkanoidImage {

    private final int INITIAL_X = 1000;
    Random rand;
    int MattoneColor;
    int mattoneenergy;
    public ArkanoidEnemy(int x, int y) {
        super(x, y);

        initmattoni();
    }

    private void initmattoni() {
		rand = new Random ();
		MattoneColor = (rand.nextInt (4) +1);	

        if (MattoneColor ==1) {
			loadImage("mblu.png");
			mattoneenergy =1;
		}
        if (MattoneColor ==2) {
			loadImage("mgiallo.png");
			mattoneenergy =3;
		}
        if (MattoneColor ==3) {
			loadImage("mrosso.png");
			mattoneenergy =5;
		}
        if (MattoneColor ==4) {
			loadImage("mverde.png");
			mattoneenergy =7;
		}
        getImageDimensions(); 
    }
    public void setMattoneEnergy () {
		mattoneenergy -=1;
		if (mattoneenergy <0 ) mattoneenergy =0;
	}
    public int RitornoEnergy () {
		return mattoneenergy ;
	}
    
  
}
