public class YahtzeeTester
{
    public static void main(String args[]){
        //start a new game, dice are between 1 and 6
        Yahtzee game = new Yahtzee();
        while(!game.isOver()){
            //start a new roll
            game.startRoll();
            //user has 3 possible rolls, must decide on 4th prompt
            for(int i=0; i<4; i++){
                //display the dice
                game.displayDice();
                //display options for user
                //if the user has already rolled 3 times,
                //the user must choose a slot to score
                //the display must also only display valid
                //current options
                game.displayOptions();
                //prompt user for a correct option
                int choice = game.promptUser();
                if(choice==1){
                    //roll dice, pick which one to roll
                    int dice = game.pickRoll();
                    game.roll(dice);
                    //the above could have been combined into 1 method but
                    //I wanted 2 method calls instead
                } else if(choice==2){
                    //choose to put a score for yahtzee
                    if(game.isYahtzee()){
                        game.addTotal(50);
                    } else{
                        game.addTotal(0);
                    }
                    //do not allow yahtzee to be chosen again
                    game.noMoreYahtzee();
                    i = 4;  //end the turn
                } else if(choice==3){
                    //choose to put a score for straight
                    if(game.isStraight()){
                        game.addTotal(25);
                    } else{
                        game.addTotal(0);
                    }
                    //do not allow straight to be chosen again
                    game.noMoreStraight();
                    i = 4;  //end the turn
                } else if(choice==4){
                    //choose to put a score for chance
                    int score = game.addDice();
                    game.addTotal(score);
                    //do not allow chance to be chosen again
                    game.noMoreChance();
                    i = 4;  //end the turn
                }
            }
            game.displayInformation();
        }
        game.displayFinals();
    }
}
