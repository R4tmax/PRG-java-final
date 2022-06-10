package enemies;

public class TheMatriarch extends Monster implements HostileActions{
    public TheMatriarch(String name, int health, int damage) {
        super(name, health, damage);
    }

    @Override
    public void initialMessage() {
        System.out.println("""
                            Jak zkoumáš cesty pahorkatiny, nemůžeš se zbavit pocitu,
                            že tě někdo... něco, pozoruje.
                            Jak postupuješ hlouběji, vzduch jakoby houstnul a ztěžknul.
                            Tvé tělo střídá návaly chladu a horka, a okolo je nepřirozené ticho.
                            Když v tom.... BRAŇ SE!
                            """);
    }

    //TODO:Attacks twice in a row
    @Override
    public void attackPattern() {
    }
}
