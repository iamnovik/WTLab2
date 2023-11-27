package SevereShop.Model.Services;

import SevereShop.Model.Configs.Cart.Cart;

public interface StockService {
    int getAvailableQuantity(Cart cart, Long id);
}
