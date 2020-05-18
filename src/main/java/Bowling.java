import java.util.Scanner;

public class Bowling {
    private int totalScore;
    private int currentFrame = 1;
    private int currentRoll = 1;
    private int nextBonus;
    private int nextNextBonus;
    private Integer lastRollScore;
    boolean overtime;

    /**
     * Simulates one roll
     *
     * @param rollScore the amount of pins scored in the roll
     */
    public void inputRoll(int rollScore) {
        if (gameFinished()) {
            throw new IllegalStateException("Too many rolls, game already finished!");
        }

        validate(rollScore);
        calculateBonus(rollScore);

        // In the 10th frame, no bonus is awarded
        if (!isLastFrame()) {
            if (isSpare(rollScore)) {
                nextBonus++;
            } else if (isStrike(rollScore)) {
                nextBonus++;
                nextNextBonus++;
            }
        }

        totalScore += rollScore;

        if (isLastFrame() && (isSpare(rollScore) || isStrike(rollScore))) {
            overtime = true;
        }

        lastRollScore = rollScore;
        currentRoll++;

        if (!overtime && (currentRoll > 2 || isStrike(rollScore)) || (overtime && (currentRoll > 3))) {
            currentFrame++;
            currentRoll = 1;
            lastRollScore = 0;
        }
    }

    private boolean isLastFrame() {
        return currentFrame == 10;
    }

    /**
     * Validates if the given input is possible regarding the previous roll
     *
     * @param rollScore score of the current roll
     */
    private void validate(int rollScore) {
        if (rollScore > 10 || !overtime && (lastRollScore != null && rollScore + lastRollScore > 10)) {
            throw new IllegalArgumentException("Impossibly high score!");
        }
    }

    private boolean isStrike(int rollScore) {
        return rollScore == 10;
    }

    private boolean isSpare(int rollScore) {
        return currentRoll == 2 && rollScore + lastRollScore == 10;
    }

    /**
     * Calculates the bonus points gathered from the previous rolls
     *
     * @param rollScore score of the current roll
     */
    private void calculateBonus(int rollScore) {
        totalScore += nextBonus * rollScore;
        nextBonus = nextNextBonus;
        nextNextBonus = 0;
    }

    private boolean gameFinished() {
        return currentFrame > 10;
    }

    private int getTotalScore() {
        return totalScore;
    }

    /**
     * Simulates a whole game of bowling
     *
     * @param inputs scores of the individual rolls
     * @return total score of the game
     */
    public int runWholeGame(int[] inputs) {
        for (int i : inputs) {
            inputRoll(i);
        }
        return totalScore;
    }

    public void printState() {
        System.out.printf("Score: %d\nFrame: %d\nRoll: %d\n\n", totalScore, currentFrame, currentRoll);
    }


    public static void main(String[] args) {
        Bowling bowling = new Bowling();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to bowling! Enter your first score to begin.");
        bowling.printState();
        while (!bowling.gameFinished() && scanner.hasNext()) {
            bowling.inputRoll(Integer.parseInt(scanner.next()));
            bowling.printState();
        }
        System.out.println("Game finished! Total score: " + bowling.getTotalScore());
    }
}
