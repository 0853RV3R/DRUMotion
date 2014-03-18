
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class UserScreen  extends GameStateBase<GameData,States>{
	
	private Image Background;
	private boolean pickSongClick = false;
	private boolean progressClick, logoutClick = false;
	
	public UserScreen(ClientBase<GameData> theClient, States theState) {
		super(theClient, theState);
	}

	
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		Background = new Image("res/Screens/Main Screen.png");		
	}

	public void mousePressed(int button  , int x, int y){
		
		//test listener
		
		System.out.println( "x = " + x + "  y = " +y);

		//See if they click continue or back
		if  (125 <= x && x <= 375 && 255 <= y && y <= 405){
			pickSongClick = true;
			System.out.println( "x = " + x + "  y = " +y);
		}
		if  (440 <= x && x <= 690 && 255 <= y && y <= 405){
			progressClick = true;
			System.out.println( "x = " + x + "  y = " +y);
		}
		if  (10 <= x && x <= 180 && 450 <= y && y <= 580){
			logoutClick = true;
			System.out.println( "x = " + x + "  y = " +y);
		}
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		
		//Draw Background
		g.drawImage(Background, 0,0 ,800, 600,0,0,1350,770);
	}

	
	public void update(GameContainer gc, StateBasedGame sbg, int arg2)
			throws SlickException {
		
		Input input = gc.getInput();
		
		if(  pickSongClick){
			// go to pick song
			pickSongClick = false;
			sbg.enterState(5);
		}
		if( progressClick){
			// go to home
			progressClick = false;
			sbg.enterState(7);
		}
		if( input.isKeyPressed(Input.KEY_BACK) || logoutClick){
			// go to home
			logoutClick = false;
			sbg.enterState(0);
		}
	}

	
	public int getID() {
		return 6;
	}


	@Override
	public void enter(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void leave(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseWheelMoved(int arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void inputEnded() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void inputStarted() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setInput(Input arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(int arg0, char arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(int arg0, char arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void controllerButtonPressed(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void controllerButtonReleased(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void controllerDownPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void controllerDownReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void controllerLeftPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void controllerLeftReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void controllerRightPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void controllerRightReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void controllerUpPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void controllerUpReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}

}
