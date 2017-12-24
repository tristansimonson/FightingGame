/**game where user creates character and fights preset enemy
 * @author Tristan Simonson
 * @version 1.2
 * game now includes weapons
 */

import java.util.Scanner;
import java.util.Random;

class FightingGame{
 
    /**initiates game by having user select character and weapon
     * @returns userCharacter the user's champion
     */
    public static Character startGame(){
        // create character
	System.out.println("----character----");
	String cChoice = askForInput("Enter a name for your character!");
	Character userCharacter = new Character(cChoice, 5, 100);
	System.out.println(cChoice + " is your champion!");
        System.out.println("\n");

	// choose weapon
	System.out.println("----weapon----");
	String wChoice = askForInput("Choose: gun, sword, or shield!");
	if(wChoice.equals("gun") || wChoice.equals("Gun")){
	    wChoice = "Gun";
	    Weapon gun = new Weapon("gun", 15, 1);
	    userCharacter.weapon = gun;
	}
	if(wChoice.equals("sword") || wChoice.equals("Sword")){
	    wChoice = "Sword";
	    Weapon sword = new Weapon("sword", 10, 10);
	    userCharacter.weapon = sword;
	}
        // incorrect input will be defered to the option of a shield
	else{
	    wChoice = "Shield";
	    Weapon shield = new Weapon("shield", 0, 20);
	    userCharacter.weapon = shield;
	}
	System.out.println(wChoice + " is your weapon!");
        System.out.println("\n");
        return userCharacter;
    }
    
    /**takes action by user and executes effects of action
     * @param a textual representation of user action
     * @param user character who is performing action
     * @param enemy opponent of user 
     */    
    public static void userAction(String a, Character user, Character enemy){
        //System.out.println("\n");
	boolean needAction = true;
	// user will attack enemy
	if(a.equals("Attack") || a.equals("attack")){
	    needAction = false;
	    System.out.println("attacking...");
	    attack(user, enemy);
	}    
	// increases health by 10, or up to 100 if less than 10 away
	if(a.equals("Heal") || a.equals("heal")){
	    needAction = false;
	    System.out.println("healing...");
	    if(user.health < 100){
		// this if statement prevents exceeding health cap
		if(user.health >= 90){
		    user.health = 100;
		}
		else{
		    user.health += 10;
		}
	    }
	    System.out.println(user.name + " health at: " + user.health);
	}
	// a way to exit the program, mainly for testing
	if(a.equals("exit") || a.equals("Exit")){
	    System.out.println("exiting...");
	    System.exit(0);
	}
	// if invalid input given user will use turn to block
	if(needAction == true){ 
	    System.out.println("blocking...");
	    user.isBlocking = true;
	}
    }

    /**executes actions until a character dies 
     * @param user character who is performing action
     * @param enemy opponent of user 
     */
    public static void battle(Character user, Character enemy){
        if(enemy.health > 0 && user.health > 0){
	    System.out.println("YOUR TURN");
	    String action = askForInput("Choose an Action:                                                               Attack, Block, or Heal"                                                       );
	    userAction(action, user, enemy);
            System.out.println("\n");
	    System.out.println("ENEMY TURN");
	    enemyAction(user, enemy);
   	    battle(user, enemy);
	}
    }
	
    /**handles enemy actions
     * @param user the character of the user
     * @param enemy opponent of the user
     */
    public static void enemyAction(Character user, Character enemy){
        // randomly choose action for enemy
	int r = getRandom(3,1);
	// make the enemy a little bit smarter, no healing when full health
	if(enemy.health == 100){
	    r = getRandom(3,2);
	}
	// heal
   	if(r == 1){
            System.out.println("Enemy healing...");
	    if(enemy.health < 100){
		if(enemy.health >= 95){
		    enemy.health = 100;
		}
		else{
		    enemy.health += 5;
	        }
	    }
	    System.out.println("Enemy health at: " + enemy.health);
	    System.out.println("\n"); 
	}
	// attack
        if(r == 2){
            System.out.println("Enemy attacking...");
            attack(enemy, user);
	    System.out.println("\n"); 
	}
	// block
	if(r == 3){
            System.out.println("Enemy blocking...");
	    enemy.isBlocking = true;
	    System.out.println("\n"); 
	}
    }

    /**method gets random number to decide action of enemy
     * @return n the random number
     */
    public static int getRandom(int max, int min){
        Random r = new Random();
   	int n = r.nextInt((max-min) + 1) + min;
	return n;
    }

    /**method that asks for input from user, for choosing things
     * @param question question that you want user's answer to
     * @return n the choice of the user
     */
    public static String askForInput(String question){
        Scanner reader = new Scanner(System.in);
	System.out.println(question);
	String n = "null";
	if(reader.hasNext()){
	    n = reader.next();
	}
	return n;
    }
    
    /**method to validate character input from user
     * @param c string of user's input
     * @param x a character choice
     * @param y a character choice
     * @return boolean depicting validity of user's input\
     */
    public static boolean checkChoice(String c, Character x, Character y){
  	if(c.equals(x.name) || c.equals(y.name)){
	    return true;
	}
        else{
	    return false;
	}
    }

    /**method to deal with when character attacks, if someone dies end game
     * @param er character who is attacker
     * @param ed character who is attacked
     */
    public static void attack(Character er, Character ed){
	if(ed.isBlocking == true){
	    ed.isBlocking = false;
	    // statement for if user is being attacked
	    if(ed.weapon != null){
	        ed.health -= er.damage - ed.weapon.blockingPower;
	    }
	    // statement for if enemy is being attacked
	    else{
	        ed.health -= er.damage / 2;
	    }
	}
	else{
	    // statement for if attacker has weapon
	    if(er.weapon != null){
	        ed.health -= er.damage + er.weapon.damage;
	    }
	    // attacker has no weapon
	    else{
	        ed.health -= er.damage;
    	    }
	}
	if(ed.health <= 0){
	    System.out.println(ed.name + " health at: 0");
	    endGame();
	}    
	System.out.println(ed.name + " health at: " + ed.health);
    }

    // game over when a character's health reaches zero
    public static void endGame(){
	System.out.println("GAME OVER");
	System.exit(0);
    }

    public static void main(String[] args){
        Character e = new Character("Enemy", 25, 100);
 	Character c = startGame();   
	battle(c, e);
    }
}// class FightingGame

// class to handle characters
class Character{
    String name;
    int damage;
    int health;
    Weapon weapon;
    boolean isBlocking = false;
    
    /**constructor for characters
     * @param name what character is referred to as
     * @param damage numerical value of damage character can deal
     * @param health numerical valur of damage character can take
     */
    Character(String name, int damage, int health){
        this.name = name;
        this.damage = damage;
        this.health = health;
    }
}// class Character

// class to handle weapons
class Weapon{
    String name;
    int damage;
    int blockingPower;
    
    /**constructor for weapons
     * @param name what weapon is referred to as
     * @param damage numerical value of damage weapon can deal
     * @param blockingPower damage weapon can prevent when blocking
     */
    Weapon(String name, int damage, int blockingPower){
        this.name = name;
        this.damage = damage;
	this.blockingPower = blockingPower;
    }
}// class Weapon
