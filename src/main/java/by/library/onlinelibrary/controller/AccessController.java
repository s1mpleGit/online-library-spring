package by.library.onlinelibrary.controller;

import by.library.onlinelibrary.model.User;
import by.library.onlinelibrary.service.AuthorService;
import by.library.onlinelibrary.service.BookService;
import by.library.onlinelibrary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class AccessController {

    private final UserService userService;
    private final BookService bookService;
    private final AuthorService authorService;

    @GetMapping
    public ModelAndView startPage(){
        return new ModelAndView("index");
    }

    @GetMapping("/index")
    public ModelAndView indexPage(){
        return new ModelAndView("index");
    }

    @PostMapping("/index")
    @Transactional
    public ModelAndView loginUser(@RequestParam String login, @RequestParam String password, HttpSession httpSession) {
        User user = userService.loginUser(login, password);
        if (user != null) {
            httpSession.setAttribute("user", user);
            ModelAndView mv = new ModelAndView("book");
            mv.addObject("books", bookService.getAllBooks());
            if (user.getRole().toString().equals("ADMIN")) {
                mv.addObject("authors", authorService.getAllAuthors());
                return mv;
            }
            return mv;
        }
        return new ModelAndView("redirect:/index?error=true");
    }

    @GetMapping("/registration")
    public ModelAndView registrationPage() {
        return new ModelAndView("registration");
    }

    @PostMapping("/registration")
    @Transactional
    public ModelAndView registrationUser(@ModelAttribute User user, HttpSession httpSession) {
        User newUser = userService.registrationUser(user.getLogin());
        if (newUser == null) {
            httpSession.setAttribute("user", userService.saveNewUser(user));
            ModelAndView mv = new ModelAndView("book");
            mv.addObject("books", bookService.getAllBooks());
            return mv;
        }
        return new ModelAndView("redirect:/registration?error=true");
    }

    @PostMapping("/logout")
    protected ModelAndView logout(HttpSession httpSession) {

        if (httpSession != null) {
            httpSession.invalidate();
        }
        return new ModelAndView("redirect:/index");
    }

}
