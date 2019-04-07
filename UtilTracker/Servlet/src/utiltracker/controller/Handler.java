package utiltracker.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public interface Handler {
    public void doThis(HttpServletRequest request, HttpServletResponse response, HashMap<String, Object> data);
}