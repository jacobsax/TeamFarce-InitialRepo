package org.teamfarce.mirch;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class GameSnapshotTest {
    @Test
    public void getRooms() {
        GameSnapshot gameSnapshot = new GameSnapshot(null, null, null, null, null);
        assertSame(gameSnapshot.getRooms(), gameSnapshot.rooms);
    }

    @Test
    public void getClues() {
        GameSnapshot gameSnapshot = new GameSnapshot(null, null, null, null, null);
        assertSame(gameSnapshot.getClues(), gameSnapshot.clues);
    }
}
