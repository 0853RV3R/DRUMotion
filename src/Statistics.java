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
	
	private Image Background;
	private boolean backClick, playClick = false;
	int score, hits, misses = 0;
	int percentage;
	
	public Statistics() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		Background = new Image("res/Screens/Results.png");	
		
		
	}

	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		
		//Draw Background
		g.drawImage(Background, 0,0 ,800, 600,0,0,1360,770);
		
		//write stats
		g.setColor(Color.darkGray);
		g.drawString(""+score+"", 536, 275);
		hits = 10;
		misses = 5;
		if ((hits + misses) > 0){
			percentage = hits*100/(hits + misses);
		}
		else
			percentage = -1;
		g.drawString(""+percentage+"%", 536, 324);
		g.drawString(""+hits+"", 536, 366);
		g.drawString(""+misses+"", 536, 413);
	}
	
	public void mousePressed(int button  , int x, int y){
		
		//test listener
		
		System.out.println( "x = " + x + "  y = " +y);

		//See if they click continue or back
		if  (10 <= x && x <= 180 && 450 <= y && y <= 580){
			backClick = true;
			System.out.println( "x = " + x + "  y = " +y);
		}
		if  (630 <= x && x <= 780 && 493 <= y && y <= 590){
			playClick = true;
			System.out.println( "x = " + x + "  y = " +y);
		}
	}

	
	public void update(GameContainer gc, StateBasedGame sbg, int arg2)
			throws SlickException {
		
		Input input = gc.getInput();
		
		if( input.isKeyPressed(Input.KEY_BACK) || backClick){
			// go to home
			backClick = false;
			sbg.enterState(6);
		}
		if( input.isKeyPressed(Input.KEY_BACK) || playClick){
			// go to game
			playClick = false;
			//TODO Change to return to game
			sbg.enterState(6);
		}
	}

	
	public int getID() {
		return 4;
	}

}
