
import java.awt.FontFormatException;
import java.io.IOException;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import java.sql.*;


public class NewUser extends BasicGameState {
	
	private Image Background;
	private boolean continueClick = false;
	private boolean backClick = false;
	private int keyCode;
	String userName;
	MyInputListener listener = new MyInputListener();

	java.awt.Font UIFont1;
    org.newdawn.slick.UnicodeFont uniFont;
    

	
	//Database connection
	Connection c = null;
    Statement stmt = null;


    
	public NewUser() {
		super();
	}

	
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		Background = new Image("res/Screens/Enter Name.png");
		userName = "";
		
		//font shit
//		try {
//
//			UIFont1 = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
//					org.newdawn.slick.util.ResourceLoader.getResourceAsStream("res/Fonts/COPPERPLATE_BECKER_MED_0.TTF"));
//
//			uniFont = new org.newdawn.slick.UnicodeFont(UIFont1);
//			uniFont.addAsciiGlyphs();
//			org.newdawn.slick.font.effects.ColorEffect a = new org.newdawn.slick.font.effects.ColorEffect();
//			uniFont.getEffects().add(a);
//
//			a.setColor(java.awt.Color.BLUE);
//			uniFont.loadGlyphs();
//
//
//		} catch (FontFormatException | IOException e) {
//			e.printStackTrace();
//		}
	}

	public void mousePressed(int button  , int x, int y){
		
		//See if they click continue or back
		if  (320 <= x && x <= 490 && 400 <= y && y <= 450){
			continueClick = true;
			System.out.println( "x = " + x + "  y = " +y);
		}
		if  (10 <= x && x <= 180 && 450 <= y && y <= 580){
			backClick = true;
			System.out.println( "x = " + x + "  y = " +y);
		}
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		
		//Draw Background
		g.drawImage(Background, 0,0 ,800, 600,0,0,1350,770);
	//	g.setFont(uniFont);
		g.setColor(Color.blue);
		g.drawString( userName, 200, 345);
	}

	
	public void update(GameContainer gc, StateBasedGame sbg, int arg2)
			throws SlickException {
		char a;
		Input input = gc.getInput();
		input.addKeyListener(listener);
		
		if(  (input.isKeyPressed(Input.KEY_ENTER) || continueClick) && userName.length() != 0){
			// go to pick song
			
			continueClick = false;
			sbg.enterState(6);
			
			//Update database with newUser
			try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:res/Database/drummotion");
		      c.setAutoCommit(false);
		      
		      stmt = c.createStatement();
		      String name = userName.replace("?", "");
		      name = name.replace("\n", "");
		      String sql = "insert into users(name) values (\""+name+"\");";
		      System.out.println(sql);
		      stmt.executeUpdate(sql);
		      
		      stmt.close();
		      c.commit();
		      c.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		}
		if( backClick){
			// go to home
			userName = "";
			backClick = false;
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

	public void keyReleased(int arg0, char arg1) {
		
	}
	
	public String getUser(){
		return userName;
	}


	
	public int getID() {
		return 2;
	}

}
