package by.library.onlinelibrary.service;

import by.library.onlinelibrary.dao.CardDao;
import by.library.onlinelibrary.dao.RoleDao;
import by.library.onlinelibrary.dao.StatusDao;
import by.library.onlinelibrary.dao.UserDao;
import by.library.onlinelibrary.model.Status;
import by.library.onlinelibrary.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final StatusDao statusDao;
    private final CardDao cardDao;

    public User loginUser(String login, String password) {
        return userDao.getUserByLoginAndPassword(login, password);
    }

    public User registrationUser(String login) {
        return userDao.getUserByLogin(login);
    }

    @Transactional
    public User saveNewUser(User user) {
        user.setRole(roleDao.getRoleById(1));
        user.setStatus(statusDao.getStatusById(1));
        return userDao.saveAndFlush(user);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    public User updateUser(User user) {
        userDao.updateUser(user.getId(), user.getPassword(), user.getName(), user.getSurname(), user.getEmail(), user.getPhone());
        return userDao.getUserById(user.getId());
    }

    @Transactional
    public void unblockUser(int id) {
        Status status = statusDao.getStatusById(1);
        userDao.unblockUser(id, status);
    }

    @Transactional
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    @Transactional
    public void blockUser(int id) {
        Status status = statusDao.getStatusById(2);
        userDao.blockUser(id, status);
        cardDao.deleteCardByUser(userDao.getUserById(id));
    }

    @Transactional
    public Integer sendMessage(int userId, String message) {
        return userDao.sendMessage(userId, message);
    }
}
