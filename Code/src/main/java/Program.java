/**
 * Ersetzt Gira.java, da sonst statisch.
 */
public class Program {
    public boolean accountIsAdmin = false;
    public Employee employeeAccount = null;
    public Admin adminAccount = null;

    public Admin rootAdmin = new Admin("admin", "root");
    public Employee mainEmployee = new Employee("standardUser");
    public Ticket startupTicket = new Ticket("Gira", "Wilkommen bei Gira", "-", mainEmployee, mainEmployee);
    public Logger logger = new Logger(new Logger("").getFilePath());

    /**
     * Ersetzt die Main Methode von gira, da sonsts statisch
     */
    public void run() {

    }
}