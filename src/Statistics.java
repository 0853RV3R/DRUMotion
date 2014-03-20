import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import java.sql.*;


public class Statistics  extends GameStateBase<GameData,States>{
	
	private Image Background;
	private boolean backClick, playClick = false;
	int score, hits, misses = 0;
	int percentage;
	
	public Statistics(ClientBase<GameData> theClient, States theState) {
		super(theClient, theState);
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
		
		//do updates
		score = getClient().getGameData().getCurrentScore();
		hits = getClient().getGameData().getHits();
		misses = getClient().getGameData().getMisses();
		
		if ((hits + misses) > 0){
			percentage = hits*100/(hits + misses);
		}
		else
			percentage = -1;
		
		//update database
		Connection c = null;
		Statement stmt = null;
		int overallPercentage = 0;
		int overallHits = 0;
		int overallMisses = 0;
		int overallGamesPlayed = 0;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:res/Database/drummotion");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			//get current data
			ResultSet rs = stmt.executeQuery( "SELECT * FROM users WHERE name = \""+getClient().getGameData().getUserName()+"\";" );
			while ( rs.next() ) {
				overallPercentage = rs.getInt("percentage");
				overallHits = rs.getInt("hits");
				overallMisses = rs.getInt("misses");
				overallGamesPlayed = rs.getInt("gamesPlayed");
			}
			rs.close();
			
			//update values
			overallGamesPlayed++;
			overallHits += hits;
			overallMisses += misses;
			if ((overallHits + overallMisses) > 0){
				overallPercentage = overallHits*100/(overallHits + overallMisses);
			}
			else
				overallPercentage = -1;
			
			String sql = "UPDATE users "+
					"SET gamesPlayed = "+overallGamesPlayed+", percentage = "+overallPercentage+", hits = "+
						+overallHits+", misses = "+overallMisses+" "+
							"WHERE name = \""+getClient().getGameData().getUserName()+"\";";
			stmt.executeUpdate(sql);
		    c.commit();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}

		
		if(  backClick){
			// go to home
			backClick = false;
			sbg.enterState(6);
		}
		if(  playClick){
			// go to game
			playClick = false;
			//TODO Change to return to game
			sbg.enterState(1);
		}
	}

	
	public int getID() {
		return 4;
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
