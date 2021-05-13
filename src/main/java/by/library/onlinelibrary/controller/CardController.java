package by.library.onlinelibrary.controller;

import by.library.onlinelibrary.model.Card;
import by.library.onlinelibrary.service.BookService;
import by.library.onlinelibrary.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;
    private final BookService bookService;

    @GetMapping("/all")
    public ModelAndView getAllCards() {
        ModelAndView mv = new ModelAndView("card");
        mv.addObject("cards", cardService.getAllCards());
        return mv;
    }

    @PostMapping("/all")
    public ModelAndView setAllCards() {
        return new ModelAndView("card");
    }

    @PostMapping("/create")
    public ModelAndView takeBook(@RequestParam int bookId, @RequestParam int userId) {
        Card card = cardService.getCardByBookIdAndUserId(bookId, userId);
        if (card != null) {
            List<Card> cards = cardService.getAllCards();
            ModelAndView mv = new ModelAndView("card");
            mv.addObject("cards", cards);
            return mv;
        } else {
            ModelAndView mv = new ModelAndView("book");
            mv.addObject("books", bookService.getAllBooks());
            mv.addObject("msg", true);
            return mv;
        }
    }

    @PostMapping("/return")
    @Transactional
    public ModelAndView returnBook(@RequestParam int userBookId) {
        ModelAndView mv = new ModelAndView("card");
        cardService.deleteCardById(userBookId);
        List<Card> cards = cardService.getAllCards();
        mv.addObject("cards", cards);
        return mv;
    }

}
