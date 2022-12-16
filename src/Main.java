import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Main {
	
	Vector<Worker> workerVector = new Vector<>();
	Scanner sc = new Scanner(System.in);
	Integer money = 300;
	
	public void hireWorker() {
		String name, job, id;
		
		do {
			System.out.print("Input worker name [3..15] >> ");
			name = sc.nextLine();
		} while (name.length() < 3 || name.length() > 15);
		
		do {
			System.out.print("Input worker job [ Miner | Fisher ] (case sensitive) >> ");
			job = sc.nextLine();
		} while (!job.equals("Miner") && !job.equals("Fisher"));
		
		id = "EP" + Math.round(Math.random() * 1000);
		
		if(job.equals("Miner")) {
			workerVector.add(new MinerWorker(id, name, job, 0));
		} else {
			workerVector.add(new FisherWorker(id, name, job, 0));
		}
		
		System.out.println("Successfully hired worker!");
	}
	
	public void fireWorker() {
		if(workerVector.isEmpty()) {
			System.out.println("There is no worker!");
		} else {
			viewWorker();
			String deleteID = null;
			System.out.print("Input Worker id [ type '0' to back to Main Menu] >> ");
			deleteID = sc.nextLine();
			
			if(deleteID.equals("0")) {
				return;
			}
			
			for(int i = 0; i < workerVector.size(); i++) {
				if(workerVector.get(i).getId().equals(deleteID)) {
					workerVector.remove(i);
					System.out.println("Successfully fired worker");
				} else {
					System.out.println("Worker Id not found!");
				}
			}
		}
	}
	
	public void viewWorker() {
		if(workerVector.isEmpty()) {
			System.out.println("There is no worker!");
		} else {
			System.out.println("List of Worker:");
			for(int i = 0; i < workerVector.size(); i++) {
				Worker w = workerVector.get(i);
				System.out.println("Worker Id: " + w.getId());
				System.out.println("Worker name: " + w.getName());
				System.out.println("Worker Job: " + w.getJob());
				System.out.println();
			}
		}
	}
	
	public void printStatus() {
		Integer totalOre = 0;
		Integer totalFish = 0;
		Integer miners = 0;
		Integer fishers = 0;
		for(int i = 0; i < workerVector.size(); i++) {
			Worker w = workerVector.get(i);
			totalOre = MinerWorker.getOre();
			totalFish = FisherWorker.getFish();
			if(w.getJob().equals("Miner")){
				miners++;
			} else {
				fishers++;
			}
		}
		System.out.println("Status:");
		System.out.println("==================");
		System.out.println("Your Money : $" + money);
		System.out.println("Miner      : " + miners);
		System.out.println("Fisher     : " + fishers);
		System.out.println("Ore        : " + totalOre);
		System.out.println("Fish       : " + totalFish);
		System.out.println("==================");
	}
	
	public void orderMiner() {
		Integer totalOre = 0;
		Integer totalCost;
		Integer miners = 0;
		for(int i = 0; i < workerVector.size(); i++) {
			if(workerVector.get(i).getJob().equals("Miner")) {
				miners++;
			}
		}
		if(miners == 0) {
			System.out.println("You dont have any Miners.");
		} else {
			totalCost = (75 * miners);
			if(money < totalCost) {
				System.out.println("Every Miner got 2 ore(s)");
				totalOre += 2;
				return;
			}
			money = money - totalCost;
			totalOre = (int) ((Math.round(Math.random() * 10)) * miners);
			MinerWorker.setOre(totalOre);
			System.out.println("Success to order Miner");
		}
		
	}
	
	public void orderFisher() {
		Integer totalFish = 0;
		Integer totalCost;
		Integer fishers = 0;
		for(int i = 0; i < workerVector.size(); i++) {
			if(workerVector.get(i).getJob().equals("Fisher")) {
				fishers++;
			}
		}
		
		if(fishers == 0) {
			System.out.println("You dont have any Fishers.");
		} else {
			totalCost = (50 * fishers);
			if(money < totalCost) {
				System.out.println("Every Fisher got 2 fish(es)");
				totalFish += 2;
				return;
			}
			money = money - totalCost;
			totalFish = (int) ((Math.round(Math.random() * 10)) * fishers);
			FisherWorker.setFish(totalFish);
			System.out.println("Success to order Fishers");
		}
		
	}
	
	public void orderWorker() {
		if(workerVector.isEmpty()) {
			System.out.println("There is no worker!");
		} else {
			printStatus();
			Integer input;
			do {
				System.out.println("1. Order Miner ( cost : $75 )");
				System.out.println("2. Order Fisher ( cost : $50 )");
				System.out.println("3. Exit");
				System.out.print("Choose [1 - 3] >> ");
				input = sc.nextInt();
				sc.nextLine();
				
				switch(input) {
					case 1:
						orderMiner();
						break;
					case 2:
						orderFisher();
						break;
				}
			} while (input != 3);
		}
	}
	
	public void sellItem() {
		if(workerVector.isEmpty()) {
			System.out.println("There is no worker!");
		} else {
			Integer input;
			Integer totalOre = 0;
			Integer totalFish = 0;
			for(int i = 0; i < workerVector.size(); i++) {
				Worker w = workerVector.get(i);
				totalOre = MinerWorker.getOre();
				totalFish = FisherWorker.getFish();
			}
			do {
				System.out.println("Status:");
				System.out.println("==================");
				System.out.println("Your Money : $" + money);
				System.out.println("Ore        : " + totalOre);
				System.out.println("Fish       : " + totalFish);
				System.out.println("==================");
				System.out.println("1. Sell All Ore ($50 for each ore)");
				System.out.println("2. Sell All Fish ($25 for each fish)");
				System.out.println("3. Back to the Main Menu");
				System.out.print("Choose [1 - 3] >> ");
				input = sc.nextInt();
				sc.nextLine();
				Integer cost;
				switch (input) {
					case 1:
						if(totalOre == 0) {
							System.out.println("You dont have any ore");
						} else {
							cost = 50 * totalOre;
							money = money + cost;
							System.out.println("Successfully sold ore");
							totalOre = 0;
						}
						break;
	
					case 2:
						if(totalFish == 0) {
							System.out.println("You dont have any fish");
						} else {
							cost = 25 * totalFish;
							money = money + cost;
							System.out.println("Successfully sold fish");
							totalFish = 0;
						}
						break;
				}
			} while (input != 3);
		}
	}

	public Main() {
		Integer input;
		
		do {
			System.out.println("Welcome to JobGame");
			System.out.println("1. View worker");
			System.out.println("2. Order worker");
			System.out.println("3. Hire worker");
			System.out.println("4. Fire worker");
			System.out.println("5. Sell Item");
			System.out.println("6. Exit");
			System.out.print("Choose [1 - 6] >> ");
			input = sc.nextInt();
			sc.nextLine();
			
			switch (input) {
				case 1:
					viewWorker();
					break;
				case 2:
					orderWorker();
					break;
				case 3:
					hireWorker();
					break;
				case 4:
					fireWorker();
					break;
				case 5:
					sellItem();
					break;
			}
		} while (input != 6);
	}

	public static void main(String[] args) {
		new Main();
	}

}
