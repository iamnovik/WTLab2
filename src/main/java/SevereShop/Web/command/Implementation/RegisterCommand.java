package SevereShop.Web.command.Implementation;

import SevereShop.Web.JspPageName;
import SevereShop.Web.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class RegisterCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return JspPageName.REGISTER_JSP;
    }
}
