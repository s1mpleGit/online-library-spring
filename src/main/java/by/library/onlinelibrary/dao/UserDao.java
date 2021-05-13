package by.library.onlinelibrary.dao;

import by.library.onlinelibrary.model.Status;
import by.library.onlinelibrary.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {

    @Query("from User u left join fetch u.role left join fetch u.status where u.login like :login and u.password like :password")
    User getUserByLoginAndPassword(String login, String password);
    @Query("from User u left join fetch u.role left join fetch u.status where u.login like :login")
    User getUserByLogin(String login);
    @Query("from User u left join fetch u.role left join fetch u.status")
    List<User> getAllUsers();
    @Query("from User u left join fetch u.role left join fetch u.status where u.id = :id")
    User getUserById(int id);
    @Query("update User u set u.password=:password, u.name=:name, u.surname=:surname, u.email=:email, u.phone=:phone where u.id=:id")
    @Modifying
    void updateUser(int id, String password, String name, String surname, String email, String phone);
    @Query("update User u set u.status = :status where u.id = :id")
    @Modifying
    void unblockUser(int id, Status status);
    @Query("delete from User where id = :id")
    @Modifying
    void deleteUser(int id);
    @Query("update User u set u.status = :status where u.id = :id")
    @Modifying
    void blockUser(int id, Status status);
    @Query("update User u set u.message = :message where u.id = :id")
    @Modifying
    Integer sendMessage(int id, String message);
}
