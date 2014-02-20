


import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;



// every Slick2D window or game extends from the basic game class, provided by Slick2D
// A basic scene:
public class GamePlay extends BasicGameState{
	// assign variable to picture
	private Image logo;
	private Image penta;
	private Animation pentaSignal, pentaHit, pentaError;
	boolean isPentaSignaled,isPentaHit,isPentaError;
	
	Color backgroundColor;
	//boolean goBack;
	
	Font font;
	int score;
	
	
	
	/*
	 * signals for strip-reveal technique
	 * 
	 * 
	 *
	private float pentaX1,pentaY1,pentaX2,pentaY2,pentaStripX1,pentaStripY1,pentaStripX2,pentaStripY2;
	int iPenta; // to increment through penta PNG strip values [1-4]
	boolean pentaSignal,triSignal,squareSignal,circleSignal;
	final int EMPTY = 1; // not signaled, not hit
	final int SIGNAL = 2;
	final int SIGNAL_HIT = 3; 
	final int ERROR = 4; // they hit the drum, but it was not signaled
	 */
	
	public GamePlay() {
		super();
		// TODO Auto-generated constructor stub
	}

	//render: called every frame update 
	// render all things calculated in update method and update screen
	// Slick coordinate system: (0,0) is top left corner! --> Y axis extends down, X extends right
	public void render(GameContainer gc, StateBasedGame sgb, Graphics g) throws SlickException {
		// TODO Auto-generated method stub

		g.setBackground(backgroundColor);
		logo.draw(280,25,450,130);
		// render text for score box
		//g.setFont(font);
		g.setColor(Color.blue);
		g.drawString("Score: \n" +"  "+score, 650, 150);
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Pentagon Logic
		if(isPentaHit && isPentaSignaled){ // signaled and hit
			pentaSignal.stopAt(1);
			pentaHit.draw(200, 200, 150, 150);
			pentaHit.stopAt(1);
			isPentaHit = false;
		}
		else if (isPentaSignaled){ // signal on
			
			pentaSignal.draw(200, 200, 150, 150);
			
		}
		else if (!isPentaSignaled && isPentaError){ // no signal, hit in error
			pentaSignal.stopAt(0);
			pentaError.draw(200, 200, 150, 150);
			pentaError.stopAt(1);
			isPentaError = false;
		}
		else if (!isPentaSignaled){  // empty (no signal)
			penta.draw(200, 200, 150, 150);
				
			}
		 /*
		else if (!isPentaSignaled){
			penta.draw(200,200,150,150);
		}
		*/
		
		
		
		/* 
		 * using the  strip-reveal technique
		 */
		/*
		// modify the pentaStripX# for showing a portion of the .PNG strip
		pentaStripX1 = 694*(iPenta-1);
		pentaStripX2 = 694*iPenta;
		penta.draw(pentaX1,pentaY1,pentaX2,pentaY2,pentaStripX1,pentaStripY1,pentaStripX2,pentaStripY2);
		*/
		
		
	
		

	}

	
	// init is like a constructor for Slick 2D
	// called before the start - only called once
	// do the picture loading/ import images here!

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// set background color to white:
		backgroundColor = new Color(255, 250, 250);
		// associate variables to image files:
		logo = new Image("res/Logo.png");
		penta = new Image("res/Drums/pentagon empty.png");
		// set initial score to zero
		score = 0;
		
		// pentagon Empty --> Unpressed (signal)
		Image [] pentaSignalImages = { new Image("res/Drums/pentagon empty.png"), new Image("res/Drums/pentagon unpressed.png")};
		int [] pentaSignalDurations = {200, 50000};
		pentaSignal = new Animation(pentaSignalImages, pentaSignalDurations, false);
		
		
		
		// pentagon Unpressed --> Pressed (hit)
		Image [] pentaHitImages = { new Image("res/Drums/pentagon unpressed.png"), new Image("res/Drums/pentagon pressed.png")};
		int [] pentaHitDurations = {50, 200};
		pentaHit = new Animation(pentaHitImages, pentaHitDurations, false);
		
		
		// pentagon Empty --> Red (error)
		Image [] pentaErrorImages = { new Image("res/Drums/pentagon empty.png"), new Image("res/Drums/pentagon red.png")};
		int [] pentaErrorDurations = {50, 300};
		pentaError = new Animation(pentaErrorImages, pentaErrorDurations, false);
		
		/*
		 * using the  strip-reveal technique
		 * 
		 * 
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
		*/
		
		// set font:
		//font = new UnicodeFont( new java.awt.Font("Copperplate", java.awt.Font.PLAIN, 14));
		
		
	}
	
	// update: called every frame update, BEFORE render method
	// should do all calcs and movements etc.. GAME LOGIC GOES HERE
	// 't' ensures objects move at same speed, even with different frame rates
	// USER INPUT GOES HERE
	public void update(GameContainer gc, StateBasedGame sbg, int t) throws SlickException {
		
		// to get input from game container
		Input input = gc.getInput();
		
		// if back is pressed go to HomeScreen
				if( input.isKeyDown(Input.KEY_BACK) ){
					// go to home
					sbg.enterState(0);
				}
		// to adjust animations to frame rate
		pentaSignal.update(t);
		pentaError.update(t);
		pentaHit.update(t);
		
		
		/*
		 * 
		 * THE FOLLOWING SIMULATES INPUT DRUM PADS AND DRUM SIGNALS
		 */
		if (input.isKeyPressed(Input.KEY_1)) isPentaSignaled = false;// animation off
		if (input.isKeyPressed(Input.KEY_2)) isPentaSignaled = true;// animation on
		
		if (input.isKeyPressed(Input.KEY_9) ){
			score += 5;
			isPentaHit = true;// animation on
		}
	
		if (input.isKeyPressed(Input.KEY_6) ){
			score -= 5;
			isPentaError = true;// animation on
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
		
		
	} // end method update()
	
	public int getID(){
		return 1;
	}


} // end class GamePlay
