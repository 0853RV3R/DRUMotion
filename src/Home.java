import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Home  extends BasicGameState{
	
	private Image HomeLogo;
	private Image NewUser;
	private Image ExistingUser;
	private Image ExitButton;
	private float x,y;
	Color backgroundColor;
	
	public Home() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// set background color to white:
		backgroundColor = new Color(255, 250, 250);
		HomeLogo = new Image("res/Logo.png");
		
		NewUser = new Image("res/Buttons/New user button unclicked.png");
		
		ExistingUser = new Image("res/Buttons/Exit button unclicked.png");
		
		ExitButton = new Image("res/Buttons/Exit button unclicked.png");
		

	}

	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		g.setBackground(backgroundColor);
		HomeLogo.draw(200,10,200,50);
		NewUser.draw(50,200,200,50);
		ExistingUser.draw(500,200,200,50);
		ExitButton.draw(500,500,200,50);
		
	}

	
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
