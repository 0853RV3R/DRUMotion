import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class HomeScreen  extends BasicGameState{
	
	private Image HomeLogo;
	private Image NewUser;
	private Image ExistingUser;
	private Image ExitButton;
	private float x,y;
	
	
	public HomeScreen() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		
		HomeLogo = new Image("res/Logo.png");
		
		NewUser = new Image("res/New user button unclicked.png");
		
		ExistingUser = new Image("res/Exit button unclicked.png");
		
		ExitButton = new Image("res/Exit button unclicked.png");
		

	}

	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		
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
