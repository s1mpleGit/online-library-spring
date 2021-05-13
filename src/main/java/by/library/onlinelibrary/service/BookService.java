package by.library.onlinelibrary.service;

import by.library.onlinelibrary.dao.BookDao;
import by.library.onlinelibrary.dao.CardDao;
import by.library.onlinelibrary.model.Author;
import by.library.onlinelibrary.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookDao bookDao;
    private final CardDao cardDao;
    private final AuthorService authorService;

    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Transactional
    public Book createNewBook(Book book) {
        Book newBook = bookDao.getBookByTitle(book.getTitle());
        if (newBook == null) {
            return bookDao.saveAndFlush(book);
        } else return null;
    }

    @Transactional
    public void deleteBook(int bookId) {
        cardDao.deleteCardByBook(bookDao.getBookById(bookId));
        bookDao.deleteBookById(bookId);
    }

    public Book getBookById(int id) {
        return bookDao.getBookById(id);
    }

    @Transactional
    public void updateBook(int id, String title, String description, String imageUri, int authorId) {
        Author author = authorService.getAuthorById(authorId);
        bookDao.updateBook(id, title, description, imageUri, author);
    }
}
