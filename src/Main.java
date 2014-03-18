import java.io.File;

// based on QuickTip Slick2D tutorials
// http://www.indiedb.com/engines/slick-2d-slick-ae/tutorials/java-slick-2d-video-tutorial






import org.lwjgl.LWJGLUtil;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends ClientBase<GameData> {
	
	

	public Main(String name) {
		super(name, new GameData());
		// TODO Auto-generated constructor stub
	}

	public static void main (String[] args)  {
		// to select the correct platform-dependent files (i.e. MACOSX, Windows, Linux)
		System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
		
		try {
			// The actual window that will display and will show what's rendered in the Game class
			AppGameContainer app = new AppGameContainer(new Main("DRUMotion"));
			// set display mode 3 args: (width px, height px, fullscreen?)
			app.setDisplayMode(800, 600, false);
			//Display.setInitialBackground(255, 250, 250);
			app.setTargetFrameRate(80); // target frame rate: 60fps
			
			app.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initStatesList(GameContainer gc) throws SlickException{
		// this ordering determines the startup state

		addState(new Home());
		addState(new GamePlay(this, States.GAMEPLAY));
		addState(new NewUser(this, States.NEW_USER));
		addState(new UserScreen(this, States.USER_SCREEN));
		addState(new Login(this, States.LOGIN));
		addState(new Statistics(this, States.STATISTICS));
		addState(new PickSong(this, States.PICK_SONG));
		addState(new Progress(this, States.PROGRESS));
		addState(new Instructions(this, States.INSTRUCTIONS));
/*
		this.addState(new Home());
		this.addState(new GamePlay());
		this.addState(new NewUser());
		this.addState(new UserScreen());
		this.addState(new Login());
		this.addState(new Statistics());
		this.addState(new PickSong());
		this.addState(new Progress());
		this.addState(new Instructions());
		this.addState(new Edit());
*/
	}
}
