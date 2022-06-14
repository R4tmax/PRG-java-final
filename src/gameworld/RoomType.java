package gameworld;

/**
 * @author Martin Kadlec
 * @author Last refactored on 09.06.2022
 *
 * <p>
 *      ENUM holding the possible interaction types for the Rooms.
 *      See docs on specific enumerations for more info.
 * </p>
 * @see Interactions
 * @see Room
 */
public enum RoomType {
    /**
     * RECON represents 'stock'
     * interaction option and has no gameplay effect.
     */
    RECON,
    /**
     * TALKABLE represents Scout positioning in the world.
     * Scout acts as a tutorial or a guide for a player,
     * giving basic info and tips for the game.
     */
    TALKABLE,
    /**
     * TRADEABLE represents the market position in the world.
     * Players can upgrade their gear on the market.
     */
    TRADABLE,
    /**
     * HOSTILE is used for the rooms containing enemies, interacting here prints
     * flavor text for the player.
     */
    HOSTILE,
    /**
     * REST_AREA represents the Tavern position in the world.
     * Player can refill their resource pools here, and gain
     * minor damage benefit, for a cost.
     */
    REST_AREA
}
