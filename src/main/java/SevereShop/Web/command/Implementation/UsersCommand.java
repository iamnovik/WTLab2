package SevereShop.Web.command.Implementation;

import SevereShop.Model.Dao.UserDao;
import SevereShop.Model.Dao.Implementation.JdbcUserDao;
import SevereShop.Model.Configs.User.User;
import SevereShop.Web.JspPageName;
import SevereShop.Web.command.Command;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.util.List;

public class UsersCommand implements Command {
    private UserDao userDao = JdbcUserDao.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        List<User> users;
        try {
            users = userDao.getUsers(((User)request.getSession().getAttribute("user")).getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("users", users);
        return JspPageName.USERS_JSP;
    }
}
