package labserial;

import java.util.ArrayList;

public class User {
    int id;
    String name, username, email, phone, website;
    Address address;
    Company company;
    ArrayList<TodoCut> done, stillTodo;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", address=" + address +
                ", company=" + company +
                '}';
    }
}
