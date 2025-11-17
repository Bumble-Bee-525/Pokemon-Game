public class Pokemon {
    private String name;
    private String type;
    private String special;
    private int HP;
    private int AP;
    private Attack[] attacks;
        
    public Pokemon(String n, int H, int A, String t, String s)
    {
        name = n;
        HP = H;
        AP = A;
        type = t;
        special = s;
    }
        
    public String getName()
    {
        return name;
    }
        
    public int getHP()
    {
        return HP;
    }
        
    public int getAP()
    {
        return AP;
    }
    
    public String getType()
    {
        return type;
    }
    
    public String getSpecial()
    {
        return special;
    }
    
    public void heal()
    {
        HP += 20;
    }
    
    public void setAttacks(Attack[] attacks)
    {
        this.attacks = attacks;
    }
    
    public Attack[] getAttacks()
    {
        return attacks;
    }
    
    public void attack(Pokemon enemy, int i, String special)
    {
        //Get attack method
        Attack method = attacks[i];
        int accuracy = method.getAccuracy();
        
        //if the method deals less than maximum damage, set damage to the method's pp
        int damage;
        if (getAP() > method.getPower())
        {
            damage = method.getPower();
        }
        
        //if the method deals more than the maximum damage allowed, set damage to the maximum
        else
        {
            damage = getAP();
        }
        
        //random number between 0 and 20 (inclusive)
        int rng = (int) (Math.random() * 21);
        damage -= rng;
        
        //randomly fail
        rng = (int) (Math.random() * 100 + 1);
        if (rng > accuracy)
        {
            System.out.println("Attack failed.");
            return;
        }
        
        //multiplier if any
        if (enemy.getType().equals(special))
        {
            damage *= 1.5;
        }
        
        //deal the damage
        enemy.attacked(damage);
        System.out.println(name + " used " + method.getName() + " and did " + damage + " damage.");
    }
    
    public void attacked(int damage)
    {
        HP -= damage;
        if (HP < 0)
        {
            HP = 0;
        }
    }
}