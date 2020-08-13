
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.util.Random;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.sound.sampled.*; // for Sound

public class SimpleArkanoid extends JFrame {
    
    public SimpleArkanoid() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new GameArkanoid());

        setTitle("SimpleArkanoid");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  
        setResizable(false);      
    }

    public static void main(String[] args) {        
        
        EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                SimpleArkanoid ex = new SimpleArkanoid();
                ex.setVisible(true);
            }
        });
    }
}

class GameArkanoid extends JPanel implements ActionListener ,KeyListener {
	
	
	ArkanoidImage cursoreA = new ArkanoidImage (430,690);
	ArkanoidImage cursoreB = new ArkanoidImage (480,690);
	ArkanoidImage cursoreC = new ArkanoidImage (530,690);
	ArkanoidImage murosx = new ArkanoidImage (1,1);
	ArkanoidImage murodx = new ArkanoidImage (1017,1);
	ArkanoidImage murosu = new ArkanoidImage (1,1);
	ArkanoidImage murogiu = new ArkanoidImage (1,725);
	ArkanoidImage presentax = new ArkanoidImage (1,1);
	ArkanoidImage palla = new ArkanoidImage (497,675);
	ArkanoidImage sfondo = new ArkanoidImage (0,0);
	ArkanoidImage gameOverArka = new ArkanoidImage (260,300);
	private ArrayList<ArkanoidEnemy> mattoni;
	
	private BufferedImage Arkaback; 
	public ArkaBackground ArkaA = new ArkaBackground(); 
	public ArkaBackground ArkaB  = new ArkaBackground (ArkaA.getImageHeight(), 0);
	
	Random rand;

	
	String life ="life";
	int numberlife= 3;
	int punti =0;
	
	boolean pallaON = false;
	boolean pallasu = false;
	int pallaangolo = 1; 
	
	int quantinemici =1;
	int fasegame =3; 
	int TheMovimentDX =0;
	int TheMovimentSX =0;
	
	private final int[][] pos = { 
		 {50,100}, {100,100}, {150,100}, {200,100} ,{250,100}, {300,100}, {350,100},{400,100}, {450,100}, {500,100} ,{550,100}, {600,100}, {650,100},{700,100}, {750,100}, {800,100} ,{850,100}, {900,100}, {950,100}
		,{50,130}, {100,130}, {150,130}, {200,130} ,{250,130}, {300,130}, {350,130},{400,130}, {450,130}, {500,130} ,{550,130}, {600,130}, {650,130},{700,130}, {750,130}, {800,130} ,{850,130}, {900,130}, {950,130}
		,{50,160}, {100,160}, {150,160}, {200,160} ,{250,160}, {300,160}, {350,160},{400,160}, {450,160}, {500,160} ,{550,160}, {600,160}, {650,160},{700,160}, {750,160}, {800,160} ,{850,160}, {900,160}, {950,160}
		,{50,250}, {100,250}, {150,250}, {200,250} ,{250,250}, {300,250}, {350,250},{400,250}, {450,250}, {500,250} ,{550,250}, {600,250}, {650,250},{700,250}, {750,250}, {800,250} ,{850,250}, {900,250}, {950,250}
		,{50,290}, {100,290}, {150,290}, {200,290} ,{250,290}, {300,290}, {350,290},{400,290}, {450,290}, {500,290} ,{550,290}, {600,290}, {650,290},{700,290}, {750,290}, {800,290} ,{850,290}, {900,290}, {950,290}	
		,{50,320}, {100,320}, {150,320}, {200,320} ,{250,320}, {300,320}, {350,320},{400,320}, {450,320}, {500,320} ,{550,320}, {600,320}, {650,320},{700,320}, {750,320}, {800,320} ,{850,320}, {900,320}, {950,320}
		,{50,350}, {100,350}, {150,350}, {200,350} ,{250,350}, {300,350}, {350,350},{400,350}, {450,350}, {500,350} ,{550,350}, {600,350}, {650,350},{700,350}, {750,350}, {800,350} ,{850,350}, {900,350}, {950,350}
		,{50,380}, {100,380}, {150,380}, {200,380} ,{250,380}, {300,380}, {350,380},{400,380}, {450,380}, {500,380} ,{550,380}, {600,380}, {650,380},{700,380}, {750,380}, {800,380} ,{850,380}, {900,380}, {950,380}
		,{50,410}, {100,410}, {150,410}, {200,410} ,{250,410}, {300,410}, {350,410},{400,410}, {450,410}, {500,410} ,{550,410}, {600,410}, {650,410},{700,410}, {750,410}, {800,410} ,{850,410}, {900,410}, {950,410}
		,{50,440}, {100,440}, {150,440}, {200,440} ,{250,440}, {300,440}, {350,440},{400,440}, {450,440}, {500,440} ,{550,440}, {600,440}, {650,440},{700,440}, {750,440}, {800,440} ,{850,440}, {900,440}, {950,440}	
	};
    
    ArkanoidSound Thepop;
  
    File FThepop = new File ("ArkaSound/pop.wav");	
	private Timer timer;
	private final int DELAY = 10;
	
	
	public GameArkanoid () {
		cursoreA.loadImage ("Acursore.png");
		cursoreB.loadImage ("Bcursore.png");
		cursoreC.loadImage ("Ccursore.png");
		murodx.loadImage("murolaterale.png");
		murosx.loadImage("murolaterale.png");
		murosu.loadImage("muroalto.png");
		murogiu.loadImage("murobasso.png");
		presentax.loadImage ("presentazione.png");
		palla.loadImage ("palla.png");		
		gameOverArka.loadImage ("gameover.png");
		
		Thepop = new ArkanoidSound();
		initSurface() ;
	}
	private void initSurface() {
		
		
		
		Thepop.chargesound (FThepop);
		addKeyListener (this); 
		setFocusable (true); 
		setBackground (Color.BLACK);
		timer= new Timer (DELAY, this);
		timer.start();
	}
	

	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		if (fasegame == 3) logoiniziale (g);
		if (fasegame == 4) preparagame ();
		if (fasegame == 5) {
			if (TheMovimentDX ==1) movimentocursoreDX();
			if (TheMovimentSX ==1) movimentocursoreSX();
			if (pallaON == true) intersezioni();
			if (pallaON == true) movimentoPalla();
			if (pallaON == true) collisioneMattoni();
			if (pallaON == true) collisioneBordoBasso();			
			doDrawing (g);
			stampaScore(g);
			
		}
		if (fasegame == 6) {
			doDrawing (g);
			stampaScore(g);
			stampaGameOver(g);
			regame();
		}
		
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void doDrawing (Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		if (Arkaback == null) Arkaback = (BufferedImage)(createImage(getWidth(), getHeight()));			
		Graphics buffer = Arkaback.createGraphics();			
		ArkaA.draw(buffer);
		ArkaB.draw(buffer);			
		g2D.drawImage(Arkaback, null, 0,0);
		g2D.drawImage (murosx.getImage (), murosx.getX(), murosx.getY(), this);
		g2D.drawImage (murodx.getImage (), murodx.getX(), murodx.getY(), this);
		g2D.drawImage (murosu.getImage (), murosu.getX(), murosu.getY(), this);
		g2D.drawImage (murogiu.getImage (), murogiu.getX(), murogiu.getY(), this);
		g2D.drawImage (palla.getImage  (), palla.getX(), palla.getY(), this);
		g2D.drawImage (cursoreA.getImage () ,cursoreA.getX(), cursoreA.getY(),this);
		g2D.drawImage (cursoreB.getImage (), cursoreB.getX(), cursoreB.getY(),this);
		g2D.drawImage (cursoreC.getImage () ,cursoreC.getX(), cursoreC.getY(),this);
        for (ArkanoidEnemy a : mattoni) { 
            if (a.isVisible()) {
                g.drawImage(a.getImage(), a.getX(), a.getY(), this); 
            }
		}
		
	}
	
		
	private void logoiniziale (Graphics g) {
		Graphics2D g2D = (Graphics2D) g ;
		
		g2D.drawImage (presentax.getImage () ,1,1,this);
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
        
       
        repaint();  
	}
	public void preparagame () { 
		mattoni = new ArrayList<>();
		
		for (int[] p : pos) { 
			mattoni.add(new ArkanoidEnemy(p[0], p[1])); 
        }												
		fasegame =5;
	}	
	public void movimentocursoreDX(){ 
		if (cursoreC.getX() >950) TheMovimentDX =0;
		if (pallaON == false && cursoreC.getX() >585) TheMovimentDX =0;
		if (TheMovimentDX ==1 ) {
			cursoreA.moveDX();
			cursoreB.moveDX();
			cursoreC.moveDX();
		}
	}
	public void movimentocursoreSX(){ 
		if (cursoreA.getX() <30) TheMovimentSX =0;
		if (pallaON == false && cursoreA.getX() <380) TheMovimentSX =0;
		if (TheMovimentSX ==1) {
			cursoreA.moveSX();
			cursoreB.moveSX();
			cursoreC.moveSX();
		}
	}
	public void intersezioni () {  		
		Rectangle r1 = palla.getBounds();
		Rectangle r2 = cursoreA.getBounds();
		Rectangle r3 = cursoreB.getBounds();
		Rectangle r4 = cursoreC.getBounds();
		Rectangle r5 = murosu.getBounds();
		Rectangle r6 = murosx.getBounds();
		Rectangle r7 = murodx.getBounds();
				
		if ((r1.intersects(r2) && pallasu == false)) {
			pallasu = true;
			pallaangolo = 0;
		}			
		if ((r1.intersects(r3) && pallasu == false)) {
			pallasu = true;
			pallaangolo =1;
		}
		if ((r1.intersects(r4) && pallasu == false)) {
			pallasu = true;
			pallaangolo =2;
		}
		if (r1.intersects(r5) && pallasu == true ) {
			pallasu = false;			
		}		
		if ((r1.intersects(r6) && pallaangolo ==0)) {			
			pallaangolo =2;
		}		
				if ((r1.intersects(r7) && pallaangolo ==2)) {			
			pallaangolo =0;
		}				
	}
			
			
															
	public void movimentoPalla () {	
		if (pallasu== true) palla.PmoveSU();
		if (pallasu== false) palla.PmoveGIU();
		if (pallaangolo == 0 ) palla.PmoveSX();
		if (pallaangolo == 2 ) palla.PmoveDX();
							
	}
	public void collisioneMattoni() { 
		for (ArkanoidEnemy Ae : mattoni) {		
			Rectangle r1 = Ae.getBounds(); 
			Rectangle r2 = palla.getBounds();				
			if (Ae.isVisible () ){
				if (r1.intersects (r2)) { 
					Ae.setMattoneEnergy ();
					punti += 5;
					if (pallasu == false) {
						pallasu =true;
					}
					else if (pallasu == true) {
						pallasu = false;
					}
					rand = new Random ();
					int casualAngle = (rand.nextInt (3) +1);
					if (casualAngle ==1) pallaangolo =0; 
					if (casualAngle ==2) pallaangolo =1; 
					if (casualAngle ==3) pallaangolo =2; 
					if (Ae.RitornoEnergy () ==0) {
						Thepop.ONCEstartsound();
						Ae.setVisible(false);
						punti +=50;	
					}
				}
			}
		}
	}
	public void collisioneBordoBasso () { 
		Rectangle r1 = palla.getBounds();
		Rectangle r2 = murogiu.getBounds();
		if (r1.intersects(r2))  {
			pallaON = false;
			pallasu = false;
			pallaangolo = 1; 
			numberlife -=1;
			palla.pallanuova();
			cursoreA.setcursoreposition(430,690);
			cursoreB.setcursoreposition(480,690);
			cursoreC.setcursoreposition(530,690);
			if (numberlife < 1) fasegame =6;
		}
	} 
		 			
	public void stampaScore (Graphics g){ // print score
		
		Graphics2D g2d = (Graphics2D) g;
		Font font = new Font("Verdana", Font.BOLD, 25);
		FontMetrics metr = this.getFontMetrics(font);
		g2d.setColor(Color.RED);
		g2d.setFont(font);
		String finpunt = Integer.toString(punti);
		String finlife = Integer.toString(numberlife);
		g2d.drawString (finpunt, 80,720);
		g2d.drawString (life ,900,720);
		g2d.setColor(Color.BLUE);
		g2d.drawString (finlife,980,720);
	}
	
	private void stampaGameOver (Graphics g) { // print game over
		Graphics2D g2D = (Graphics2D) g ;
		
		g2D.drawImage (gameOverArka.getImage () ,gameOverArka.getX(),gameOverArka.getY(),this);
	}

	public void regame () {		// after loss reborn match
		// dati della palla
		pallaON = false;
		pallasu = false;
		pallaangolo = 1; // 0 sx - 1 centro - 2 dx 
		// numero di mattoncini da abbattere
		quantinemici =1;
		// variabili per la chiamata ai metodi di movimento
		TheMovimentDX =0;
		TheMovimentSX =0;
	}
		

		
	public void keyPressed (KeyEvent ke) { 
		int key = ke.getKeyCode(); 
		
		 // eventi della barra spaziatrice // space key
		 if (key == KeyEvent.VK_SPACE ){
			 // passaggio dalla schermata iniziale al gioco
			 if (fasegame == 3) fasegame = 4;
			 if (fasegame == 5 && pallaON == false) pallaON = true;
			 if (fasegame == 6 && pallaON == false) {
				 fasegame =3;
				 numberlife= 3;
				 punti =0;
			 }
		 }
		 // eventi della freccia destra  // right key
		 if ((fasegame ==5) && key == KeyEvent.VK_RIGHT) {
			 TheMovimentDX =1;
			
			
		 } 
		 // eventi della freccia sinistra // left key
		 if ((fasegame ==5) && key == KeyEvent.VK_LEFT) {
			 TheMovimentSX =1;
			
			
		 } 
	}
	public void keyReleased (KeyEvent ke) { 
		int key = ke.getKeyCode(); 
		// eventi della freccia destra  
		if ((fasegame ==5) && key == KeyEvent.VK_RIGHT) {
			TheMovimentDX =0;
		}
		// eventi della freccia sinistra
		if ((fasegame ==5) && key == KeyEvent.VK_LEFT) {
			TheMovimentSX =0;
		}
	}       
                   			
	
	public void keyTyped (KeyEvent ke) {	
	}
}
