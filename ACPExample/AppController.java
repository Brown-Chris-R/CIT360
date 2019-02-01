/**
 *
 * @author Chris Brown
 */
package ACPExample;

import java.util.HashMap;

public class AppController {
    private static HashMap<String,Handler> handlerMap = new HashMap();
    
    public static void HandleRequest(ChoiceValues choice) {
        Handler handler = handlerMap.get(choice.choice);
        if (handler != null) {
            handler.HandleRequest(choice);
        }
    }

    public void addHandler(String aRequest, Handler aHandler) {
        handlerMap.put(aRequest, aHandler);
    }
}
