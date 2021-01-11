import java.util.ArrayList;
import java.util.List;

/**
 * Die ticket Klasse beinhaltet Tickets.
 */
public class ticket {
    public List<ticket> ticketList = new ArrayList<ticket>();

    public int id;
    public String name;
    public String description;
    public String priority;
    public boolean isSolved;
    public employee editor;
    public employee reporter;

    /**
     * Constructor.
     * @param name
     * @param description
     * @param priority
     * @param reporter
     * @param editor
     */
    public ticket(String name, String description, String priority, employee reporter, employee editor) {
        id = ticketList.size();
        this.name = name;
        this.description = description;
        this.priority = priority;
        isSolved = false;
        this.reporter = reporter;
        this.editor = editor;

        if (!priority.equals("-")) {
            gira.girObj.proObj.startupTicket.ticketList.add(this);
        }
    }

    /**
     * Man kann einen Namen oder die ID von einem Ticket mitgeben und bekommt das ticket in einem objekt zurück (falls es gefunden wurde).
     * @param id_name
     * @return
     */
    public ticket getTicket(String id_name) {
        if (id_name.matches("[0-9]+")) {
            for (int i = 0; i < ticketList.size(); i++) {
                if (Integer.toString(ticketList.get(i).id).equals(id_name))
                    return ticketList.get(i);
            }
        } else {
            for (int i = 0; i < ticketList.size(); i++) {
                if (ticketList.get(i).name.equals(id_name)) return ticketList.get(i);
            }
        }
        return null;
    }
}
