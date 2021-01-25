public class GiraV2 {
    public Program proObj = new Program();
    public static GiraV2 girObj;

    static {
        try {
            girObj = new GiraV2();
        } catch (Exception ex) { }
    }

    public static void main(String[] args) {
        girObj.proObj.run();
    }
}
