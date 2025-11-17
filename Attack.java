public class Attack
{
    private String name;
    private int power;
    private int accuracy;
    
    public Attack(String n, int p, int a)
    {
        name = n;
        power = p;
        accuracy = a;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getPower()
    {
        return power;
    }
    
    public int getAccuracy()
    {
        return accuracy;
    }
    
    public String toString()
    {
        return name + ": " + power + " power, " + accuracy + "% accuracy";
    }
}