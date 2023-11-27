package SevereShop.Web.command.Implementation;

import SevereShop.Model.Configs.Cart.Cart;
import SevereShop.Model.Services.CartService;
import SevereShop.Model.Services.Implementation.HttpSessionCartService;
import SevereShop.Web.JspPageName;
import SevereShop.Web.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class MinicartCommand implements Command {
    private CartService cartService = HttpSessionCartService.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        Cart cart = cartService.getCart(request);
        request.setAttribute("cart", cart);
        return JspPageName.MINI_CART_JSP;
    }
}
