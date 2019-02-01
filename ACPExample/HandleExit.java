/**
 *
 * @author Chris Brown
 */
package ACPExample;

import java.util.HashMap;

public class HandleExit implements Handler {

    public HandleExit() {
    }

    @Override
    public void HandleRequest(ChoiceValues choice) {
        System.out.println("Goodbye");
    }
    
}
