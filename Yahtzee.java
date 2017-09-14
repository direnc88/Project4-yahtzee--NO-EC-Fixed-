import java.util.*;
/**
 * I will be writing a modified yahtzee class to cooperate with the yahtzee tester. 
 * 
 * @author Charles Di Renzo
 * @version 4/18/2014
 */
public class Yahtzee
{
    public int first = 0;
    public int middle = 0;
    public int last = 0; 
    public int roll = 0;
    public boolean chanceOver = false;
    public boolean straightOver = false;
    public boolean yahtzeeOver = false;
    public int total = 0; 
    /**
     * This method will determine whether or not the game is going to end, if all of 
     * options that the player has to choose from are already chosen the game will be 
     * over. 
     * 
     * @return will return true and end the game when every option returns true, 
     * if every option does not return true then it will return false and the game will
     * continue. 
     */
    public boolean isOver()
    {
        if(chanceOver == true && straightOver == true && yahtzeeOver == true)
        {
            return true; 
        }
        return false; 
    }

    /**
     *This is the method that brings up the beginning dice values for the first roll
     *and every roll after the end of a round. 
     *
     *
     */
    public void startRoll()
    {
        roll = 0; //called roll at the beginning to make sure that the roll is always 0 at the start
        Random rand = new Random();
        first = rand.nextInt(6)+1;
        middle = rand.nextInt(6)+1;
        last = rand.nextInt(6)+1;
    }

    /**
     * This method will display the dice values that are above the games choice options.
     */
    public void displayDice()
    {
        System.out.println("Dice are: " + first + " " + middle + " " + last);
    }

    /**
     * This method will display the options available to the player, if the option is 
     * selected before it should not be 'select-able' by the player. 
     * 
     */
    public void displayOptions()
    {
        if(roll < 3) //allows player to roll only up to 3 times then it will eliminate the option
        {
            System.out.println("1 to roll a single dice.");
        }

        if(yahtzeeOver == false)
        {
            System.out.println("2 for yahtzee score.");
        }   

        if(straightOver == false)
        {
            System.out.println("3 for straight score.");
        }  

        if(chanceOver == false)
        {
            System.out.println("4 for chance score.");
        }  

    }

    /**
     * This method will ask the user to select a choice and it will check to see if the
     * choice is valid. If user enters in a value above 4 or below 1 then it will display
     * an error message. If the user selects an option that has been taken away then it 
     * will also display an error message and as the user to try again. 
     * 
     * @return This will simply return the value of the choice the player has selected.
     */
    public int promptUser()
    {
        System.out.print("Enter a choice:");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        while(choice < 1 || choice > 4 || (yahtzeeOver == true && choice == 2)
        || (straightOver == true && choice == 3) || (chanceOver == true && choice ==4)
        || (roll >= 3 && choice == 1))//check
        {//enures only valid options are presented to the user
            System.out.println("Error, try again."); 
            displayOptions();
            System.out.print("Enter a choice:");
            choice = scan.nextInt(); 
        }
        return choice;
    }

    /**
     * This method will allow the player to select which dice they would like to re-roll
     * dice 1 through 3. 
     * 
     * @return This will return the value the player selects 1 through 3 and will re-roll
     * the appropriate dice. 
     */
    public int pickRoll()
    {
        System.out.print("What die would you like to roll:"); 
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt(); //ensure user gets error if they put in wrong numbers
        while(choice < 1 || choice > 3)
        {
            System.out.println("Error, try again.");
            System.out.print("What die would you like to roll:");
            choice = scan.nextInt();
        }
        return choice;
    }

    /**
     * This method will re-roll whatever dice appropriate to the number the player has
     * selected. 
     * 
     * @param dice This is the value 1 through 3 that the player may select from to 
     * choose the corresponding dice. 
     */
    public void roll(int dice)//must ensure correct dice are rolled when choice is selected
    {
        Random rand = new Random();
        if(dice == 1)
        {
            first = rand.nextInt(6)+1;  
        }

        if(dice == 2)
        {
            middle = rand.nextInt(6)+1;  
        }

        if(dice == 3)
        {
            last = rand.nextInt(6)+1;  
        }
        roll++;  //incrementor
    }

    /**
     * This method simply adds up the values of the previous rounds and labels it a total. 
     * 
     * @param score is the score from the previous round.
     */
    public void addTotal(int score)
    {
        total = total + score;
    }

    /**
     * This method determines if the dice are equal and if they are equal it will be a 
     * yahtzee. 
     * 
     * @return This will return true if the dice values are the same and the player will
     * get 50 points, if false then the player will get 0 points. 
     */
    public boolean isYahtzee()
    {
        if(first == middle && first ==last)
        {
            return true;  
        }
        return false;
    }

    /**
     *This method will determine whether the dice configuratin is a straight which is a
     *series of values that are 1 point away from one another. 
     *
     *@return this will return true if it finds the 3 values are 1 value apart from one 
     *another, this will result in the player getting 25 points. If it finds that the values
     *are not 1 point away from one another then it will return false and the player will 
     *get 0 points. 
     */
    public boolean isStraight()//ensure all straight orders are acceptable
    {
        if(first == middle + 1 && first == last + 2)
        {
            return true;  
        }

        if(middle == last + 1 && middle == first + 2)
        {
            return true; 
        }

        if(last == first + 1 && last == middle + 2)
        {
            return true; 
        }

        if(first == last + 1 && first == middle + 2)
        {
            return true;  
        }

        if(middle == first + 1 && middle == last + 2)
        {
            return true; 
        }

        if(last == middle + 1 && last == first + 2)
        {
            return true; 
        }
        return false; 
    }

    /**
     * This method will add the dice values together and return the sum of those values. 
     * 
     * @return This will return the sum of the added dice values.
     */
    public int addDice()//this statements are a bit frivolous here but after writing the code I had a hard time finding an area where they would be used appropriatly without altering the code.
    {
        int score = this.first + this.middle + this.last;
        return score;
    }

    /**
     * This will ensure that the player no longer has the option to select the yahztee 
     * choice after they have already made it. 
     * 
     */
    public void noMoreYahtzee()
    {
        yahtzeeOver = true; 
    }

    /**
     * This will ensure that the player no longer has the option to select the chance
     * choice after they have already made it. 
     */
    public void noMoreChance()
    {
        chanceOver = true; 
    }

    /**
     * This will ensure that the player no longer has the option to select the straight 
     * choice after they have already made it. 
     */
    public void noMoreStraight()
    {
        straightOver = true; 
    }

    /**
     * This method will print out the score information and update from round to round.
     */
    public void displayInformation()
    {
        System.out.println("Your current total is: " + total); 
    }

    /**
     * This method will print out the final score of the game after the player has played
     * three rounds and the game is over. 
     */
    public void displayFinals()
    {
        System.out.println("Your final total is: " + total);
    }


    
    
}
