package utiltracker.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import utiltracker.model.DAOHelper;
import utiltracker.model.UserAddress;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GetAddressesHandler implements Handler {
    @Override
    public void doThis(HttpServletRequest request, HttpServletResponse response, HashMap<String, Object> dataMap) {
        int userId;
        List<String> userAddresses;

        DAOHelper theModel = DAOHelper.getInstance();
        if (request.isRequestedSessionIdValid()) {
            HttpSession session = request.getSession(false);
            userId = (Integer) session.getAttribute("userid");
            userAddresses = theModel.getUserAddresses(userId);
            if (userAddresses != null) {
                // String to hold serialized data
                String addressJSON = new String();
                ObjectMapper mapper = new ObjectMapper();

                try {
                    // Serialize user object to json string
                    addressJSON = mapper.writeValueAsString(userAddresses);
                    System.out.println("\nAddress json string:" + addressJSON);
                    dataMap.put("User Addresses", addressJSON);
                    response.setHeader("Status", "Success");
                    response.setHeader("User Addresses", addressJSON);
                }
                catch (JsonParseException e) {e.printStackTrace();}
                catch (JsonMappingException e) {e.printStackTrace();}
                catch (IOException e) {e.printStackTrace();}
            } else {
                response.setHeader("Status", "Error");
                response.setHeader("Error", "Error retrieving address records!");
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
}
