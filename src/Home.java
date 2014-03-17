import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Home  extends BasicGameState{
	
	private Image Background;
	private boolean newUserClick, exitClick = false;
	private boolean loginClick = false;
	
	public Home() {
		super();
	}

	
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		Background = new Image("res/Screens/Home Screen.png");		
	}

	public void mousePressed(int button  , int x, int y){
		
		//test listener
		
		System.out.println( "Mouse Pressed at: "+"x = " + x + ",  y = " +y);

		//See if they click continue or back
		if  (125 <= x && x <= 375 && 255 <= y && y <= 405){
			newUserClick = true;
			System.out.println( "x = " + x + "  y = " +y);
		}
		if  (440 <= x && x <= 690 && 255 <= y && y <= 404){
			loginClick = true;
			System.out.println( "x = " + x + "  y = " +y);
		}
		if  (10 <= x && x <= 180 && 490 <= y && y <= 590){
			exitClick = true;
			System.out.println( "x = " + x + "  y = " +y);
		}
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		
		//Draw Background
		g.drawImage(Background, 0,0 ,800, 600,0,0,1350,770);
	}

	
	public void update(GameContainer gc, StateBasedGame sbg, int arg2)
			throws SlickException {
		
		Input input = gc.getInput();
		
		if(  newUserClick){
			// go to pick song
			newUserClick = false;
			sbg.enterState(2);
		}
		if( loginClick){
			// go to home
			loginClick = false;
			sbg.enterState(3);
		}
		if( exitClick){
			// quit game
			gc.exit();
		}
	}

	
	public int getID() {
		return 0;
	}

}
