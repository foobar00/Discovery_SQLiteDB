package discovery.sqlite_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper
{
    // Database Version
    private static final int DATABASE_VERSION = 1;


    // Database Name
    private static final String DATABASE_NAME = "discoveryDB5";


    //table name
    private static final String TABLE_userData = "userData_table";


    //Table Columns names
    //Add more columns as needed
    //Possible additional columns: savedColorScheme1 ect...
    private static final String KEY_userName = "username";
    private static final String KEY_password = "password";


    public DBHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_userData + "("
                + KEY_userName + " TEXT," + KEY_password + " TEXT)";
        db.execSQL(CREATE_USER_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_userData);
        // Creating tables again
        onCreate(db);
    }


    // Adding new user
    public void addUser(User_Data userData)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_userName, userData.get_userName()); // add username
        values.put(KEY_password, userData.getPassword()); // add password

        // Inserting Row
        db.insert(TABLE_userData, null, values);
        db.close(); // Closing database connection
    }


    // Retrieve user data
    public User_Data getUser_Data(String userName)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_userData, new String[]{KEY_userName,
                        KEY_password}, KEY_userName + "=?",
                new String[]{String.valueOf(userName)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User_Data user = new User_Data((cursor.getString(0)), cursor.getString(1));
        // return user
        return user;
    }


    // Getting ALL users
    public List<User_Data> getALL_UserData()
    {
        List<User_Data> userList = new ArrayList<User_Data>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_userData;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User_Data userData = new User_Data();
                userData.set_userName((cursor.getString(0)));
                userData.setPassword(cursor.getString(1));
                // Adding user to list
                userList.add(userData);
            } while (cursor.moveToNext());
        }

        // return user list
        return userList;
    }


    // Getting user count
    public int getUserCount()
    {
        String countQuery = "SELECT  * FROM " + TABLE_userData;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


    // Updating a shop
    public int update_userData(User_Data userData) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_userName, userData.get_userName());
        values.put(KEY_password, userData.getPassword());

        // updating row
        return db.update(TABLE_userData, values, KEY_userName + " = ?",
                new String[]{String.valueOf(userData.get_userName())});
    }


    // Deleting a shop
    public void deleteUser(User_Data userData) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_userData, KEY_userName + " = ?",
                new String[] { String.valueOf(userData.get_userName()) });
        db.close();
    }


}