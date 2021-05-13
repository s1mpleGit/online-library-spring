package by.library.onlinelibrary.dao;

import by.library.onlinelibrary.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusDao extends JpaRepository<Status, Long> {

    Status getStatusById(int id);
}
