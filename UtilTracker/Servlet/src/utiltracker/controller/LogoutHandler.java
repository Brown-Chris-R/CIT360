package utiltracker.controller;

import utiltracker.model.DAOHelper;
import utiltracker.model.Users;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class LogoutHandler implements Handler {

    @Override
    public void doThis(HttpServletRequest request, HttpServletResponse response, HashMap<String, Object> dataMap) {
        String userSession = (String) dataMap.get("SessionId");

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.setHeader("Status", "Success");
//        response.setHeader("Status", "Error");
//        response.setHeader("Error", "Something went wrong destroying sessionId "+userSession);
        try {
            ServletOutputStream out = response.getOutputStream();
            // Set Parameter values
            out.print("");
            out.flush();
            out.close();
        } catch (Exception e) {
            System.out.println("Error sending response is " + e.getMessage());
        }

    }
}