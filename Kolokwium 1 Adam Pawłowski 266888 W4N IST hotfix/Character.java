public abstract class Character {
    private Weapon weapon;
    private Armor armor;
    private String name;

    abstract public void goForward();

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        if (weapon == null) this.weapon = new Fork();
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
        if (armor == null) this.armor = new LightArmor();
    }

    public void setName(String name) {
        this.name = name;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public String getName() {
        return name;
    }

}
