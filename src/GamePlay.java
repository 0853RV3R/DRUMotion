


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;



// every Slick2D window or game extends from the basic game class, provided by Slick2D
// A basic scene:
public class GamePlay extends GameStateBase<GameData,States>{
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
	private long minTime, maxTime, durationTime,lastFrame;
	GameGenerator2 myGameGen = null;
	Thread myThread =null;
	private int currentDrum = 0;
	Color backgroundColor;
	private boolean alert = false;
	private int timer, song1Timer, song2Timer;
	private int timerLast = 1500;
	boolean fade;
	boolean isInitialized = false;
	
	Font font;
	int score;
	int hits, misses;
	Sound drumSound1, drumSound2, drumSound3, drumSound4;
	Music song1, song2, song3, song4;
	
	
	
	
	public GamePlay(ClientBase<GameData> theClient, States theState) {
		super(theClient, theState);
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
		g.drawString("Time Remaining:  " +"  "+ time, 650, 135);
				
		/*
		 * 
		 * SLOW EVERYTHING DOWN BY 80 MILLIS TO MAKE ANIMATIONS SMOOTHER
		 */
		try { 
			Thread.sleep(80);
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
					
					
					// code for score fade in and out
					float timerPercent = (float) timer / timerLast;
				      int alphaPercent = (int) (255 * timerPercent);
				      g.setColor(new Color(255, 255, 255, alphaPercent));
				      g.drawString("test", 0, 0);

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
		hits = 0;
		misses = 0;
		initSquare();
		initPenta();
		initTri();
		initCircle();
		song1Timer = 158;
		song2Timer = 160;
		
		
		
		/*
		 * Initialize Thread
		 */
		//initGameThread();
		
		// set font:
		//font = new UnicodeFont( new java.awt.Font("Copperplate", java.awt.Font.PLAIN, 14));
		
		/*
		 * 
		 * Music & Sound 
		 */
		initSounds();
		initMusic();
		
		
	}
	public void initSquare() throws SlickException {
		isSquareSignaled = false;
		// square Empty --> Unpressed (signal)
			Image [] squareSignalImages = { new Image("res/Drums/square empty.png"), new Image("res/Drums/square unpressed.png")};
			int [] squareSignalDurations = {200, 900000};
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
				int [] pentaSignalDurations = {200, 900000};
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
			int [] triSignalDurations = {200, 900000};
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
			int [] circleSignalDurations = {200, 900000};
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
	
	public void initSounds() throws SlickException{
		drumSound1 = new Sound("res/Sounds/VEH2-Perc-041.ogg");
		drumSound2 = new Sound("res/Sounds/VEH2-Perc-042.ogg");
		drumSound3 = new Sound("res/Sounds/VEH2-Perc-043.ogg");
		drumSound4 = new Sound("res/Sounds/VEH2-Perc-044.ogg");
	}
	
	public void initMusic() throws SlickException{
		song2 =new Music("res/Music/Younevercantell.ogg");
		song1 = new Music("res/Music/Spindizzy.ogg");
	}
	
	public void initGameGen(){
		System.out.println("**initGameGen()**");
			if (myGameGen == null ){
			myGameGen = new GameGenerator2(1);// runnable task
			System.out.println("New Runnable Instance Made");
			}
			else if(!myGameGen.isRunning()){
				myGameGen.revive();
				
			}
	}
	
	
	public long getTime() {
		return System.nanoTime() / 1000000;
	}
	
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		
		return delta;
	}
	
	// update: called every frame update, BEFORE render method
	// should do all calcs and movements etc.. GAME LOGIC GOES HERE
	// 't' ensures objects move at same speed, even with different frame rates
	// USER INPUT GOES HERE
	public void update(GameContainer gc, StateBasedGame sbg, int t) throws SlickException {
		
		/* thread business:
		 If game state is correct Game Generator is null, then initialize it and start execution
		
		*/
		
		
		if (sbg.getCurrentStateID() == 1 && !isInitialized){ 
			System.out.println("***Starting Thread in GamePlay***");
			initGameGen(); // if myGameGen is null, initialize it
			System.out.println("song picked: song "+ getClient().getGameData().getSongName());
			if (getClient().getGameData().getSongName().equals("1")) song1.play(); // play song 1
			if (getClient().getGameData().getSongName().equals("2")) song2.play(); // play song 2
			
			
			
			
			
			Executor executor = Executors.newSingleThreadExecutor();
			executor.execute(myGameGen);// start gamegen
			
			isInitialized = true;
			
			}
		
		
		if (sbg.getCurrentStateID() != 1)
			{
				System.out.println("Current State ID is " +sbg.getCurrentStateID() + ", not 1 -- thread will not start");
			}
		
		
		/*
		 * code for score fade in and out
		 * 
		 * 
		if (fade) {
	         timer += t;
	         if (timer > timerLast) {
	            fade = !fade;
	         }
	      } else {
	         timer -= t;
	         if (timer < 0) {
	            fade = !fade;
	         }
	      }
		*/ 
		
		// to get input from game container
		Input input = gc.getInput();
		
		
		
		
		// if back is pressed go to Stats screen
				if( input.isKeyDown(Input.KEY_BACK) ){
					song2.pause(); // pause song
					
					//update GameData for User
					getClient().getGameData().setHits(getClient().getGameData().getHits() + hits);
					getClient().getGameData().setMisses( getClient().getGameData().getMisses() + misses);
					getClient().getGameData().setCurrentScore( getClient().getGameData().getCurrentScore() + score);
					
					// end game generator while loop
					myGameGen.kill(); //indicates target thread should stop running
					score = 0;
					hits = 0;
					misses = 0;
						
					isInitialized = false;
					
					// go to stats page
					sbg.enterState(4);
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
		 * score is increased by 1 and drum sound is played if correct drum is hit
		 * score is decreased by 5 if incorrect drum is hit
		 */
		
		// square
		if (input.isKeyPressed(Input.KEY_7)){
			if(isSquareSignaled){
				drumSound1.playAt(-1,0,0);
				score += 1;
				hits++;
				isSquareHit = true;// animation on
				
			}
			if(!isSquareSignaled){
				score -= 5;
				misses++;
				isSquareError = true;// animation on
			}

		}
		// pentagon
		if (input.isKeyPressed(Input.KEY_8)){
			if(isPentaSignaled){
				drumSound2.playAt(0,1,0);
				score += 1;
				hits++;
				isPentaHit = true;// animation on
			}
			if(!isPentaSignaled){
				score -= 5;
				misses++;
				isPentaError = true;// animation on
			}

		}
		// triangle
		if (input.isKeyPressed(Input.KEY_9)){
			if(isTriSignaled){
				drumSound3.playAt(0,0.5f,0.5f);
				score += 1;
				hits++;
				isTriHit = true;// animation on
			}
			if(!isTriSignaled){
				score -= 5;
				misses++;
				isTriError = true;// animation on
			}

		}
		// circle
		if (input.isKeyPressed(Input.KEY_0)){
			if(isCircleSignaled){
				drumSound4.playAt(0,0,-1);
				score += 1;
				hits++;
				isCircleHit = true;// animation on
			}
			if(!isCircleSignaled){
				score -= 5;
				misses++;
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

	/*
	 * METHODS I WAS FORCED TO ADD:
	 * */
	
	@Override
	public void enter(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leave(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputEnded() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputStarted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setInput(Input arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(int arg0, char arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(int arg0, char arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerButtonPressed(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerButtonReleased(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerDownPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerDownReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerLeftPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerLeftReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerRightPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerRightReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerUpPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerUpReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}


} // end class GamePlay
