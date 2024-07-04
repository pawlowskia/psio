package labserial;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GsonExamples {
    /* Other examples not described below, please refer to
    https://github.com/google/gson/blob/master/UserGuide.md
     */

    public static void main(String[] args) {
        new GsonExamples().run();
    }

    private void run() {
        //serializeSimpleObject();
        //deserializeSimpleObject();
        //serializeSimpleObjectWithFieldNames();
        //serializeSimpleObjectWithUnexposedFields();

        //serializeObjectInsideObject();
        //deserializeOnlyPartOfJson();
        //serializeObjectWithListInside();

        serializeListOfObjects();
        //deserializeListOfObjects();
    }

    private void serializeSimpleObject() {
        SimpleObject so = new SimpleObject(123, "Matejko");
        Gson gson = new Gson();
        System.out.println(gson.toJson(so));
    }

    private void deserializeSimpleObject() {
        String json = "{\"id\":1,\"value\":\"Something\"}";
        Gson gson = new Gson();
        SimpleObject so = gson.fromJson(json, SimpleObject.class);
        System.out.println(so.getId() + " " + so.getValue());
    }

    private void serializeSimpleObjectWithFieldNames() {
        SimpleObjectWithFieldNames so = new SimpleObjectWithFieldNames(123, "Matejko");
        Gson gson = new Gson();
        System.out.println(gson.toJson(so));
    }

    private void serializeSimpleObjectWithUnexposedFields() {
        SimpleObjectWithUnexposedField so = new SimpleObjectWithUnexposedField(123, "Matejko");
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        System.out.println(gson.toJson(so));
    }

    private void serializeObjectInsideObject() {
        ObjectInsideObject objectInsideObject = new ObjectInsideObject();
        Gson gson = new Gson();
        System.out.println(gson.toJson(objectInsideObject));
    }

    private void deserializeOnlyPartOfJson() {
        String json = "{\"id\":1,\"value\":\"Something\"}";
        Gson gson = new Gson();
        ObjectWithIdOnly idOnly = gson.fromJson(json, ObjectWithIdOnly.class);
        System.out.println(idOnly.id);
    }

    private void serializeObjectWithListInside() {
        SimpleObjectWithInternalList simpleObjectWithInternalList = new SimpleObjectWithInternalList(14124);
        Gson gson = new Gson();
        System.out.println(gson.toJson(simpleObjectWithInternalList));
    }

    private void serializeListOfObjects() {
        List<SimpleObject> simpleObjectList = new ArrayList<>();
        simpleObjectList.add(new SimpleObject(123, "AAA"));
        simpleObjectList.add(new SimpleObject(222, "BBB"));
        simpleObjectList.add(new SimpleObject(333, "CCC"));
        Gson gson = new Gson();
        System.out.println(gson.toJson(simpleObjectList));
    }

    private void deserializeListOfObjects() {
        String json = "[{\"id\":123,\"value\":\"AAA\"},{\"id\":222,\"value\":\"BBB\"},{\"id\":333,\"value\":\"CCC\"}]";
        Gson gson = new Gson();

        Type listType = new TypeToken<ArrayList<SimpleObject>>(){}.getType();
        List<SimpleObject> simpleObjects = new Gson().fromJson(json, listType);

        simpleObjects.forEach(so -> System.out.println(so.getId() + " " + so.getValue()));
    }
}
