package SevereShop.Web;

import SevereShop.Web.command.CommandHandler;
import SevereShop.Web.command.CommandName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Controller extends HttpServlet {
    private CommandHandler commandHandler = CommandHandler.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("command");
        CommandName commandName = CommandName.valueOf(name);
        String page = commandHandler.getCommandByName(commandName).execute(request);
        request.setAttribute("command", name);
        if(commandName == CommandName.MINICART) {
            request.getRequestDispatcher(page).include(request, response);
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("command");
        CommandName commandName = CommandName.valueOf(name);
        String page = commandHandler.getCommandByName(commandName).execute(request);
        request.setAttribute("command", name);
        if(commandName == CommandName.MINICART) {
            request.getRequestDispatcher(page).include(request, response);
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
