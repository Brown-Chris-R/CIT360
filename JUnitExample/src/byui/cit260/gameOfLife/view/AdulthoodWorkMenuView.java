/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.gameOfLife.view;

/**
 *
 * @author cbrown
 */
public class AdulthoodWorkMenuView extends View {
    public AdulthoodWorkMenuView() {
                   super("\n"
                  + "\n----------------------------------------"
                  + "\n| Adulthood Work Menu                       |"
                  + "\n----------------------------------------"
                  + "\nB - Breakroom"
                  + "\nO - Office"
                  + "\nP - Parking Lot"
                  + "\nS - Supervisor's Office"
                  + "\nQ - Quit"
                  + "\n----------------------------------------");
    }
      


    @Override
    public boolean doAction(String choice) {
        choice = choice.toUpperCase(); // need upper case for comparison
        
        switch (choice) {
            case "B": // Adulthood Work Breakroom Scenario
                this.displayAdulthoodWorkBreakroomScenario();
                break;
            case "O": // Adulthood Work Office Scenario
                this.displayAdulthoodWorkOfficeScenario();
                break;
            case "P": // Adulthood Work Parking Lot Scenario
                this.displayAdulthoodWorkParkingLotScenario();
                break;
            case "S": // Adulthood Work Supervisor's Scenario
                this.displayAdulthoodWorkSupervisorsOfficeScenario();
                break;
            default:
                this.console.println("\n*** Invalid selection *** Try again");
                break;
        }
        return false;
    }

    private void displayAdulthoodWorkBreakroomScenario() {
        AdulthoodWorkBreakroomScenarioView workBreakroom = new AdulthoodWorkBreakroomScenarioView();
        workBreakroom.display();
    }

    private void displayAdulthoodWorkOfficeScenario() {
         AdulthoodWorkOfficeScenario workOffice = new AdulthoodWorkOfficeScenario();
         workOffice.display();
    }

    private void displayAdulthoodWorkParkingLotScenario() {
        AdulthoodWorkParkingLotScenario workParkingLot = new AdulthoodWorkParkingLotScenario();
        workParkingLot.display();
    }
     private void displayAdulthoodWorkSupervisorsOfficeScenario() {
         AdulthoodWorkSupervisorsOfficeScenario workSupervisorOffice = new AdulthoodWorkSupervisorsOfficeScenario();
         workSupervisorOffice.display();
     }
    
}
