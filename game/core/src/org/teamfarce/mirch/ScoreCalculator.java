package org.teamfarce.mirch;

/**
 * A score calculator function which takes the given parameters and provides back a simple
 * integer score.
 */
public class ScoreCalculator implements ScoreTracker.ScoreCalculator {
    public int calculateScore(
        int timeTaken, int incorrectAccusations, int askedQuestions, int cluesFound
    ) {
        // Decrease the score by 1 every 5 seconds.
        final int timeScore = -(timeTaken / 300);

        // An incorrect accusation has a penalty of 50 points.
        final int accusationsScore = incorrectAccusations * -50;

        // Each clue is worth 5 points.
        final int clueScore = cluesFound * 5;

        // The base score is 150.
        return 150 + timeScore + accusationsScore + askedQuestions + clueScore;
    }
}
