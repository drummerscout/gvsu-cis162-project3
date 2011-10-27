
/**
 * This is the GUI that will display the dice and buttons.  
 * 
 * 
 * @Taylor Countryman
 * @October 26, 2011
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
    private JLabel credits;
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
        add(crapsStatus);
        add(comeOut);
        add(roll);

        setPreferredSize(new Dimension(600, 500));
        setBackground(Color.orange);

        add(die1);
        add(die2);

        credits = new JLabel("Credits " + myGame.getCredits());
        add(credits);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == comeOut)
                myGame.comeOut();
            else if (event.getSource() == roll)
                 myGame.roll();
            credits.setText("Credits " + myGame.getCredits());
            
            crapsStatus.setText(myGame.getMessage());
        }
    }

    public static void main (String [] args){
        JFrame frame = new JFrame ("Taylor's Crapy Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GUIPanel());
        frame.pack();
        frame.setVisible(true);
    }

}
