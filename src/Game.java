import org.lwjgl.input.Mouse;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

// every Slick2D window or game extends from the basic game class, provided by Slick2D
// A basic scene:
public class Game extends BasicGameState{
	// assign variable to picture
	private Image logo;
	private Image penta;
	private Image exitButton;
	private float x,y;
	boolean goBack;
	private int posX = Mouse.getX();
	private int posY = Mouse.getY();
	
	public Game() {
		super();
		// TODO Auto-generated constructor stub
	}

	//render: called every frame update 
	// render all things calculated in update method and update screen
	// Slick coordinate system: (0,0) is top left corner! --> Y axis extends down, X extends right
	public void render(GameContainer gc, StateBasedGame sgb, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.drawOval(300, 350, 80, 80);
		logo.draw(180,25,450,130);
		penta.draw(x,y,150,150);
		
		//test
		g.drawString("position x,y " + posX, posX, posY);

		
		exitButton.draw(500,500,200,50);
	}

	
	// init is like a constructor for Slick 2D
	// called before the start - only called once
	// do the picture loading/ import images here!
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// associate variables to image files
		
		logo = new Image("res/Logo.png");
		penta = new Image("res/pentagon empty.png");
		x = 30;
		y= 200;
		goBack=false;
		
		exitButton = new Image("res/Exit button unclicked.png");
		x = 800;
		y = 600;
		
		
	}
	
	// update: called every frame update, before render method
	// should do all calcs and movements etc.. GAME LOGIC GOES HERE
	// 't' ensures objects move at same speed, even with different frame rates
	// USER INPUT GOES HERE
	public void update(GameContainer gc, StateBasedGame sbg, int t) throws SlickException {
		// to get input from game container
		
				
		
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_RIGHT)){
			x += 0.1*t;
		}
		if(input.isKeyDown(Input.KEY_LEFT)){
			x -= 0.1*t;
		}
		if(input.isKeyDown(Input.KEY_UP)){
			y -= 0.1*t;
		}
		if(input.isKeyDown(Input.KEY_DOWN)){
			y += 0.1*t;
		}
		
		// if back is pressed go to HomeScene
		if( input.isKeyDown(Input.KEY_BACK) ){
			// go to home
			sbg.enterState(0);
		}
		/*
		 * SOME AUTOMATIC PENTAGON MOVEMENT HORIZONTAL BACK AND FORTH:
		 * 
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
		
		*/
		
		
		
		
	}
	
	public int getID(){
		return 1;
	}


}
