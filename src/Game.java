<<<<<<< HEAD
import org.lwjgl.input.Mouse;
=======


>>>>>>> 95061b2db297b2cb544f0e32ed67c9586d3cb6b1
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
<<<<<<< HEAD
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
=======
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.gui.TextField;
>>>>>>> 95061b2db297b2cb544f0e32ed67c9586d3cb6b1

// every Slick2D window or game extends from the basic game class, provided by Slick2D
// A basic scene:
public class Game extends BasicGameState{
	// assign variable to picture
	private Image logo;
	private Image penta;
<<<<<<< HEAD
	private Image exitButton;
	private float x,y;
	boolean goBack;
	private int posX = Mouse.getX();
	private int posY = Mouse.getY();
=======
	private float pentaX1,pentaY1,pentaX2,pentaY2,pentaStripX1,pentaStripY1,pentaStripX2,pentaStripY2;
	Color backgroundColor;
	//boolean goBack;
	
	Font font;
	int score;
	TrueTypeFont ttf;
	int iPenta; // to increment through penta PNG strip values [1-4]
	boolean pentaSignal,triSignal,squareSignal,circleSignal;
	// signals
	final int EMPTY = 1; // not signaled, not hit
	final int SIGNAL = 2;
	final int SIGNAL_HIT = 3; 
	final int ERROR = 4; // they hit the drum, but it was not signaled
>>>>>>> 95061b2db297b2cb544f0e32ed67c9586d3cb6b1
	
	public Game() {
		super();
		// TODO Auto-generated constructor stub
	}

	//render: called every frame update 
	// render all things calculated in update method and update screen
	// Slick coordinate system: (0,0) is top left corner! --> Y axis extends down, X extends right
	public void render(GameContainer gc, StateBasedGame sgb, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		g.drawOval(300, 350, 80, 80);
		logo.draw(180,25,450,130);
		penta.draw(x,y,150,150);
		
		//test
		g.drawString("position x,y " + posX, posX, posY);

		
		exitButton.draw(500,500,200,50);
=======
		g.setBackground(backgroundColor);
		logo.draw(280,25,450,130);
		
		
		// modify the pentaStripX# for showing a portion of the .PNG strip
		pentaStripX1 = 694*(iPenta-1);
		pentaStripX2 = 694*iPenta;
		penta.draw(pentaX1,pentaY1,pentaX2,pentaY2,pentaStripX1,pentaStripY1,pentaStripX2,pentaStripY2);
		
		// render text for score box
		//g.setFont(font);
		g.setColor(Color.blue);
		g.drawString("Score: \n" +"  "+score, 650, 150);
		
>>>>>>> 95061b2db297b2cb544f0e32ed67c9586d3cb6b1
	}

	
	// init is like a constructor for Slick 2D
	// called before the start - only called once
	// do the picture loading/ import images here!
<<<<<<< HEAD
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// associate variables to image files
		
		logo = new Image("res/Logo.png");
		penta = new Image("res/pentagon empty.png");
		x = 30;
		y= 200;
		goBack=false;
		
		exitButton = new Image("res/Exit button unclicked.png");
		x = 800;
		y = 600;
		
		
=======
	@Override
	public void init(GameContainer gc) throws SlickException {
		// set background color to white:
		backgroundColor = new Color(255, 250, 250);
		// associate variables to image files:
		logo = new Image("res/Logo.png");
		penta = new Image("res/Drums/pentagon strip.png");
		//initialize signals to false(off)
		pentaSignal =false;
		iPenta = 1;
		// locations of images on screen:
		pentaX1 = 200;
		pentaY1 = 200;
		pentaX2 = 350;
		pentaY2 = 350;
		// reveal parts of strip -- initially first picture on strip
		pentaStripX1 = 0; // this should change to access different image on strip
		pentaStripY1 = 0;
		pentaStripX2 = 694; // this should change to access different image on strip
		pentaStripY2 = 660; 
		// set font:
		//font = new UnicodeFont( new java.awt.Font("Copperplate", java.awt.Font.PLAIN, 14));
		
		// set initial score to zero
		score = 0;
		//goBack=false;
>>>>>>> 95061b2db297b2cb544f0e32ed67c9586d3cb6b1
	}
	
	// update: called every frame update, BEFORE render method
	// should do all calcs and movements etc.. GAME LOGIC GOES HERE
	// 't' ensures objects move at same speed, even with different frame rates
	// USER INPUT GOES HERE
	public void update(GameContainer gc, StateBasedGame sbg, int t) throws SlickException {
		// to get input from game container
		
				
		
		Input input = gc.getInput();
		if (input.isKeyPressed(Input.KEY_1)) pentaSignal = true;
		
		if (pentaSignal){
			iPenta = SIGNAL;
		}
		else{
			iPenta = EMPTY;
		}
		// testing counter increment and decrement based on keyboard input
		
		if (input.isKeyPressed(Input.KEY_0)){
			iPenta = SIGNAL_HIT;
			
			score += 5;
		}
		if (input.isKeyPressed(Input.KEY_9)){
			iPenta = ERROR;
			score -= 5;
		}
		
		/*
		 * 
		 * SOME MOVEMENT CONTROLLED BY D-PAD
		 * 
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_RIGHT)){
			x += 0.1*t;
		}
		if(input.isKeyDown(Input.KEY_LEFT)){
			x -= 0.1*t;
		}
		if(input.isKeyDown(Input.KEY_UP)){
			y -= 0.1*t;
		}
		if(input.isKeyDown(Input.KEY_DOWN)){
			y += 0.1*t;
		}
		*/
		
		// if back is pressed go to HomeScene
		if( input.isKeyDown(Input.KEY_BACK) ){
			// go to home
			sbg.enterState(0);
		}
		/*
		 * SOME AUTOMATIC PENTAGON MOVEMENT HORIZONTAL BACK AND FORTH:
		 * 
		if(x<=400 && goBack==false){
			x += 0.1*t;
		}
		if(x>=400){
			goBack = true;
		}
		if(x<=30){
			goBack= false;
		}
		if (goBack){
			x -=0.2*t;
		}
		
		*/
		
		
		
		
	}
	
	public int getID(){
		return 1;
	}


}
