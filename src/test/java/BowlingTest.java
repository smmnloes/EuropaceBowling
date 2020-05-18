import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BowlingTest {

    @Test
    public void testPerfectGame() {
        Bowling bowling = new Bowling();
        int[] inputs = new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        assertEquals(300, bowling.runWholeGame(inputs));
    }

    @Test
    public void testStrike() {
        Bowling bowling = new Bowling();
        int[] inputs = new int[]{10, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
        assertEquals(50, bowling.runWholeGame(inputs) );
    }

    @Test
    public void testSpareLastFrame() {
        Bowling bowling = new Bowling();
        int[] inputs = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5,5,3};
        assertEquals(31, bowling.runWholeGame(inputs) );
    }

    @Test
    public void testSpare() {
        Bowling bowling = new Bowling();
        int[] inputs = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 2, 2};
        assertEquals(16, bowling.runWholeGame(inputs));
    }

    @Test
    public void testNoSpareNoStrike() {
        Bowling bowling = new Bowling();
        int[] inputs = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        assertEquals(20, bowling.runWholeGame(inputs));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidInputStrikeSecondRoll() {
        Bowling bowling = new Bowling();
        int[] inputs = new int[]{5, 10, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        bowling.runWholeGame(inputs);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidInputInputTooLarge() {
        Bowling bowling = new Bowling();
        int[] inputs = new int[]{11, 10, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        bowling.runWholeGame(inputs);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidInputInputTooLargeSecondRoll() {
        Bowling bowling = new Bowling();
        int[] inputs = new int[]{5, 8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        bowling.runWholeGame(inputs);
    }

    @Test(expected = IllegalStateException.class)
    public void testInvalidInputInputTooManyRolls() {
        Bowling bowling = new Bowling();
        int[] inputs = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        bowling.runWholeGame(inputs);
    }


}