package enemies;

public class TheBrute extends Monster implements HostileActions{

    public TheBrute(String name, int health, int damage) {
        super(name, health, damage);
    }

    @Override
    public void initialMessage() {
        System.out.println("""
                            Stojí proti tobě obrovské stvoření, minimálně tři metry vysoké!
                            Není jiného zbytí, tohle bude maraton!
                            """);
    }

    //TODO: Attacks once, but with higher damage than others
    @Override
    public void attackPattern() {
    }
}
