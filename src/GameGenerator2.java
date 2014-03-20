import org.newdawn.slick.Music;


public class GameGenerator2 implements Runnable {
	private int drum, minDrum, maxDrum;
	private long duration, minTime, maxTime;
	private boolean stopFlag, isRunning;
	//private Music song2;
	
	public GameGenerator2(int drumInput){
		this.drum = drumInput;
		this.isRunning = true;
		
		//this.stopFlag = false;// initially, don't want to break out of run() method
	}
	@Override
	public synchronized void run() {
		//song2.play();
		while(isRunning){
		minDrum = 1;
		maxDrum = 4;
		this.drum = minDrum + (int) (Math.random()*((maxDrum-minDrum)+1)); // generate drum from [1 - 4]
		
		minTime = 1000; // 1 seconds
		maxTime = 3500; // 3.5 seconds
		duration = minTime + (int) (Math.random()*((maxTime-minTime)+1));
		
		try {
			System.out.println("Game Generator Thread = " + drum);
			Thread.sleep(duration);
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
