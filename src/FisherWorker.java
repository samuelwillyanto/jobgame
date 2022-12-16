
public class FisherWorker extends Worker {

	static Integer fish;
	public FisherWorker(String id, String name, String job, Integer fish) {
		super(id, name, job);
		this.fish = fish;
	}
	
	

	public static Integer getFish() {
		return fish;
	}



	public static void setFish(Integer fish) {
		FisherWorker.fish = fish;
	}



	@Override
	public Integer totalCost() {
		Integer total;
		
		return null;
	}

}
