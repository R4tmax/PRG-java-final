package enemies;

/**
 * @author Martin Kadlec
 * @version Last refactored on 10.06.2022.
 *
 * Public interface for use in Monster subtypes.
 * Allows overriding for customized interaction
 * based on different enemy types.
 *
 * @see Monster
 */
public interface HostileActions {

   /**
    * Initial declaration for the initialMessage
    * method of the interface
    */
   void initialMessage();

   /**
    *
    * Initial declaration for the attackPattern
    * method of the interface
    *
    * @param damageValue Integer value representing calculated
    *                   base damage modifiers passed by the combat Class
    */
   void attackPattern(int damageValue);



}
