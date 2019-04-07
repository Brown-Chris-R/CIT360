package utiltracker.controller;

import utiltracker.model.DAOHelper;
import utiltracker.model.UserAddress;
import utiltracker.model.Users;
import utiltracker.model.UtilityBill;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;

public class AddUtilityHandler implements Handler {
    @Override
    public void doThis(HttpServletRequest request, HttpServletResponse response, HashMap<String, Object> dataMap) {
        int userId;
        int addressId;
        String stringUserId;
        String billingMonth = (String) dataMap.get("BillingMonth");
        String city = (String) dataMap.get("City");
        String state = (String) dataMap.get("State");
        String zipcode = (String) dataMap.get("Zipcode");
        String utilityType = (String) dataMap.get("UtilityType");
        String stringAmount = (String) dataMap.get("Amount");
        UtilityBill newUtilityBill;
        BigDecimal amount = BigDecimal.ZERO;
        try {
            amount = parse(stringAmount, Locale.US);
        } catch (ParseException e) {
            System.out.println("Error parsing amount: " + e.getMessage());
        }

        DAOHelper theModel = DAOHelper.getInstance();
        if (request.isRequestedSessionIdValid()) {
            HttpSession session = request.getSession(false);
            userId = (Integer) session.getAttribute("userid");
            Users foundUser = theModel.getUserById(userId);
            HashMap<String, Object> responseMap = new HashMap<>();
            if (foundUser != null) {
                UserAddress foundUserAddress = theModel.getUserAddress(userId, city, state, zipcode);
                if (foundUserAddress != null) {
                    addressId = foundUserAddress.getAddressId();
                } else {
                    foundUserAddress = new UserAddress(city, state, zipcode);
                    foundUserAddress.setUser(foundUser);
                    theModel.addUserAddress(foundUserAddress);
                    addressId = foundUserAddress.getAddressId();
                }
                newUtilityBill = new UtilityBill(billingMonth, utilityType, amount, userId, addressId);
                theModel.addUtilityBill(newUtilityBill);
                response.setHeader("Status", "Success");
            } else {
                response.setHeader("Status", "Error");
                response.setHeader("Error", "Error adding Utility record!");
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

    public static BigDecimal parse(final String amount, final Locale locale) throws ParseException {
        final NumberFormat format = NumberFormat.getNumberInstance(locale);
        if (format instanceof DecimalFormat) {
            ((DecimalFormat) format).setParseBigDecimal(true);
        }
        return (BigDecimal) format.parse(amount.replaceAll("[^\\d.,]",""));
    }

}
