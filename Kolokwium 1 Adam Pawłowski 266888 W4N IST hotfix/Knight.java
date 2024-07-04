public class Knight extends Character {
    public void goForward() {
        System.out.println("Knight " + getName() + " walks forward bravely. ");
    }

    public String toString() {
        return "Knight " + getName();
    }
}
