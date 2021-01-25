import java.util.ArrayList;
import java.util.List;

/**
 * Die ticket Klasse beinhaltet Tickets.
 */
public class Ticket implements IGira{
    public List<Ticket> ticketList = new ArrayList<Ticket>();

    public int id;
    public String name;
    public String description;
    public String priority;
    public boolean isSolved;
    public Employee editor;
    public Employee reporter;

    /**
     * Constructor.
     * @param name
     * @param description
     * @param priority
     * @param reporter
     * @param editor
     */
    public Ticket(String name, String description, String priority, Employee reporter, Employee editor) {
        id = ticketList.size();
        this.name = name;
        this.description = description;
        this.priority = priority;
        isSolved = false;
        this.reporter = reporter;
        this.editor = editor;

        if (!priority.equals("-")) {
            GiraV2.proObj.startupTicket.ticketList.add(this);
        }
    }

    /**
     * Man kann einen Namen oder die ID von einem Ticket mitgeben und bekommt das ticket in einem objekt zurück (falls es gefunden wurde).
     * @param id_name
     * @return
     */
    public Ticket get(String id_name) {
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
