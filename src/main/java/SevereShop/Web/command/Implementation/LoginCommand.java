package SevereShop.Web.command.Implementation;

import SevereShop.Web.JspPageName;
import SevereShop.Web.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return JspPageName.LOGIN_JSP;
    }
}
