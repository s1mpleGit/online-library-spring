package by.library.onlinelibrary.dao;

import by.library.onlinelibrary.model.Book;
import by.library.onlinelibrary.model.Card;
import by.library.onlinelibrary.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardDao extends JpaRepository<Card, Long> {

    @Query("from Card c left join fetch c.user left join fetch c.book b left join fetch b.author")
    List<Card> getAllCards();
    @Modifying
    void deleteCardById(int id);
    @Query("delete from Card c where c.user = :user")
    @Modifying
    void deleteCardByUser(User user);
    @Query("delete from Card c where c.book = :book")
    @Modifying
    void deleteCardByBook(Book book);

    Card getCardByBookAndUser(Book book, User user);

}
