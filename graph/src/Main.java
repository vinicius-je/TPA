import entities.Graph;
import entities.Menu;

import java.io.Console;

public class Main {
    public static void main(String[] args) {
        Console con = System.console();

        Menu menu = new Menu(new Graph(), con);

        int option;

        do{
            menu.display();
            option = Integer.parseInt(con.readLine("\n\tDigite a opção: "));
            menu.readOption(option);
        }while(option != 4);
    }
}