package discovery.sqlite_db;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import java.util.List;

public class MainActivity extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHandler db = new DBHandler(this);

        // Inserting Shop/Rows
        Log.d("Insert: ", "Inserting ..");


        db.addUser(new User_Data("captainUnderPants", "plunger"));
        db.addUser(new User_Data("Kanye West", "cantRead"));
        db.addUser(new User_Data("snooky", "WantsSmushSmush123"));
        db.addUser(new User_Data("Adrian", "shittypassword"));

        db.deleteUser(new User_Data("captainUnderPants", "plunger"));
        db.deleteUser(new User_Data("snooky", "WantsSmushSmush123"));

        db.getALL_UserData();



        //To DELETE!:
       // db.deleteShop(new Shop(1,"a", " 475 Brannan St #330, San Francisco, CA 94107, United States"));


        // Reading all shops
        Log.d("Reading: ", "Reading all shops..");
        List<User_Data> shops = db.getALL_UserData();

        for (User_Data shop : shops) {
            String log = "Username: " + shop.get_userName() + " ,Password: " + shop.getPassword();
        // Writing shops to log
            Log.d("Users: : ", log);
        }


    }

}

