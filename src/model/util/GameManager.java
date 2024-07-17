package model.util;

import model.ai.Ai;

public class GameManager {

    private boolean[][] playfield;
    private Ai ai;

    public GameManager(int size) {
        playfield = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                playfield[i][j] = false;
            }
        }
        ai = new Ai(size, this);
    }

    public boolean getField(int xCord, int yCord) {
        return playfield[xCord][yCord];
    }

    public void test() {
        ai.printField();
        ai.shootField(4, 4);
        ai.printField();
    }


}
