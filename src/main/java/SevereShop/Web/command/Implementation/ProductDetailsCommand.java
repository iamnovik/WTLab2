package SevereShop.Web.command.Implementation;

import SevereShop.Model.Configs.Product.Product;
import SevereShop.Model.Dao.ProductDao;
import SevereShop.Model.Dao.Implementation.JdbcProductDao;
import SevereShop.Web.JspPageName;
import SevereShop.Web.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class ProductDetailsCommand implements Command {
    private ProductDao productDao = JdbcProductDao.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        Long id = Long.valueOf(request.getParameter("id"));
        Product product = productDao.getProduct(id);
        request.setAttribute("tshirt", product);
        return JspPageName.PRODUCT_DETAILS_JSP;
    }
}
