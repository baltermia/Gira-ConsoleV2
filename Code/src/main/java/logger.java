import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Mit der Logger Klasse kann man Loggen.
 */
public class logger {
    private String filePath;

    /**
     * Constructor
     * @param filePath
     */
    public logger (String filePath) {
        this.filePath = filePath;
    }

    /**
     * Schreibt den mitgegebenen Text
     * @param text
     * @throws IOException
     */
    public void log (String text) throws IOException {
        text = Files.readString(Paths.get(filePath))  + "\n" + text;
                Files.write(Paths.get(filePath), text.getBytes());
    }

    /**
     * Holt den Pfad der Log-Datei aus der Config.
     * @return
     */
    public String getFilePath() {
        try {
            if (!Files.exists(Path.of(filePath))) {
                throw new Exception(
                        "Incorrect filepath : " + filePath);
            }
            return Files.readString(Paths.get("config.txt"));
        }
        catch (Exception ex) {
            return null;
        }
    }
}