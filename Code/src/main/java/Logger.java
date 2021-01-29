import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;


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
     */
    public void log (String text, String type) {
        try {
            String info;
            switch (type.toLowerCase()) {
                case "fatal":
                    info = "Fatal";
                    break;
                case "error":
                    info = "Error";
                    break;
                case "info":
                    info = "Info";
                    break;
                default:
                    info = "Unspecified";
                    break;
            }

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateTime = formatter.format(new Date());
            String nText = (Files.readString(Paths.get(filePath))  + "\n" + dateTime + "\t;\t" + info + "\t;\t" + text);
            Files.write(Paths.get(filePath), nText.getBytes());
        }
        catch (IOException ex) { }
    }
}