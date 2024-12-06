package sg.edu.nus.iss.ssf_19t.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.iss.ssf_19t.service.TodoService;




@Controller
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/all")
    public ModelAndView allTodos() throws ParseException {
        ModelAndView mav = new ModelAndView("listing");
        mav.addObject("todos", todoService.getAll());
        return mav;
    }
    
    @GetMapping("/filter/{status}")
    public ModelAndView filterStatus(@PathVariable("status") String status) throws ParseException {
        ModelAndView mav = new ModelAndView("listing");
        mav.addObject("todos", todoService.filterByStatus(status));

        return mav;
    }
    
}
