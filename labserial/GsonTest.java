package labserial;

import com.google.gson.Gson;

import java.util.Arrays;

public class GsonTest {
    public static void main(String[] args) {
        Gson gson = new Gson();
        System.out.println(gson.toJson(1));
        System.out.println(gson.toJson("abcd"));
        System.out.println(gson.toJson(new Long(10)));
        int[] values = { 1 };
        System.out.println(gson.toJson(values));


        int one = gson.fromJson("1", int.class);
        String str = gson.fromJson("\"abc\"", String.class);
        String[] anotherStr = gson.fromJson("[\"EEE\", \"FFF\"]", String[].class);

        System.out.println(one);
        System.out.println(str);
        Arrays.stream(anotherStr).forEach(System.out::println);
    }
}
