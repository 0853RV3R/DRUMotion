

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.gui.TextField;

// every Slick2D window or game extends from the basic game class, provided by Slick2D
// A basic scene:
public class Game extends BasicGame{
	// assign variable to picture
	private Image logo;
	private Image penta;
	private float pentaX1,pentaY1,pentaX2,pentaY2,pentaStripX1,pentaStripY1,pentaStripX2,pentaStripY2;
	Color backgroundColor;
	//boolean goBack;
	
	Font font;
	int score;
	TrueTypeFont ttf;
	
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
		g.setBackground(backgroundColor);
		logo.draw(280,25,450,130);
		penta.draw(pentaX1,pentaY1,pentaX2,pentaY2,pentaStripX1,pentaStripY2);
		
		// render text for score box
		//g.setFont(font);
		g.setColor(Color.blue);
		g.drawString("Score: \n" +"  "+score, 650, 150);
		
	}

	
	// init is like a constructor for Slick 2D
	// called before the start - only called once
	// do the picture loading/ import images here!
	@Override
	public void init(GameContainer gc) throws SlickException {
		// set background color to white:
		backgroundColor = new Color(255, 250, 250);
		// associate variables to image files:
		logo = new Image("res/Logo.png");
		penta = new Image("res/Drums/pentagon empty.png");
		
		// locations of images on screen:
		pentaX1 = 200;
		pentaY1 = 200;
		pentaX2 = 150;
		pentaY2 = 150;
		// set font:
		//font = new UnicodeFont( new java.awt.Font("Copperplate", java.awt.Font.PLAIN, 14));
		
		// set initial score to zero
		score = 0;
		//goBack=false;
	}
	
	// update: called every frame update, BEFORE render method
	// should do all calcs and movements etc.. GAME LOGIC GOES HERE
	// 't' ensures objects move at same speed, even with different frame rates
	// USER INPUT GOES HERE
	@Override
	public void update(GameContainer gc, int t) throws SlickException {
		// to get input from game container
		
		
		
		// testing counter increment and decrement based on keyboard input
		Input input = gc.getInput();
		if (input.isKeyPressed(Input.KEY_0)){
			score += 5;
		}
		if (input.isKeyPressed(Input.KEY_9)){
			score -= 5;
		}
		
		/*
		 * 
		 * SOME MOVEMENT CONTROLLED BY D-PAD
		 * 
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
		*/
		
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

}
