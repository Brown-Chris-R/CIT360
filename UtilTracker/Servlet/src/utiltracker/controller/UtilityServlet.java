package utiltracker.controller;

import org.hibernate.tool.schema.extract.spi.ExtractionContext;
import utiltracker.model.DAOHelper;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "UtilityServlet", urlPatterns = {"/UtilityServlet"})
public class UtilityServlet extends HttpServlet {
    private HashMap<String, Handler> handlerMap = new HashMap<>();

    private DAOHelper theModel = null;

    public void init() {
        System.out.print("Initializing");
        handlerMap.put("Register", new RegistrationHandler());
        handlerMap.put( "Login", new LoginHandler());
        handlerMap.put( "AddUtility", new AddUtilityHandler());
        handlerMap.put( "GetAddresses", new GetAddressesHandler());
        handlerMap.put("Logout", new LogoutHandler());
        // this starts a new factorySession and instantiates one copy of DAOHelper.
        theModel = DAOHelper.getInstance();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // get Command and DataObject from request
            String command = request.getParameter("Command");
            String data = request.getParameter("DataObject");
            // Use Jackson object mapper to deserialize request Json into HashMap
            ObjectMapper mapper = new ObjectMapper();
            HashMap<String, Object> dataMap = mapper.readValue(data, HashMap.class);

            //Process command using ACP pattern
            Handler aCommandHandler = handlerMap.get(command);
            if(aCommandHandler != null) {
                aCommandHandler.doThis(request, response, dataMap);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}