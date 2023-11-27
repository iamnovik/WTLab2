package SevereShop.Web.command.Implementation;

import SevereShop.Model.Services.CartService;
import SevereShop.Model.Services.Implementation.HttpSessionCartService;
import SevereShop.Web.JspPageName;
import SevereShop.Web.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class CartCommand implements Command {
    private CartService cartService = HttpSessionCartService.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("cart", cartService.getCart(request));
        return JspPageName.CART_JSP;
    }
}
