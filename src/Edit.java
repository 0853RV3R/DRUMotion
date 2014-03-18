import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Edit extends BasicGameState{
	
	private Image Background;
	private boolean continueClick = false;
	private boolean backClick, resetClick, deleteClick = false;
	String userName;
	MyInputListener listener = new MyInputListener();
//	NewUser temp = new NewUser();
	
	
	public Edit() {
		super();
	}

	
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		Background = new Image("res/Screens/Edit Details.png");	
//		userName = temp.getUser();
		userName = "test person";
	}

	public void mousePressed(int button  , int x, int y){
		
		
		//See where the user clicked
		if  (610 <= x && x <= 780 && 290 <= y && y <= 345){
			continueClick = true;
			System.out.println( "x = " + x + "  y = " +y );
		}
		if  (10 <= x && x <= 180 && 450 <= y && y <= 580){
			backClick = true;
			System.out.println( "x = " + x + "  y = " +y);
		}
		if  (225 <= x && x <= 475 && 400 <= y && y <= 550){
			resetClick = true;
			System.out.println( "x = " + x + "  y = " +y+ "/n song 1");
		}
		if  (525 <= x && x <= 780 && 400 <= y && y <= 550){
			deleteClick = true;
			System.out.println( "x = " + x + "  y = " +y+ "/n song 2");
		}
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		
		//Draw Background
		g.drawImage(Background, 0,0 ,800, 600,0,0,1350,770);
		g.setColor(Color.blue);
		g.drawString( userName, 200, 345);
	
	}

	
	
	public void update(GameContainer gc, StateBasedGame sbg, int arg2)
			throws SlickException {
		
		Input input = gc.getInput();
		input.addKeyListener(listener);
		
		if(  continueClick){
			// go to pick song
			continueClick = false;
			sbg.enterState(6);
		}
		if( backClick){
			// go to home
			backClick = false;
			sbg.enterState(6);
		}
				
	}
	
	public void keyPressed(int code, char letter) {
		
		if (code == 14 && userName.length() != 0){
			userName = userName.substring(0, userName.length()-1);
		}else{
			userName += letter;
			System.out.println("userName: " + userName);
		}
}

	
	public int getID() {
		return 10;
	}

}
