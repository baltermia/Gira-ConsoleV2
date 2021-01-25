public abstract class User implements IGira{
    public int id;
    public String username;
    public String password;

    public abstract User get(String id_username);
}
