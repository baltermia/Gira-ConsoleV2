import java.io.IOException;

/**
 * Ersetzt gira.java, da sonst statisch.
 */
public class program {
    public boolean accountIsAdmin = false;
    public employee employeeAccount = null;
    public admin adminAccount = null;

    public admin rootAdmin = new admin("admin", "root");
    public employee mainEmployee = new employee("standardUser");
    public ticket startupTicket = new ticket("Gira", "Wilkommen bei Gira", "-", mainEmployee, mainEmployee);
    public logger logger = new logger(new logger("").getFilePath());
    /**
     * Ersetzt die Main Methode von gira, da sonsts statisch
     */
    public void run() {

    }
}
