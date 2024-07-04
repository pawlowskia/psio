//Adam Pawłowski W4N IST 266888
import java.util.Random;

public class Simulator {
    public void beginFight(Character x, Character y) {
        System.out.print("\n*****\n");
        x.goForward();
        System.out.print(x.toString() + " is ");
        x.getWeapon().presentWeapon();
        System.out.println(x.toString() + " shouts \"" + y.getName() + "! I challenge you!\"\nThe crowd freezes.\n");

        y.goForward();
        System.out.print(y.toString() + " is ");
        y.getWeapon().presentWeapon();
        System.out.print("He accepts the challenge. They stand opposite to each other. Suddenly...");
        System.out.print("\n*****\n");
    }

    private static int damage(int att, int def) {
        Random random = new Random();

        int tmp = random.nextInt(att) - random.nextInt(def);
        tmp = Math.max(tmp, 0);
        return tmp;
    }

    public Character simulateFight(Character x, Character y) {
        int hpX = 20, hpY = 20;
        Character toDelete = x;

        Random random = new Random();

        while (hpX * hpY > 0) {
            if (random.nextBoolean()) { //x strikes
                int dmg = damage(x.getWeapon().damage(), y.getArmor().armorValue());
                if (dmg >= hpY) {
                    System.out.println(x.toString() + " finishes the duel, dealing " + dmg + " damage. " + y.toString() + " is lying on the ground, unconscious.\n");
                    toDelete = y;
                } else System.out.println(x.toString() + " attacks. He deals " + dmg + " damage. \n");

                hpY -= dmg;
            } else {
                int dmg = damage(y.getWeapon().damage(), x.getArmor().armorValue());
                if (dmg >= hpX) {
                    System.out.println(y.toString() + " is about to attack once more (for " + dmg + " damage), but " + x.toString() + " begs him for mercy.\n");
                    toDelete = x;
                } else System.out.println(y.toString() + " attacks. He deals " + dmg + " damage. \n");

                hpY -= dmg;
            }
        }
        System.out.print("\nThe duel is over. The crowd applauds the victor.\n*****\n");
        return toDelete;
    }

}
