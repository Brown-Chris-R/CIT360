package SimpleMVC;

public class EmployeeController {
    private Employee model;
    private EmployeeView view;

    public EmployeeController(Employee model, EmployeeView view){
        this.model = model;
        this.view = view;
    }

    public void setEmployeeName(String name){
        model.setName(name);
    }

    public String getEmployeeName(){
        return model.getName();
    }

    public void setEmployeeRole(String role){
        model.setRole(role);
    }

    public String getEmployeeRole(){
        return model.getRole();
    }

    public void updateView(){
        view.printEmployeeDetails(model.getName(), model.getRole());
    }
}
