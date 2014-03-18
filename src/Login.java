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
import java.util.ArrayList;


public class Login  extends BasicGameState{
	
	private Image Background, select, scrollUp, scrollDown;
	private boolean continueClick, refresh = false;
	private boolean backClick, user1Click, user2Click, user3Click, user4Click, upClick, downClick = false;
	String User_1, User_2, User_3, User_4, User_5, User_6, du1, du2, du3, du4;

	private int highlight;
	int user;

	ArrayList<String> names = new ArrayList<String>();

	
	
	public Login() {
		super();
	}

	
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		Background = new Image("res/Screens/Pick a Name.png");	
		select = new Image("res/Buttons/select.png");
		scrollUp = new Image("res/Buttons/scrollUp.png");
		scrollDown = new Image("res/Buttons/scrollDown.png");
		
		highlight = 1;

		//Get users from Database
		Connection c = null;
	    Statement stmt = null;
	    
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:res/Database/drummotion");
	      c.setAutoCommit(false);

	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM users;" );
	      while ( rs.next() ) {
	         String  name = rs.getString("name");
	         names.add(name);
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
		
	    switch(names.size()){
		    case 0:
		    	du1 = du2 = du3 = du4 = "";
		    	break;
		    case 1:
		    	du1 = names.get(0);
		    	du2 = du3 = du4 = "";
		    	break;
		    case 2:
		    	du1 = names.get(0);
				du2 = names.get(1);
				du3 = du4 = "";
				break;
		    case 3:
		    	du1 = names.get(0);
				du2 = names.get(1);
				du3 = names.get(2);
				du4 = "";
				break;
		    default:
		    	du1 = names.get(0);
				du2 = names.get(1);
				du3 = names.get(2);
				du4 = names.get(3);
				break;
	    }
		
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
			highlight = 1;
			user = 1;
			System.out.println( "x = " + x + "  y = " +y);
		}
		if  (175 <= x && x <= 645 && 316 <= y && y <= 370){
			user2Click = true;
			highlight = 2;
			user = 2;
			System.out.println( "x = " + x + "  y = " +y);
		}
		if  (175 <= x && x <= 645 && 371 <= y && y <= 435){
			user3Click = true;
			highlight = 3;
			user = 3;
			System.out.println( "x = " + x + "  y = " +y);
		}
		if  (175 <= x && x <= 645 && 436 <= y && y <= 490){
			user4Click = true;
			highlight = 4;
			user = 4;
			System.out.println( "x = " + x + "  y = " +y);
		}
		if  (650 <= x && x <= 700 && 260 <= y && y <= 315){
			upClick = true;
			System.out.println( "x = " + x + "  y = " +y);
		}
		if  (650<= x && x <= 700 && 426 <= y && y <= 480){
			downClick = true;
			System.out.println( "x = " + x + "  y = " +y);
		}
		if  (660<= x && x <= 700 && 345 <= y && y <= 385){
			refresh = true;
			System.out.println( "Refresh: x = " + x + "  y = " +y);
		}
	}
	
	public int getUser(){
		return user;
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		
		//Draw Background
		g.drawImage(Background, 0,0 ,800, 600,0,0,1350,770);
		g.drawImage(scrollUp, 650, 260, 700, 315, 0, 0, 1087, 1043);
		g.drawImage(scrollDown, 650, 426, 700, 480, 0, 0, 1087, 1043);
		//draw user names
		g.setColor(Color.blue);
		g.drawString(du1, 190, 280);
		g.drawString(du2, 190, 330);
		g.drawString(du3, 190, 385);
		g.drawString(du4, 190, 450);
		
		//draw scroll highlight
		
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

	
	public void update(GameContainer gc, StateBasedGame sbg, int arg2)
			throws SlickException {

		Input input = gc.getInput();
		//scroll feature **** have to change this with database
		
		if (refresh){ 
			refresh = false;
				//Get users from Database
				Connection c = null;
				Statement stmt = null;

				try {
					Class.forName("org.sqlite.JDBC");
					c = DriverManager.getConnection("jdbc:sqlite:res/Database/drummotion");
					c.setAutoCommit(false);

					stmt = c.createStatement();
					ResultSet rs = stmt.executeQuery( "SELECT * FROM users;" );
					while ( rs.next() ) {
						String  name = rs.getString("name");
						if(!names.contains(name))
							names.add(name);
					}
					rs.close();
					stmt.close();
					c.close();
				} catch ( Exception e ) {
					System.err.println( e.getClass().getName() + ": " + e.getMessage() );
					System.exit(0);
				}
				
				switch(names.size()){
			    case 0:
			    	du1 = du2 = du3 = du4 = "";
			    	break;
			    case 1:
			    	du1 = names.get(0);
			    	du2 = du3 = du4 = "";
			    	break;
			    case 2:
			    	du1 = names.get(0);
					du2 = names.get(1);
					du3 = du4 = "";
					break;
			    case 3:
			    	du1 = names.get(0);
					du2 = names.get(1);
					du3 = names.get(2);
					du4 = "";
					break;
			    default:
			    	du1 = names.get(0);
					du2 = names.get(1);
					du3 = names.get(2);
					du4 = names.get(3);
					break;
		    }
				downClick = true;
		}
		
		if (downClick && !(du4.equals(names.get(names.size()-1)) ||du4.equals(""))){

			du1 = du2;
			du2 = du3;
			du3 = du4;
			
			int index = names.indexOf(du4);
			if (names.size() > index +1)
				du4 = names.get(index+1);
			else
				du4 = "";
			downClick = false;
			
		}
		if (upClick && !du1.equals(names.get(0))){

			du4 = du3;
			du3 = du2;
			du2 = du1;
			
			int index = names.indexOf(du1);
			du1 = names.get(index-1);
			upClick = false;
			
		}
		
		
		
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
