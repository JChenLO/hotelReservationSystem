/**
 * A User Account
 * @author Andre Mak
 */
public class User
{
    private int id;
    private String name;
    private String password;
    
    public User(int id, String name, String password)
    {
        this.id = id;
        this.name = name;
        this.password = password;
    }
    
    public String getName()
    {
        return name;
    }
    
    public boolean authentification(int id, String password)
    {
        return this.id == id && this.password.equals(password);
    }
}
