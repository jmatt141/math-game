package Mathgame;

import java.util.Random;

/**
 * Simple 10 question Math Game
 *
 * @author Jole Matthews
 */

public class Question {
    private static final Random RAND = new Random(System.currentTimeMillis());

    private double left;
    private double right;
    private Operator operator;

    public Question(double left, double right, Operator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public Question(int max) {
        this(randInt(max), randInt(max), Operator.randomOperator());
    }

    public Question() {
        this(10); // Random 0 -> 10
    }

    public String askQuestion() {
        return String.format("What is %s? ", operator.expression(left, right));
    }

    public String explain(boolean correct) {
        return correct ? "Correct" : String.format("Incorrect, it is: %.2f", calculate());
    }

    public boolean makeGuess(double guess) {
        return compareDouble(guess, calculate(), 0.01);
    }

    private double calculate() {
        return operator.calculate(left, right);
    }

    @Override
    public String toString() {
        return String.format("%s = %.2f", operator.expression(left, right), calculate());
    }

    private static boolean compareDouble(double expected, double actual, double threshold) {
        return Math.abs(expected - actual) < threshold;
    }

    private static double randInt(int range) {
        return Math.floor(RAND.nextDouble() * range);
    }
}