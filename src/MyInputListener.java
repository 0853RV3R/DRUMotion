import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;


public class MyInputListener implements KeyListener{
	
	String user;
	char letter;	
	
	public void inputEnded() {
		
	}

	public void inputStarted() {
		
	}

	public boolean isAcceptingInput() {
		return false;
	}

	public void setInput(Input arg0) {
	}

	public void keyPressed(int arg0, char arg1) {
		letter = arg1;
		user += arg1;
	}
	
	public char getChar() {
        return letter ;
    }
	public String getUser() {
        return user ;
    }
	public void keyReleased(int arg0, char arg1) {
		
	}

}
