package discovery.sqlite_db;

public class User_Data
{

    private String userName;
    private String password;


    public User_Data()
    {
    }


    public User_Data(String userName,String password)
    {
        this.userName=userName;
        this.password=password;

    }


    public void set_userName(String userName)
    {
        this.userName = userName;
    }


    public void setName(String userName)
    {
        this.userName=userName;
    }


    public void setPassword(String password)
    {
        this.password = password;
    }


    public String get_userName()
    {

        return userName;
    }


    public String getPassword()
    {
        return password;
    }


}