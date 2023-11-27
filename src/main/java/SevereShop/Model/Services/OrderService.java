package SevereShop.Model.Services;

import SevereShop.Model.Configs.Cart.Cart;
import SevereShop.Model.Configs.Order.Order;

import java.sql.SQLException;

public interface OrderService {
    Order getOrder(Cart cart);
    void placeOrder(Order order) throws SQLException;
}
