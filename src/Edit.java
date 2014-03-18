import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Edit extends GameStateBase<GameData,States> {
	
	private Image Background;
	private boolean continueClick = false;
	private boolean backClick, resetClick, deleteClick = false;
	String userName;
	MyInputListener listener = new MyInputListener();
	int count = 0;
//	NewUser temp = new NewUser();
	
	
	public Edit(ClientBase<GameData> theClient, States theState) {
		super(theClient, theState);
	}

	
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		Background = new Image("res/Screens/Edit Details.png");	
//		userName = temp.getUser();
		userName = "";
	}

	public void mousePressed(int button  , int x, int y){
		System.out.println( "x = " + x + "  y = " +y );
		
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
		g.drawString( userName, 160, 300);
	}

	
	
	public void update(GameContainer gc, StateBasedGame sbg, int arg2)
			throws SlickException {
		
		Input input = gc.getInput();
		input.addKeyListener(listener);
		if (count == 0)
			userName = getClient().getGameData().getUserName();
		count++;	
		if(  continueClick){
			// go to pick song
			continueClick = false;
			count = 0;
			
			//update database
			Connection c = null;
			Statement stmt = null;

			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:res/Database/drummotion");
				c.setAutoCommit(false);

				stmt = c.createStatement();
				String sql = "UPDATE users "+
						"SET name = \""+userName+"\" "+
							"WHERE name = \""+getClient().getGameData().getUserName()+"\";";
				stmt.executeUpdate(sql);
			    c.commit();
				stmt.close();
				c.close();
			} catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
			
			sbg.enterState(6);
		}
		if( backClick){
			// go to home
			backClick = false;
			count = 0;
			sbg.enterState(6);
		}
		
		if(resetClick){
			resetClick = false;
			count = 0;
			
			//update database
			Connection c = null;
			Statement stmt = null;

			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:res/Database/drummotion");
				c.setAutoCommit(false);

				stmt = c.createStatement();
				String sql = "UPDATE users "+
						"SET gamesPlayed = 0, percentage = 0, hits = 0, misses = 0, averageScore = 0 "+
							"WHERE name = \""+getClient().getGameData().getUserName()+"\";";
				stmt.executeUpdate(sql);
			    c.commit();
				stmt.close();
				c.close();
			} catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
			
			sbg.enterState(6);
		}
		
		if(deleteClick){
			deleteClick = false;
			count = 0;
			
			//update database
			Connection c = null;
			Statement stmt = null;

			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:res/Database/drummotion");
				c.setAutoCommit(false);

				stmt = c.createStatement();
				String sql = "DELETE FROM users "+
							"WHERE name = \""+getClient().getGameData().getUserName()+"\";";
				stmt.executeUpdate(sql);
			    c.commit();
				stmt.close();
				c.close();
				getClient().getGameData().setUserName("");
			} catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
			
			sbg.enterState(0);
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
