package SevereShop.Model.Services.Implementation;

import SevereShop.Model.Configs.Cart.Cart;
import SevereShop.Model.Dao.OrderDao;
import SevereShop.Model.Dao.Implementation.JdbcOrderDao;
import SevereShop.Model.Configs.Order.Order;
import SevereShop.Model.Services.OrderService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class JdbcOrderService implements OrderService {
    private OrderDao orderDao = JdbcOrderDao.getInstance();
    private static class SingletonHolder {
        private static final JdbcOrderService INSTANCE = new JdbcOrderService();
    }
    private JdbcOrderService() {

    }
    public static JdbcOrderService getInstance() {
        return SingletonHolder.INSTANCE;
    }
    @Override
    public Order getOrder(Cart cart) {
        Order order = new Order();
        order.setItems(new ArrayList<>(cart.getItems()));
        order.setTotalCost(cart.getTotalCost());
        return order;
    }

    @Override
    public void placeOrder(Order order) throws SQLException {
        order.setSecureId(UUID.randomUUID().toString());
        orderDao.save(order);
    }
}
