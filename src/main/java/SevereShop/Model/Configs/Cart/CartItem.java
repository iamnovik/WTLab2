package SevereShop.Model.Configs.Cart;

import SevereShop.Model.Configs.Product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem implements Serializable {
    private static final long serialVersionUID = 1L;
    private Product product;
    private int quantity;
}
