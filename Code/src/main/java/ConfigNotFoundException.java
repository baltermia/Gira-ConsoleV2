public class ConfigNotFoundException extends Exception {

    public ConfigNotFoundException(String filePath) {
        super("Config not found: " + filePath);
    }

}
