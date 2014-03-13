import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class NewUser  extends BasicGameState{
	
	private Image Background;
	private boolean continueClick = false;
	private boolean backClick = false;
	
	public NewUser() {
		super();
	}

	
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		Background = new Image("res/Screens/Enter Name.png");		
	}

	public void mousePressed(int button  , int x, int y){
		
		//See if they click continue or back
		if  (320 <= x && x <= 490 && 400 <= y && y <= 450){
			continueClick = true;
			System.out.println( "x = " + x + "  y = " +y);
		}
		if  (10 <= x && x <= 180 && 450 <= y && y <= 580){
			backClick = true;
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
		
		if(  continueClick){
			// go to pick song
			continueClick = false;
			sbg.enterState(6);
		}
		if( input.isKeyPressed(Input.KEY_BACK) || backClick){
			// go to home
			backClick = false;
			sbg.enterState(0);
		}
	}

	
	public int getID() {
		return 2;
	}

}
