
public class MinerWorker extends Worker {

	static Integer ore;
	public MinerWorker(String id, String name, String job, Integer ore) {
		super(id, name, job);
		this.ore = ore;
	}
	
	

	public static Integer getOre() {
		return ore;
	}



	public static void setOre(Integer ore) {
		MinerWorker.ore = ore;
	}



	@Override
	public Integer totalCost() {
		Integer total;
		
		return null;
	}
}
