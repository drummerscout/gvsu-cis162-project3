
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
    private int numGamesPlayed;

    public Craps(){
        credits = 10;
        numGamesPlayed = 0;
        point = 0;
        comeOut = true;
        dice = new GVdie[NUM_DICE];
        for(int i = 0; i < NUM_DICE; ++i){
            dice[i] = new GVdie();
            dice[i].setDelay(25);
        }
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
        comeOut = false;

        int sum = rollAndComputeSum();
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
            message = "Point is equal to " + point;
            break;
        }
    }

    public void roll(){
        if (comeOut){
            message = "It's not time to roll.";            
            return;
        }
        if ( point == 0){
            message = "It's not the point roll.";
            return;
        }
        int sum = rollAndComputeSum();
        if( sum == 7)
            lose();
        else if ( sum == point){
            message = ("The point is " + sum);            
            win();

        }
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

    private int rollAndComputeSum(){
        int sum = 0;        
        for(GVdie die : dice){
            die.roll();
            sum += die.getValue();
        }
        return sum;
    }

    private void win(){
        credits++;
        message = "You're a winner!";
        comeOut = true;
        numGamesPlayed++;
    }

    private void lose(){
        credits--;
        message = "You're a loser.";
        comeOut = true;
        numGamesPlayed++;
    }

    public int getNumGamesPlayed(){
        return numGamesPlayed;      
    }


    public static void main (String [] args){
        Craps craps = new Craps();
        System.out.println (craps.getMessage());
        craps.comeOut();
        System.out.println (craps.getMessage());
         while (craps.getNumGamesPlayed() < 1 ){
         craps.roll();
        System.out.println (craps.getMessage());
        }
        System.out.println ( "You ended with " + 
            craps.getCredits());
    }
}       
