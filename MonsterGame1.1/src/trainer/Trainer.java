package trainer;
import monster.Monster;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Trainer {
	String name; 
	ArrayList <Monster> monsterList; // Creating an ArrayList containing a trainer and
										// Corresponding owned monsters
	public Trainer(String name) {
		monsterList = new ArrayList<>();
		this.name = name;
		// TODO Auto-generated constructor stub
	}
	
	public String getName() { //Getter method for name of trainer
		return name; 
	}

	public boolean addMonster(Monster monster) { // Method for adding monsters
		if (monsterList.contains(monster)) {	// Returns false/true depending whether monster already exists
			return false;
		}
		else {
			monsterList.add(monster);
			return true;
		}
	}
	
	public boolean removeMonster(Monster monster) { // Method for removing monsters
		if (monsterList.contains(monster)) { 		// Checks whether monster currently exists 
			monsterList.remove(monster);			// Correspondingly returns true/false
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean hasMonster(Monster monster) { //Method for checking whether a monster exists
		if (monsterList.contains(monster)) { 	// Correspondingly returns true/false
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public Map<String, Integer> countMonstersByType() { // HashMap for counting the monsters of a trainer
		Map <String, Integer> numMonstersByType = new HashMap<String, Integer>(); // and grouping by their type
		numMonstersByType.put("Fire", 0);
		numMonstersByType.put("Water", 0);
		numMonstersByType.put("Electric", 0);
		
		for (Monster monster: monsterList) {
			numMonstersByType.put(monster.getType(), numMonstersByType.get(monster.getType()) + 1); // Adds 1
			}																		// to each type when given type
		return numMonstersByType;													// is identified
			
			
		}

	@Override
	public String toString() {
		return "Traner name: " + name + ", Monsters captured are" + monsterList;
	}
	
	
	
	
	
}
