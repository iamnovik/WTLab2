package SevereShop.Web.command.Implementation;

import SevereShop.Model.Configs.Order.Order;
import SevereShop.Model.Services.CartService;
import SevereShop.Model.Services.OrderService;
import SevereShop.Model.Services.Implementation.HttpSessionCartService;
import SevereShop.Model.Services.Implementation.JdbcOrderService;
import SevereShop.Web.command.Command;
import SevereShop.Web.command.CommandHandler;
import SevereShop.Web.command.CommandName;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PlaceOrderCommand implements Command {
    private OrderService orderService = JdbcOrderService.getInstance();
    private CartService cartService = HttpSessionCartService.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        String firstName = Optional.ofNullable(request.getParameter("firstName")).orElse("");
        String lastName = Optional.ofNullable(request.getParameter("lastName")).orElse("");
        String address = Optional.ofNullable(request.getParameter("deliveryAddress")).orElse("");
        String phone = Optional.ofNullable(request.getParameter("phone")).orElse("");
        Map<String, String> errors = validate(firstName, lastName, address, phone);
        Order order = orderService.getOrder(cartService.getCart(request));
        order.setPhone(phone);
        order.setFirstName(firstName);
        order.setLastName(lastName);
        order.setDeliveryAddress(address);
        if (errors.isEmpty()) {
            try {
                orderService.placeOrder(order);
                cartService.clearCart(request);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("secureId", order.getSecureId());
        } else {
            request.setAttribute("errors", errors);
        }
        return CommandHandler.getInstance().getCommandByName(CommandName.CHECKOUT).execute(request);
    }

    private Map<String, String> validate(String firstName, String lastName, String address, String phone) {
        Map<String, String> errors = new HashMap<>();
        if (firstName.isBlank()) {
            errors.put("firstName", "Name is required");
        }
        if (lastName.isBlank()) {
            errors.put("lastName", "Surname is required");
        }
        if (address.isBlank()) {
            errors.put("deliveryAddress", "Address is required");
        }
        if (phone.isBlank()) {
            errors.put("phone", "Phone is required");
        }
        if (!phone.matches("\\+375(33|29|25|44)\\d{7}")) {
            errors.put("phone", "Phone is invalid");
        }
        return errors;
    }
}
