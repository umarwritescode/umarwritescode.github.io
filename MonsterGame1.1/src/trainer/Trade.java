package trainer;

import monster.Monster;
import monster.MonsterException;


public class Trade {
	
	Trainer trainer1;
	Trainer trainer2; 
	Monster monster1; 
	Monster monster2; 
	
	public Trade (Trainer trainer1, Monster monster1, Trainer trainer2, Monster monster2) throws MonsterException {
		this.trainer1 = trainer1;
		this.trainer2 = trainer2; 
		this.monster1 = monster1;
		this.monster2 = monster2; 
		
		if (trainer1.hasMonster(monster1) == false) { // Checks for whether trainers have the monsters in question
			throw new MonsterException(trainer1 + " has not captured the monster " + monster1); // For trade to occur
		}else if(trainer2.hasMonster(monster2) == false) {
			throw new MonsterException(trainer2 + " has not captured the monster " + monster2);
		}
	}
	
	public void doTrade() { // Adds & removes the corresponding monsters from the trainers (traders)
		trainer1.addMonster(monster2); 
		trainer1.removeMonster(monster1);
		trainer2.addMonster(monster1);
		trainer2.removeMonster(monster2);
	}
}
