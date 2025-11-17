import java.util.ArrayList;
import java.util.Scanner;

public class User
{
    private ArrayList<Pokemon> oofPokemon  = new ArrayList<Pokemon>();
    private ArrayList<Pokemon> livePokemon;
    private String name;
    private Pokemon currentPokemon;
    
    //constructor
    public User(String n, ArrayList<Pokemon> l)
    {
        name = n;
        livePokemon = l;
    }
    
    
    public void attack(Pokemon enemy)
    {
        //list all attacks of current pokemon
        Scanner input = new Scanner(System.in);
        System.out.println("All attacks of current pokemon: ");
        Attack[] ats = currentPokemon.getAttacks();
        for (int i = 0; i < ats.length; i++)
        {
            System.out.print(i + 1 + ". ");
            System.out.println(ats[i]);
        }
                
        //user chooses an attack
        int choice = -1;
        while (choice < 1 || choice > ats.length)
        {
            System.out.println("Enter the corresponding number to select an attack");
            choice = Integer.parseInt(input.nextLine());
        }
        choice--;
                
        // call pokemon attack method
        System.out.print(name + " is attacking. ");
        currentPokemon.attack(enemy, choice, currentPokemon.getSpecial());
        //input.close();
    }
    
    public void heal()
    {
        currentPokemon.heal();
        System.out.println(name + " used heal on " + currentPokemon.getName());
    }
    
    public ArrayList<Pokemon> getLivePokemon()
    {
        return livePokemon;
    }
    
    public String getName()
    {
        return name;
    }
    
    //On the instructions it says this should be named "switch", but this is a reserved keyword in java, so I've named this switchTo.
    public void switchTo(Pokemon p)
    {
        currentPokemon = p;
        System.out.println(name + " is now using " + currentPokemon.getName());
    }
    
    public void printOptions()
    {
        //print alive pokemon
        int i = 1;
        for (Pokemon p : livePokemon)
        {
            System.out.println(i + ". " + p.getName() + ": " + p.getHP() + "HP, " + p.getAP() + "AP");
            i++;
        }
    }
    public void printStats()
    {
        //print alive pokemon
        for (Pokemon p : livePokemon)
        {
            System.out.println(p.getName() + ": " + p.getHP() + "HP, " + p.getAP() + "AP");
        }
        
        //print dead pokemon
        for (Pokemon p : oofPokemon)
        {
            System.out.println(p.getName() + ": " + p.getHP() + "HP, " + p.getAP() + "AP");
        }
    }
    
    public Pokemon getCurrentPokemon()
    {
        return currentPokemon;
    }
    
    public void currentRemove()
    {
        for (int i = 0; i < livePokemon.size(); i++)
        {
            if (livePokemon.get(i).getName().equals(currentPokemon.getName()))
            {
                livePokemon.remove(i);
                break;
            }
        }
    }
    
    public void play_turn(Computer bot)
    {
        String temp = "";
        Scanner input = new Scanner(System.in);
        
        //if current pokemon is dead, user must switch
        if (getCurrentPokemon().getHP() <= 0)
        {
            System.out.println("Your current pokemon is down, you must switch");
            temp = "switch";
        }
            
        else
        {
            //get user input
            temp = "";
            while (!temp.equals("attack") & !temp.equals("heal") & !temp.equals("switch") & !temp.equals("stats"))
            {
                System.out.println("What do you wish to do? Enter either 'attack', 'heal', 'switch' or 'stats'");
                temp = input.nextLine();
            }
        
            //stats doesn't end turn, so they can technically do this as many times as they want. Keep getting inputs until they decide to do something that ends the turn.
            while (temp.equals("stats"))
            {
                printStats();
                temp = "";
                while (!temp.equals("attack") & !temp.equals("heal") & !temp.equals("switch") & !temp.equals("stats"))
                {
                    System.out.println("What do you wish to do? Enter either 'attack', 'heal', 'switch' or 'stats'");
                    temp = input.nextLine();
                }
            }
        }
        
        
        //do the user's action
        switch (temp)
        {
            case "attack":
                attack(bot.getCurrentPokemon());
                if (bot.getCurrentPokemon().getHP() <= 0)
                {
                    bot.currentRemove();
                }
                break;
            case "heal":
                //heal current pokemon
                heal();
                break;
            case "switch":
                //print the current alive pokemon
                printOptions();
                
                //get user to choose one
                int choice = -1;
                while (choice > getLivePokemon().size() || choice < 1)
                {
                    System.out.println("Enter a number to select");
                    choice = Integer.parseInt(input.nextLine());
                }
                choice--;
                
                //call method to change the current pokemon
                switchTo(getLivePokemon().get(choice));
                break;
        }

        //input.close();
    }
}