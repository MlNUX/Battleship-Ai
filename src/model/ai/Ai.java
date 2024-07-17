package model.ai;

import model.util.GameManager;

/**
 * Main class for the Ai logic.
 *
 * @author minux
 */
public class Ai {

    private FieldStatus[][] playfield;
    private final int fieldSize;
    private GamePhase aiStatus;
    private GameManager manager;

    public Ai(int size, GameManager manager) {
        playfield = new FieldStatus[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                playfield[i][j] = FieldStatus.GOODFIELD;
            }
        }
        this.manager = manager;
        fieldSize = size;
        aiStatus = GamePhase.GAMBLING;
    }

    public void performMove() {
        switch (aiStatus) {
            case GAMBLING -> {
                int[] cords = findGamblingCords();
                shootField(cords[0], cords[1]);
            }
            case BATTLE -> {

            }
            case STRUCTURE -> {

            }
        }
    }

    private int[] findGamblingCords() {

    }

    public void shootField(int xCord, int yCord) {
        if (manager.getField(xCord, yCord)) {
            aiStatus = GamePhase.BATTLE;
            playfield[xCord][yCord] = FieldStatus.SHIP;
        } else {
            blockFields(xCord, yCord, 2);
        }
    }

    /**
     * Temporarily blocks spaces around a hit space if there is no ship part there.
     *
     * @param xCord  X coordinate of the hit space
     * @param yCord  Y coordinate of the hit space
     * @param radius radius around the space that should be blocked
     */
    private void blockFields(int xCord, int yCord, int radius) {
        int tempXCord = xCord - radius;
        for (int i = 0; i < 2 * radius + 1; i++) {
            int tempYCord = yCord - radius;
            for (int t = 0; t < 2 * radius + 1; t++) {
                if (tempXCord >= 0 && tempXCord < fieldSize && tempYCord >= 0 && tempYCord < fieldSize) {
                    playfield[tempXCord][tempYCord] = FieldStatus.BLOCKED;
                }
                tempYCord++;
            }
            tempXCord++;
        }
        playfield[xCord][yCord] = FieldStatus.FORBIDDEN;
    }

    /**
     * Changes the value of all fields around a ship to forbidden fields.
     *
     * @param xCord1 X coordinate of the first part of the ship
     * @param yCord1 Y coordinate of the first part of the ship
     * @param xCord2 X coordinate of the last part of the ship
     * @param yCord2 Y coordinate of the last part of the ship
     */
    private void findForbiddenFields(int xCord1, int yCord1, int xCord2, int yCord2) {
        if (xCord1 == xCord2) {
            findVerticalForbiddenFields(xCord1, yCord1, yCord2);
        } else {
            findHorizontalForbiddenFields(yCord1, xCord1, xCord2);
        }
    }

    /**
     * Changes the value of all fields around a ship to forbidden fields
     * if the ship is vertically aligned.
     *
     * @param xCord  X coordinate of the complete ship
     * @param yCord1 Y coordinate of the first ship part
     * @param yCord2 Y coordinate of the last ship part
     */
    private void findVerticalForbiddenFields(int xCord, int yCord1, int yCord2) {
        for (int x = xCord - 1; x == xCord + 1; x++) {
            for (int y = yCord1 - 1; y == yCord2; y++) {
                playfield[x][y] = FieldStatus.FORBIDDEN;
            }
        }
    }

    /**
     * Changes the value of all fields around a ship to forbidden fields
     * if the ship is horizontally aligned.
     *
     * @param yCord  Y coordinate of the complete ship
     * @param xCord1 X coordinate of the first ship part
     * @param xCord2 X coordinate of the last ship part
     */
    private void findHorizontalForbiddenFields(int yCord, int xCord1, int xCord2) {
        for (int x = xCord1 - 1; x == xCord2 + 1; x++) {
            for (int y = yCord - 1; y == yCord + 1; y++) {
                playfield[x][y] = FieldStatus.FORBIDDEN;
            }
        }
    }

    public void printField() {
        StringBuilder fieldbuilder = new StringBuilder();
        for (int y = 0; y < fieldSize; y++) {
            for (int x = 0; x < fieldSize; x++) {
                fieldbuilder.append("[ ").append(playfield[x][y].name().charAt(0)).append(" ]");
            }
            fieldbuilder.append(System.lineSeparator());
        }
        System.out.println(fieldbuilder);
    }

}
