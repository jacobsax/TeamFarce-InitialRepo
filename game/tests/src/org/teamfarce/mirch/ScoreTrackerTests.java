package org.teamfarce.mirch;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ScoreTrackerTests {
    public static final ScoreTracker.ScoreCalculator simpleCal = (i, j, k, l) -> {
        return i + j + k + l;
    };

    // These test cases test the general functionality of the score tracker.
    @Test
    public void testCase0() {
        final ScoreTracker sc = new ScoreTracker();
        final int calScore = sc.collectScore(this.simpleCal);
        assertEquals(calScore, 0);
    }

    @Test
    public void testCase1() {
        final ScoreTracker sc = new ScoreTracker();

        sc.addIncorrectAccusation();
        sc.addIncorrectAccusation();
        sc.addQuestion();
        sc.addQuestion();
        sc.addQuestion();
        sc.addQuestion();
        sc.addQuestion();
        sc.addClue();
        sc.incrementGameTicks();
        sc.incrementGameTicks();
        sc.incrementGameTicks();
        sc.incrementGameTicks();
        sc.incrementGameTicks();
        sc.incrementGameTicks();
        sc.incrementGameTicks();
        sc.incrementGameTicks();

        final int calScore = sc.collectScore(this.simpleCal);
        assertEquals(calScore, 16);
    }
}
