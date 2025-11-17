import java.util.Scanner;
import java.util.ArrayList;

public class GameClient
{
    public static void main(String[] args)
    {
        //declare variables
        Scanner input = new Scanner(System.in);
        ArrayList<Pokemon> master = new ArrayList<Pokemon>();
        ArrayList<Pokemon> userPokemon = new ArrayList<Pokemon>();
        ArrayList<Pokemon> botPokemon = new ArrayList<Pokemon>();
        String username;
        String botname;
        
        //initialize all pokemon to master list
        master.add(new Fire("Charmander", 25, 70));
        master.add(new Fire("Ninetails", 30, 50));
        master.add(new Fire("Ponyta", 40, 60));
        master.add(new Water("Squirtle", 80, 20));
        master.add(new Water("Psyduck", 70, 40));
        master.add(new Water("Polywag", 50, 50));
        master.add(new Grass("Bulbasoar", 60, 40));
        master.add(new Grass("Bellsprout", 40, 60));
        master.add(new Grass("Oddish", 50, 50));
        
        //get username
        System.out.println("What is your name?");
        username = input.nextLine();
        System.out.println();
        
        //get computer name
        System.out.println("What is your opponents name?");
        botname = input.nextLine();
        System.out.println();
        
        //print out list of pokemon
        System.out.println("These are your options for pokemon:");
        for (Pokemon p : master)
        {
            System.out.println(p.getName() + ": " + p.getHP() + "HP, " + p.getAP() + "AP");
        }
        System.out.println();
        
        //get user to input which pokemon to be in their hand
        String temp;
        while (userPokemon.size() < 3)
        {
            System.out.println("Please pick a pokemon (enter name):");
            temp = input.nextLine();

            for (int i = 0; i < master.size(); i++)
            {
                if (master.get(i).getName().equals(temp))
                {
                    userPokemon.add(master.get(i));
                    master.remove(i);
                    break;
                }
            }
            System.out.println("You now currently have " + userPokemon.size() + " pokemon in your hand");
            System.out.println();
        }
        
        //generate bot current pokemon
        for (int i = 0; i < 3; i++)
        {
            int j = (int) (Math.random() * master.size());
            botPokemon.add(master.get(j));
            master.remove(j);
        }

        //create user and bot
        User player = new User(username, userPokemon);
        Computer bot = new Computer(botname, botPokemon);
        
        //pick current pokemon for person and for bot
        System.out.println("To start, pick one of the pokemon you chose using a number 1-3");
        
        int i = 0;
        while (i < 1 || i > 3)
        {
            i = Integer.parseInt(input.nextLine());
        }
        
        i--;
        System.out.println();
        player.switchTo(player.getLivePokemon().get(i));
        bot.switchTo();
        
        //announce game started
        System.out.println("The game has started!\n");
        

        
        System.out.println("Your current pokemon " + player.getCurrentPokemon().getName() + " has " + player.getCurrentPokemon().getHP() + "HP");
        System.out.println("The computer's current pokemon " + bot.getCurrentPokemon().getName() + " has " + bot.getCurrentPokemon().getHP() + "HP");
        System.out.println();

        
        //Start the match
        while (true)
        {
            //do user's action
            System.out.println("It is now your turn:");
            player.play_turn(bot);
            System.out.println();
            
            //check if user won
            if (bot.getLivePokemon().size() == 0)
            {
                System.out.println(username + " has won.");
                input.close();
                return;
            }
            
            //print currentHP
            System.out.println("Your current pokemon " + player.getCurrentPokemon().getName() + " has " + player.getCurrentPokemon().getHP() + "HP");
            System.out.println("The computer's current pokemon " + bot.getCurrentPokemon().getName() + " has " + bot.getCurrentPokemon().getHP() + "HP");
            System.out.println();
            
            //do the computer's action
            bot.play_turn(player);
            System.out.println();

            //print currentHP
            System.out.println("Your current pokemon " + player.getCurrentPokemon().getName() + " has " + player.getCurrentPokemon().getHP() + "HP");
            System.out.println("The computer's current pokemon " + bot.getCurrentPokemon().getName() + " has " + bot.getCurrentPokemon().getHP() + "HP");
            System.out.println();

            //check if computer won
            if (player.getLivePokemon().size() == 0)
            {
                System.out.println(botname + " has won.");
                input.close();
                return;
            }
        }
    }
}