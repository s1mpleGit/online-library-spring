package by.library.onlinelibrary.dao;

import by.library.onlinelibrary.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {

    Role getRoleById(int id);
}
