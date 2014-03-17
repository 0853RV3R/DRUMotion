import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class PickSong extends BasicGameState{
	
	private Image Background;
	private boolean continueClick = false;
	private boolean backClick, song1Click, song2Click, song3Click, song4Click, upClick, downClick = false;
	
	public PickSong() {
		super();
	}

	
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		Background = new Image("res/Screens/Pick a Song.png");		
	}

	public void mousePressed(int button  , int x, int y){
		
		//test listner
		
		System.out.println( "x = " + x + "  y = " +y);
		
		//See where the user clicked
		if  (320 <= x && x <= 490 && 485 <= y && y <= 535){
			continueClick = true;
			System.out.println( "x = " + x + "  y = " +y );
		}
		if  (10 <= x && x <= 180 && 450 <= y && y <= 580){
			backClick = true;
			System.out.println( "x = " + x + "  y = " +y);
		}
		if  (175 <= x && x <= 645 && 260 <= y && y <= 315){
			song1Click = true;
			System.out.println( "x = " + x + "  y = " +y+ "/n song 1");
		}
		if  (175 <= x && x <= 645 && 316 <= y && y <= 370){
			song2Click = true;
			System.out.println( "x = " + x + "  y = " +y+ "/n song 2");
		}
		if  (175 <= x && x <= 645 && 371 <= y && y <= 435){
			song3Click = true;
			System.out.println( "x = " + x + "  y = " +y+ "/n song 3");
		}
		if  (175 <= x && x <= 645 && 436 <= y && y <= 490){
			song4Click = true;
			System.out.println( "x = " + x + "  y = " +y+ "/n song 4");
		}
		
		// add this if we add more songs and need to scroll
//		if  (650 <= x && x <= 700 && 260 <= y && y <= 315){
//			upClick = true;
//			System.out.println( "x = " + x + "  y = " +y);
//		}
//		if  (650<= x && x <= 700 && 436 <= y && y <= 490){
//			downClick = true;
//			System.out.println( "x = " + x + "  y = " +y);
//		}
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		
		//Draw Background
		g.drawImage(Background, 0,0 ,800, 600,0,0,1350,770);
	}

	
	public void update(GameContainer gc, StateBasedGame sbg, int arg2)
			throws SlickException {
		
		Input input = gc.getInput();
		
		if(  continueClick){
			// go to pick song
			continueClick = false;
			sbg.enterState(9);
		}
		if( input.isKeyPressed(Input.KEY_BACK) || backClick){
			// go to home
			backClick = false;
			sbg.enterState(6);
		}
		if(  song1Click || song2Click || song3Click || song4Click){
			// go to game play
			continueClick = false;
		//	sbg.enterState(0);
		}
		
	}

	
	public int getID() {
		return 5;
	}

}
