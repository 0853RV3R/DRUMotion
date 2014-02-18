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
	private Image penta;
	private float x,y;
	boolean goBack;
	
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
		g.drawOval(300, 350, 80, 80);
		logo.draw(180,25,450,130);
		penta.draw(x,y,150,150);
	}

	
	// init is like a constructor for Slick 2D
	// called before the start - only called once
	// do the picture loading/ import images here!
	@Override
	public void init(GameContainer gc) throws SlickException {
		// associate variables to image files
		logo = new Image("res/Logo.png");
		penta = new Image("res/pentagon empty.png");
		x = 30;
		y= 200;
		goBack=false;
	}
	
	// update: called every frame update, before render method
	// should do all calcs and movements etc.. GAME LOGIC GOES HERE
	// 't' ensures objects move at same speed, even with different frame rates
	@Override
	public void update(GameContainer gc, int t) throws SlickException {
		if(x<=400 && goBack==false){
			x += 0.1*t;
		}
		if(x>=400){
			goBack = true;
		}
		if(x<=30){
			goBack= false;
		}
		if (goBack){
			x -=0.2*t;
		}
		
		
		
		
	}

}
