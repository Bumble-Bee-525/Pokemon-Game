public class Water extends Pokemon
{
    public Water(String n, int H, int A)
    {
        super(n, H, A, "water", "fire");
        Attack[] ats = {
            new Attack("Bubble", 40, 100),
            new Attack("Hydro Pump", 185, 30),
            new Attack("Surf", 70, 90)
        };
        super.setAttacks(ats);
    }
}