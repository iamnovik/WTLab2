package SevereShop.Model.Services;

import SevereShop.Model.Exceptions.OutOfStockException;
import SevereShop.Model.Configs.Cart.Cart;
import jakarta.servlet.http.HttpServletRequest;

public interface CartService {
    Cart getCart(HttpServletRequest request);
    void add(Cart cart, Long id, int quantity);
    void update(Cart cart, Long id, int quantity) throws OutOfStockException;
    void delete(Cart cart, Long id);
    void clearCart(HttpServletRequest request);
}
