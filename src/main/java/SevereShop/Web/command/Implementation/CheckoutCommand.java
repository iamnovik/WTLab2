package SevereShop.Web.command.Implementation;

import SevereShop.Model.Configs.Order.Order;
import SevereShop.Model.Services.CartService;
import SevereShop.Model.Services.OrderService;
import SevereShop.Model.Services.Implementation.HttpSessionCartService;
import SevereShop.Model.Services.Implementation.JdbcOrderService;
import SevereShop.Web.JspPageName;
import SevereShop.Web.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class CheckoutCommand implements Command {
    private CartService cartService = HttpSessionCartService.getInstance();
    private OrderService orderService = JdbcOrderService.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        Order order = orderService.getOrder(cartService.getCart(request));
        request.setAttribute("order", order);
        return JspPageName.CHECKOUT_JSP;
    }
}
