import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Mit der Logger Klasse kann man Loggen.
 */
public class Logger {
    private String filePath;

    /**
     * Constructor
     * @param filePath
     */
    public Logger(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Schreibt den mitgegebenen Text
     * @param text
     * @throws IOException
     */
    public void log (String text) {
        try {
            Files.write(Paths.get(filePath), (Files.readString(Paths.get(filePath))  + "\n" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDate.now()) + text).getBytes());
        }
        catch (IOException ex) { }
    }
}