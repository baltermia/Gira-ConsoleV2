import java.util.ArrayList;
import java.util.List;

/**
 * Die employee Klasse beinhaltet die Employees
 */
public class Employee {
    public List<Employee> employeeList = new ArrayList<>();

    public int id;
    public String username;

    /**
     * Constructor
     * @param username
     */
    public Employee(String username) {
        id = employeeList.size();
        this.username = username;

        if (!username.equals("standardUser")) {
            GiraV2.girObj.proObj.mainEmployee.employeeList.add(this);
        }
    }

    /**
     * Man kann einen Nutzernamen oder die ID von einem Employee mitgeben und bekommt den employee in einem objekt zurück (falls er gefunden wurde).
     * @param id_username
     * @return
     */
    public Employee getEmployee(String id_username) {
        if (id_username.matches("[0-9]+")) {
            for (int i = 0; i < employeeList.size(); i++) {
                if (Integer.toString(employeeList.get(i).id).equals(id_username))
                    return employeeList.get(i);
            }
        } else {
            for (int i = 0; i < employeeList.size(); i++) {
                if (employeeList.get(i).username.equals(id_username)) return employeeList.get(i);
            }
        }
        return null;
    }
}