import java.util.ArrayList;

public class Computer extends User
{
    public Computer(String n, ArrayList<Pokemon> l)
    {
        super(n, l);
    }
    
    //On the instructions it says this should be named "switch", but this is a reserved keyword in java, so I've named this switchTo.
    public void switchTo()
    {
        int i = (int) (Math.random() * super.getLivePokemon().size());
        super.switchTo(super.getLivePokemon().get(i));
        
    }
    
    public void attack(Pokemon enemy)
    {
        //list all attacks of current pokemon
        int rng = (int) (Math.random() * 3);
        super.getCurrentPokemon().attack(enemy, rng, super.getCurrentPokemon().getSpecial());
    }    

    public void play_turn(User player)
    {
        //random number between 1 and 6 inclusive
        int rng = (int) (Math.random() * 6 + 1);
        
        //if a switch must occur
        if (super.getCurrentPokemon().getHP() <= 0)
        {
            switchTo();
        }
        //if number 1-4 (2/3 chance)
        else if (rng <= 4)
        {
            attack(player.getCurrentPokemon());
            if (player.getCurrentPokemon().getHP() <= 0)
            {
                player.currentRemove();
            }
        }
        //if number is 5 (1/6 chance)
        else if (rng == 5)
        {
            super.heal();
        }
        //if number is 6 (1/6 chance)
        else
        {
            switchTo();
        }
    }
}