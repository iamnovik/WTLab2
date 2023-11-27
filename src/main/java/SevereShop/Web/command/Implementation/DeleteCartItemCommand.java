package SevereShop.Web.command.Implementation;

import SevereShop.Model.Configs.Cart.Cart;
import SevereShop.Model.Services.CartService;
import SevereShop.Model.Services.Implementation.HttpSessionCartService;
import SevereShop.Web.JspPageName;
import SevereShop.Web.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class DeleteCartItemCommand implements Command {
    private CartService cartService = HttpSessionCartService.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        Long tshirtId = Long.parseLong(request.getParameter("tshirtId"));
        Cart cart = cartService.getCart(request);
        cartService.delete(cart, tshirtId);
        request.setAttribute("deleted", true);
        return JspPageName.CART_JSP;
    }
}
