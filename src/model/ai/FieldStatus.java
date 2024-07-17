package model.ai;

/**
 * The state of a court field.
 *
 * @author minux
 */
public enum FieldStatus {
    /**
     * On this field is a part of a ship.
     */
    SHIP,
    /**
     * The AI couldn't shoot at this field temporary.
     */
    BLOCKED,
    /**
     * This field is a suitable field for the next shot.
     */
    GOODFIELD,
    /**
     * This field is not a suitable field for the next shot.
     */
    BADFIELD,
    /**
     * The AI couldn't target this field anymore.
     */
    FORBIDDEN;

}
