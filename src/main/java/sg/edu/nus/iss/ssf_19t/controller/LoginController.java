package sg.edu.nus.iss.ssf_19t.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("")
public class LoginController {
    
    @GetMapping(path={"/", ""})
    public ModelAndView loginPage() {
        return new ModelAndView("login.html");
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam("username") String username, @RequestParam("age") int age, HttpSession session) {
        session.setAttribute("username", username);
        session.setAttribute("age", age);

        return new ModelAndView("redirect:/todo/all");
    }

    @GetMapping("/invalidate")
    public ModelAndView invalidate(HttpSession session) {
        session.invalidate();
        return new ModelAndView("redirect:/");
    }
    
}
