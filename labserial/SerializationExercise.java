package labserial;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SerializationExercise {
    /*
        https://jsonplaceholder.typicode.com/
     */
    /*
    1. Korzystając z podanej strony i klasy GetHttpData przygotuj program, który pobierze dane dotyczące użytkowników
    i zadań które mają do zrobienia (users i todos).
    2. Przygotuj listę potrzebnych obiektów niezbędnych do poprawnego zdeserializowania pobranych danych.
    3. Jako wynik programu zapisz plik users_todo.json, który:
        - będzie zawierał użytkowników (dla każdego użytkownika ma być podane imię, nazwisko, email, i firma w której pracuje)
        - dla każdego użytkownika będzie podana lista zadań do zrobienia i zrobionych ( "todos" pobrane ze strony,
        pogrupowane względem identyfikatorów użytkowników, ale podzielone na kategorie "done" oraz "stillTodo" - użyj
        pola "completed" aby to osiągnąć).
        - kolejność elementow na listach nie ma znaczenia, liczy się poprawność

        Oczekiwana struktura pliku json:
[
   {
  	"id": 1,
  	"name": "Leanne Graham",
  	"username": "Bret",
  	"email": "Sincere@april.biz",
  	"company": {
  		"name": "Romaguera-Crona",
  		"catchPhrase": "Multi-layered client-server neural-net",
  		"bs": "harness real-time e-markets"
  	},
  	"done": [{
  			"id": 4,
  			"title": "et porro tempora"
  		},
  		{
  			"id": 8,
  			"title": "quo adipisci enim quam ut ab"
  		},
  		{
  			"id": 10,
  			"title": "illo est ratione doloremque quia maiores aut"
  		},
  		{
  			"id": 11,
  			"title": "vero rerum temporibus dolor"
  		},
  		{
  			"id": 12,
  			"title": "ipsa repellendus fugit nisi"
  		},
  		{
  			"id": 14,
  			"title": "repellendus sunt dolores architecto voluptatum"
  		},
  		{
  			"id": 15,
  			"title": "ab voluptatum amet voluptas"
  		},
  		{
  			"id": 16,
  			"title": "accusamus eos facilis sint et aut voluptatem"
  		},
  		{
  			"id": 17,
  			"title": "quo laboriosam deleniti aut qui"
  		},
  		{
  			"id": 19,
  			"title": "molestiae ipsa aut voluptatibus pariatur dolor nihil"
  		},
  		{
  			"id": 20,
  			"title": "ullam nobis libero sapiente ad optio sint"
  		}
  	],
  	"stillTodo": [{
  			"id": 1,
  			"title": "delectus aut autem"
  		},
  		{
  			"id": 18,
  			"title": "dolorum est consequatur ea mollitia in culpa"
  		},
  		{
  			"id": 2,
  			"title": "quis ut nam facilis et officia qui"
  		},
  		{
  			"id": 3,
  			"title": "fugiat veniam minus"
  		},

  		{
  			"id": 5,
  			"title": "laboriosam mollitia et enim quasi adipisci quia provident illum"
  		},
  		{
  			"id": 6,
  			"title": "qui ullam ratione quibusdam voluptatem quia omnis"
  		},
  		{
  			"id": 7,
  			"title": "illo expedita consequatur quia in"
  		},
  		{
  			"id": 9,
  			"title": "molestiae perspiciatis ipsa"
  		},
  		{
  			"id": 13,
  			"title": "et doloremque nulla"
  		}
  	]
  },
  {
    "id": 2,
    "name": "Ervin Howell",
    "username": "Antonette",
    "email": "Shanna@melissa.tv",
    "company": {
      "name": "Deckow-Crist",
      "catchPhrase": "Proactive didactic contingency",
      "bs": "synergize scalable supply-chains"
    }
    "done" : [ ... lista ... ],
    "stillTodo" : [ ... lista ...]
  }
  {.... kolejni uzytkownicy ...}
]
     */


    public static void main(String[] args) {
        new SerializationExercise().run();
    }

    private void run() {
        String json = GetHttpData.HttpGet("https://jsonplaceholder.typicode.com/users");
        Type listType = new TypeToken<ArrayList<UserNoAdress>>(){}.getType();
        List<UserNoAdress> users = new Gson().fromJson(json, listType);
        //users.forEach(so -> System.out.println(so.toString()));

        json = GetHttpData.HttpGet("https://jsonplaceholder.typicode.com/todos");
        listType = new TypeToken<ArrayList<Todo>>(){}.getType();
        List<Todo> todos = new Gson().fromJson(json, listType);
        //todos.forEach(so -> System.out.println(so.toString()));

        for(UserNoAdress u : users){ //adding all tasks
            u.done = new ArrayList<TodoCut>();
            u.stillTodo = new ArrayList<TodoCut>();

            for(Todo td : todos){
                if(td.userId == u.id){
                    TodoCut tdc = new TodoCut(td.id, td.title);

                    if(td.completed) u.done.add(tdc);
                    else u.stillTodo.add(tdc);
                }
            }
        }
        Gson gson = new Gson();
        System.out.println(gson.toJson(users));

        String output = gson.toJson(users);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("outp.txt")))) {
            bw.write(output);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
