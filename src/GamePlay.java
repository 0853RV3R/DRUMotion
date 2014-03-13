


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
	private Image square, penta,tri, circle;
	private Animation squareSignal, squareHit, squareError;
	private Animation pentaSignal, pentaHit, pentaError;
	private Animation triSignal, triHit, triError;
	private Animation circleSignal, circleHit, circleError;
	private boolean isPentaSignaled,isPentaHit,isPentaError;
	private boolean isSquareSignaled,isSquareHit,isSquareError;
	private boolean isTriSignaled,isTriHit,isTriError;
	private boolean isCircleSignaled,isCircleHit,isCircleError;
	private int minDrum, maxDrum, drum;
	private long minTime, maxTime, durationTime;
	GameGenerator2 myGameGen = new GameGenerator2(1);
	Thread myThread = new Thread(myGameGen);
	private int currentDrum = 0;
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
		
		
		/*
		 * 
		 * SLOW EVERYTHING DOWN BY 100 MILLIS TO MAKE ANIMATIONS SMOOTHER
		 */
		try { 
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//Square Logic
		
				if(isSquareHit && isSquareSignaled){ // signaled and hit
					squareSignal.stopAt(1);
					squareHit.draw(50, 400, 150, 150);
					squareHit.stopAt(1);
					isSquareHit = false;
					
				}
				else if (isSquareSignaled){ // signal on
					
					squareSignal.draw(50, 400, 150, 150);
					
				}
				else if (!isSquareSignaled && isSquareError){ // no signal, hit in error
					squareSignal.stopAt(0);
					squareError.draw(50, 400, 150, 150);
					squareError.stopAt(1);
					isSquareError = false;
					
				}
				else if (!isSquareSignaled){  // empty (no signal)
					square.draw(50, 400, 150, 150);
					
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
		//Tri Logic
				if(isTriHit && isTriSignaled){ // signaled and hit -- working!
					triSignal.stopAt(1);
					triHit.draw(400, 300, 150, 150);
					triHit.stopAt(1);
					isTriHit = false;
				}
				else if (isTriSignaled){ // signal on
					
					triSignal.draw(400, 300, 150, 150);
					
				}
				else if (!isTriSignaled && isTriError){ // no signal, hit in error
					triSignal.stopAt(0);
					triError.draw(400, 300, 150, 150);
					triError.stopAt(1);
					isTriError = false;
				}
				else if (!isTriSignaled){  // empty (no signal)
					tri.draw(400, 300, 150, 150);
						
				}		
				// Circle Logic
				if(isCircleHit && isCircleSignaled){ // signaled and hit
					circleSignal.stopAt(1);
					circleHit.draw(600, 400, 150, 150);
					circleHit.stopAt(1);
					isCircleHit = false;
				}
				else if (isCircleSignaled){ // signal on
					
					circleSignal.draw(600, 400, 150, 150);
					
				}
				else if (!isCircleSignaled && isCircleError){ // no signal, hit in error
					circleSignal.stopAt(0);
					circleError.draw(600, 400, 150, 150);
					circleError.stopAt(1);
					isCircleError = false;
				}
				else if (!isCircleSignaled){  // empty (no signal)
					circle.draw(600, 400, 150, 150);
						
					}		 
		
		
		
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
		square = new Image("res/Drums/square empty.png");
		penta = new Image("res/Drums/pentagon empty.png");
		tri = new Image("res/Drums/triangle empty.png");
		circle = new Image("res/Drums/circle empty.png");
		// set initial score to zero
		score = 0;
		initSquare();
		initPenta();
		initTri();
		initCircle();
		
		myThread.start();
		
		
		// set font:
		//font = new UnicodeFont( new java.awt.Font("Copperplate", java.awt.Font.PLAIN, 14));
		
		
	}
	public void initSquare() throws SlickException {
		isSquareSignaled = false;
		// square Empty --> Unpressed (signal)
			Image [] squareSignalImages = { new Image("res/Drums/square empty.png"), new Image("res/Drums/square unpressed.png")};
			int [] squareSignalDurations = {200, 50000};
			squareSignal = new Animation(squareSignalImages, squareSignalDurations, false);
			
			
			
			// square Unpressed --> Pressed (hit)
			Image [] squareHitImages = { new Image("res/Drums/square unpressed.png"), new Image("res/Drums/square pressed.png")};
			int [] squareHitDurations = {50, 200};
			squareHit = new Animation(squareHitImages, squareHitDurations, false);
			
			
			// square Empty --> Red (error)
			Image [] squareErrorImages = { new Image("res/Drums/square empty.png"), new Image("res/Drums/square red.png")};
			int [] squareErrorDurations = {50, 300};
			squareError = new Animation(squareErrorImages, squareErrorDurations, false);
}
	public void initPenta() throws SlickException {
		isPentaSignaled = false;
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
	}
	public void initTri() throws SlickException {
		isTriSignaled = false;
		// triangle Empty --> Unpressed (signal)
			Image [] triSignalImages = { new Image("res/Drums/triangle empty.png"), new Image("res/Drums/triangle unpressed.png")};
			int [] triSignalDurations = {200, 50000};
			triSignal = new Animation(triSignalImages, triSignalDurations, false);
			
			
			
			// triangle Unpressed --> Pressed (hit)
			Image [] triHitImages = { new Image("res/Drums/triangle unpressed.png"), new Image("res/Drums/triangle pressed.png")};
			int [] triHitDurations = {50, 200};
			triHit = new Animation(triHitImages, triHitDurations, false);
			
			
			// triangle Empty --> Red (error)
			Image [] triErrorImages = { new Image("res/Drums/triangle empty.png"), new Image("res/Drums/triangle red.png")};
			int [] triErrorDurations = {50, 300};
			triError = new Animation(triErrorImages, triErrorDurations, false);
}
	public void initCircle() throws SlickException {
		isCircleSignaled = false;
		// circle Empty --> Unpressed (signal)
			Image [] circleSignalImages = { new Image("res/Drums/circle empty.png"), new Image("res/Drums/circle unpressed.png")};
			int [] circleSignalDurations = {200, 50000};
			circleSignal = new Animation(circleSignalImages, circleSignalDurations, false);
			
			
			
			// circle Unpressed --> Pressed (hit)
			Image [] circleHitImages = { new Image("res/Drums/circle unpressed.png"), new Image("res/Drums/circle pressed.png")};
			int [] circleHitDurations = {50, 200};
			circleHit = new Animation(circleHitImages, circleHitDurations, false);
			
			
			// pentagon Empty --> Red (error)
			Image [] circleErrorImages = { new Image("res/Drums/circle empty.png"), new Image("res/Drums/circle red.png")};
			int [] circleErrorDurations = {50, 300};
			circleError = new Animation(circleErrorImages, circleErrorDurations, false);
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
		
		squareSignal.update(t);
		squareError.update(t);
		squareHit.update(t);	
			
	    pentaSignal.update(t);
		pentaError.update(t);
		pentaHit.update(t);
		
		triSignal.update(t);
		triError.update(t);
		triHit.update(t);		
		
		circleSignal.update(t);
		circleError.update(t);
		circleHit.update(t);	
		
		
		
		/*
		 * 
		 * THE FOLLOWING SIMULATES SIGNALS TO HIT DRUMS
		 */
		/*
		if (input.isKeyPressed(Input.KEY_A)) isSquareSignaled = false;// animation off
		if (input.isKeyPressed(Input.KEY_Z)) isSquareSignaled = true;// animation on
		
		if (input.isKeyPressed(Input.KEY_S)) isPentaSignaled = false;// animation off
		if (input.isKeyPressed(Input.KEY_X)) isPentaSignaled = true;// animation on
		if (input.isKeyPressed(Input.KEY_D)) isTriSignaled = false;// animation off
		if (input.isKeyPressed(Input.KEY_C)) isTriSignaled = true;// animation on
		if (input.isKeyPressed(Input.KEY_F)) isCircleSignaled = false;// animation off
		if (input.isKeyPressed(Input.KEY_V)) isCircleSignaled = true;// animation on
		
		*/
		/*try {
			Thread.sleep(50);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		
		try {
			if(currentDrum != myGameGen.getDrum()){
				resetDrums();
			}
			setDrums(myGameGen.getDrum());
			currentDrum = myGameGen.getDrum();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		 * THE FOLLOWING SIMULATES INPUT FROM DRUM PADS via keyboard 
		 * 
		 */
		
		// square
		if (input.isKeyPressed(Input.KEY_7)){
			if(isSquareSignaled){
				score += 5;
				isSquareHit = true;// animation on
			}
			if(!isSquareSignaled){
				score -= 5;
				isSquareError = true;// animation on
			}

		}
		// pentagon
		if (input.isKeyPressed(Input.KEY_8)){
			if(isPentaSignaled){
				score += 5;
				isPentaHit = true;// animation on
			}
			if(!isPentaSignaled){
				score -= 5;
				isPentaError = true;// animation on
			}

		}
		// triangle
		if (input.isKeyPressed(Input.KEY_9)){
			if(isTriSignaled){
				score += 5;
				isTriHit = true;// animation on
			}
			if(!isTriSignaled){
				score -= 5;
				isTriError = true;// animation on
			}

		}
		// circle
		if (input.isKeyPressed(Input.KEY_0)){
			if(isCircleSignaled){
				score += 5;
				isCircleHit = true;// animation on
			}
			if(!isCircleSignaled){
				score -= 5;
				isCircleError = true;// animation on
			}

		}
		
		
		
		
		
	} // end method update()
	
	
	public void setDrums(int drum) throws InterruptedException{
		
		switch (drum){
		case 1: isSquareSignaled = true;
			break;
		case 2: isPentaSignaled = true;
			break;
		case 3: isTriSignaled = true;
			break;
		case 4: isCircleSignaled = true;
			break;
		
		}// end switch
		
		
		/*
		switch (drum){ // restore
		case 1: isSquareSignaled = false;
			break;
		case 2: isPentaSignaled = false;
			break;
		case 3: isTriSignaled = false;
			break;
		case 4: isCircleSignaled = false;
			break;
		
		}// end switch
		*/
		
	}// end method setDrums()
	
	public void resetDrums(){
		
		 isSquareSignaled = false;
			
		isPentaSignaled = false;
			
		isTriSignaled = false;
			
		 isCircleSignaled = false;
		
		
		
	}// end method resetDrums()
	
	public int getID(){
		return 1;
	}


} // end class GamePlay
