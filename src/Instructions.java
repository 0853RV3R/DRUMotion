import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class Instructions extends GameStateBase<GameData,States>{
	
	private Image Background;
	private boolean continueClick, backClick = false;
	

	
	public Instructions(ClientBase<GameData> theClient, States theState) {
		super(theClient, theState);

	}

	
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		Background = new Image("res/Screens/instructions.png");	
		
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
		
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		
		//Draw Background
		g.drawImage(Background, 0,0 ,800, 600,0,0,1350,770);
	}

	
	public void update(GameContainer gc, StateBasedGame sbg, int arg2)
			throws SlickException {
		
		
		
		Input input = gc.getInput();
		
		if(  input.isKeyPressed(Input.KEY_ENTER) || continueClick){
			// go to game play
			continueClick = false;
			sbg.enterState(1, new FadeOutTransition(),new FadeInTransition());
		}
		if( input.isKeyPressed(Input.KEY_BACK) || backClick){
			// go to pick a song
			backClick = false;
			sbg.enterState(5);
		}
	}

	
	public int getID() {
		return 9;
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
