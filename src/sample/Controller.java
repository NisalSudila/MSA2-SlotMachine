package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
    //Controller Properties. Input and output fields
    public TextField betInput;
    public Label wheelOne;
    public Label wheelTwo;
    public Label wheelThree;
    public Label playerMoneyAmount;
    public Label resultLabel;

    //Instantiate the SlotMachine Object and the Person Object
    SlotMachine slotMachine = new SlotMachine();
    Person player = new Person(150);

    //Play method triggered when player clicks "play" button
    public void play() {
        //Conditional check - Make sure player has made a bet (not blank) and it is a number.
        if (!betInput.getText().isEmpty() && !betInput.getText().matches(".*[a-zA-Z]+.*")){
           //Creates variable to hold the current bet.
            int currentBet = Integer.parseInt(betInput.getText());
            //Conditional check - Make sure player has the money to actually make the bet.
            if (currentBet > 0 && player.getMoney() > 0 && currentBet < player.getMoney()) {
                player.setBet(currentBet);
                //Calls the "play" method on the slot machine object which generates random numbers
                slotMachine.play();
                //Sets the wheels to the numbers generated by the slot machine "play" method.
                wheelOne.setText(String.valueOf(slotMachine.getWheelOne()));
                wheelTwo.setText(String.valueOf(slotMachine.getWheelTwo()));
                wheelThree.setText(String.valueOf(slotMachine.getWheelThree()));

                //Conditional checks to see if player won.

                //Checks to see if play won bonus (7-7-7).
                if (slotMachine.didBonusHit()) {
                    player.setMoney(player.getMoney() + player.getBet() * 3);
                    playerMoneyAmount.setText(String.valueOf("$" + player.getMoney()));
                    resultLabel.setText("BONUS! YOU WON: " + "$" + player.getBet() * 3);

                    //Check to see if player won (matched any two wheels)
                } else if (slotMachine.didPlayerWin()) {
                    player.setMoney(player.getMoney() + player.getBet() * 2);
                    playerMoneyAmount.setText(String.valueOf("$" + player.getMoney()));
                    resultLabel.setText("WIN! YOU WON: " + "$"+ player.getBet() * 2);

                    //Handles what happens in the likely event the player looses
                } else  {
                    player.setMoney(player.getMoney() - player.getBet());
                    playerMoneyAmount.setText(String.valueOf("$" + player.getMoney()));
                    resultLabel.setText("LOOSER! YOU LOST: " + "$" + player.getBet());

                }

            }
        }
    }

    //Close method to handle exiting the program
    public void close() {
        System.exit(0);
    }
}

