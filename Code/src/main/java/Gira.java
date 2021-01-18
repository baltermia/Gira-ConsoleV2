public class Gira {
    public Program proObj = new Program();
    public static Gira girObj;

    static {
        try {
            girObj = new Gira();
        } catch (Exception ex) { }
    }

    public static void main(String[] args) {
        girObj.proObj.run();
    }
}
