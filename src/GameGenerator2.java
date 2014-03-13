
public class GameGenerator2 implements Runnable {
	private int drum, minDrum, maxDrum;
	private long duration, minTime, maxTime;
	
	public GameGenerator2(int drumInput){
		this.drum = drumInput;
	}
	@Override
	public void run() {
		while(true){
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
	public int getDrum() {
		// TODO Auto-generated method stub
		return this.drum;
	}

}
