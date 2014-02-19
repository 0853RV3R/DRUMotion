import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class Scene1 extends Scene 
{
	public Scene1 ()
	{
		super();
		setPriority(1);
	}
	
	protected void CustomRender(GameContainer gc, Graphics g) throws SlickException 
	{
		// Render a static red box
		g.setColor(Color.red);
		g.fillRect(100, 100, 200, 200);
	}
	
	protected void CustomUpdate(GameContainer gc, int t) throws SlickException 
	{
		// If Space is pressed
		if( gc.getInput().isKeyPressed(Input.KEY_SPACE) ) 
		{
			// Add a new Sence2 instance to the SenceManager
			Game.manager.addSence( new Scene2() );
		}
	}
	
	public void init(GameContainer gc) throws SlickException 
	{
		
	}
	
	public String toString()
	{
		return "Sence1";
	}
}
