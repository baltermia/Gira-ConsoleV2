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

    public File (String configPath) {
        try {
            configContent = String.valueOf(Files.readAllLines(Path.of(configPath), StandardCharsets.UTF_8));
        } catch (IOException e) {
            //throw new ConfigNotFoundException();
        }

    }

    public boolean writeTickets(List<Ticket> ticketList) {
        return true;
    }

    public boolean writeUsers(List<Employee> employeeList, List<Admin> adminList) {
        return true;
    }

    public String getJsonValue(String key) {
        JSONObject obj = new JSONObject(configContent);
        return "";
    }
}
