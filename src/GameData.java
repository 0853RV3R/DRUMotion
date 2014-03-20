
public class GameData {
	public String UserName ="";
	public String SongName ="";
	public int currentScore = 0;
	public int currentHits = 0;
	public int currentMisses = 0;
	public int hits = 0;
	public int misses = 0;
	public int score = 0;
	
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getSongName() {
		return SongName;
	}
	public void setSongName(String songName) {
		SongName = songName;
	}
	public int getCurrentScore() {
		return currentScore;
	}
	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}
	public int getCurrentHits() {
		return currentHits;
	}
	public void setCurrentHits(int currentHits) {
		this.currentHits = currentHits;
	}
	public int getCurrentMisses() {
		return currentMisses;
	}
	public void setCurrentMisses(int currentMisses) {
		this.currentMisses = currentMisses;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getMisses() {
		return misses;
	}
	public void setMisses(int misses) {
		this.misses = misses;
	}
	
		
}
