package SevereShop.Model.Dao;

import SevereShop.Model.Configs.User.User;
import SevereShop.Model.Configs.User.UserRole;
import SevereShop.Model.Configs.User.UserStatus;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> getUser(String login, String password) throws SQLException;
    void saveUser(String login, String password) throws SQLException;
    List<User> getUsers(Long id) throws SQLException;
    void updateUser(Long id, Double discount, UserRole role, UserStatus status) throws SQLException;
}
