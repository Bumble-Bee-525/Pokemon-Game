public class Grass extends Pokemon
{
    public Grass(String n, int H, int A)
    {
        super(n, H, A, "grass", "water");
        Attack[] ats = {
            new Attack("Leaf Storm", 130, 90),
            new Attack("Mega Drain", 50, 100),
            new Attack("Razor Leaf", 55, 95)
        };
        super.setAttacks(ats);
    }
}