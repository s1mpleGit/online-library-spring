package by.library.onlinelibrary.dao;

import by.library.onlinelibrary.model.Author;
import by.library.onlinelibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookDao extends JpaRepository<Book, Long> {

    @Query("from Book b left join fetch b.author")
    List<Book> getAllBooks();
    Book getBookByTitle(String title);
    Book getBookById(int id);
    @Modifying
    @Query("delete from Book b where b.id = :id")
    void deleteBookById(int id);
//    @Modifying
//    @Query("update Book b set b.title = :title, b.description = :description, b.imageUri = :imageUri, b.author = :author where b.id = :id")
//    void updateBook(int id, String title, String description, String imageUri, Author author);
}
