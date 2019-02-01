/**
 *
 * @author Chris Brown
 */
package ACPExample;

import java.util.HashMap;
import java.util.Scanner;

public class CircleCalculator {
    private static AppController theAppController = new AppController();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // We will use a ChoiceValue POJO to pass choice values as needed
        ChoiceValues choice = new ChoiceValues("",0);

        // Initialize handler hashmap
        theAppController.addHandler("C", new HandleCircleCircumference());
        theAppController.addHandler("D", new HandleCircleDiameter());
        theAppController.addHandler("A", new HandleCircleArea());
        theAppController.addHandler("X", new HandleExit());

        // Get User input and process it, keep doing that until they choose to exit.
        while(!choice.choice.equals("X")) {
            choice = GetInput.GetInput();
            theAppController.HandleRequest(choice);
        }
    }

   
        

    
}
