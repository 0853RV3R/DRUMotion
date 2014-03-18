import org.newdawn.slick.*;
import org.newdawn.slick.state.GameState;

public abstract class GameStateBase<T, U extends StateBase> implements GameState {

	private U state;
	private ClientBase<T> client;
	
	public GameStateBase(ClientBase<T> theClient, U theState){
		setClient(theClient);
	}
	
	public U getState(){
		return state;
	}
	private void setClient(ClientBase<T> theClient) {
		client = theClient;
		
	}
	
	public ClientBase<T> getClient(){
		return client;
	}
	
	
	@Override
	public int getID(){
		return getState().getValue();
	}
	
	
	public boolean isAcceptingInput() {
		return getClient().getCurrentState()==this;
	}
}
