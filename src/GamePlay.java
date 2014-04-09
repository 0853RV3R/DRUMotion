


import java.util.Date;
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
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;



// every Slick2D window or game extends from the basic game class, provided by Slick2D
// A basic scene:
public class GamePlay extends GameStateBase<GameData,States>{
	// assign variable to picture
	private Image logo, pause, exitButton;
	private Image square, penta,tri, circle;
	private Animation squareSignal, squareHit, squareError, squareTrans;
	private Animation pentaSignal, pentaHit, pentaError, pentaTrans;
	private Animation triSignal, triHit, triError, triTrans;
	private Animation circleSignal, circleHit, circleError, circleTrans;
	private boolean isPentaSignaled,isPentaHit,isPentaError, isPentaTrans;
	private boolean isSquareSignaled,isSquareHit,isSquareError, isSquareTrans;
	private boolean isTriSignaled,isTriHit,isTriError, isTriTrans;
	private boolean isCircleSignaled,isCircleHit,isCircleError, isCircleTrans;
	private int minDrum, maxDrum, drum;
	private long minTime, maxTime, durationTime,lastFrame;
	GameGenerator2 myGameGen = null;
	Thread myThread =null;
	private int currentDrum = 0;
	private int transDrum = 0;
	Color backgroundColor;
	private boolean exit = false;
	private int timer;
	long timeLeft,pauseTimeMin, pauseTimeSec;
	private int timerLast = 1500;
	private boolean fade, isPaused;
	boolean isInitialized = false;
	private int pauseCount;
	Date date = new Date();
	long  songsec, songmin, sec2, min1, min2, minLeft, secLeft,  initsec, initmin;
//	SerialInput drumPads = null;
	boolean isTimeUp;
	Font font;
	int score;
	int hits, misses;
	Sound drumSound1, drumSound2, drumSound3, drumSound4;
	Music song1, song2, song3, song4;
	public static boolean isDrum1Hit =false;
	public static boolean isDrum2Hit =false;
	public static boolean isDrum3Hit =false;
	public static boolean isDrum4Hit =false;
	
	
	
	public GamePlay(ClientBase<GameData> theClient, States theState) {
		super(theClient, theState);
	}

	//render: called every frame update 
	// render all things calculated in update method and update screen
	// Slick coordinate system: (0,0) is top left corner! --> Y axis extends down, X extends right
	public void render(GameContainer gc, StateBasedGame sgb, Graphics g) throws SlickException {
		
	
		g.setBackground(backgroundColor);
		logo.draw(280,25,450,130);
		exitButton.draw(280,500,200,100);
		
		// render text for score box
		//g.setFont(font);
		g.setColor(Color.blue);
		g.drawString("Score: \n" +"  "+score, 600, 200);
		
		// get the time left
		if (!isPaused){
			
	
			if (getClient().getGameData().getSongName().equals("1")){
				secLeft = (158  +(initsec- System.currentTimeMillis())/1000)%60;
				minLeft = ((158  +(initmin- System.currentTimeMillis())/1000)/60)%60;
				if(secLeft <=9){
					g.drawString("Time Remaining:  " +"  "+ minLeft + ":0" + secLeft, 500, 150);
				}else
					g.drawString("Time Remaining:  " +"  "+ minLeft + ":" + secLeft, 500, 150);
			}
			if (getClient().getGameData().getSongName().equals("2")){
				secLeft = (160  +(initsec- System.currentTimeMillis())/1000)%60;
				minLeft = ((160  +(initmin- System.currentTimeMillis())/1000)/60)%60;
				if(secLeft <=9){
					g.drawString("Time Remaining:  " +"  "+ minLeft + ":0" + secLeft, 500, 150);
				}else
					g.drawString("Time Remaining:  " +"  "+ minLeft + ":" + secLeft, 500, 150);
			}
		}else{
			secLeft = (160  +(initsec- pauseTimeSec)/1000)%60;
			minLeft = ((160  +(initmin- pauseTimeMin)/1000)/60)%60;
			if(secLeft <=9){
				g.drawString("Time Remaining:  " +"  "+ minLeft + ":0" + secLeft, 500, 150);
			}else
				g.drawString("Time Remaining:  " +"  "+ minLeft + ":" + secLeft, 500, 150);
	
		}
	
	
		/*
		 * 
		 * SLOW EVERYTHING DOWN BY 80 MILLIS TO MAKE ANIMATIONS SMOOTHER
		 */
		try { 
			Thread.sleep(80);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
//		// code for score fade in and out
//		  float timerPercent = (float) timer / timerLast;
//	      int alphaPercent = (int) (255 * timerPercent);
//	      g.setColor(new Color(255, 255, 255, alphaPercent));
//	      g.drawString("test", 0, 0);
//	      
	      
		//Square Logic
		
				if(isSquareHit && isSquareSignaled&& !isPaused){ // signaled and hit
					squareSignal.stopAt(1);
					squareHit.draw(50, 400, 150, 150);
					squareHit.stopAt(1);
					isSquareHit = false;
					

				}
				else if (isSquareTrans){
					
					squareTrans.draw(50, 400, 150, 150);
					squareTrans.stopAt(1);
				}
				else if (isSquareSignaled	&& !isPaused){ // signal on
					
					squareSignal.draw(50, 400, 150, 150);
					
				}
				else if (!isSquareSignaled && isSquareError && !isPaused){ // no signal, hit in error
					squareSignal.stopAt(0);
					squareError.draw(50, 400, 150, 150);
					squareError.stopAt(1);
					isSquareError = false;
					
				}
				else if (!isSquareSignaled && !isPaused){  // empty (no signal)
					square.draw(50, 400, 150, 150);
					
				}
		
		
		
		
		//Pentagon Logic
		if(isPentaHit && isPentaSignaled && !isPaused){ // signaled and hit
			pentaSignal.stopAt(1);
			pentaHit.draw(200, 200, 150, 150);
			pentaHit.stopAt(1);
			isPentaHit = false;
			
		}
		
		else if (isPentaTrans){
			pentaTrans.draw(200, 200, 150, 150);
		}
		else if (isPentaSignaled && !isPaused){ // signal on
			
			pentaSignal.draw(200, 200, 150, 150);
			pentaTrans.stopAt(1);
		}
		else if (!isPentaSignaled && isPentaError && !isPaused){ // no signal, hit in error
			pentaSignal.stopAt(0);
			pentaError.draw(200, 200, 150, 150);
			pentaError.stopAt(1);
			isPentaError = false;
			
		}
		else if (!isPentaSignaled && !isPaused){  // empty (no signal)
			penta.draw(200, 200, 150, 150);
				
			}
		//Tri Logic
				if(isTriHit && isTriSignaled && !isPaused){ // signaled and hit -- working!
					triSignal.stopAt(1);
					triHit.draw(400, 300, 150, 150);
					triHit.stopAt(1);
					isTriHit = false;
					
				}
				else if (isTriTrans){
					triTrans.draw(400, 300, 150, 150);
					triTrans.stopAt(1);
				}
				else if (isTriSignaled && !isPaused){ // signal on
					
					triSignal.draw(400, 300, 150, 150);
					
				}
				else if (!isTriSignaled && isTriError && !isPaused){ // no signal, hit in error
					triSignal.stopAt(0);
					triError.draw(400, 300, 150, 150);
					triError.stopAt(1);
					isTriError = false;
					
				}
				else if (!isTriSignaled && !isPaused){  // empty (no signal)
					tri.draw(400, 300, 150, 150);
						
				}		
				// Circle Logic
				if(isCircleHit && isCircleSignaled && !isPaused){ // signaled and hit
					circleSignal.stopAt(1);
					circleHit.draw(600, 400, 150, 150);
					circleHit.stopAt(1);
					isCircleHit = false;
					
				}
				else if (isCircleTrans){
					circleTrans.draw(600, 400, 150, 150);
					circleTrans.stopAt(1);
				}
				else if (isCircleSignaled && !isPaused){ // signal on
					
					circleSignal.draw(600, 400, 150, 150);
					
				}
				else if (!isCircleSignaled && isCircleError && !isPaused){ // no signal, hit in error
					circleSignal.stopAt(0);
					circleError.draw(600, 400, 150, 150);
					circleError.stopAt(1);
					isCircleError = false;
					
				}
				else if (!isCircleSignaled&& !isPaused){  // empty (no signal)
					circle.draw(600, 400, 150, 150);
						
					}	
				
				//draw pause when p is hit
				if (isPaused){
					pause.draw(150,300,450,130);
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
		pause = new Image("res/Buttons/pause.png");
		exitButton = new Image("res/Buttons/Quit.png");
		// set initial score to zero
		score = 0;
		hits = 0;
		misses = 0;
		initSquare();
		initPenta();
		initTri();
		initCircle();

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
		isPaused = false;
		
		
	}// end of init method
	
	public void initSquare() throws SlickException {
		isSquareSignaled = false;
		// square Empty --> Unpressed (signal)
			Image [] squareSignalImages = { new Image("res/Drums/square empty.png"), new Image("res/Drums/square unpressed.png")};
			int [] squareSignalDurations = {200, 900000};
			squareSignal = new Animation(squareSignalImages, squareSignalDurations, false);
			
			Image [] squareTransImages = { new Image("res/Drums/white.png"), new Image("res/Drums/square empty.png")};
			int [] squareTransDurations = {200, 200};
			squareTrans = new Animation(squareTransImages, squareTransDurations, false);
			
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
				
				Image [] pentaTransImages = { new Image("res/Drums/white.png"), new Image("res/Drums/pentagon empty.png")};
				int [] pentaTransDurations = {200, 200};
				pentaTrans = new Animation(pentaTransImages, pentaTransDurations, false);
				
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
			
			Image [] triTransImages = { new Image("res/Drums/white.png"), new Image("res/Drums/triangle empty.png")};
			int [] triTransDurations = {200, 200};
			triTrans = new Animation(triTransImages, triTransDurations, false);
			
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
			
			Image [] circleTransImages = { new Image("res/Drums/white.png"), new Image("res/Drums/circle empty.png")};
			int [] circleTransDurations = {200, 200};
			circleTrans = new Animation(circleTransImages, circleTransDurations, false);
			
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
			if (getClient().getGameData().getSongName().equals("1")){
				song1.play(); // play song 1
				
				// set up the timer for song 1
				secLeft = 1;
				minLeft = 1;
				
				songmin = 2;
				songsec = 38;
				
				initsec = System.currentTimeMillis();
				initmin = System.currentTimeMillis();
			}
			if (getClient().getGameData().getSongName().equals("2")){
				song2.play(); // play song 2
				
				//set up the timer for the song 2
				secLeft = 1;
				minLeft = 1;
				
				songmin = 2;
				songsec = 40;
				
				initsec = System.currentTimeMillis();
				initmin = System.currentTimeMillis();
			}
			
			
			
			Executor executor = Executors.newSingleThreadExecutor();
			executor.execute(myGameGen);// start gamegen
			
//			drumPads = new SerialInput();
//			drumPads.initialize();
			System.out.println("Started DrumPads");
			
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
				if( input.isKeyDown(Input.KEY_BACK) || exit ){
//					drumPads.close();
					exit = false;
					endGameToUserScreen(sbg);
				}

		
	
				// if game is done (countdown timer is done), go to Stats screen
				if( secLeft < 0 && isInitialized){

					
					endGameToResults(sbg);
				}

				
				/*
				//if p is pressed go pause the game
				if( input.isKeyDown(Input.KEY_P) ){
					
						song2.pause();
						isPaused = true;
						System.out.println("Game Paused");
						
						pauseTimeMin = System.currentTimeMillis();
						pauseTimeSec = System.currentTimeMillis();
				}	
				if(input.isKeyDown(Input.KEY_ENTER) && isPaused){
					song2.resume();
					isPaused = false;
					System.out.println("Game back On");
				}
	*/

				
				

		// to adjust animations to frame rate
		squareSignal.update(t);
		squareError.update(t);
		squareHit.update(t);
		//squareTrans.update(t);
			
	    pentaSignal.update(t);
		pentaError.update(t);
		pentaHit.update(t);
		//pentaTrans.update(t);
		
		triSignal.update(t);
		triError.update(t);
		triHit.update(t);		
		//triTrans.update(t);
		
		circleSignal.update(t);
		circleError.update(t);
		circleHit.update(t);	
		//circleTrans.update(t);
		
		try {
			if(transDrum != myGameGen.getNextDrum()){ // if changed then..
				if(myGameGen.getNextDrum() != myGameGen.getDrum()){
				setTrans(myGameGen.getNextDrum()); // show next drum transition
				}
			}
			if(currentDrum != myGameGen.getDrum()){ // if current drum is not the same as generated then reset signal
				
				
				resetDrums();//turn all signals to off
			}
			setDrums(myGameGen.getDrum());// show NEW drum signal based on generated signal
			currentDrum = myGameGen.getDrum();
			transDrum = myGameGen.getNextDrum();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		/*
		 * INPUT FROM ACTUAL DRUM HARDWARE
		 * 
		 */
		
		/*
		// square
				if (isDrum1Hit){
					if(isSquareSignaled){
						drumSound1.playAt(-1,0,0);
						score += 1;
						hits++;
						isSquareHit = true;// animation on
						
					}
					if(!isSquareSignaled){
						if (score>= 2){
						score -= 2;
						}
						misses++;
						isSquareError = true;// animation on
					}
					isDrum1Hit = false;
				}
				// pentagon
				if (isDrum2Hit){
					if(isPentaSignaled){
						drumSound2.playAt(0,1,0);
						score += 1;
						hits++;
						isPentaHit = true;// animation on
					}
					if(!isPentaSignaled){
						if (score>= 2){
							score -= 2;
							}
						misses++;
						isPentaError = true;// animation on
					}
					isDrum2Hit = false;
				}
				// triangle
				if (isDrum3Hit){
					if(isTriSignaled){
						drumSound3.playAt(0,0.5f,0.5f);
						score += 1;
						hits++;
						isTriHit = true;// animation on
					}
					if(!isTriSignaled){
						if (score>= 2){
							score -= 2;
							}
						misses++;
						isTriError = true;// animation on
					}
					isDrum3Hit = false;
				}
				// circle
				if (isDrum4Hit){
					if(isCircleSignaled){
						drumSound4.playAt(0,0,-1);
						score += 1;
						hits++;
						isCircleHit = true;// animation on
					}
					if(!isCircleSignaled){
						if (score>= 2){
							score -= 2;
							}
						misses++;
						isCircleError = true;// animation on
					}
					isDrum4Hit = false;
				}
		*/
		

		
//		 * THE FOLLOWING SIMULATES INPUT FROM DRUM PADS via keyboard 
//		 * score is increased by 1 and drum sound is played if correct drum is hit
//		 * score is decreased by 5 if incorrect drum is hit
//		 *
		

		/*
		 * THE FOLLOWING SIMULATES INPUT FROM DRUM PADS via keyboard 
		 * score is increased by 1 and drum sound is played if correct drum is hit
		 * score is decreased by 5 if incorrect drum is hit
		 *
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
				if (score>= 2){
					score -= 2;
					}
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
				if (score>= 2){
					score -= 2;
					}
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
				if (score>= 2){
					score -= 2;
					}
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
				if (score>= 2){
					score -= 2;
					}
				misses++;
				isCircleError = true;// animation on
			}

		}
	
	
	


		
				
	}// end method update()
	
	private void endGameToUserScreen(StateBasedGame sbg) {
		 		// TODO Auto-generated method stub
		 		song2.pause(); // pause song
		 		// end game generator while loop
		 		myGameGen.kill(); //indicates target thread should stop running
		 		score = 0;
		 		hits = 0;
		 		misses = 0;

//		 		drumPads.close();
		 		isInitialized = false;
		 		
		 		// go to user page
		 		sbg.enterState(6);
		 	}

		 
		 	private void endGameToResults(StateBasedGame sbg) {
		 		// TODO Auto-generated method stub
		 		song2.pause(); // pause song
		 		
		 		//update+store GameData for User
		 		getClient().getGameData().setHits(hits);
		 		getClient().getGameData().setMisses(misses);
		 		getClient().getGameData().setCurrentScore(score);
		 		
		 	// end game generator while loop
		 		myGameGen.kill(); //indicates target thread should stop running
		 		score = 0;
		 		hits = 0;
		 		misses = 0;
//**		 		drumPads.close();
		 		isInitialized = false;
		 		
		 		// go to stats page
		 		sbg.enterState(4);
		 		//sbg.enterState(4, new FadeOutTransition(),new FadeInTransition());
		 	}

		 	public void setTrans(int drum) throws InterruptedException{
				
				switch (drum){
				case 1: isSquareTrans = true;
					break;
				case 2: isPentaTrans= true;
					break;
				case 3: isTriTrans = true;
					break;
				case 4: isCircleTrans = true;
					break;
				
				}// end switch
				
				
				
				
			}// end method setDrums()
		
		


	
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
		isSquareTrans=false;
		isPentaSignaled = false;
			isPentaTrans = false;
		isTriSignaled = false;
			isTriTrans = false;
		 isCircleSignaled = false;
		isCircleTrans = false;
		
		
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


	public void mousePressed(int arg, int x, int y) {

		System.out.println( "x = " + x + "  y = " +y);

		
		//Check which user they select
		if  (280<= x && x <= 480 && 500 <= y && y <= 600){
			exit = true;
			System.out.println( "x = " + x + "  y = " +y);
		}
		
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
