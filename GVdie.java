import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
/*****************************************************************
A graphical representation of a six-sided die with various controls 
over the appearance.  Current value is constrained between 1 and 6. 
You can control the size and color of the dice. 
<h4>An Example</h4>
<blockquote><pre><code>
GVdice die1 = new GVdice();
die1.roll( );
int result = die1.getValue(); 
</code></pre></blockquote>
<p> 
@author Scott Grissom, modified by Sean Fisk
@version 1.4 October 10, 2006
*****************************************************************/
public class GVdie extends JPanel implements MouseListener, Comparable{
/** current value of the die */
private int myValue, displayValue; 

/** is the dice currently on hold? */
private boolean held;

/** current size in pixels */
private int mySize;

/** dot size in pixels defined by overall die size */ 
private int dotSize;

/** offset in pixels for the left row of dots */ 
private int left;

/** offset in pixels for the right row of dots */ 
private int right;

/** offset in pixels for the middle dot */ 
private int middle;

/** delay for animation */
// private int DELAY; // Unnecessary, stored by timer
	
/** color of the dice when held */
private Color HELD_COLOR = Color.pink;
	
/** default color of dice */
private Color BACKGROUND = Color.white;

/** repeats for animation */
private int NUM_ROLLS;

/** Timer for animation */
private javax.swing.Timer myTimer;


/*****************************************************************
constructor creates a die of specified size X size pixels

@param size the length of each side in pixels
*****************************************************************/
public GVdie(int size) {
// initialize the die and determine display characteristics 
mySize = size;
held = false; 
dotSize = mySize / 5;
int spacer = (mySize - (3*dotSize))/4; 
left = spacer; 
right = mySize - spacer - dotSize; 
middle = (mySize - dotSize) /2;
setBackground(BACKGROUND);
setForeground(Color.black);
setSize(size,size); 
setPreferredSize(new Dimension(size, size));
setMinimumSize(new Dimension(size, size));
setMaximumSize(new Dimension(size, size));

// create the fancy border
Border raised = BorderFactory.createRaisedBevelBorder();
Border lowered = BorderFactory.createLoweredBevelBorder();
Border compound = BorderFactory.createCompoundBorder(raised, lowered);
setBorder(compound);

// set default values
displayValue = myValue = (int) (Math.random()*6)+1;
setNumRolls(6);
myTimer = new javax.swing.Timer(250, new Animator());
addMouseListener(this);
}


/*****************************************************************
* default constructor creates a die of size 100 X 100 pixels
*****************************************************************/
public GVdie() {
    this(100);
}

/*****************************************************************
Is the dice currently held?
@return true if the die is held. Otherise, false.
*****************************************************************/
public boolean isHeld(){
    return held;
}

/*****************************************************************
Set the die face to blank
*****************************************************************/
public void setBlank(){
    displayValue = 0;
    repaint();
}

/*****************************************************************
Set whether the die is held or not
@param h true if die is currently held
*****************************************************************/
public void setHeld(boolean h){
    held = h;
    if(held){
        setBackground(HELD_COLOR);
    }else{
        setBackground(BACKGROUND);
    }
    repaint();    
}

/*****************************************************************
Sets the color of the dots
@param c a Java Color object such as Color.red
*****************************************************************/
public void setForeground(Color c){
    super.setForeground(c);
}

/*****************************************************************
Updates the image after obtaining a random value in the range 1 - 6.
@param none
@return the new value between 1 - 6
*****************************************************************/
public void roll (){
    myValue = (int) (Math.random()*6)+1; 

    // start the animated roll
     myTimer.restart();
    
}

/*****************************************************************
Set the delay in milliseconds between frames of the animation.
Default value is 250.
@param msec milliseconds to delay
*****************************************************************/
public void setDelay (int msec){
   if (msec > 0)
		 myTimer.setDelay(msec);
}

/*****************************************************************
Set the number of rolls before stopping the animation. 
Default value is 6.
@param num number of rolls before stopping
*****************************************************************/
public void setNumRolls (int num){
   NUM_ROLLS = 0;
   if (num > 0)
       NUM_ROLLS = num;
}

/*****************************************************************
gets the current value of the die (1 - 6)

@return the current value of the die
*****************************************************************/
public int getValue(){
    return myValue;
}

   
/*****************************************************************
Display the current value of the die.  Called automatically
after rolling.  There is no need to call this method directly.
@param g the graphics context for the panel
@return none
*****************************************************************/
public void paintComponent(Graphics g){
    super.paintComponent(g);

// paint dots    
switch (displayValue){   
    case 1:
        g.fillOval (middle,middle,dotSize,dotSize); 
        break;
    case 2:
        g.fillOval (left,left,dotSize,dotSize); 
        g.fillOval (right,right,dotSize,dotSize); 
        break;
    case 3:
        g.fillOval (middle,left,dotSize,dotSize); 
        g.fillOval (middle,middle,dotSize,dotSize); 
        g.fillOval (middle,right,dotSize,dotSize); 
        break;
    case 5:     g.fillOval (middle,middle,dotSize,dotSize);
        // fall throught and paint four more dots
    case 4:
        g.fillOval (left,left,dotSize,dotSize); 
        g.fillOval (left,right,dotSize,dotSize); 
        g.fillOval (right,left,dotSize,dotSize); 
        g.fillOval (right,right,dotSize,dotSize); 
        break;
    case 6:
        g.fillOval (left,left,dotSize,dotSize); 
        g.fillOval (left,middle,dotSize,dotSize); 
        g.fillOval (left,right,dotSize,dotSize); 
        g.fillOval (right,left,dotSize,dotSize); 
        g.fillOval (right,middle,dotSize,dotSize); 
        g.fillOval (right,right,dotSize,dotSize); 
        break;
    }   

}

/*****************************************************************
respond to the dice being clicked

@param e the mouse event
*****************************************************************/
public void mouseClicked(MouseEvent e){
    if(held){
        held = false;
        setBackground(BACKGROUND);
    }else{
        held = true;
        setBackground(HELD_COLOR);
    }
    repaint();
    
}
public void mousePressed(MouseEvent e){}
public void mouseReleased(MouseEvent e){}
public void mouseExited(MouseEvent e){}
public void mouseEntered(MouseEvent e){}

/*****************************************************************
allows dice to be compared if necessary

@param o compare the dice with this object
@return -1 if dice is less than passed object
*****************************************************************/
public int compareTo(Object o){
    GVdie d = (GVdie) o;
    return getValue() - d.getValue();
} 

/******************************************************
INNER class to roll the dice as an animation
******************************************************/
private class Animator implements ActionListener{
int count = 0;
public void actionPerformed(ActionEvent e){
        displayValue = (int) (Math.random()*6)+1; 
        repaint();
    count++;
    // Should we stop rolling?
    if (count == NUM_ROLLS){
        count=0;
        myTimer.stop();
        displayValue = myValue;
        repaint();
    }
}
}    


}