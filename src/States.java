
public enum States implements StateBase{
	HOME(0),GAMEPLAY(1), NEW_USER(2), USER_SCREEN(3), 
	LOGIN(4), STATISTICS(5), PICK_SONG(6), PROGRESS(7), INSTRUCTIONS(8), EDIT(9);
	
	private int value;
	
	States(int theValue){
		value = theValue;
	}
	
	

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return value;
	}

}
