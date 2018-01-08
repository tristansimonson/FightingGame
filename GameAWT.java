/**GameAWT is used to create a GUI for FightingGame.java
 * @author Tristan Simonson
 * @version 1.0
 */
import java.awt.*;
import java.awt.event.*;

public class GameAWT extends Frame implements ActionListener{
    public Label lblUserHealth;
    public Label lblEnemyHealth;
    public Button btnAttack;
    public Button btnHeal;
    public Button btnBlock;

    public GameAWT(){
        setLayout(new FlowLayout());

        lblUserHealth = new Label("User Health: " +                                                                FightingGame.getUser().health);
        add(lblUserHealth);

  	lblEnemyHealth = new Label("Enemy Health: " +                                                               FightingGame.getEnemy().health);
	add(lblEnemyHealth);

        btnAttack = new Button("Attack");
        btnHeal = new Button("Heal");
        btnBlock = new Button("Block");
	btnAttack.addActionListener(this);
	btnHeal.addActionListener(this);
	btnBlock.addActionListener(this);
        add(btnAttack);
        add(btnHeal);
        add(btnBlock);

        setTitle("Fighting Game");
        setSize(400,200);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
     
    //executes actions for when user action buttons pressed
    public void fightingAction(String s){
        if(s.equals("attack") || s.equals("Attack")){
	    FightingGame.userAction("attack", FightingGame.getUser(),                                               FightingGame.getEnemy());
	}
	if(s.equals("heal") || s.equals("Heal")){
	    FightingGame.userAction("heal", FightingGame.getUser(),                                                 FightingGame.getEnemy());
	}
	if(s.equals("block") || s.equals("Block")){
	    FightingGame.userAction("block", FightingGame.getUser(),                                                FightingGame.getEnemy());
	}
    }

    //for testing
    public static void main(String[] args){
        GameAWT app = new GameAWT();
    }
}// class GameAWT
