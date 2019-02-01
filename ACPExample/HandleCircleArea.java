/**
 *
 * @author Chris Brown
 */
package ACPExample;

public class HandleCircleArea implements Handler{

    public void HandleArea() {
    }

    @Override
    public void HandleRequest(ChoiceValues choice) {
        Double area = Math.PI * choice.radius * choice.radius;

        System.out.printf("\nA circle with a radius of "
                + choice.radius
                + "\nhas an area of %.2f.", area
        );
        System.out.println();
    }

}