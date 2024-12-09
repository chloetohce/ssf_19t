package sg.edu.nus.iss.ssf_19t.controller;

import java.text.ParseException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.ssf_19t.model.Todo;
import sg.edu.nus.iss.ssf_19t.service.TodoService;





@Controller
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/all")
    public ModelAndView allTodos(HttpSession session) throws ParseException {
        Optional<String> usernameOpt = Optional.ofNullable((String)session.getAttribute("username"));

        ModelAndView mav = new ModelAndView("listing");
        mav.addObject("todos", todoService.getAll());

        ModelAndView refused = new ModelAndView("redirect:/refused.html");
        return usernameOpt.map(u -> mav).orElse(refused);
    }
    
    @GetMapping("/filter/{status}")
    public ModelAndView filterStatus(@PathVariable("status") String status) throws ParseException {
        ModelAndView mav = new ModelAndView("listing");
        mav.addObject("todos", todoService.filterByStatus(status));

        return mav;
    }

    @PostMapping("/add")
    public ModelAndView add(@ModelAttribute("todo") Todo entity) {
        ModelAndView mav = new ModelAndView();
        todoService.save(entity);
        // System.out.println(entity.toString());

        mav.setViewName("redirect:/todo/all");
        return mav;
    }

    @GetMapping("/add")
    public ModelAndView addForm() {
        ModelAndView mav = new ModelAndView("add");

        Todo newTodo = new Todo();
        // System.out.println("GET: " + newTodo.toString());
        mav.addObject("todo", newTodo);

        return mav;
    }

    @GetMapping("/edit")
    public ModelAndView editTodo(@RequestParam("id") String id) throws ParseException {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("edit");
        mav.addObject("id", id);
        mav.addObject("todo", todoService.getByID(id));
        return mav;
    }

    @PostMapping("/edit")
    public ModelAndView editTodo(@RequestParam("id") String id, @ModelAttribute("todo") Todo entity) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/todo/all");
        todoService.save(entity);
        System.out.println("Editing todo id: "+ id);
        return mav;
    }
    
    @GetMapping("/del")
    public ModelAndView deleteTodo(@RequestParam("id") String id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/todo/all");
        todoService.deleteById(id);
        return mav;
    }
    
}
