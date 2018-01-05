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

        lblUserHealth = new Label("User Health");
        lblEnemyHealth = new Label("Enemy Health");
        add(lblUserHealth);
        add(lblEnemyHealth);

        btnAttack = new Button("Attack");
        btnHeal = new Button("Heal");
        btnBlock = new Button("Block");
        add(btnAttack);
        add(btnHeal);
        add(btnBlock);

        setTitle("Fighting Game");
        setSize(250,100);

        setVisible(true);
    }

   @Override
   public void actionPerformed(ActionEvent e) {
 
   }
}// class GameAWT
