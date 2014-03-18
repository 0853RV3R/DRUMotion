import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
public abstract class ClientBase<T> extends StateBasedGame {
	private T gameData = null;
	
	public ClientBase(String name, T theGameData) {
		super(name);
		setGameData(theGameData);
	}

	private void setGameData(T theGameData) {
		gameData = theGameData;
		
	}
	public T getGameData(){
		return gameData;
	}

}
