
public abstract class Worker {

	private String id, name, job;
	
	public abstract Integer totalCost();

	public Worker(String id, String name, String job) {
		super();
		this.id = id;
		this.name = name;
		this.job = job;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	
}
