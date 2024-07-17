package model.ai;

/**
 * The different AI strategies.
 *
 * @author minux
 */
public enum GamePhase {
    /**
     * The AI picks random fields.
     */
    GAMBLING,
    /**
     * The AI got a hit and is searching for the rest of the ship.
     */
    BATTLE,
    /**
     * The AI shoots down all strategically clever fields.
     */
    STRUCTURE;

}
