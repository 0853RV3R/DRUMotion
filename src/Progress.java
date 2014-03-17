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


public class Progress  extends BasicGameState{
	
	private Image Background;
	private boolean backClick, editClick = false;
	int gamesPlayed, percentage, hits, misses = 0;
	
	public Progress() {
		super();
	}

	
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		Background = new Image("res/Screens/Progress.png");	
		
		
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
			editClick = true;
			System.out.println( "x = " + x + "  y = " +y);
		}
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		
		//Draw Background
		g.drawImage(Background, 0,0 ,800, 600,0,0,1360,770);
		
		//write stats
		g.setColor(Color.darkGray);
		g.drawString(""+gamesPlayed+"", 546, 299);
		g.drawString(""+percentage+"%", 546, 343);
		g.drawString(""+hits+"", 546, 390);
		g.drawString(""+misses+"", 546, 434);
	}

	
	public void update(GameContainer gc, StateBasedGame sbg, int arg2)
			throws SlickException {
		
		Input input = gc.getInput();
		
		if( input.isKeyPressed(Input.KEY_BACK) || backClick){
			// go to home
			backClick = false;
			sbg.enterState(6);
		}
		if( input.isKeyPressed(Input.KEY_BACK) || editClick){
			// go to home
			editClick = false;
			sbg.enterState(10);
		}
		
		//Get values from Database
		Connection c = null;
		Statement stmt = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:res/Database/drummotion");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			//TODO change username
			ResultSet rs = stmt.executeQuery( "SELECT * FROM users WHERE name = \"Riccardo Caimano\";" );
			while ( rs.next() ) {
				gamesPlayed = rs.getInt("gamesPlayed");
				percentage = rs.getInt("percentage");
				hits = rs.getInt("hits");
				misses = rs.getInt("misses");
			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}

	
	public int getID() {
		return 7;
	}

}
