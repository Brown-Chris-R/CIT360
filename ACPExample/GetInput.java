/**
 *
 * @author Chris Brown
 */
package ACPExample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

public class GetInput {

    public static ChoiceValues GetInput() {
        BufferedReader systemIn = new BufferedReader(new InputStreamReader(System.in));
        String thisChoice = "";
        int thisRadius = 0;

        while(!thisChoice.equals("X") && !thisChoice.equals("A") && !thisChoice.equals("C") && !thisChoice.equals("D")) {
            System.out.println("\nChoose a circle calculation"
                                    +"\nA - Area"
                                    +"\nC - Circumference"
                                    +"\nD - Diameter"
                                    +"\nX - Exit");
            try {
                thisChoice = systemIn.readLine();
                thisChoice = thisChoice.trim().toUpperCase();
                if (!thisChoice.equals("A") && !thisChoice.equals("C") && !thisChoice.equals("D") && !thisChoice.equals("X")) {
                    System.out.println("Invalid choice. Please enter A, C, D or X");
                } else {
                    while (!thisChoice.equals("X") && thisRadius <= 0) {
                        System.out.println("Enter a radius");
                        try {
                            thisRadius = Integer.parseInt(systemIn.readLine());
                            if (thisRadius <= 0) {
                                System.out.println("Invalid choiceError. please enter a positive whole number.");
                            }
                        } catch (Exception e) {
                            System.out.println("Error. Please enter positive whole number.");
                        }
                    }

                }
            } catch (Exception e) {
                System.out.println("Error. Please enter A, C, D or X");
            }
        }
        ChoiceValues choice = new ChoiceValues(thisChoice, thisRadius);
        return choice;
    }
    
}
