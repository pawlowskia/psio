public class Peasant extends Character {
    public void goForward() {
        System.out.println("Peasant " + getName() + " walks forward. He looks tired. ");
    }

    public String toString() {
        return "Peasant " + getName();
    }
}
