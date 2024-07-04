public class King extends Character {
    public void goForward() {
        System.out.println("King " + getName() + " walks forward with grace. ");
    }

    public String toString() {
        return "King " + getName();
    }
}
