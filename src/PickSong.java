import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class PickSong extends GameStateBase<GameData,States>{
	
	private Image Background, select;
	private boolean continueClick = false;
	private boolean backClick, song1Click, song2Click, song3Click, song4Click, upClick, downClick = false;
	private int highlight;
	
	public PickSong(ClientBase<GameData> theClient, States theState) {
		super(theClient, theState);
	}

	
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		Background = new Image("res/Screens/Pick a Song.png");	
		select = new Image("res/Buttons/select.png");
		highlight = 1;
	}

	public void mousePressed(int button  , int x, int y){
		
		//test listner
		
		System.out.println( "x = " + x + "  y = " +y);
		
		//See where the user clicked
		if  (320 <= x && x <= 490 && 485 <= y && y <= 535){
			continueClick = true;
			System.out.println( "x = " + x + "  y = " +y );
		}
		if  (10 <= x && x <= 180 && 450 <= y && y <= 580){
			backClick = true;
			System.out.println( "x = " + x + "  y = " +y);
		}
		if  (175 <= x && x <= 645 && 260 <= y && y <= 315){
			song1Click = true;
			highlight = 1;
			System.out.println( "x = " + x + "  y = " +y+ "/n song 1");
		}
		if  (175 <= x && x <= 645 && 316 <= y && y <= 370){
			song2Click = true;
			highlight = 2;
			System.out.println( "x = " + x + "  y = " +y+ "/n song 2");
		}
		if  (175 <= x && x <= 645 && 371 <= y && y <= 435){
			song3Click = true;
			highlight = 3;
			System.out.println( "x = " + x + "  y = " +y+ "/n song 3");
		}
		if  (175 <= x && x <= 645 && 436 <= y && y <= 490){
			song4Click = true;
			highlight = 4;
			System.out.println( "x = " + x + "  y = " +y+ "/n song 4");
		}
		
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		
		//Draw Background
		g.drawImage(Background, 0,0 ,800, 600,0,0,1350,770);
		
		if ( highlight ==1  ){
			g.drawImage(select, 175, 260, 635, 315,0,0,62,781);
		}
		if ( highlight ==2 ){
			g.drawImage(select, 175, 316, 635, 370,0,0,62,781);
		}
		if ( highlight ==3 ){
			g.drawImage(select, 175, 365, 635,  425,0,0,62,781);
		}
		if ( highlight ==4 ){
			g.drawImage(select, 175, 420, 635, 475,0,0,62,781 );
		}
	}

	public int getSong(){
		if (song1Click){
			return 1;
		}else if(song2Click){
			return 2;
		}else if(song3Click){
			return 3;
		}else if(song4Click){
			return 4;
		}else 
			return 0;
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int arg2)
			throws SlickException {
		
		Input input = gc.getInput();
		
		if(  continueClick){
			// go to pick song
			continueClick = false;
			sbg.enterState(9);
		}
		if( input.isKeyPressed(Input.KEY_BACK) || backClick){
			// go to home
			backClick = false;
			sbg.enterState(6);
		}
		if(  song1Click || song2Click || song3Click || song4Click){
			// go to game play
			continueClick = false;
		//	sbg.enterState(0);
		}
		
	}

	
	public int getID() {
		return 5;
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
