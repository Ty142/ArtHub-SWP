package controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping    (value ="login", method = RequestMethod.POST)
    public String Login(@RequestParam("username")String username, @RequestParam("password")String password, HttpSession session){
        ModelAndView model = new ModelAndView();
        if(userService.checkLogin(username, password)) {
            session.setAttribute("username", username);
            return "index";
        }
        else {
            model.addObject("error", "Tên đăng nhập hoặc mật khẩu không đúng");
            return "login";
        }
    }

    @RequestMapping("/home")
    public String homePage(HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }
        return "home";
    }

    @GetMapping (name ="logout")
    public String Logout(HttpSession session){
        session.removeAttribute("username");
        return "redirect:/login";
    }
}
