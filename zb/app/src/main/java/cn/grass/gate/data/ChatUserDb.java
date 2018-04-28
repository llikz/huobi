//package cn.grass.palmTax.data;
//
//import android.content.ContentValues;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.text.TextUtils;
//
//import com.hyphenate.chat.EMClient;
//import com.hyphenate.easeui.domain.EaseUser;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by weedys on 16/8/22.
// */
//public class ChatUserDb {
//    public static String TABLE_NAME = "chat_users";
//    public static final String COLUMN_NAME__ID = "mid";
//    public static final String COLUMN_NAME_UID = "cid";
//    public static final String COLUMN_NAME_USERID = "userid";
//    public static final String COLUMN_NAME_UNAME = "username";
//    public static final String COLUMN_NAME_NICK = "nick";
//    public static final String COLUMN_NAME_AVATAR = "avatar";
//    public static final String COLUMN_NAME_PHONE = "phone";
//    public static final String COLUMN_NAME_SIGNATURE = "signature";
//    public static final String COLUMN_NAME_ROLE = "role";
//    public static final String COLUMN_NAME_UTYPE = "user_type";
//    public static final String COLUMN_NAME_SEX = "sex";
//    public static final String COLUMN_NAME_PERSON = "person_count";
//    public static final String COLUMN_RELEASE = "contact_release";
//    public static final String COLUMN_NAME_ADMIN = "contact_admin";
//    public static final String COLUMN_NAME_REMARK = "contact_remark";
//    public static final String CHAT_USERS_TABLE_CREATE = "CREATE TABLE "
//            + TABLE_NAME + " ( "
//            + COLUMN_NAME__ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//            + COLUMN_NAME_UID + " TEXT , "
//            + COLUMN_NAME_USERID + " TEXT ,"
//            + COLUMN_NAME_UNAME + " TEXT, "
//            + COLUMN_NAME_NICK + " TEXT, "
//            + COLUMN_NAME_AVATAR + " TEXT, "
//            + COLUMN_NAME_PHONE + " TEXT, "
//            + COLUMN_NAME_ADMIN + " TEXT, "
//            + COLUMN_NAME_REMARK + " TEXT, "
//            + COLUMN_NAME_ROLE + " INTEGER ,"
//            + COLUMN_NAME_SEX + " INTEGER ,"
//            + COLUMN_NAME_UTYPE + " INTEGER ,"
//            + COLUMN_RELEASE + " INTEGER ,"
//            + COLUMN_NAME_PERSON + " INTEGER ,"
//            + COLUMN_NAME_SIGNATURE + " TEXT);";
//    public static final String CHAT_USERS_TABLE_DROP = "DROP TABLE " + TABLE_NAME + " ";
//
//    public static void saveChatUser(EaseUser u) {
//        SQLiteDatabase db = DatabaseManager.open();
//        if (!db.isOpen()) {
//            return;
//        }
//        if (TextUtils.isEmpty(u.getUid())) {
//            return;
//        }
//        if (!hasUserByUid(u.uid)) {
//            db.insert(TABLE_NAME, null, getValues(u));
//        } else {
//            int id = db.update(TABLE_NAME, getValues(u), COLUMN_NAME_UID + " =?", new String[]{u.uid});
//        }
//        db.close();
//    }
//
//    public static void saveChatUsers(ArrayList<EaseUser> us) {
//        SQLiteDatabase db = DatabaseManager.open();
//        if (!db.isOpen()) {
//            return;
//        }
//        if (us == null || us.size() == 0) {
//            return;
//        }
//        for (int i = 0; i < us.size(); i++) {
//            EaseUser u = us.get(i);
//            if (hasUserByUid(u.uid)) {
//                String where = COLUMN_NAME_UID + " =?";
//                db.update(TABLE_NAME, getValues(u), where, new String[]{u.uid});
//            } else {
//                db.insert(TABLE_NAME, null, getValues(u));
//            }
//        }
//        db.close();
//    }
//
//    public static void addChatUsers(ArrayList<EaseUser> us) {
//        SQLiteDatabase db = DatabaseManager.open();
//        if (!db.isOpen()) {
//            return;
//        }
//        if (us == null || us.size() == 0) {
//            return;
//        }
//        for (int i = 0; i < us.size(); i++) {
//            EaseUser u = us.get(i);
//            if (hasUserByUid(u.uid)) {
//                String where = COLUMN_NAME_UID + " =?";
//                db.update(TABLE_NAME, getValues(u), where, new String[]{u.uid});
//            } else {
//                db.insert(TABLE_NAME, null, getValues(u));
//            }
//        }
//        db.close();
//    }
//
//    private static boolean hasUserByUid(String chatid) {
//        SQLiteDatabase db = DatabaseManager.open();
//        if (!db.isOpen()) {
//            return false;
//        }
//        String sql = "select " + COLUMN_NAME_UID + " from " + ChatUserDb.TABLE_NAME + " WHERE " + COLUMN_NAME_UID + " =" + chatid;
//        Cursor cs = db.rawQuery(sql, null);
//        int cc = cs.getCount();
//        if (cs != null && cc > 0) {
//            cs.close();
//            return true;
//        }
//        cs.close();
//        return false;
//    }
//
//    private static ContentValues getValues(EaseUser u) {
//        ContentValues values = new ContentValues();
//        if (u == null) {
//            return null;
//        }
//        values.put(COLUMN_NAME_UID, u.uid);
//        values.put(COLUMN_NAME_USERID, u.userId);
//        values.put(COLUMN_NAME_UNAME, u.username);
//        values.put(COLUMN_NAME_PHONE, u.mphone);
//        values.put(COLUMN_NAME_AVATAR, u.avatar);
//        values.put(COLUMN_NAME_SIGNATURE, u.sign);
//        values.put(COLUMN_NAME_ROLE, u.role);
//        values.put(COLUMN_NAME_PERSON, u.person);
//        values.put(COLUMN_NAME_SEX, u.gender);
//        values.put(COLUMN_NAME_UTYPE, u.type);
//        values.put(COLUMN_NAME_NICK, u.nick);
//        values.put(COLUMN_RELEASE,u.release);
//
//        values.put(COLUMN_NAME_ADMIN,u.admin);
//        values.put(COLUMN_NAME_REMARK,u.remark);
//        return values;
//    }
//
//    public static EaseUser getUser(String uid) {
//        SQLiteDatabase db = DatabaseManager.open();
//        if (!db.isOpen()) {
//            return null;
//        }
//        String sql = "select * from " + TABLE_NAME + " where " + COLUMN_NAME_UID + " = '" + uid + "'";
//        Cursor cs = db.rawQuery(sql, null);
//        EaseUser user = null;
////        Cursor cs=db.query(TABLE_NAME,null,null,null,null,null,null);
//        if (cs != null && cs.getCount() > 0) {
//            user = new EaseUser(uid);
//            while (cs.moveToNext()) {
//                user.username = cs.getString(cs.getColumnIndex(COLUMN_NAME_UNAME));
//                user.uid = cs.getString(cs.getColumnIndex(COLUMN_NAME_UID));
//                user.avatar = cs.getString(cs.getColumnIndex(COLUMN_NAME_AVATAR));
//                user.mphone = cs.getString(cs.getColumnIndex(COLUMN_NAME_PHONE));
//                user.sign = cs.getString(cs.getColumnIndex(COLUMN_NAME_SIGNATURE));
//                user.type = cs.getInt(cs.getColumnIndex(COLUMN_NAME_UTYPE));
//                user.gender = cs.getInt(cs.getColumnIndex(COLUMN_NAME_SEX));
//                user.role = cs.getInt(cs.getColumnIndex(COLUMN_NAME_ROLE));
//                user.person = cs.getInt(cs.getColumnIndex(COLUMN_NAME_ROLE));
//                user.userId = cs.getString(cs.getColumnIndex(COLUMN_NAME_USERID));
//                user.nick = cs.getString(cs.getColumnIndex(COLUMN_NAME_NICK));
//                user.release = cs.getInt(cs.getColumnIndex(COLUMN_RELEASE));
//
//                user.remark = cs.getString(cs.getColumnIndex(COLUMN_NAME_REMARK));
//                user.admin = cs.getString(cs.getColumnIndex(COLUMN_NAME_ADMIN));
//            }
////            cs.close();
//        }
//        cs.close();
//        return user;
//    }
//
//    public static ArrayList<EaseUser> getUserList() {
//        SQLiteDatabase db = DatabaseManager.open();
//        if (!db.isOpen()) {
//            return null;
//        }
//        String sql = "select * from " + TABLE_NAME;
//        Cursor cs = db.rawQuery(sql, null);
//        ArrayList<EaseUser> users = null;
////        Cursor cs=db.query(TABLE_NAME,null,null,null,null,null,null);
//        if (cs != null && cs.getCount() > 0) {
//            users = new ArrayList<>();
//            cs.moveToFirst();
//            while (cs.moveToNext()) {
//                EaseUser user = new EaseUser("u");
//                user.username = cs.getString(cs.getColumnIndex(COLUMN_NAME_UNAME));
//                user.uid = cs.getString(cs.getColumnIndex(COLUMN_NAME_UID));
//                user.userId = cs.getString(cs.getColumnIndex(COLUMN_NAME_USERID));
//                user.avatar = cs.getString(cs.getColumnIndex(COLUMN_NAME_AVATAR));
//                user.mphone = cs.getString(cs.getColumnIndex(COLUMN_NAME_PHONE));
//                user.sign = cs.getString(cs.getColumnIndex(COLUMN_NAME_SIGNATURE));
//                user.type = cs.getInt(cs.getColumnIndex(COLUMN_NAME_UTYPE));
//                user.gender = cs.getInt(cs.getColumnIndex(COLUMN_NAME_SEX));
//                user.role = cs.getInt(cs.getColumnIndex(COLUMN_NAME_ROLE));
//                user.person = cs.getInt(cs.getColumnIndex(COLUMN_NAME_PERSON));
//
//                user.nick = cs.getString(cs.getColumnIndex(COLUMN_NAME_NICK));
//                user.release = cs.getInt(cs.getColumnIndex(COLUMN_RELEASE));
//                user.remark = cs.getString(cs.getColumnIndex(COLUMN_NAME_REMARK));
//                user.admin = cs.getString(cs.getColumnIndex(COLUMN_NAME_ADMIN));
//                users.add(user);
//            }
//            cs.close();
//        }
//        return users;
//    }
//    public static ArrayList<FriendItem> getContactUserList() {
//        SQLiteDatabase db = DatabaseManager.open();
//        if (!db.isOpen()) {
//            return null;
//        }
//        String sql = "select * from " + TABLE_NAME +" where "+COLUMN_RELEASE +" = ? ";
//        Cursor cs = db.rawQuery(sql, new String[]{""+EaseUser.RELEASE_FRIEND});
//        ArrayList<FriendItem> users = null;
////        Cursor cs=db.query(TABLE_NAME,null,null,null,null,null,null);
//        if (cs != null && cs.getCount() > 0) {
//            users = new ArrayList<>();
//            cs.moveToFirst();
//            for(int i =0;i<cs.getCount();i++){
//                FriendItem user = new FriendItem("u");
//                user.username = cs.getString(cs.getColumnIndex(COLUMN_NAME_UNAME));
//                user.uid = cs.getString(cs.getColumnIndex(COLUMN_NAME_UID));
//                user.userId = cs.getString(cs.getColumnIndex(COLUMN_NAME_USERID));
//                user.avatar = cs.getString(cs.getColumnIndex(COLUMN_NAME_AVATAR));
//                user.mphone = cs.getString(cs.getColumnIndex(COLUMN_NAME_PHONE));
//                user.sign = cs.getString(cs.getColumnIndex(COLUMN_NAME_SIGNATURE));
//                user.type = cs.getInt(cs.getColumnIndex(COLUMN_NAME_UTYPE));
//                user.gender = cs.getInt(cs.getColumnIndex(COLUMN_NAME_SEX));
//                user.role = cs.getInt(cs.getColumnIndex(COLUMN_NAME_ROLE));
//                user.person = cs.getInt(cs.getColumnIndex(COLUMN_NAME_PERSON));
//
//                user.nick = cs.getString(cs.getColumnIndex(COLUMN_NAME_NICK));
//                user.release = cs.getInt(cs.getColumnIndex(COLUMN_RELEASE));
//                user.remark = cs.getString(cs.getColumnIndex(COLUMN_NAME_REMARK));
//                user.admin = cs.getString(cs.getColumnIndex(COLUMN_NAME_ADMIN));
//                users.add(user);
//                cs.moveToNext();
//            }
//            cs.close();
//        }
//        return users;
//    }
//    public static ArrayList<FriendItem> getOtherContactUserList() {
//        SQLiteDatabase db = DatabaseManager.open();
//        if (!db.isOpen()) {
//            return null;
//        }
//        String name= EMClient.getInstance().getCurrentUser();
//        if (TextUtils.isEmpty(name)){
//            return null;
//        }
//        String sql = "select * from " + TABLE_NAME +" where "+COLUMN_NAME_UID +"!="+name+" and "+COLUMN_RELEASE +" = ? ";
//        Cursor cs = db.rawQuery(sql, new String[]{""+EaseUser.RELEASE_FRIEND});
//        ArrayList<FriendItem> users = null;
////        Cursor cs=db.query(TABLE_NAME,null,null,null,null,null,null);
//        if (cs != null && cs.getCount() > 0) {
//            users = new ArrayList<>();
//            cs.moveToFirst();
//            for(int i =0;i<cs.getCount();i++){
//                FriendItem user = new FriendItem("u");
//                user.username = cs.getString(cs.getColumnIndex(COLUMN_NAME_UNAME));
//                user.uid = cs.getString(cs.getColumnIndex(COLUMN_NAME_UID));
//                user.userId = cs.getString(cs.getColumnIndex(COLUMN_NAME_USERID));
//                user.avatar = cs.getString(cs.getColumnIndex(COLUMN_NAME_AVATAR));
//                user.mphone = cs.getString(cs.getColumnIndex(COLUMN_NAME_PHONE));
//                user.sign = cs.getString(cs.getColumnIndex(COLUMN_NAME_SIGNATURE));
//                user.type = cs.getInt(cs.getColumnIndex(COLUMN_NAME_UTYPE));
//                user.gender = cs.getInt(cs.getColumnIndex(COLUMN_NAME_SEX));
//                user.role = cs.getInt(cs.getColumnIndex(COLUMN_NAME_ROLE));
//                user.person = cs.getInt(cs.getColumnIndex(COLUMN_NAME_PERSON));
//
//                user.nick = cs.getString(cs.getColumnIndex(COLUMN_NAME_NICK));
//                user.release = cs.getInt(cs.getColumnIndex(COLUMN_RELEASE));
//                user.remark = cs.getString(cs.getColumnIndex(COLUMN_NAME_REMARK));
//                user.admin = cs.getString(cs.getColumnIndex(COLUMN_NAME_ADMIN));
//                users.add(user);
//                cs.moveToNext();
//            }
//            cs.close();
//        }
//        return users;
//    }
//
//    public static Map<String, EaseUser> getUserMap() {
//        SQLiteDatabase db = DatabaseManager.open();
//        if (!db.isOpen()) {
//            return null;
//        }
//        String sql = "select * from " + TABLE_NAME;
//        Cursor cs = db.rawQuery(sql, null);
//        Map<String, EaseUser> users = null;
////        Cursor cs=db.query(TABLE_NAME,null,null,null,null,null,null);
//        if (cs != null && cs.getCount() > 0) {
//            users = new HashMap<String, EaseUser>();
//            cs.moveToFirst();
//            while (cs.moveToNext()) {
//                EaseUser user = new EaseUser(cs.getString(cs.getColumnIndex(COLUMN_NAME_UID)));
//                user.username = cs.getString(cs.getColumnIndex(COLUMN_NAME_UNAME));
//                user.uid = cs.getString(cs.getColumnIndex(COLUMN_NAME_UID));
//                user.userId = cs.getString(cs.getColumnIndex(COLUMN_NAME_USERID));
//                user.avatar = cs.getString(cs.getColumnIndex(COLUMN_NAME_AVATAR));
//                user.mphone = cs.getString(cs.getColumnIndex(COLUMN_NAME_PHONE));
//                user.sign = cs.getString(cs.getColumnIndex(COLUMN_NAME_SIGNATURE));
//                user.type = cs.getInt(cs.getColumnIndex(COLUMN_NAME_UTYPE));
//                user.gender = cs.getInt(cs.getColumnIndex(COLUMN_NAME_SEX));
//                user.role = cs.getInt(cs.getColumnIndex(COLUMN_NAME_ROLE));
//                user.person = cs.getInt(cs.getColumnIndex(COLUMN_NAME_PERSON));
//
//                user.nick = cs.getString(cs.getColumnIndex(COLUMN_NAME_NICK));
//                user.release = cs.getInt(cs.getColumnIndex(COLUMN_RELEASE));
//                user.remark = cs.getString(cs.getColumnIndex(COLUMN_NAME_REMARK));
//                user.admin = cs.getString(cs.getColumnIndex(COLUMN_NAME_ADMIN));
//                users.put(user.uid, user);
//            }
//            cs.close();
//        }
//        return users;
//    }
//
//    public static void clearUserInfo() {
//        SQLiteDatabase db = DatabaseManager.open();
//        if (!db.isOpen()) {
//            return;
//        }
//        db.delete(TABLE_NAME, null, null);
//    }
//    public static void delUserInfo(String uid) {
//        SQLiteDatabase db = DatabaseManager.open();
//        if (!db.isOpen()) {
//            return ;
//        }
//        String sql = "delete from " + TABLE_NAME +" where "+COLUMN_NAME_UID+" = "+uid;
//        Cursor cs = db.rawQuery(sql, null);
//    }
//}
