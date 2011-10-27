
/**
 * This creates all the methods 
 * 
 * @Taylor Countryman
 * @October
 */
public class Craps
{
    private static final int NUM_DICE = 2;
    private GVdie[] dice;
    private int credits;
    private int point;
    private String message ;
    private boolean comeOut;
    public boolean gameOver;


    public Craps(){
        credits = 10;
        point = 0;
        comeOut = true;
        gameOver = false;
        dice = new GVdie[NUM_DICE];
        for(int i = 0; i < NUM_DICE; ++i)
            dice[i] = new GVdie();
        message = "Welcome to the ARCH lab Casino";
    }

    public void comeOut(){
        if(credits <= 0 ){
            return;
        }

        if ( !comeOut){
            message = "It's not the come out.";
            return;
        }

        roll();

        int sum = getDiceSum();

        switch (sum){
            case 7:
            case 11:
            win();
            break;
            case 2:
            case 3:
            case 12:
            lose();
            break;
            default:
            point = sum;
            break;
        }
    }

    public void roll(){
        if ( point == 0){
            message = "It's not the point roll.";
            return;
        }
        for(GVdie die : dice)
            die.roll();
        int sum = getDiceSum();
        if( sum == 7)
            lose();
        else if ( sum == point)
            win();
    }

    public int getCredits(){
        return credits;
    }

    public boolean okToRoll(){
        return !comeOut;
    }

    public void setCredits( int credits ){
        if ( credits > 0)
            this.credits = credits;

    }

    public GVdie getDie( int num){
        num--; // This converts it to zero index
        if ( num >= 0 && num < dice.length)
            return dice[num];
        return null;
    }

    public String getMessage(){
        return message;
    }

    private int getDiceSum(){
        int sum = 0;

        for(GVdie die : dice)
            sum += die.getValue();
        return sum;
    }

    private void win(){
        credits++;
        message = "You're a winner!";
    }

    private void lose(){
        credits--;
        message = "You're a loser.";
        gameOver = true;
    }

    private boolean getGameOver(){
        return gameOver;
    }

    public static void main (String [] args){
        Craps GVcraps = new Craps();
        System.out.println (GVcraps.getMessage());
        GVcraps.comeOut();
        System.out.println (GVcraps.getMessage());
        while ( GVcraps.getGameOver() == false){
            GVcraps.roll();
            System.out.println (GVcraps.getMessage());
        }
        System.out.println ( "You ended with " + GVcraps.getCredits());
    }
}       
