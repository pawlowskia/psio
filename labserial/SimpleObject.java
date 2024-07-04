package labserial;

public class SimpleObject {
    private int id;
    private String value;

    public SimpleObject(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
