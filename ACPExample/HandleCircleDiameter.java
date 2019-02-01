/**
 *
 * @author Chris Brown
 */
package ACPExample;

public class HandleCircleDiameter implements Handler{

    public void HandleDiameter() {
    }

    @Override
    public void HandleRequest(ChoiceValues choice) {
        Double diameter = choice.radius * 2.0;

        System.out.printf("\nA circle with a radius of "
                + choice.radius
                + "\nhas a diameter of %.2f.", diameter
        );
        System.out.println();
    }

}