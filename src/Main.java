import java.io.File;

// based on QuickTip Slick2D tutorials
// http://www.indiedb.com/engines/slick-2d-slick-ae/tutorials/java-slick-2d-video-tutorial







import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {
	
	

	public Main(String name) {
		super(name);
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
			app.setTargetFrameRate(60); // target frame rate: 60fps
			app.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initStatesList(GameContainer gc) throws SlickException{
		this.addState(new Game());
		this.addState(new HomeScreen());
		
	}
}
