import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

// every Slick2D window or game extends from the basic game class, provided by Slick2D
// A basic scene:
public class Game extends BasicGame{
	// assign variable to picture
	private Image logo;
	
	
	public Game() {
		super("Game");
		// TODO Auto-generated constructor stub
	}

	//render: called every frame update 
	// render all things calculated in update method and update screen
	// Slick coordinate system: (0,0) is top left corner! --> Y axis extends down, X extends right
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.drawOval(300, 350, 40, 40);
		logo.draw(250,25,350,150);
	}

	
	// init is like a constructor for Slick 2D
	// called before the start - only called once
	// do the picture loading/ import images here!
	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		logo = new Image("res/Logo.png");
	}
	
	// update: called every frame update, before render method
	// should do all calcs and movements etc.. GAME LOGIC GOES HERE
	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}

}
