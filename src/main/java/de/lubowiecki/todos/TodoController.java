package de.lubowiecki.todos;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // Normale Browserfähige Seite die z.B. HTML ausliefern
public class TodoController {

    //private List<Todo> todos = new ArrayList<>();
    private TodoRepository repository;

    public TodoController(TodoRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/") // http://localhost:8080/
    public String home(Model ui) {
        // Model ermöglicht Informationen aus den Methoden an die HTML-Templates zu übergeben
        ui.addAttribute("headline", "Startseite");
        return "standard";
    }

    @RequestMapping("/list") // http://localhost:8080/list
    public String list(Model ui) {
        // attributeName = Name der Variable im HTML-Template
        // attributeValue = der Wert der übertragen wird
        ui.addAttribute("headline", "Todo-Liste");
        ui.addAttribute("showTable", true);
        //ui.addAttribute("todos", todos);
        ui.addAttribute("todos", repository.findAll());
        return "standard";
    }

    @RequestMapping("/settings") // http://localhost:8080/settings
    public String settings(Model ui) {
        ui.addAttribute("headline", "Einstellungen");
        return "standard";
    }

    @PostMapping("/save") // http://localhost:8080/save Erlaubt nur Aufrufe mit der HTTP-Post-Methode
    public String save(String title, String description) {
        //todos.add(new Todo(title, description));
        repository.save(new Todo(title, description));
        return "redirect:/list";
    }

    @GetMapping("/done/{id}")
    public String done(@PathVariable int id) {
        Todo todo = repository.findById(id).get();
        todo.setDone(true);
        repository.save(todo);
        return "redirect:/list";
    }

    @GetMapping("/undone/{id}")
    public String undone(@PathVariable int id) {
        Todo todo = repository.findById(id).get();
        todo.setDone(false);
        repository.save(todo);
        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        repository.deleteById(id);
        return "redirect:/list";
    }
}
