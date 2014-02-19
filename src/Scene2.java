import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class Scene2 extends Scene
{
	float x,vx;
	
	public Scene2 ()
	{
		super();
		setPriority(2);
	}
	
	protected void CustomRender(GameContainer gc, Graphics g) throws SlickException 
	{
		// Render a moving blue box
		g.setColor(Color.blue);
		g.fillRect(x, 200, 200, 200);
	}
	
	protected void CustomUpdate(GameContainer gc, int t) throws SlickException 
	{
		// move box
		x += t * vx;
		if( x > 750 ) vx = -0.3f;
		if( x < -150 ) vx = 0.3f;
		
		
		if( gc.getInput().isKeyDown(Input.KEY_UP) ) 
		{
			// Set priority to 2 -> Blue box is rendered ontop of the first Sence
			setPriority(2);
			Game.manager.sort();
		}
		if( gc.getInput().isKeyDown(Input.KEY_DOWN) )
		{
			// Set priority to -2 -> Blue box is rendered below of the first Sence
			setPriority(-2);
			Game.manager.sort();
		}
		if( gc.getInput().isKeyDown(Input.KEY_LEFT) )
		{
			// Remove this Sence
			Game.manager.removeSence(this);
		}
		if( gc.getInput().isKeyDown(Input.KEY_RIGHT) )
		{
			// Freeze this Sence
			setState( STATE.FREEZE_NEXT );
		}
	}
	
	public void init(GameContainer gc) throws SlickException 
	{
		x = 200;
		vx = 0.3f;
	}
	
	public String toString()
	{
		return "Sence2";
	}
}
