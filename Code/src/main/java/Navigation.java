import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * In der Navigation Klasse wird der Hauptteil vom Input/Output getätigt.
 */
public class Navigation {
    public BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Man kann eine End-Nummer mitgeben und die Method fragt den Nutzer welche Zahl er im gewünschten Raum wählen will.
     * @param end
     * @return
     * @throws IOException
     */
    public int inputIndex(int end) throws IOException {
        boolean hasPassed = false;
        String input;
        int inputNum;

        while (true) {
            if (hasPassed)
                System.out.println("Bitte geben Sie eine Zahl von 1-" + end +" ein.");
            else
                hasPassed = true;

            input = reader.readLine();

            if (input.matches("[0-9]+")) {
                inputNum = Integer.parseInt(input);
            } else {
                continue;
            }

            for (int i = 0; i <= end; i++) {
                if (inputNum == i)
                    return inputNum;
            }
        }
    }

    /**
     * Methode meldet Nutzer als Employee an
     * @return
     * @throws IOException
     */
    public Employee loginAsEmployee() throws IOException {
        System.out.println("Sie wollen sich als normaler Benutzer anmelden. Bitte geben Sie Ihren Benutzernamen oder Ihre Benutzer-ID ein:");
        while (true) {
            String input = reader.readLine();

            Employee account = GiraV2.proObj.mainEmployee.getEmployee(input);

            if (account == null) {
                System.out.println("Ihr Account wurde nicht gefunden. Probieren Sie es nochmals: ");
                continue;
            } else {
                System.out.println("Ihr Account wurde gefunden. Sie sind nun angemeldet.");
                return account;
            }
        }
    }

    /**
     *  Methode meldet Nutzer als Admin an
     * @return
     * @throws IOException
     */
    public Admin loginAsAdmin() throws IOException {
        System.out.println("Sie wollen sich als Admin anmelden. Bitte geben Sie Ihren Benutzernamen oder Ihre Benutzer-ID ein:");
        while (true) {
            String input = reader.readLine();

            Admin account = GiraV2.proObj.rootAdmin.getAdmin(input);

            if (account == null) {
                System.out.println("Ihr Account wurde nicht gefunden. Probieren Sie es nochmals: ");
                continue;
            }

            System.out.println("Ihr Account wurde gefunden. Bitte geben Sie das Passwort ein:");
            while (true) {
                String password = reader.readLine();

                if (account.password.equals(password)) {
                    System.out.println("Das eingegebene Passwort ist korrekt. Sie sind nun angemeldet.");
                    return account;
                } else {
                    System.out.println("Das eingegebene Passwort ist falsch. Versuchen Sie es nochmals:");
                    continue;
                }
            }
        }
    }

    /**
     * Methode erstellt Account (mit der variable isAdmin kann man zwischen Employee und Admin account entscheiden)
     * @param isAdmin
     * @throws IOException
     */
    public void createAccount(boolean isAdmin) throws IOException {
        System.out.println("Bitte geben Sie Vor- und Nachnamen ein (keine zweiten Vornamen): ");

        String name;
        String password;

        while (true) {
            name = reader.readLine();

            if (!name.equals("")) {
                if (name.contains(" ")) {
                    if (name.charAt(name.length() - 1) != ' ') {
                        if (name.split(" ")[0].length() < 3 || name.split(" ")[1].length() < 3 || name.split(" ")[1] == null) {
                            System.out.println("Ihr Vor- und Nachname müssen mindestens 3 Zeichen lange sein." +
                                    "\nVersuchen Sie es nochmal: ");
                            continue;
                        }
                    } else {
                        System.out.println("Das letzte Zeichen darf kein Leerzeichen sein." +
                                "\nVersuchen Sie es nochmal: ");
                        continue;
                    }
                } else {
                    System.out.println("Ihre Antwort muss mindestens 1 Leerzeichen enthalten." +
                            "\nVersuchen Sie es nochmal: ");
                    continue;
                }
            } else {
                System.out.println("Ihre Antwort darf nicht leer sein." +
                        "\nVersuchen Sie es nochmal: ");
                continue;
            }
            break;
        }

        if (isAdmin) {
            System.out.println("Bitte geben Sie ein Passwort ein: ");

            password = reader.readLine();

            Admin account = GiraV2.proObj.rootAdmin.createAdminAccount(name, password);

            System.out.println("Account wurde erfolgreich erstellt." +
                    "\nIhre Nutzername lautet: " + account.username +
                    "\nIhre Account-ID ist: " + account.id +
                    "\n-------------------------------");
        }
        else {
            Employee account = GiraV2.proObj.rootAdmin.createEmployeeAccount(name);

            System.out.println("Account wurde erfolgreich erstellt." +
                    "\nIhre Nutzername lautet: " + account.username +
                    "\nIhre Account-ID ist: " + account.id +
                    "\n-------------------------------");
        }
    }

    /**
     * Methode löscht Account (mit der variable isAdmin kann man zwischen Employee und Admin account entscheiden)
     * @param isAdmin
     * @throws IOException
     */
    public void deleteAccount(boolean isAdmin) throws IOException {
        System.out.println("Bitte geben Sie den Benutzernamen oder die Benutzer-ID vom Account an, welchen Sie löschen wollen: ");

        while (true)
        {
            String input = reader.readLine();

            if (isAdmin) {
                Admin acc = GiraV2.proObj.rootAdmin.getAdmin(input);
                if (acc == null) {
                    System.out.println("Der eingegebene Account wurde nicht gefunen. Bitte probieren Sie es nochmals:");
                    continue;
                }

                if (GiraV2.proObj.rootAdmin.deleteAdminAccount(acc)) {
                    System.out.print("Der Account wurde erfolgreich gelöscht.");
                } else {
                    System.out.println("Beim Löschen vom Account ist ein fehler aufgetreten. Abbruch.");
                }
                return;
            } else {
                Employee acc = GiraV2.proObj.mainEmployee.getEmployee(input);

                if (GiraV2.proObj.mainEmployee.getEmployee(input) == null) {
                    System.out.println("Der eingegebene Account wurde nicht gefunen. Bitte probieren Sie es nochmals:");
                    continue;
                }

                if (GiraV2.proObj.rootAdmin.deleteEmployeeAccount(acc)) {
                    System.out.print("Der Account wurde erfolgreich gelöscht.");
                } else {
                    System.out.println("Beim Löschen vom Account ist ein fehler aufgetreten. Abbruch.");
                }
                return;
            }
        }
    }

    /**
     * Methode erstellt Ticket
     * @throws IOException
     */
    public void createTicket() throws IOException {
        System.out.println("Wie soll Ihr Ticket heissen?");
        String name = reader.readLine();

        System.out.println("Was soll in die Ticket-Beschreibung geschrieben werden?");
        String desc = reader.readLine();

        System.out.println("Was für eine Priorität hat das Ticket?");
        System.out.println("- 1: Low\n- 2: Medium\n- 3: High");
        String priority = "";
        int input = inputIndex(3);
        switch(input) {
            case 1:
                priority = "Low";
                break;
            case 2:
                priority = "Medium";
                break;
            case 3:
                priority = "High";
                break;
        }

        System.out.println("Welcher Nutzer ist der Bearbeiter? (id/username)");
        Employee editor;
        while (true) {
            String user = reader.readLine();
            if (GiraV2.proObj.mainEmployee.getEmployee(user) == null) {
                System.out.println("Es wurde kein Nutzer mit den Angaben gefunden. Bitte probieren Sie es nochals:");
                continue;
            }
            else {
                editor = GiraV2.proObj.mainEmployee.getEmployee(user);
            }
            break;
        }

        new Ticket(name, desc, priority, GiraV2.proObj.employeeAccount, editor);
    }

    /**
     * Methode zeigt Ticket-Infos an und fragt ob der Nutzer das Ticket bearbeiter oder abschliessen will.
     * @throws IOException
     */
    public void viewTicket() throws IOException {
        System.out.println("Welches Ticket wollen sie anzeigen lassen? (id/name)");
        Ticket tckt;
        while (true) {
            String id_name = reader.readLine();
            if (GiraV2.proObj.startupTicket.getTicket(id_name) == null) {
                System.out.println("Ticket wurde nicht gefunden. Bitte probieren Sie es nochmals:");
                continue;
            } else {
                tckt = GiraV2.proObj.startupTicket.getTicket(id_name);
                break;
            }
        }
        System.out.println("Das Ticket wurd gefunden. Hier sind die Infos:");
        System.out.println("- Name: " + tckt.name + "\n- ID: " + tckt.id + "\n- Beschreibung: " + tckt.description + "\n- Reporter: " + tckt.reporter.username + " / " + tckt.reporter.id +
                "\n- Bearbeiter: " + tckt.editor.username + " / " + tckt.editor.id + "\n- Status: " + (tckt.isSolved ? "Gelöst" : "Ungelöst") + "\n- Priorität: " + tckt.priority);

        while (true) {
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("Was möchten Sie tun?");
            System.out.println("1: - Ticket Bearbeiten\n2: - Ticket abschliessen\n3: - Zurück zur Hauptauswahl");
            int input = inputIndex(3);
            switch(input) {
                case 1:
                    editTicket(tckt);
                    break;
                case 2:
                    tckt.isSolved = true;
                    break;
                case 3:
                    return;
            }
        }
    }

    /**
     *
     * @param tckt
     * @throws IOException
     */
    public void editTicket(Ticket tckt) throws IOException{
        System.out.println("Was möchten Sie änder?");
        System.out.println("1. - Name\n2: - Beschreibung\n3: - Reporter\n4: - Bearbeiter\n5: - Priorität\n6: - Abbruch");
        int input = inputIndex(6);
        switch (input) {
            case 1:
                System.out.println("Bitte geben Sie den gewünschten Namen ein:");
                tckt.name = reader.readLine();
                System.out.println("Name wurde geändert.");
                break;
            case 2:
                System.out.println("Bitte geben Sie die gewünschte Beschreibung ein:");
                tckt.description = reader.readLine();
                System.out.println("Beschreibung wurde geändert.");
                break;
            case 3:
                System.out.println("Welchen Nutzer wollen Sie zum Reporter ändern? (id/username)");
                Employee reporter;
                while (true) {
                    String user = reader.readLine();
                    if (GiraV2.proObj.mainEmployee.getEmployee(user) == null) {
                        System.out.println("Es wurde kein Nutzer mit den Angaben gefunden. Bitte probieren Sie es nochals:");
                        continue;
                    }
                    else {
                        reporter = GiraV2.proObj.mainEmployee.getEmployee(user);
                    }
                    break;
                }
                tckt.reporter = reporter;
                break;
            case 4:
                System.out.println("Welchen Nutzer wollen Sie zum Bearbeiter ändern? (id/username)");
                Employee editor;
                while (true) {
                    String user = reader.readLine();
                    if (GiraV2.proObj.mainEmployee.getEmployee(user) == null) {
                        System.out.println("Es wurde kein Nutzer mit den Angaben gefunden. Bitte probieren Sie es nochals:");
                        continue;
                    }
                    else {
                        editor = GiraV2.proObj.mainEmployee.getEmployee(user);
                    }
                    break;
                }
                tckt.editor = editor;
                break;
            case 5:
                System.out.println("Auf was wollen Sie die Priorität ändern?");
                System.out.println("- 1: Low\n- 2: Medium\n- 3: High");
                String priority = "";
                int prio = inputIndex(3);
                switch(prio) {
                    case 1:
                        priority = "Low";
                        break;
                    case 2:
                        priority = "Medium";
                        break;
                    case 3:
                        priority = "High";
                        break;
                }
                tckt.priority = priority;
                System.out.println("Priorität wurde geändert.");
                break;
            case 6:
                break;
        }
    }
}
