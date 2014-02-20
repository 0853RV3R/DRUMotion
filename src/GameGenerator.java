import org.newdawn.slick.Image;


public class GameGenerator implements Runnable {
	Thread t;
	int drum, minDrum, maxDrum;
	long duration, minTime, maxTime;
	// constructor
	GameGenerator(){
		//create new thread
		t = new Thread(this, "Game Generator Thread");
		System.out.println("Game generator thread: + t");
		t.start();// start the thread
	}
	
	public int getDrum(){
		return this.drum;
	}
	public void run(){
		minDrum = 1;
		maxDrum = 4;
		this.drum = minDrum + (int) (Math.random()*((maxDrum-minDrum)+1)); // generate drum from [1 - 4]
		
		minTime = 2000; // 2 seconds
		maxTime = 5000; // 5 seconds
		duration = minTime + (int) (Math.random()*((maxTime-minTime)+1));
		
		try {
			System.out.println("Game Generator Thread = " + drum);
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
}
