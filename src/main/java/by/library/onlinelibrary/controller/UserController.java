package by.library.onlinelibrary.controller;

import by.library.onlinelibrary.model.User;
import by.library.onlinelibrary.service.AuthorService;
import by.library.onlinelibrary.service.BookService;
import by.library.onlinelibrary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final BookService bookService;
    private final AuthorService authorService;

    @GetMapping
    public ModelAndView userMainPage() {
        return new ModelAndView("user");
    }

    @GetMapping("/info")
    public ModelAndView userPage() {
        return new ModelAndView("user");
    }

    @GetMapping("/admin")
    public ModelAndView adminPage() {
        return new ModelAndView("admin");
    }

    @PostMapping("/admin")
    public ModelAndView updateBook(@RequestParam int bookId) {
        return new ModelAndView("admin").addObject("book", bookService.getBookById(bookId)).addObject("authors", authorService.getAllAuthors());
    }

    @GetMapping("/block")
    public ModelAndView blockPage() {
        ModelAndView mv = new ModelAndView("block");
        mv.addObject("users", userService.getAllUsers());
        return mv;
    }

    @PostMapping("/block")
    public ModelAndView blockPage(@RequestParam int userId) {
        ModelAndView mv = new ModelAndView("block");
        userService.blockUser(userId);
        mv.addObject("users", userService.getAllUsers());
        return mv;
    }

    @PostMapping("/update")
    public ModelAndView updateUser(@ModelAttribute("user") User user, HttpSession httpSession) {
        ModelAndView mv = new ModelAndView("user");
        User newUser = userService.updateUser(user);
        httpSession.setAttribute("user", newUser);
        mv.addObject("user", newUser);
        mv.addObject("msg", true);
        return mv;
    }

    @PostMapping("/unblock")
    public ModelAndView unblockUser(@RequestParam int userId) {
        userService.unblockUser(userId);
        return new ModelAndView("block").addObject("users", userService.getAllUsers());
    }

    @PostMapping("/delete")
    public ModelAndView deleteUser(@RequestParam int userId) {
        userService.deleteUser(userId);
        return new ModelAndView("block").addObject("users", userService.getAllUsers());
    }

    @PostMapping("/message")
    public ModelAndView sendMessage(@RequestParam int userId, @RequestParam String message) {
        Integer check = userService.sendMessage(userId, message);
        ModelAndView mv = new ModelAndView("block");
        if (check != 0) {
            return mv.addObject("msg", true);
        } else return mv.addObject("msg", false);
    }

}
