import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Login  extends BasicGameState{
	
	private Image Background;
	private boolean continueClick = false;
	private boolean backClick, user1Click, user2Click, user3Click, user4Click, upClick, downClick = false;
	String User_1, User_2, User_3, User_4, User_5, User_6, du1, du2, du3, du4;
	
	
	public Login() {
		super();
	}

	
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		Background = new Image("res/Screens/Pick a Name.png");	
		
	}

	public void mousePressed(int button  , int x, int y){
		
		//test
		
		System.out.println( "x = " + x + "  y = " +y);

		
		//Check which user they select
		if  (325 <= x && x <= 495 && 485 <= y && y <= 540){
			continueClick = true;
			System.out.println( "x = " + x + "  y = " +y);
		}
		if  (10 <= x && x <= 180 && 490 <= y && y <= 585){
			backClick = true;
			System.out.println( "x = " + x + "  y = " +y);
		}
		if  (175 <= x && x <= 645 && 260 <= y && y <= 315){
			user1Click = true;
			System.out.println( "x = " + x + "  y = " +y);
		}
		if  (175 <= x && x <= 645 && 316 <= y && y <= 370){
			user2Click = true;
			System.out.println( "x = " + x + "  y = " +y);
		}
		if  (175 <= x && x <= 645 && 371 <= y && y <= 435){
			user3Click = true;
			System.out.println( "x = " + x + "  y = " +y);
		}
		if  (175 <= x && x <= 645 && 436 <= y && y <= 490){
			user4Click = true;
			System.out.println( "x = " + x + "  y = " +y);
		}
		if  (650 <= x && x <= 700 && 260 <= y && y <= 315){
			upClick = true;
			System.out.println( "x = " + x + "  y = " +y);
		}
		if  (650<= x && x <= 700 && 436 <= y && y <= 490){
			downClick = true;
			System.out.println( "x = " + x + "  y = " +y);
		}
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		
		//Draw Background
		g.drawImage(Background, 0,0 ,800, 600,0,0,1350,770);
		
		//draw user names
		g.setColor(Color.blue);
		g.drawString(du1, 190, 280);
		g.drawString(du2, 190, 330);
		g.drawString(du3, 190, 385);
		g.drawString(du4, 190, 450);
	}

	
	public void update(GameContainer gc, StateBasedGame sbg, int arg2)
			throws SlickException {
		
		//scroll feature **** have to change this with database
		if (upClick){
			du1 = du2;
			du2 = du3;
			du3 = du4;
			du4 = User_5;
			upClick = false;
		}
		if (downClick){
			du4 = du3;
			du3 = du2;
			du2 = du1;
			du1 = User_6;
			downClick = false;
		}
		
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
		return 3;
	}

}
