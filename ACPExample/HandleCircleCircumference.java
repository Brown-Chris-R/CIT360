/**
 *
 * @author Chris Brown
 */
package ACPExample;

public class HandleCircleCircumference implements Handler{

    public void HandleCircumference() {
    }

    @Override
    public void HandleRequest(ChoiceValues choice) {
        Double circ = choice.radius * 2 * Math.PI;
        
        System.out.printf("\nA circle with a radius of " 
                + choice.radius
                + "\nhas a cicumference of %.2f.", circ 
                );
        System.out.println();
    }
                
    }
    

