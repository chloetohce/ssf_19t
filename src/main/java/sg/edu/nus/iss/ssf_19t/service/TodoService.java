package sg.edu.nus.iss.ssf_19t.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.ssf_19t.model.Todo;
import sg.edu.nus.iss.ssf_19t.repository.MapRepository;
import sg.edu.nus.iss.ssf_19t.utilities.Constant;

@Service
public class TodoService {
    @Autowired
    private MapRepository repository;

    @Value("${file}")
    private String file;

    private File f;
    private static final Logger log = Logger.getLogger(TodoService.class.getName());

    @PostConstruct
    public void initData() throws ParseException {
        this.f = new File(file);

        try (JsonReader reader = Json.createReader(new FileInputStream(f))) {
            JsonArray array = reader.readArray();

            for (int i = 0; i < array.size(); i++) {
                JsonObject todoJson = array.get(i).asJsonObject();

                Todo todo = Todo.readData(todoJson.toString());
                // System.out.println(todo.getCreated_at());
                repository.create(Constant.KEY_TODO, todo.getId(), todo.deserialize());
            }

        } catch (FileNotFoundException e) {
            log.log(Level.SEVERE, "File %s not found.".formatted(f.getPath()));
        }
    }

    public List<Todo> getAll() throws ParseException {
        Map<Object, Object> map = repository.entries(Constant.KEY_TODO);
        List<Todo> todos = new ArrayList<>();
        for (Object o: map.values()) {
            JsonReader reader = Json.createReader(new StringReader(o.toString()));
            JsonObject obj = reader.readObject();
            todos.add(Todo.serialize(obj.toString()));
        }
        return todos;
    }

    public List<Todo> filterByStatus(String status) throws ParseException {
        Map<Object, Object> all = repository.entries(Constant.KEY_TODO);
        List<Todo> todos = new ArrayList<>();
        for (Object o: all.values()) {
            JsonReader reader = Json.createReader(new StringReader(o.toString()));
            JsonObject obj = reader.readObject();
            Todo todo = Todo.serialize(obj.toString());

            if (todo.getStatus().equals(status)) {
                todos.add(todo);
            }
        }
        return todos;
    }

    public boolean save(Todo entity) {
        try {
            repository.create(Constant.KEY_TODO, entity.getId(), entity.deserialize());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Todo getByID(String id) throws ParseException {
        String rawData = (String)repository.get(Constant.KEY_TODO, id);
        Todo todo = Todo.serialize(rawData);
        // System.out.println(todo.toString());
        return todo;
    }

    public void deleteById(String id) {
        repository.delete(Constant.KEY_TODO, id);
    }
}
