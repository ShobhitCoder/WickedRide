package chat.chatmvvm.database;

/**
 * Created by singsys-0095 on 3/1/2016.
 */
public class DatabaseConstant {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "chat";


    // constant new for table.....


    public static final String CHAT_TABLE = "chat_table";
    public static final String ID = "id";
    public static final String CHAT_ID = "chat_id";
    public static final String CHAT_HIDE_SHOW_TIME = "chat_hide_show_time";
    public static final String SENDER_ID = "sender_id";
    public static final String RECEIVER_ID = "receiver_id";
    public static final String USER_ID = "user_id";
    public static final String CHAT_MESSAGE = "chat_message";
    public static final String READ_UNREAD = "read_unread";
    public static final String CHAT_STATUS = "chat_status";
    public static final String TIMESTAMP = "chat_time";
    public static final String SENDER_NAME = "sender_name";
    public static final String PROFILE_PIC_ONE = "profile_pic_one";
    public static final String PROFILE_PIC_TWO = "profile_pic_two";
    public static final String PROFILE_PIC_THREE = "profile_pic_three";
    public static final String Image_Url_Path = "image_url_path";
    public static final String Vedio_Url_Path = "vedio_url_path";
    public static final String Receiver_Name = "receiver_name";


    public static final String Privacy = "privacy";
    public static final String USER_TABLE = "user_table";
    public static final String USER_TABLE_ID = "id";
    public static final String USER_TABLE_USERID = "user_id";
    public static final String USER_TABLE_USERNAME = "name";
    public static final String USER_TABLE_INSTATNT_STATUS = "instant_status";
    public static final String USER_TABLE_LOGINUSERID = "login_user_id";

    public static final String Action = "action";
    public static final String Undeliver = "undeliver";
    public static final String Sent = "sent";
    public static final String Seen = "seen";
    public static final String is_delete = "is_delete";
    public static final String is_starred = "is_starred";


    public static final String Chat_Type = "chat_type";
    public static final String Text_Message = "text_message";
    public static final String Video_Message = "video_message";
    public static final String Image_Message = "image_message";
    public static final String Audio_Message = "audio_message";
    public static final String Video_Thumbnail_Path = "video_thumbnail_path";

    public static final String Media_Chat_Status = "media_chat_status";


    public static final String None = "none";
    public static final String Media_Chat_Uploading = "media_chat_uploading";
    public static final String Media_Chat_Downloading = "media_chat_downloading";
    public static final String Media_Chat_Complete = "media_chat_complete";
    public static final String Media_Chat_Failed = "media_chat_failed";
    public static final String User_Block_Unblock_Status = "user_block_unblock_status";
    public static final String UNBLOCKED = "unblock";
    public static final String BLOCKED = "block";


}
