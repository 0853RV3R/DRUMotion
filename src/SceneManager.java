import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;



public class SceneManager 
{
	// A list with all our scenes
	private List<Scene> sences ;
	// The Gamecontainer to handle the init method
	private GameContainer gc;
	
	public SceneManager ( GameContainer gc )
	{
		this.gc = gc;
		sences = new ArrayList<Scene>();
	}
	
	// Add a scene to the list and call the init method
	public void addSence ( Scene sence )
	{
		sences.add( sence );
		try {
			sence.init(gc);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		Collections.sort(sences);
	}
	
	// Removes a scene by an specific object
	public void removeSence ( Scene sence )
	{
		sences.remove(sence);
	}
	
	// Removes a scene by an specific name
	public boolean removeSence ( String sence )
	{
		for( int i = 0 ; i < sences.size() ; i++ )
		{
			if( sences.get(i).toString().equals(sence) )
			{
				sences.remove(i);
				return true;
			}
		}
		return false;
	}
	
	// Render all scenes
	public void render(GameContainer gc, Graphics g) throws SlickException {
		for( int i = 0 ; i < sences.size() ; i++ )
		{
			sences.get(i).render(gc, g);
		}
	}
	
	// Update all scenes
	public void update(GameContainer gc, int t) throws SlickException {
		for( int i = 0 ; i < sences.size() ; i++ )
		{
			sences.get(i).update(gc,t);
		}
	}
	
	// Get a scene by name
	public Scene getSence ( String sence )
	{
		for( int i = 0 ; i < sences.size() ; i++ )
		{
			if( sences.get(i).toString().equals(sence) )
			{
				return sences.get(i);
			}
		}
		return null;
	}
	
	// Re-Sort the list 
	public void sort ()
	{
		Collections.sort(sences);
	}
	
	// Clear the list
	public void clear ()
	{
		sences = new ArrayList<Scene>();
	}
}
