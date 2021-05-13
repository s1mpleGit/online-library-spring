package by.library.onlinelibrary.service;

import by.library.onlinelibrary.dao.BookDao;
import by.library.onlinelibrary.dao.CardDao;
import by.library.onlinelibrary.dao.UserDao;
import by.library.onlinelibrary.model.Book;
import by.library.onlinelibrary.model.Card;
import by.library.onlinelibrary.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardDao cardDao;
    private final BookDao bookDao;
    private final UserDao userDao;

    public List<Card> getAllCards() {
        return cardDao.getAllCards();
    }

    @Transactional
    public Card getCardByBookIdAndUserId(int bookId, int userId) {
        Book book = bookDao.getBookById(bookId);
        User user = userDao.getUserById(userId);
        Card card = cardDao.getCardByBookAndUser(book, user);
//        List<Card> cards = cardDao.getAllCards();
//        long check = cards.stream()
//                .filter(c -> c.getBook().getId() == bookId && c.getUser().getId() == userId)
//                .count();
        if (card == null) {
            Card newCard = Card.builder()
                    .book(book)
                    .user(user)
                    .return_date(LocalDate.now().plusWeeks(1))
                    .build();
            cardDao.saveAndFlush(newCard);
            return newCard;
        } else return null;
    }

    @Transactional
    public void deleteCardById(int id) {
        cardDao.deleteCardById(id);
    }
}
