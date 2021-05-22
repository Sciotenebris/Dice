package com.rc.dice;

import java.util.Random;

public class DiceOutcome {

    private int outcome1, outcome2, outcome3, outcome4, result;
    private final int DICE_OUTCOMES = 6, DICE_MIN = 1;
    //DICE_MIN is to increase result by 1 as Random method nextInt() includes a result of 6.
    private int numberOfDice;
    private final Random random;

    public DiceOutcome(int numberOfDice) {
        this.numberOfDice = numberOfDice;
        random = new Random();
    }

    /**
     * Method for one or two dice rolls. Returns an int based on the result of
     * the outcome.
     *
     * @return
     */
    public int rollDice() {
        if (numberOfDice == 4) {
            outcome1 = random.nextInt(DICE_OUTCOMES) + DICE_MIN;
            outcome2 = random.nextInt(DICE_OUTCOMES) + DICE_MIN;
            outcome3 = random.nextInt(DICE_OUTCOMES) + DICE_MIN;
            outcome4 = random.nextInt(DICE_OUTCOMES) + DICE_MIN;
            result = outcome1 + outcome2 + outcome3 + outcome4;
        } else if (numberOfDice == 3) {
            outcome1 = random.nextInt(DICE_OUTCOMES) + DICE_MIN;
            outcome2 = random.nextInt(DICE_OUTCOMES) + DICE_MIN;
            outcome3 = random.nextInt(DICE_OUTCOMES) + DICE_MIN;
            result = outcome1 + outcome2 + outcome3;
        } else if (numberOfDice == 2) {
            outcome1 = random.nextInt(DICE_OUTCOMES) + DICE_MIN;
            outcome2 = random.nextInt(DICE_OUTCOMES) + DICE_MIN;
            result = outcome1 + outcome2;
        } else if (numberOfDice == 1) {
            outcome1 = random.nextInt(DICE_OUTCOMES) + DICE_MIN;
            result = outcome1;
        }
        return result;
    }

    public int getResult() {
        return result;
    }

    public int getOutcome1() {
        return outcome1;
    }

    public int getOutcome2() {
        return outcome2;
    }

    public int getOutcome3() {
        return outcome3;
    }

    public int getOutcome4() {
        return outcome4;
    }
}