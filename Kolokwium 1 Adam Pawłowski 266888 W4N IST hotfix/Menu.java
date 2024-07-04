//Adam Pawłowski W4N IST 266888
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private ArrayList<Character> characters;
    public Scanner scanner;

    private int scanInt(int L, int R) {
        int tmp;

        System.out.print("\nYour choice (insert an integer): ");

        try {
            tmp = scanner.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.print("\nThat's not an integer! Please, insert an integer: ");
            scanner.nextLine();
            return scanInt(L, R);
        }

        if (tmp < L || tmp > R) {
            System.out.printf("\nPlease, insert an integer from range [%d, %d]: ", L, R);
            return scanInt(L, R);
        }
        return tmp;
    }

    public Menu() {
        scanner = new Scanner(System.in);
        characters = new ArrayList<Character>();
    }

    private void options() {
        System.out.println("0) Print the game rules.");
        System.out.println("1) Create a new character.");
        System.out.println("2) Rename an existing character.");
        System.out.println("3) Change an existing character's weapon.");
        System.out.println("4) Print all created characters.");
        System.out.println("5) Choose an existing character to present his weapon.");
        System.out.println("6) Choose an existing character to march forward.");
        System.out.println("7) Choose two existing characters to fight each other."); //use class simulator
        System.out.println("8) Exit the game.");
    }

    private void printRules() {
        System.out.println("A character can be only one character type.\nCharacter type cannot be changed after creation.\nA character can be given only one weapon, which can be changed later.\nA character can have only one armor type, which cannot be changed after creation.\n\n");
    }

    private void printCharacterList() {
        System.out.println("Available characters :");
        for (int i = 0; i < characters.size(); i++) System.out.println((i + 1) + ". " + characters.get(i).toString());
        System.out.println();
    }

    private void printWeaponList() {
        System.out.println("Available weapons :");
        System.out.println("1. Bow\n2. Sword\n3. Fork\n");
        System.out.println();
    }

    private Character chooseCharacter() {
        if(characters.size() == 0){
            System.out.println("\nThe list of characters is empty! Please, create a character first:");
            createCharacter();
        }

        System.out.println("\nWhich character do you choose?\n");
        printCharacterList();
        return characters.get(scanInt(1, characters.size()) - 1);
    }

    private void createCharacter() {
        System.out.println("\nAvailable character types: ");
        System.out.println("1. Knight\n2. King\n3. Peasant\n4. Cook");
        int tmp = scanInt(1, 4);

        Character c = switch (tmp) {
            case 1 -> new Knight();
            case 2 -> new King();
            case 3 -> new Peasant();
            default -> new Cook();
        };

        nameCharacter(c);
        chooseWeapon(c);
        chooseArmor(c);
        characters.add(c);
        System.out.println("\nCharacter " + c.toString() + " added!\n");
    }

    private void chooseWeapon(Character c) {
        if (c == null) System.out.println("\nError! Character not found.");

        printWeaponList();
        int tmp = scanInt(1, 3);

        Weapon w = switch (tmp) {
            case 1 -> new Bow();
            case 2 -> new Sword();
            default -> new Fork();
        };

        c.setWeapon(w);
    }

    private void chooseArmor(Character c) {
        System.out.print("\nAvailable armor types:\n1. Light armor\n2. Heavy armor");
        int tmp = scanInt(1, 2);

        if (tmp == 1) c.setArmor(new LightArmor());
        else c.setArmor(new HeavyArmor());
    }

    private void nameCharacter(Character c) {
        System.out.print("\nYour choice of character name: ");
        String tmp;

        try {
            tmp = scanner.next();
        } catch (java.util.InputMismatchException e) {
            System.out.print("\nThat's not a name! Please, insert a text: ");
            scanner.nextLine();
            nameCharacter(c);
            return;
        }
        c.setName(tmp);
    }

    private void simulatePresentWeapon(Character c) {
        if (c == null) System.out.println("\nError! Character not found.");

        System.out.print("\n" + c.toString() + " is ");
        c.getWeapon().presentWeapon();
    }

    private void sendToFight(){
        if(characters.size() < 2){
            System.out.println("Not enough characters! (you need at least two).\n");
            return;
        }

        boolean ready = false;
        Simulator simulator = new Simulator();
        Character x = new King(), y = x;
        while(!ready){
            x = chooseCharacter();
            y = chooseCharacter();

            if(x == y) {
                System.out.println("You need to choose two different characters. Please, try again.\n");
            }
            else ready = true;
        }
        simulator.beginFight(x, y);
        characters.remove(simulator.simulateFight(x, y));
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        int choice = 0;

        System.out.println("Welcome! Choose an option:");
        while (choice != 8) {
            menu.options();

            choice = menu.scanInt(0, 8);

            switch (choice) {
                case 0 -> menu.printRules();
                case 1 -> menu.createCharacter();
                case 2 -> menu.nameCharacter(menu.chooseCharacter());
                case 3 -> menu.chooseWeapon(menu.chooseCharacter());
                case 4 -> menu.printCharacterList();
                case 5 -> menu.simulatePresentWeapon(menu.chooseCharacter());
                case 6 -> menu.chooseCharacter().goForward();
                case 7 -> menu.sendToFight();
                case 8 -> System.out.println("\nExiting...");
                default -> System.out.println("Somehow you managed to break my scanInt method, please insert an integer.");
            }
        }
    }
}
