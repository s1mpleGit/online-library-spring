package by.library.onlinelibrary.controller;

import by.library.onlinelibrary.model.Author;
import by.library.onlinelibrary.model.Book;
import by.library.onlinelibrary.model.User;
import by.library.onlinelibrary.service.AuthorService;
import by.library.onlinelibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    @GetMapping
    public ModelAndView bookPage() {
        return new ModelAndView("book");
    }

    @GetMapping("/all")
    public ModelAndView getAllBooks(@SessionAttribute(value = "user", required = false) User user) {
        ModelAndView mv = new ModelAndView("book");
        mv.addObject("books", bookService.getAllBooks());
            if (user != null && user.getRole().toString().equalsIgnoreCase("ADMIN")) {
                mv.addObject("authors", authorService.getAllAuthors());
                return mv;
        }
        return mv;
    }

    @PostMapping("/create")
    public ModelAndView createBook(@ModelAttribute Book book, @RequestParam int authorId) {
        Author author = authorService.getAuthorById(authorId);
        book.setAuthor(author);
        Book newBook = bookService.createNewBook(book);
        ModelAndView mv = new ModelAndView("book");
        mv.addObject("books", bookService.getAllBooks());
        mv.addObject("authors", authorService.getAllAuthors());
        if (newBook != null) {
            mv.addObject("bk", true);
        } else {
            mv.addObject("bk", false);
        }
        return mv;
    }

    @PostMapping("/delete")
    public ModelAndView deleteBook(@RequestParam int bookId) {
        bookService.deleteBook(bookId);
        ModelAndView mv = new ModelAndView("book");
        mv.addObject("books", bookService.getAllBooks());
        mv.addObject("authors", authorService.getAllAuthors());
        return mv;
    }

    @PostMapping("/createAuthor")
    public ModelAndView createAuthor(@RequestParam String author) {
        Author newAuthor = authorService.createAuthor(new Author(author));
        ModelAndView mv = new ModelAndView("admin");
        mv.addObject("authors", authorService.getAllAuthors());
        if (newAuthor != null) {
            mv.addObject("msg", true);
        } else {
            mv.addObject("msg", false);
        }
        return mv;
    }

    @PostMapping("/update")
    public ModelAndView updateBook(@ModelAttribute Book book, @RequestParam int authorId) {
        Book newBook = bookService.updateBook(book, authorId);
        ModelAndView mv = new ModelAndView("book");
        mv.addObject("books", bookService.getAllBooks());
        mv.addObject("authors", authorService.getAllAuthors());
        if (newBook != null) {
            mv.addObject("bk", true);
        } else {
            mv.addObject("bk", false);
        }
        return mv;
    }
}
