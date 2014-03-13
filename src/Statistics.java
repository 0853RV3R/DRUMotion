import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Statistics  extends BasicGameState{
	
	private Image HomeLogo;

	private float x,y;
	Color backgroundColor;
	
	public Statistics() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// set background color to white:
		backgroundColor = new Color(255, 250, 250);
		HomeLogo = new Image("res/Logo.png");
		
		

	}

	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		g.setBackground(backgroundColor);
		HomeLogo.draw(200,10,200,50);
		
		//test
		g.drawString("Login", 0, 0);

		
	}

	
	public void update(GameContainer gc, StateBasedGame sbg, int arg2)
			throws SlickException {
		
		Input input = gc.getInput();
		
		if( input.isKeyPressed(Input.KEY_BACK) ){
			// go to home
			sbg.enterState(0);
		}
	}

	
	public int getID() {
		return 4;
	}

}
