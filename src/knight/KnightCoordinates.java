package knight;

/**
 * @author Martin Kadlec
 * @version Last refactored on 13.06.2022.
 *
 * <p>
 *     Auxiliary class for the TheKnight class.
 *     Saves integer values representing coordinates on the
 *     2D grid.
 * </p>
 *
 * @see TheKnight
 * @see gameworld.Map
 */
public class KnightCoordinates {
    protected int horizontal;
    protected int vertical;

    public int getHorizontal() {
        return horizontal;
    }

    public int getVertical() {
        return vertical;
    }


    /**
     * Standard constructor for the class.
     * By design is called once on build time by TheKnight.
     *
     * @param horizontal Integer representing the X coordinate
     * @param vertical Integer representing the Y coordinate
     */
    public KnightCoordinates(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }
}
