import org.newdawn.slick.Music;


public class GameGenerator2 implements Runnable {
	private int nextDrum, drum, minDrum, maxDrum;
	private long duration, minTime, maxTime;
	private boolean stopFlag, isRunning;
	//private Music song2;
	
	public GameGenerator2(int drumInput){
		this.drum = drumInput;
		this.nextDrum = drumInput;
		this.isRunning = true;
		
		//this.stopFlag = false;// initially, don't want to break out of run() method
	}
	
	public void sleep(long ms){
		try {
			Thread.sleep(1000); // to allow for transition or warning
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	@Override
	public synchronized void run() {
		//song2.play();
		while(isRunning){
		minDrum = 1;
		maxDrum = 4;
		//generate the next drum signal --> make transition animation for 1000ms
		this.nextDrum = minDrum + (int) (Math.random()*((maxDrum-minDrum)+1)); // generate drum from [1 - 4]
		try {
			Thread.sleep(1200);// allow for transition time
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// set the next drum after transition time
		this.drum = this.nextDrum;
		this.nextDrum = 0; // reset the nextDrum so transition stops
		minTime = 1000; // 1 seconds
		maxTime = 3500; // 3.5 seconds
		duration = minTime + (int) (Math.random()*((maxTime-minTime)+1));
		
		try {
			System.out.println("Game Generator Thread = " + drum);
			Thread.sleep(duration); // sleep on this set drum for a certian duration
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		if(stopFlag){// if stop flag is true, break out of the run() method
			System.out.println("Game Generator Thread is STOPPING");
			break;
		}
		*/
		
		
		}	// end while loop
	}// end of run() method
	
	public void kill() {
		isRunning = false;
		System.out.println("Game Generator Runnable is STOPPING");
	}
	public void revive(){
		isRunning = true;
		System.out.println("Game Generator Runnable is REVIVED");
	}
	
	
	public int getDrum() {
		// TODO Auto-generated method stub
		return this.drum;
	}
	public int getNextDrum() {
		// TODO Auto-generated method stub
		return this.nextDrum;
	}
	/*
	public void setStopFlag(boolean stop){
		this.stopFlag = stop;
	}
	*/
	public boolean isRunning() {
		if (isRunning) return true;
		else return false;
	}

}
