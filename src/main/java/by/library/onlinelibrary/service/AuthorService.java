package by.library.onlinelibrary.service;

import by.library.onlinelibrary.dao.AuthorDao;
import by.library.onlinelibrary.model.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorDao authorDao;

    public List<Author> getAllAuthors() {
        return authorDao.findAll();
    }

    @Transactional
    public Author createAuthor(Author author) {
        List<Author> authors = authorDao.findAll();
        long check = authors.stream()
                .filter(a -> a.getName().equalsIgnoreCase(author.getName()))
                .count();
        if (check == 0) {
            return authorDao.saveAndFlush(author);
        }
        else return null;
    }

    public Author getAuthorById(int id) {
        return authorDao.getAuthorById(id);
    }
}
