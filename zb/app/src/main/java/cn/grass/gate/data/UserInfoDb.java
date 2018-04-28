package cn.grass.gate.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import cn.grass.gate.beans.UserInfo;

/**
 * Created by weedys on 16/7/22.
 */
public class UserInfoDb {
    public static String TABLE_NAME = "users";
    public static final String COLUMN_NAME__ID = "mid";
    public static final String COLUMN_NAME_TOKEN = "token";
    public static final String COLUMN_NAME_CREATE_TIME = "createTime";
    public static final String COLUMN_NAME_PHONE = "phone";
    public static final String COLUMN_NAME_BIRTH = "birth";
    public static final String COLUMN_NAME_SORT = "sort";
    public static final String COLUMN_NAME_IMAGE_URL = "imageUrl";
    public static final String COLUMN_NAME_XB_DM = "xb_dm";
    public static final String COLUMN_NAME_PASSWORD = "password";
    public static final String COLUMN_NAME_UID = "uid";
    public static final String COLUMN_NAME_LAND_LINE = "landLine";
    public static final String COLUMN_NAME_USERNAME = "username";
    public static final String COLUMN_NAME_PERSONNEL = "personnel";
    public static final String COLUMN_NAME_HEAD = "head";
    public static final String COLUMN_NAME_REV_TIME = "revTime";
    public static final String COLUMN_NAME_MODIFY_TIME = "modifyTime";
    public static final String COLUMN_NAME_ID_NUMBER = "idNumber";
    public static final String COLUMN_NAME_CREATE_USER_ID = "createUserId";
    public static final String COLUMN_NAME_STATUS = "status";
    public static final String COLUMN_NAME_NICKNAME = "nickname";
    public static final String COLUMN_NAME_CORNET = "cornet";
    public static final String COLUMN_NAME_REV_OR_STOP = "revOrStop";
    public static final String COLUMN_NAME_IM_USER_NAME = "imUserName";
    public static final String COLUMN_NAME_STOP_TIME = "stopTime";
    public static final String COLUMN_NAME_MODIFY_USER_ID = "modifyUserId";
    public static final String COLUMN_NAME_DEPT_CODE = "deptCode";
    public static final String COLUMN_NAME_SALT = "salt";

    public static final String USERNAME_TABLE_CREATE = "CREATE TABLE "
            + TABLE_NAME + " ( "
            + COLUMN_NAME__ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME_CREATE_TIME + " TEXT , "
            + COLUMN_NAME_PHONE + " TEXT , "
            + COLUMN_NAME_BIRTH + " TEXT , "
            + COLUMN_NAME_SORT + " TEXT , "
            + COLUMN_NAME_IMAGE_URL + " TEXT , "
            + COLUMN_NAME_XB_DM + " TEXT , "
            + COLUMN_NAME_PASSWORD + " TEXT , "
            + COLUMN_NAME_UID + " TEXT , "
            + COLUMN_NAME_LAND_LINE + " TEXT , "
            + COLUMN_NAME_USERNAME + " TEXT , "
            + COLUMN_NAME_PERSONNEL + " TEXT , "
            + COLUMN_NAME_HEAD + " TEXT , "
            + COLUMN_NAME_REV_TIME + " TEXT , "
            + COLUMN_NAME_MODIFY_TIME + " TEXT , "
            + COLUMN_NAME_ID_NUMBER + " TEXT , "
            + COLUMN_NAME_CREATE_USER_ID + " TEXT , "
            + COLUMN_NAME_STATUS + " TEXT , "
            + COLUMN_NAME_NICKNAME + " TEXT , "
            + COLUMN_NAME_CORNET + " TEXT , "
            + COLUMN_NAME_REV_OR_STOP + " TEXT , "
            + COLUMN_NAME_IM_USER_NAME + " TEXT , "
            + COLUMN_NAME_STOP_TIME + " TEXT , "
            + COLUMN_NAME_MODIFY_USER_ID + " TEXT , "
            + COLUMN_NAME_DEPT_CODE + " TEXT , "
            + COLUMN_NAME_SALT + " TEXT , "
            + COLUMN_NAME_TOKEN + " TEXT );";
    public static final String USERNAME_TABLE_DROP = "DROP TABLE " + TABLE_NAME + " ";

    public static void saveUser(UserInfo u) {
        SQLiteDatabase db = DatabaseManager.open();
        if (!db.isOpen()) {
            return;
        }
        clearUserInfo();
        db.insert(TABLE_NAME, null, getValues(u));
        db.close();
    }



    private static boolean hasUserByUid(String uid) {
        SQLiteDatabase db = DatabaseManager.open();
        if (!db.isOpen()) {
            return false;
        }
        String sql = "select count(*) from " + UserInfoDb.TABLE_NAME + " where " + COLUMN_NAME_UID + " = ?";

        Cursor cs = db.rawQuery(sql, new String[]{uid});
//        cs=db.rawQuery()
        if (cs != null && cs.getCount() > 0) {
            return true;
        }
        return false;
    }

    private static ContentValues getValues(UserInfo u) {
        ContentValues values = new ContentValues();
        if (u == null) {
            return null;
        }
        values.put(COLUMN_NAME_CREATE_TIME, u.createTime);
        values.put(COLUMN_NAME_PHONE, u.phone);
        values.put(COLUMN_NAME_BIRTH, u.birth);
        values.put(COLUMN_NAME_SORT, u.sort);
        values.put(COLUMN_NAME_IMAGE_URL, u.imageUrl);
        values.put(COLUMN_NAME_XB_DM, u.xb_dm);
        values.put(COLUMN_NAME_PASSWORD, u.password);
        values.put(COLUMN_NAME_UID, u.id);
        values.put(COLUMN_NAME_LAND_LINE, u.landline);
        values.put(COLUMN_NAME_USERNAME, u.username);
        values.put(COLUMN_NAME_PERSONNEL, u.personnel);
        values.put(COLUMN_NAME_HEAD, u.head);
        values.put(COLUMN_NAME_REV_TIME, u.revTime);
        values.put(COLUMN_NAME_MODIFY_TIME, u.modifyTime);
        values.put(COLUMN_NAME_ID_NUMBER, u.idNumber);
        values.put(COLUMN_NAME_CREATE_USER_ID, u.createUserId);
        values.put(COLUMN_NAME_STATUS, u.status);
        values.put(COLUMN_NAME_NICKNAME, u.nickname);
        values.put(COLUMN_NAME_CORNET, u.cornet);
        values.put(COLUMN_NAME_REV_OR_STOP, u.revOrStop);
        values.put(COLUMN_NAME_IM_USER_NAME, u.imUserName);
        values.put(COLUMN_NAME_STOP_TIME, u.stopTime);
        values.put(COLUMN_NAME_MODIFY_USER_ID, u.modifyUserId);
        values.put(COLUMN_NAME_DEPT_CODE, u.deptCode);
        values.put(COLUMN_NAME_SALT, u.salt);
        if (!TextUtils.isEmpty(u.token))
            values.put(COLUMN_NAME_TOKEN, u.token);
        return values;
    }

    public static UserInfo getUser() {
        SQLiteDatabase db = DatabaseManager.open();
        if (!db.isOpen()) {
            return null;
        }
        String sql = "select * from " + UserInfoDb.TABLE_NAME;
        Cursor cs = db.rawQuery(sql, null);
        UserInfo user = null;
        if (cs != null && cs.getCount() > 0) {
            user = new UserInfo();
            cs.moveToFirst();
            for(int i=0;i<cs.getCount();i++){
                user.createTime = cs.getString(cs.getColumnIndex(COLUMN_NAME_CREATE_TIME));
                user.phone = cs.getString(cs.getColumnIndex(COLUMN_NAME_PHONE));
                user.birth = cs.getString(cs.getColumnIndex(COLUMN_NAME_BIRTH));
                user.sort = cs.getInt(cs.getColumnIndex(COLUMN_NAME_SORT));
                user.imageUrl = cs.getString(cs.getColumnIndex(COLUMN_NAME_IMAGE_URL));
                user.xb_dm = cs.getString(cs.getColumnIndex(COLUMN_NAME_XB_DM));
                user.password = cs.getString(cs.getColumnIndex(COLUMN_NAME_PASSWORD));
                user.id = cs.getString(cs.getColumnIndex(COLUMN_NAME_UID));
                user.landline = cs.getString(cs.getColumnIndex(COLUMN_NAME_LAND_LINE));
                user.username = cs.getString(cs.getColumnIndex(COLUMN_NAME_USERNAME));
                user.personnel = cs.getInt(cs.getColumnIndex(COLUMN_NAME_PERSONNEL));
                user.head = cs.getInt(cs.getColumnIndex(COLUMN_NAME_HEAD));
                user.revTime = cs.getString(cs.getColumnIndex(COLUMN_NAME_REV_TIME));
                user.modifyTime = cs.getString(cs.getColumnIndex(COLUMN_NAME_MODIFY_TIME));
                user.idNumber = cs.getString(cs.getColumnIndex(COLUMN_NAME_ID_NUMBER));
                user.createUserId = cs.getString(cs.getColumnIndex(COLUMN_NAME_CREATE_USER_ID));
                user.status = cs.getInt(cs.getColumnIndex(COLUMN_NAME_STATUS));
                user.nickname = cs.getString(cs.getColumnIndex(COLUMN_NAME_NICKNAME));
                user.cornet = cs.getString(cs.getColumnIndex(COLUMN_NAME_CORNET));
                user.revOrStop = cs.getInt(cs.getColumnIndex(COLUMN_NAME_REV_OR_STOP));
                user.imUserName = cs.getString(cs.getColumnIndex(COLUMN_NAME_IM_USER_NAME));
                user.stopTime= cs.getString(cs.getColumnIndex(COLUMN_NAME_STOP_TIME));
                user.modifyUserId = cs.getString(cs.getColumnIndex(COLUMN_NAME_MODIFY_USER_ID));
                user.deptCode = cs.getString(cs.getColumnIndex(COLUMN_NAME_DEPT_CODE));
                user.salt = cs.getString(cs.getColumnIndex(COLUMN_NAME_SALT));
                user.token = cs.getString(cs.getColumnIndex(COLUMN_NAME_TOKEN));

                cs.moveToNext();
            }
            cs.close();
        }
        return user;
    }

    public static void clearUserInfo() {
        SQLiteDatabase db = DatabaseManager.open();
        if (!db.isOpen()) {
            return;
        }
        db.delete(TABLE_NAME, null, null);
    }
}
