package utiltracker.controller;

import utiltracker.model.DAOHelper;
import utiltracker.model.Users;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class LoginHandler implements Handler {
    @Override
    public void doThis(HttpServletRequest request, HttpServletResponse response, HashMap<String, Object> dataMap) {
        String user = (String) dataMap.get("User");
        String password = (String) dataMap.get("Password");
        DAOHelper theModel = DAOHelper.getInstance();

        Users foundUser = theModel.getUser(user, password);
        HashMap<String, Object> responseMap = new HashMap<>();
//        String sessionId = "";
        if (foundUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userid", foundUser.getUserId());
            session.setAttribute("user", user);
            session.setAttribute("userName", foundUser.getName());
            //setting session to expiry in 30 mins
            session.setMaxInactiveInterval(30*60);
            Cookie userName = new Cookie(Integer.toString(foundUser.getUserId()), session.getId());
            userName.setMaxAge(30*60);
            response.addCookie(userName);
            response.setHeader("Status", "Success");
            response.setHeader("Session", session.getId());
            response.setHeader("UserId", Integer.toString(foundUser.getUserId()));
        } else {
            response.setHeader("Status", "Error");
            response.setHeader("Error", "User and Password not found!");
        }
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
