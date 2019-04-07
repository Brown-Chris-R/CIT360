package utiltracker.controller;

import utiltracker.model.DAOHelper;
import utiltracker.model.Users;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import utiltracker.model.Users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.UUID;

public class RegistrationHandler implements Handler {
    @Override
    public void doThis(HttpServletRequest request, HttpServletResponse response, HashMap<String, Object> dataMap) {
        // get username and password from dataMap input from user
        Users foundUser;
        String sessionID = "";
        String userName = "";
        String password = "";
        DAOHelper theModel = null;
        UUID sessionUUID = null;
        HashMap<String, Object> responseMap = new HashMap<>();
        try {
            userName = (String) dataMap.get("uname");
            password = (String) dataMap.get("pword");
            // get the model and check to see if user name and password are already in use
            theModel = (DAOHelper) dataMap.get("model");
            foundUser = theModel.getUser(userName, password);
            // create new response hashmap and sessionId string


        } catch (Exception e) {
            foundUser = null;
        }
        // if no user was found that means username and password are available to register
        if (foundUser == null) {
            // create new random session userid
            sessionUUID = UUID.randomUUID();
            // create new user and save information in it
            Users aUser = new Users();
            aUser.setUser(userName);
            aUser.setPassword(password);
            // add new user to the model
            theModel.addUser(aUser);
            // and put the new session Id in the response
            responseMap.put("id", sessionUUID.toString());
        }

        // if user was found, then put empty sessionId to let user know username not available for registration
        responseMap.put("id", sessionUUID);
        // create object mapper
        ObjectMapper mapper = new ObjectMapper();

        // turn responseMap to JsonString and put it in dataMap to send back to client.
        PrintWriter out = (PrintWriter) dataMap.get("toClient");
        try {
            mapper.writeValue(out, responseMap);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}