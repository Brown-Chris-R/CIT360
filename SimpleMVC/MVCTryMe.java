package SimpleMVC;

public class MVCTryMe {
    public static void main(String[] args) {

        //fetch employee record based on his roll no from the database
        Employee model  = retrieveEmployeeFromDatabase();

        //Create a view : to write employee details on console
        EmployeeView view = new EmployeeView();

        EmployeeController controller = new EmployeeController(model, view);

        controller.updateView();

        //update model data
        controller.setEmployeeName("Adam");

        controller.updateView();
    }

    private static Employee retrieveEmployeeFromDatabase(){
        Employee employee = new Employee();
        employee.setName("Holly");
        employee.setRole("User Support");
        return employee;
    }
}