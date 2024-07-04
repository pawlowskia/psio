public class Cook extends Character {
    public void goForward() {
        System.out.println("Cook " + getName() + "walks forward. He doesn't seem to care too much.");
    }

    public String toString() {
        return "Cook " + getName();
    }
}
