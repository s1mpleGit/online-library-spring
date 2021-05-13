package by.library.onlinelibrary.dao;

import by.library.onlinelibrary.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorDao extends JpaRepository<Author, Long> {

    Author getAuthorById(int id);
}
