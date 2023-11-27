package SevereShop.Model.Dao.Implementation;

import SevereShop.Model.Configs.Cart.CartItem;
import SevereShop.Model.Configs.Order.Order;
import SevereShop.Model.ConnectionPool.ConnectionPool;
import SevereShop.Model.Mappers.OrderItemsRowMapper;
import SevereShop.Model.Mappers.OrderRowMapper;
import SevereShop.Model.Dao.OrderDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcOrderDao implements OrderDao {
    private static class SingletonHandler {
        private static final JdbcOrderDao INSTANCE = new JdbcOrderDao();
    }
    public static JdbcOrderDao getInstance() {
        return SingletonHandler.INSTANCE;
    }
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private JdbcOrderDao() {

    }
    @Override
    public Order getOrder(Long id) throws SQLException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from orders where id=?");
        preparedStatement.setLong(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        Order order = new OrderRowMapper().mapRows(rs);
        preparedStatement = connection.prepareStatement("select * from orderitems where orderId=?");
        preparedStatement.setLong(1, id);
        rs = preparedStatement.executeQuery();
        order.setItems(new OrderItemsRowMapper().mapRows(rs));
        connectionPool.releaseConnection(connection);
        return order;
    }

    @Override
    public Order getOrderBySecureId(String secureId) throws SQLException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from orders where secureId=?");
        preparedStatement.setString(1, secureId);
        ResultSet rs = preparedStatement.executeQuery();
        Order order;
        if(rs.next()) {
            order = new OrderRowMapper().mapRows(rs);
        } else {
            throw new SQLException();
        }
        preparedStatement = connection.prepareStatement("select * from orderitems where orderId=?");
        preparedStatement.setLong(1, order.getId());
        rs = preparedStatement.executeQuery();
        order.setItems(new OrderItemsRowMapper().mapRows(rs));
        connectionPool.releaseConnection(connection);
        return order;
    }

    @Override
    public void save(Order order) throws SQLException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into orders (secureId, totalCost," +
                "firstName, lastName, phone, deliveryAddress) values (?,?,?,?,?,?)");
        preparedStatement.setString(1, order.getSecureId());
        preparedStatement.setInt(2, order.getTotalCost().intValue());
        preparedStatement.setString(3, order.getFirstName());
        preparedStatement.setString(4, order.getLastName());
        preparedStatement.setString(5, order.getPhone());
        preparedStatement.setString(6, order.getDeliveryAddress());
        preparedStatement.execute();
        Order created = getOrderBySecureId(order.getSecureId());
        for(CartItem cartItem : order.getItems()) {
            preparedStatement = connection.prepareStatement("insert into orderitems (tshirtId, orderId, quantity)" +
                    " values(?,?,?)");
            preparedStatement.setInt(1, cartItem.getProduct().getId().intValue());
            preparedStatement.setInt(2, created.getId().intValue());
            preparedStatement.setInt(3, cartItem.getQuantity());
            preparedStatement.execute();
        }
        connectionPool.releaseConnection(connection);
    }
}
