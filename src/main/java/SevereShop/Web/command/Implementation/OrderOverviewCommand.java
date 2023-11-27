package SevereShop.Web.command.Implementation;

import SevereShop.Model.Dao.OrderDao;
import SevereShop.Model.Dao.Implementation.JdbcOrderDao;
import SevereShop.Model.Configs.Order.Order;
import SevereShop.Web.JspPageName;
import SevereShop.Web.command.Command;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;

public class OrderOverviewCommand implements Command {
    private OrderDao orderDao = JdbcOrderDao.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String secureId = request.getParameter("secureId");
        Order order;
        try {
            order = orderDao.getOrderBySecureId(secureId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("order", order);
        return JspPageName.ORDER_OVERVIEW_JSP;
    }
}
