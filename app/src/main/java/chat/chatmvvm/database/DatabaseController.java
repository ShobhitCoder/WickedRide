package chat.chatmvvm.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;


import java.sql.SQLException;
import java.util.ArrayList;

/**
 */
public class DatabaseController {


    private static Context context;
    private static SQLiteDatabase myDataBase;
    private static DataBaseHelper dbHelper;
    private static DatabaseController databaseController;


    /*
   * Method to insert data in table
   * */
    public DatabaseController(Context context) {
        this.context = context;
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseController getInstance(Context context) {
        if (databaseController == null) {
            databaseController = new DatabaseController(context);
        }
        return databaseController;
    }

    public DatabaseController open() throws SQLException {

        if (context != null)
            dbHelper = new DataBaseHelper(context);

        myDataBase = dbHelper.getWritableDatabase();
        return this;

    }

    public void close() {

        dbHelper.close();

    }


    public static long insertData(Context context, ContentValues values) {
        long id = -1;
        try {
            id = DataBaseHelper.getInstance(context).openDataBase(SQLiteDatabase.OPEN_READWRITE).insert(DatabaseConstant.CHAT_TABLE, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    //    database query for user listing of contact start


    public static void deleteAllDataFromUserTable(Context context) {

        DataBaseHelper.getInstance(context).openDataBase(SQLiteDatabase.OPEN_READWRITE).execSQL("delete from " + DatabaseConstant.USER_TABLE);

    }


    public static long insertDataIntoUserTable(Context context, ContentValues values) {
        long id = -1;
        try {
            id = DataBaseHelper.getInstance(context).openDataBase(SQLiteDatabase.OPEN_READWRITE).insert(DatabaseConstant.USER_TABLE, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public static ArrayList<Bundle> getContactUsersListing(Context context, String user) {

        ArrayList<Bundle> userList = new ArrayList<Bundle>();

        String sql = "select * from " + DatabaseConstant.USER_TABLE + " where " + DatabaseConstant.USER_TABLE_LOGINUSERID + " = '" + user + "' order by " + DatabaseConstant.ID;


        Cursor cursor = DataBaseHelper.getInstance(context).openDataBase(SQLiteDatabase.OPEN_READWRITE).rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            do {

                Bundle data = new Bundle();
                data.putString(DatabaseConstant.Privacy, cursor.getString(cursor.getColumnIndex(DatabaseConstant.Privacy)));
                data.putString(DatabaseConstant.User_Block_Unblock_Status, cursor.getString(cursor.getColumnIndex(DatabaseConstant.User_Block_Unblock_Status)));
                data.putString(DatabaseConstant.USER_TABLE_ID, cursor.getString(cursor.getColumnIndex(DatabaseConstant.USER_TABLE_ID)));
                data.putString(DatabaseConstant.USER_TABLE_USERID, cursor.getString(cursor.getColumnIndex(DatabaseConstant.USER_TABLE_USERID)));
                data.putString(DatabaseConstant.USER_TABLE_USERNAME, cursor.getString(cursor.getColumnIndex(DatabaseConstant.USER_TABLE_USERNAME)));
                data.putString(DatabaseConstant.USER_TABLE_INSTATNT_STATUS, cursor.getString(cursor.getColumnIndex(DatabaseConstant.USER_TABLE_INSTATNT_STATUS)));
                data.putString(DatabaseConstant.USER_TABLE_LOGINUSERID, cursor.getString(cursor.getColumnIndex(DatabaseConstant.USER_TABLE_LOGINUSERID)));
                userList.add(data);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return userList;
    }






    public static int getChatCount(String receiverID) {
        String sql = "SELECT COUNT('" + DatabaseConstant.RECEIVER_ID + "') FROM " + DatabaseConstant.CHAT_TABLE + " WHERE " + DatabaseConstant.RECEIVER_ID + " = " + receiverID;
        Cursor cursor = DataBaseHelper.getInstance(context).openDataBase(SQLiteDatabase.OPEN_READWRITE).rawQuery(sql, null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public static void updateData(Context context, ContentValues values, String selection, String key[]) {
        try {
            myDataBase.update(DatabaseConstant.CHAT_TABLE, values, selection, key);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }











    public static void updateChatStatus(String senderId, String messageId, String seen_sent) {

        String sql = "UPDATE " + DatabaseConstant.CHAT_TABLE + " SET " + DatabaseConstant.CHAT_STATUS + " = '" + seen_sent + "'" + " WHERE " + DatabaseConstant.SENDER_ID + " = '" + senderId + "'" + " And " + DatabaseConstant.ID + " = '" + messageId + "'";
        Cursor cursor = DataBaseHelper.getInstance(context).openDataBase(SQLiteDatabase.OPEN_READWRITE).rawQuery(sql, null);
        cursor.moveToFirst();

    }



    public static ArrayList<Bundle> getUserListing(Context context) {
        ArrayList<Bundle> chatUserList = new ArrayList<>();

        String sql = "select * from " + DatabaseConstant.CHAT_TABLE + " group by " + DatabaseConstant.RECEIVER_ID + " order by " + DatabaseConstant.ID + " desc ";

        Cursor cursor = DataBaseHelper.getInstance(context).openDataBase(SQLiteDatabase.OPEN_READONLY).rawQuery(sql, null);
        try {
            if (cursor.moveToFirst()) {

                do {

                    Bundle data = new Bundle();
                    data.putString(DatabaseConstant.Receiver_Name, cursor.getString(cursor.getColumnIndex(DatabaseConstant.Receiver_Name)));
                    data.putString(DatabaseConstant.ID, cursor.getString(cursor.getColumnIndex(DatabaseConstant.ID)));
                    data.putString(DatabaseConstant.SENDER_ID, cursor.getString(cursor.getColumnIndex(DatabaseConstant.SENDER_ID)));
                    data.putString(DatabaseConstant.SENDER_NAME, cursor.getString(cursor.getColumnIndex(DatabaseConstant.SENDER_NAME)));
                    data.putString(DatabaseConstant.RECEIVER_ID, cursor.getString(cursor.getColumnIndex(DatabaseConstant.RECEIVER_ID)));
                    data.putString(DatabaseConstant.USER_ID, cursor.getString(cursor.getColumnIndex(DatabaseConstant.USER_ID)));
                    data.putString(DatabaseConstant.CHAT_MESSAGE, cursor.getString(cursor.getColumnIndex(DatabaseConstant.CHAT_MESSAGE)));
                    data.putString(DatabaseConstant.CHAT_STATUS, cursor.getString(cursor.getColumnIndex(DatabaseConstant.CHAT_STATUS)));
                    data.putString(DatabaseConstant.READ_UNREAD, cursor.getString(cursor.getColumnIndex(DatabaseConstant.READ_UNREAD)));
                    data.putString(DatabaseConstant.TIMESTAMP, cursor.getString(cursor.getColumnIndex(DatabaseConstant.TIMESTAMP)));
                    data.putString(DatabaseConstant.PROFILE_PIC_ONE, cursor.getString(cursor.getColumnIndex(DatabaseConstant.PROFILE_PIC_ONE)));
                    data.putString(DatabaseConstant.PROFILE_PIC_TWO, cursor.getString(cursor.getColumnIndex(DatabaseConstant.PROFILE_PIC_TWO)));
                    data.putString(DatabaseConstant.PROFILE_PIC_THREE, cursor.getString(cursor.getColumnIndex(DatabaseConstant.PROFILE_PIC_THREE)));

                    chatUserList.add(data);

                } while (cursor.moveToNext());
            }

            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return chatUserList;
    }



    /*
    * Method to fetch data from table
    * */

    public static ArrayList<Bundle> getChatHistory(Context context, String user, String userId) {

        ArrayList<Bundle> chatHistory = new ArrayList<Bundle>();


        String sql = "select * from " + DatabaseConstant.CHAT_TABLE + " where ((" +
                DatabaseConstant.RECEIVER_ID + " = '" + user + "' AND " + DatabaseConstant
                .SENDER_ID + " = '" + userId + "') OR (" + DatabaseConstant.RECEIVER_ID + " = '" +
               userId + "' AND " + DatabaseConstant.SENDER_ID + " = '" + user + "')) AND " +
                DatabaseConstant.is_delete + " = 'no' order by " + DatabaseConstant.ID;


        Cursor cursor = DataBaseHelper.getInstance(context).openDataBase(SQLiteDatabase.OPEN_READWRITE).rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            do {

                Bundle data = new Bundle();

                data.putString(DatabaseConstant.CHAT_HIDE_SHOW_TIME, cursor.getString(cursor.getColumnIndex(DatabaseConstant.CHAT_HIDE_SHOW_TIME)));
                data.putString(DatabaseConstant.ID, cursor.getString(cursor.getColumnIndex(DatabaseConstant.ID)));
                data.putString(DatabaseConstant.SENDER_ID, cursor.getString(cursor.getColumnIndex(DatabaseConstant.SENDER_ID)));
                data.putString(DatabaseConstant.SENDER_NAME, cursor.getString(cursor.getColumnIndex(DatabaseConstant.SENDER_NAME)));
                data.putString(DatabaseConstant.RECEIVER_ID, cursor.getString(cursor.getColumnIndex(DatabaseConstant.RECEIVER_ID)));
                data.putString(DatabaseConstant.Receiver_Name, cursor.getString(cursor.getColumnIndex(DatabaseConstant.Receiver_Name)));
                data.putString(DatabaseConstant.USER_ID, cursor.getString(cursor.getColumnIndex(DatabaseConstant.USER_ID)));
                data.putString(DatabaseConstant.CHAT_MESSAGE, cursor.getString(cursor.getColumnIndex(DatabaseConstant.CHAT_MESSAGE)));
                data.putString(DatabaseConstant.READ_UNREAD, cursor.getString(cursor.getColumnIndex(DatabaseConstant.READ_UNREAD)));
                data.putString(DatabaseConstant.TIMESTAMP, cursor.getString(cursor.getColumnIndex(DatabaseConstant.TIMESTAMP)));
                data.putString(DatabaseConstant.Chat_Type, cursor.getString(cursor.getColumnIndex(DatabaseConstant.Chat_Type)));
                data.putString(DatabaseConstant.CHAT_STATUS, cursor.getString(cursor.getColumnIndex(DatabaseConstant.CHAT_STATUS)));
                data.putString(DatabaseConstant.Image_Url_Path, cursor.getString(cursor.getColumnIndex(DatabaseConstant.Image_Url_Path)));
                data.putString(DatabaseConstant.Vedio_Url_Path, cursor.getString(cursor.getColumnIndex(DatabaseConstant.Vedio_Url_Path)));
                data.putString(DatabaseConstant.Media_Chat_Status, cursor.getString(cursor.getColumnIndex(DatabaseConstant.Media_Chat_Status)));
                data.putString(DatabaseConstant.Video_Thumbnail_Path, cursor.getString(cursor.getColumnIndex(DatabaseConstant.Video_Thumbnail_Path)));


                chatHistory.add(data);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return chatHistory;
    }









}
