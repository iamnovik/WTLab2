package SevereShop.Web.command.Implementation;

import SevereShop.Model.Configs.Cart.Cart;
import SevereShop.Model.Dto.ErrorMessageAddToCartDto;
import SevereShop.Model.Services.CartService;
import SevereShop.Model.Services.StockService;
import SevereShop.Model.Services.Implementation.HttpSessionCartService;
import SevereShop.Model.Services.Implementation.JdbcStockService;
import SevereShop.Web.command.Command;
import SevereShop.Web.command.CommandHandler;
import SevereShop.Web.command.CommandName;
import jakarta.servlet.http.HttpServletRequest;

public class AddToCartCommand implements Command {
    private CartService cartService = HttpSessionCartService.getInstance();
    private StockService stockService = JdbcStockService.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        Cart cart = cartService.getCart(request);
        ErrorMessageAddToCartDto dto = new ErrorMessageAddToCartDto();
        String strQuantity = request.getParameter("quantity");
        Long id = Long.parseLong(request.getParameter("productId"));
        dto.setTshirtId(id);
        if (handleErrors(cart, strQuantity, id, dto)) {
            request.setAttribute("error", dto);
        } else {
            Long quantity = Long.parseLong(strQuantity);
            cartService.add(cart, id, quantity.intValue());
            request.setAttribute("message", "Tshirt was successfully added to cart");
        }
        String prevPage = request.getParameter("prevPage");
        return CommandHandler.getInstance().getCommandByName(CommandName.valueOf(prevPage)).execute(request);
    }

    private boolean handleErrors(Cart cart, String strQuantity, Long id, ErrorMessageAddToCartDto dto) {
        try {
            Long quantity = Long.parseLong(strQuantity);
            if (quantity < 1) {
                dto.setMessage("Quantity must be more than 0");
                return true;
            }
            int availableStock = stockService.getAvailableQuantity(cart, id);
            if (quantity > availableStock) {
                dto.setMessage(String.format("Out of stock, max %d", availableStock));
                return true;
            }
        } catch (NumberFormatException e) {
            dto.setMessage("Quantity must be number");
            return true;
        }
        return false;
    }
}
