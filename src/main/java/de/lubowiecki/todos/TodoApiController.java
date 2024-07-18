package de.lubowiecki.todos;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController // REST API: Produziert Json oder XML
@RequestMapping("/api/v1")
public class TodoApiController {

    private List<Todo> todos = new ArrayList<>();

    public TodoApiController() {
    }

    @RequestMapping("/add") // http://localhost:8080/api/v1/add
    public String addTodo() {
        return "<h1>Eintrag hinzugefügt</h1>";
    }

    @RequestMapping("/list") // http://localhost:8080/api/v1/list
    public List<Todo> getTodos() {
        return todos;
    }
}
