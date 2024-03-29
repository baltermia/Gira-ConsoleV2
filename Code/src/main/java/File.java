import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.FileReader;

import org.json.*;

public class File {
    private String configPath;
    private String ticketFilePath;
    private String userFilePath;
    private String configContent;

    public File (String configPath) throws ConfigNotFoundException {
        if (!Files.exists(Path.of(configPath))) {
            throw new ConfigNotFoundException(configPath);
        }
        try {
            configContent = String.valueOf(Files.readAllLines(Path.of(configPath), StandardCharsets.UTF_8));
        }
        catch (IOException ex) {

        }
    }

    public void writeTickets(List<Ticket> ticketList) {

    }

    public void writeUsers(List<Employee> employeeList, List<Admin> adminList) {

    }

    public List<Ticket> getTickets() {
        String text = "";
        String tmp = text.replaceAll("\\s+","");
        return null;
    }

    public List<Employee> getEmployees() {
        return null;
    }

    public List<Admin> getAdmins() {
        return null;
    }

    public String getFromConfig(String key)  {
        try {
            JSONArray arr = new JSONArray(configContent);
            JSONObject obj = arr.getJSONObject(0);
            return obj.getString(key);
        }
        catch (Exception ex) {
            return null;
        }
    }
}
