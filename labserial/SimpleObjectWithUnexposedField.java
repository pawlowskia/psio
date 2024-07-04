package labserial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SimpleObjectWithUnexposedField {
    @SerializedName("Identifier")
    @Expose
    private int id;

    @SerializedName("Name")
    @Expose
    private String value;

    @Expose(serialize = false)
    private String unexposedString = "UNAVAILABLE";

    public SimpleObjectWithUnexposedField(int id, String value) {
        this.id = id;
        this.value = value;
    }
}
