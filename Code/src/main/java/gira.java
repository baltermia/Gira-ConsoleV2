public class gira {
    public program proObj = new program();
    public static gira girObj;

    static {
        try {
            girObj = new gira();
        } catch (Exception ex) { }
    }

    public static void main(String[] args) {
        girObj.proObj.run();
    }
}
