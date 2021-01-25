import java.util.ArrayList;
import java.util.List;

/**
 * Die Admin Klasse beinhaltet die Admin Konten. Mit Ihr kann man zudem Konten erstellen/Löscen
 */
public class Admin {
    public List<Admin> adminList = new ArrayList<>();

    public int id;
    public String username;
    public String password;

    /**
     * Constructor
     * @param username
     * @param password
     */
    public Admin(String username, String password) {
        id = adminList.size();
        this.username = username;
        this.password = password;

        if (!username.equals("admin")) {
            GiraV2.proObj.rootAdmin.adminList.add(this);
        } else {
            this.adminList.add(this);
        }
    }

    /**
     * Erstellt einen neuen Emloyee account.
     * @param name
     * @return
     */
    public Employee createEmployeeAccount(String name) {

        return new Employee(getNewUsername(name, false));
    }

    /**
     * Erstellt einen neuen Admin account.
     * @param name
     * @param password
     * @return
     */
    public Admin createAdminAccount(String name, String password) {
        return new Admin(getNewUsername(name, true), password);
    }

    /**
     * Löscht den mitgegebenen Employee Account aus der Liste.
     * @param acc
     * @return
     */
    public boolean deleteEmployeeAccount(Employee acc) {
        for (int i = 0; i < GiraV2.proObj.employeeAccount.employeeList.size(); i++) {
            if (GiraV2.proObj.employeeAccount.employeeList.get(i).equals(acc))
            {
                GiraV2.proObj.employeeAccount.employeeList.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Löscht den mitgegebenen Admin Account aus der Liste.
     * @param acc
     * @return
     */
    public boolean deleteAdminAccount(Admin acc) {
        for (int i = 0; i < GiraV2.proObj.employeeAccount.employeeList.size(); i++) {
            if (GiraV2.proObj.adminAccount.adminList.get(i).equals(acc))
            {
                GiraV2.proObj.adminAccount.adminList.remove(i);
                return true;
            }
        }
        return false;    }

    /**
     * Gibt einen freien Benutzernamen zurück.
     * @param name
     * @param isAdmin
     * @return
     */
    private String getNewUsername(String name, boolean isAdmin) {
        String username = (name.substring(0, 3) + name.split(" ")[1].substring(0, 3)).toLowerCase();

        while (true) {
            if ((isAdmin == false ? GiraV2.proObj.mainEmployee.getEmployee(username) : getAdmin(username)) != null) {
                if (username.length() > 6) {
                    int strLenth = username.length();
                    while (strLenth > 0 && Character.isDigit(username.charAt(strLenth - 1))) {
                        strLenth--;
                    }
                    int endNumber = Integer.parseInt(username.substring(strLenth));

                    username = username.substring(0, username.length() - String.valueOf(endNumber).length()) + (endNumber + 1);
                } else {
                    username += "1";
                }
            } else {
                break;
            }
        }

        return username;
    }

    /**
     * Man kann einen Nutzernamen oder die ID von einem Admin mitgeben und bekommt den admin in einem objekt zurück (falls er gefunden wurde).
     * @param id_username
     * @return
     */
    public Admin getAdmin(String id_username) {
        if (id_username.matches("[0-9]+")) {
            for (int i = 0; i < adminList.size(); i++) {
                if (Integer.toString(adminList.get(i).id).equals(id_username))
                    return adminList.get(i);
            }
        } else {
            for (int i = 0; i < adminList.size(); i++) {
                if (adminList.get(i).username.equals(id_username)) return adminList.get(i);
            }
        }
        return null;
    }
}
