package SevereShop.Model.Dao;

import SevereShop.Model.Configs.Order.Order;

import java.sql.SQLException;

public interface OrderDao {
    Order getOrder(Long id) throws SQLException;
    Order getOrderBySecureId(String secureId) throws SQLException;
    void save(Order order) throws SQLException;
}
