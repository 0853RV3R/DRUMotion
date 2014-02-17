import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

// every Slick2D window or game extends from the basic game class, provided by Slick2D
// A basic scene:
public class Game extends BasicGame{

	
	
	public Game() {
		super("Game");
		// TODO Auto-generated constructor stub
	}

	//render: called every frame update 
	// render all things calculated in update method and update screen
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.drawOval(100, 50, 200, 200);
	}

	
	// init is like a constructor for Slick 2D
	// called before the start - only called once
	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
	// update: called every frame update, before render method
	// should do all calcs and movements etc.. GAME LOGIC GOES HERE
	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}

}
