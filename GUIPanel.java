
/**
 * Write a description of class Jcraps here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GUIPanel extends JPanel{
    /**This creates the two buttons*/
    private JButton comeOut;
    private JButton roll;
    /**This creates the two status messages*/
    private JLabel crapsStatus;
    private JLabel score;
    /**this creates the two dice that will be shown*/
    private GVdie die1;
    private GVdie die2;
    private Craps myGame;

    public GUIPanel() {

        myGame = new Craps();
        die1 = myGame.getDie(1);
        die2 = myGame.getDie(2);

        comeOut = new JButton("Come Out");
        roll = new JButton("Roll");

        ButtonListener listener = new ButtonListener();
        comeOut.addActionListener(listener);
        roll.addActionListener(listener);

        crapsStatus = new JLabel("Taylor's Awesome Craps Game");

        add(comeOut);
        add(roll);

        setPreferredSize(new Dimension(600, 500));
        setBackground(Color.orange);

        add(die1);
        add(die2);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == comeOut) {
                myGame.comeOut();
                crapsStatus.setText(myGame.getMessage());
                if(event.getSource() == roll) {
                    myGame.roll();
                    crapsStatus.setText(myGame.getMessage());
                }
            }
        }
    }

    
    public static void main (String [] args){
        JFrame frame = new JFrame ("Taylor's Crapy Game");
        frame.setDefaultCloseOperation(JFarem.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GUIPanel());
        frame.pack();
        frame.setVisible(true);
    }

}
