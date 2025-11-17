public class Fire extends Pokemon
{
    public Fire(String n, int H, int A)
    {
        super(n, H, A, "fire", "grass");
        Attack[] ats = {
            new Attack("Ember", 60, 100),
            new Attack("Fire Punch", 85, 80),
            new Attack("Flame Wheel", 70, 90),
        };
        super.setAttacks(ats);
    }
}